package com.drunkenbear.lib.ooxml.wordreader.exceptions;

/**
 * Exception thrown when there is an issue during the parsing of 
 * file content
 * @author andrewcowlin
 *
 */
public class ParseException extends Exception {

    private static final long serialVersionUID = 1L;

    public ParseException(String message) {
        super(message);
    }
    
    public ParseException(String message, Throwable thrown) {
        super(message, thrown);
    }

}
