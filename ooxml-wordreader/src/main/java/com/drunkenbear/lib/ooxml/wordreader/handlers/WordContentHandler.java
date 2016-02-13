package com.drunkenbear.lib.ooxml.wordreader.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.drunkenbear.lib.ooxml.wordreader.domain.Paragraph;
import com.drunkenbear.lib.ooxml.wordreader.util.OOXMLElement;

/**
 * SAX Parser for parsing an OOXML word document
 * @author andrewcowlin
 *
 */
public class WordContentHandler extends DefaultHandler {

    private static final Logger LOG = LoggerFactory.getLogger(WordContentHandler.class);

    private OOXMLElement currentElement;
    private Paragraph currentParagraph;
    private StringBuilder content;
    
    public WordContentHandler() {
        this.content = new StringBuilder();
    }

    public void startElement(String uri, String localName,String qName, 
            Attributes attributes) throws SAXException {
        
        currentElement = OOXMLElement.get(qName);
        if(currentElement != null) {
            LOG.debug(qName);

            switch(currentElement) {
                case PARAGRAPH: 
                    this.currentParagraph = Paragraph.newInstance();
                    break;
                default:
            }
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {

        if(currentElement == null) {
            //Element not handled
            return;
        }
        String textContent = new String(ch, start, length);
        switch(currentElement) {
            case TEXT: 
                if(this.currentParagraph != null && !this.currentParagraph.containsContent()) {
                    //First line of the paragraph
                    this.content.append(this.currentParagraph.getSeparator());
                    this.currentParagraph.setContainsContent(true);
                }
                
                this.content.append(textContent);
                break;
            default: 
                LOG.warn("Couldn't handle element " + currentElement);
        }
    }

    public void endElement(String uri, String localName,
            String qName) throws SAXException {
        currentElement = OOXMLElement.get(qName);
        if(currentElement != null) {
            LOG.debug("END " +qName);

            switch(currentElement) {
                case PARAGRAPH: 
                    if(currentParagraph.containsContent()) {
                        this.content.append(currentParagraph.getSeparator());
                    }
                    this.currentParagraph = null;
                    break;
                default: 
            }
        }
        currentElement = null;
    }
    
    public String getParsedContent() {
        return content.toString();
    }
}

