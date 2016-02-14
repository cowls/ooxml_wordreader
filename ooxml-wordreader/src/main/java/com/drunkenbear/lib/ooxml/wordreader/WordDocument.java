package com.drunkenbear.lib.ooxml.wordreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.drunkenbear.lib.ooxml.wordreader.exceptions.InvalidFileException;
import com.drunkenbear.lib.ooxml.wordreader.exceptions.ParseException;
import com.drunkenbear.lib.ooxml.wordreader.handlers.WordContentHandler;
import com.drunkenbear.lib.ooxml.wordreader.util.OOXMLConstants;
import com.drunkenbear.lib.ooxml.wordreader.util.ZipFileProcessor;


public class WordDocument {

    private static final Logger LOG = LoggerFactory.getLogger(WordDocument.class);

    private InputStream docXmlInputStream;

    private String content;
    
    public WordDocument() {
        LOG.debug("Creating Word Document Reader");
    }

    public void parse(ZipInputStream zis) throws IOException, InvalidFileException, ParseException {

        LOG.debug("Parsing word document from zip input stream");
        InputStream wordDocEntry = ZipFileProcessor.getFileInputStream(zis, OOXMLConstants.WORD_DOCUMENT_XML_FILE);

        if(wordDocEntry == null) {
            throw new InvalidFileException("File is not ooxml word doc (.docxml)");
        }

        this.docXmlInputStream = wordDocEntry;
        parseContent();

    }

    public void parse(String absoluteFilePath) throws IOException, InvalidFileException, ParseException {
        LOG.debug("Parsing word document from file name");
        File file = new File(absoluteFilePath);
        if(!file.exists()) {
            throw new FileNotFoundException("File " + absoluteFilePath + " does not exist.");
        }
        ZipFile zipFile = new ZipFile(file);
        InputStream wordDocEntry = ZipFileProcessor.getFileInputStream(zipFile, OOXMLConstants.WORD_DOCUMENT_XML_FILE);

        if(wordDocEntry == null) {
            throw new InvalidFileException("File is not ooxml word doc (.docxml)");
        }

        this.docXmlInputStream = wordDocEntry;
        parseContent();
        
        zipFile.close();
    }

    private void parseContent() throws IOException, ParseException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
    
            WordContentHandler handler = new WordContentHandler();
            saxParser.parse(this.docXmlInputStream, handler);

            this.content = handler.getParsedContent();
        } catch (ParserConfigurationException pce) {
            throw new ParseException("Parser Config Exception whilst parsing content",pce);
        } catch (SAXException se) {
            throw new ParseException("SAX Error whilst parsing content",se);
        }
    }
    
    public String getContent() {
        return this.content;
    }
}
