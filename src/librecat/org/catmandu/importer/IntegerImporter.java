package librecat.org.catmandu.importer;

import librecat.org.catmandu.Generator;
import librecat.org.catmandu.Importer;

/**
 * Importer that generates numbers
 * 
 * @author hochsten
 */
public class IntegerImporter extends Importer {
    private int size = -1;
    
    public IntegerImporter() {}
    
    public IntegerImporter(int size) {
        this.size = size;   
    }
    
    @Override
    public Generator<Integer> generator() {
        return new Generator() {
            private int start = 0;
            
            @Override
            public Integer next() {
                 if (size == -1 ||  (size > 0 && start < size)) {
                     start += 1;
                     return start;
                 }
                 else {
                     return null;
                 }
            }
        };
    }
}
