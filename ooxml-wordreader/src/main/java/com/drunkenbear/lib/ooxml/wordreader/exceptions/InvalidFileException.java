package com.drunkenbear.lib.ooxml.wordreader.exceptions;

/**
 * Exception thrown when trying to parse a file that is 
 * not of the expected type
 * @author andrewcowlin
 *
 */
public class InvalidFileException  extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidFileException(String message) {
        super(message);
    }

    public InvalidFileException(String message, Throwable thrown) {
        super(message,thrown);
    }
}
