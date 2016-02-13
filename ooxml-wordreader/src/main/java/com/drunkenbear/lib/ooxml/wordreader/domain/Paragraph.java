package com.drunkenbear.lib.ooxml.wordreader.domain;

public class Paragraph {

    private boolean containsContent;
    private String separator;

    public static Paragraph newInstance() {
        return new Paragraph();
    }
    private Paragraph() {
        containsContent = false;
        setSeparator("\n");
    }
    
    public boolean containsContent() {
        return containsContent;
    }

    public void setContainsContent(boolean containsContent) {
        this.containsContent = containsContent;
    }
    public String getSeparator() {
        return separator;
    }
    public void setSeparator(String separator) {
        this.separator = separator;
    }

}
