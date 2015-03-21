/*
 *  Copyright (c) 2011 . Patrick Hochstenbach <Patrick.Hochstenbach@gmail.com>
 */
package librecat.org.catmandu.cmd;

import librecat.org.catmandu.Exporter;
import librecat.org.catmandu.Importer;
import librecat.org.catmandu.Util;

/**
 *
 * @author hochsten
 */
public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("usage: jcatmandu importer exporter");
            System.exit(1);
        }
        
        System.err.println(args[0] + "->" + args[1]);
        
        Importer importer = Util.createImporter(args[0], "/dev/stdin");
        Exporter exporter = Util.createExporter(args[1], "/dev/stdout");
        
        importer.benchmark().export(exporter);
    }
}
