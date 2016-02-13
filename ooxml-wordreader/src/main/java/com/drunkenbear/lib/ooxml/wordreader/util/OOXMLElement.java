package com.drunkenbear.lib.ooxml.wordreader.util;

import java.util.HashMap;
import java.util.Map;

public enum OOXMLElement {

    PARAGRAPH("w:p"),
    TEXT("w:t");

    private static final Map<String, OOXMLElement> lookup = new HashMap<String, OOXMLElement>();

    static {
        for(OOXMLElement o : OOXMLElement.values()) {
            lookup.put(o.getValue(), o);
        }
    }

    public static OOXMLElement get(String val) {
        return lookup.get(val);
    }

    private String val;
    private OOXMLElement(String val) {
        this.val = val;
    }

    public String getValue() {
        return val;
    }
}
