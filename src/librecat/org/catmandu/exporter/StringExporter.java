package librecat.org.catmandu.exporter;

import librecat.org.catmandu.Exporter;

/**
 *
 * @author hochsten
 */
public class StringExporter extends Exporter {

    @Override
    public void add(Object data) {
        if (data != null)
             System.out.println(data);
    }
}
