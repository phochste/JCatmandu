package librecat.org.catmandu.bind;

import java.util.function.Function;
import librecat.org.catmandu.Binder;
import librecat.org.catmandu.Bindable;
import librecat.org.catmandu.Generator;
import librecat.org.catmandu.Streamable;
import librecat.org.catmandu.Streamer;
import librecat.org.catmandu.StreamerFactory;

/**
 * Converts a Streamer of one type to another type
 * 
 * @author hochsten
 * @param <T>
 * @param <S>
 */
public class StreamableBinder<T,S> extends Binder<Streamable<T>,Streamable<S>> {
    private final Bindable<T,S> binder;
    
    public StreamableBinder() {
        this.binder = (Bindable<T,S>) new IdentityBinder<>();    
    }
    
    public StreamableBinder(Bindable<T,S> binder) {
        this.binder = binder;
    }
    
    @Override
    public Streamer<S> unit(final Streamable<T> stream) {
        return new StreamerFactory<>(new Generator<S>() {
            private final Generator<T> gen = stream.generator();
             
            @Override
            public S next() {
                T data = gen.next();
                
                if (data == null) {
                    return null;
                }
                
                return binder.unit(data);
            }
        });
    }

    @Override
    public Streamable<T> bind(final Streamable<S> xdata, final Function<Streamable<T>, Streamable<T>> fixer) {
        return null;
    }
}