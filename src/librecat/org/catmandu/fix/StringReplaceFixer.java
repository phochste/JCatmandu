/*
 *  Copyright (c) 2011 . Patrick Hochstenbach <Patrick.Hochstenbach@gmail.com>
 */
package librecat.org.catmandu.fix;

import librecat.org.catmandu.Fixable;

/**
 *
 * @author hochsten
 */
public class StringReplaceFixer implements Fixable<String> {
    private final String search;
    private final String replace;
    
    public StringReplaceFixer(String search, String replace) {
        this.search = search;
        this.replace = replace;
    }
    
    @Override
    public String fix(String data) {
        return data.replaceAll(search, replace);
    }
}
