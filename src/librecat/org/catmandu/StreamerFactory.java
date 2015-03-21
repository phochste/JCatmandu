package librecat.org.catmandu;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author hochsten
 * @param <T>
 */
public final class StreamerFactory<T> extends Streamer<T> {
    private final Generator<T> generator;
    
    public StreamerFactory(final Generator<T> generator) {
        this.generator = generator;
    }
    
    public StreamerFactory(final T[] args) {
        this.generator = new Generator<T>() {
            private int index = 0;
            
            @Override
            public T next() {
                if (index < args.length) {
                    return args[index++];
                }
                else {
                    return null;
                }
            }
        };
    }
    
    public StreamerFactory(final Collection<T> col) {
        this.generator = new Generator<T>() {
            private Iterator<T> it = col.iterator();
            
            @Override
            public T next() {
                return it.next();
            }
        };
    }
    
    @Override
    public Generator<T> generator() {
        return this.generator;
    }
}
