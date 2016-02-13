package com.drunkenbear.lib.ooxml.wordreader.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drunkenbear.lib.ooxml.wordreader.WordDocument;
import com.drunkenbear.lib.ooxml.wordreader.exceptions.InvalidFileException;

public class ZipFileProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(ZipFileProcessor.class);
    
    public static InputStream getFileInputStream(ZipInputStream zis, String fileName) throws IOException {
        InputStream in = null;
        ZipEntry entry;

        while((entry = zis.getNextEntry()) != null) {;
            LOG.debug(entry.getName());
            if(entry.getName().equals(OOXMLConstants.WORD_DOCUMENT_XML_FILE)) {
                in = zis;
                break;
            }
        }

        return in;
    }
    
}
