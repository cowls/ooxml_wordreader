package com.drunkenbear.lib.ooxml.wordreader;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.junit.Before;
import org.junit.Test;

import com.drunkenbear.lib.ooxml.wordreader.WordDocument;
import com.drunkenbear.lib.ooxml.wordreader.exceptions.InvalidFileException;
import com.drunkenbear.lib.ooxml.wordreader.exceptions.ParseException;

public class WordDocumentTest {

    WordDocument testee;

    @Before
    public void setup() {
        testee = new WordDocument();
    }

    @Test
    public void test_parseDocument() throws Exception {

        InputStream in = this.getClass().getResourceAsStream("/test_doc.docx");
        ZipInputStream zis = new ZipInputStream(in);
        testee.parse(zis);

    }
    
    @Test 
    public void test_parseDocumentSimplePara() throws Exception {
        InputStream in = this.getClass().getResourceAsStream("/docs/doc_simple_paragraph.docx");
        ZipInputStream zis = new ZipInputStream(in);
        testee.parse(zis);

        assertEquals("\nThis is a simple paragraph\n", testee.getContent());
    }
    
    @Test 
    public void test_parseDocumentSimpleParaWithHeader() throws Exception {
        InputStream in = this.getClass().getResourceAsStream("/docs/doc_simple_paragraph_with_header.docx");
        ZipInputStream zis = new ZipInputStream(in);
        testee.parse(zis);

        assertEquals("\nHEADER\n\nThis is a simple paragraph\n", testee.getContent());
    }

}
