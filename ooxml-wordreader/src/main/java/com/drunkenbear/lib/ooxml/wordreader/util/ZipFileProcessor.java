package com.drunkenbear.lib.ooxml.wordreader.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipFileProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(ZipFileProcessor.class);
    
    public static InputStream getFileInputStream(ZipInputStream zis, String targetFile) throws IOException {
        InputStream in = null;
        ZipEntry entry;

        while((entry = zis.getNextEntry()) != null) {;
            LOG.debug(entry.getName());
            if(entry.getName().equals(targetFile)) {
                in = zis;
                break;
            }
        }

        return in;
    }
    
    public static InputStream getFileInputStream(ZipFile zipFile, String targetFile) throws IOException {
        InputStream in = null;

        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while(entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if(entry.getName().equals(targetFile)) {
                in = zipFile.getInputStream(entry);
                break;
            }
        }

        return in;
    }
    
}
