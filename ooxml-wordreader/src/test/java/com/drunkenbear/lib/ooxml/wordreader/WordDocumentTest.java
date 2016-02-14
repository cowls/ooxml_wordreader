package com.drunkenbear.lib.ooxml.wordreader;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipInputStream;

import org.junit.Before;
import org.junit.Test;

public class WordDocumentTest {

    WordDocument testee;

    @Before
    public void setup() {
        testee = new WordDocument();
    }

    @Test
    public void test_parseDocument() throws Exception {

        InputStream in = this.getClass().getResourceAsStream("/docs/doc_multiple_paragraphs.docx");
        ZipInputStream zis = new ZipInputStream(in);
        testee.parse(zis);

    }
    
    @Test 
    public void test_parseDocumentSimplePara() throws Exception {
        InputStream in = this.getClass().getResourceAsStream("/docs/doc_simple_paragraph.docx");
        ZipInputStream zis = new ZipInputStream(in);
        testee.parse(zis);

        assertEquals("This is a simple paragraph\n", testee.getContent());
    }
    
    @Test 
    public void test_parseDocumentSimplePara_fromFile() throws Exception {
        
        URL url = this.getClass().getResource("/docs/doc_simple_paragraph.docx");
        String fileName = url.getFile();
       
        File file = new File(fileName);

        testee.parse(file.getAbsolutePath());

        assertEquals("This is a simple paragraph\n", testee.getContent());
    }
    
    @Test 
    public void test_parseDocumentSimpleParaWithHeader() throws Exception {
        InputStream in = this.getClass().getResourceAsStream("/docs/doc_simple_paragraph_with_header.docx");
        ZipInputStream zis = new ZipInputStream(in);
        testee.parse(zis);

        assertEquals("HEADER\n\nThis is a simple paragraph\n", testee.getContent());
    }
    
    @Test 
    public void test_parseDocumentDocWithHeaders() throws Exception {
        InputStream in = this.getClass().getResourceAsStream("/docs/doc_headers.docx");
        ZipInputStream zis = new ZipInputStream(in);
        testee.parse(zis);

        assertEquals("Heading 1\n\nHeading 2\n\nHeading 3\n\nHeading 4\n\nNormal Text\n\nNormal Test no style\n\nHello\n", testee.getContent());
    }
    
    @Test 
    public void test_parseDocumentDocWithTable() throws Exception {
        InputStream in = this.getClass().getResourceAsStream("/docs/doc_tables.docx");
        ZipInputStream zis = new ZipInputStream(in);
        testee.parse(zis);

        assertEquals("Tables\n\nCell 1\n\nCell 2\n\nCell 3\n\nCell 4\n\nCell 5\n\n"+
                     "Cell 6\n\nCell 7\n\nCell 8\n\nCell 9\n\nCell 10\n\nCell 11\n\n"+
                     "Cell 12\n\nCell 13\n\nCell 14\n\nCell 15\n\nCell 16\n\nCell 17\n\n"+
                     "Cell 18\n", testee.getContent());
    }

}
