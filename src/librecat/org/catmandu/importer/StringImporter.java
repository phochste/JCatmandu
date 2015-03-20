/*
 *  Copyright (c) 2011 . Patrick Hochstenbach <Patrick.Hochstenbach@gmail.com>
 */
package librecat.org.catmandu.importer;

import java.util.Date;
import librecat.org.catmandu.Generator;
import librecat.org.catmandu.Importer;

/**
 *
 * @author hochsten
 */
public class StringImporter extends Importer { 
    
    @Override
    public Generator<String> generator() {
        return new Generator<String>() {
            @Override
            public String next() {
            Date date = new Date();
            return "Hi, the current date is: " + date.toString();            }
        };
    }
}
