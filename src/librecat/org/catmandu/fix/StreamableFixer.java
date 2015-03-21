package librecat.org.catmandu.fix;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import librecat.org.catmandu.Binder;
import librecat.org.catmandu.Fixable;
import librecat.org.catmandu.Streamer;
import librecat.org.catmandu.Util;

/**
 * Applies all fixes to a Streamer
 * 
 * @author hochsten
 * @param <T>
 */
public class StreamableFixer<T> implements Fixable<Streamer<T>>  {
    private final List<Fixable<T>> fixes;
    
    public StreamableFixer() {
        this.fixes  = new ArrayList<>();
    }
    
    public StreamableFixer(String name, Object ... args) {
        this.fixes = new ArrayList<>();
        fixes.add((Fixable<T>) Util.createFixer(name, args));
    }
    
    public StreamableFixer(Fixable<T> fix) {
        this.fixes = new ArrayList<>();
        fixes.add(fix);
    }
    
    public StreamableFixer(List<Fixable<T>> fixes) {
        this.fixes = fixes;
    }
  
    public StreamableFixer add(String name, Object ... args) {
        fixes.add((Fixable<T>) Util.createFixer(name, args));
        return this;
    }
    
    public StreamableFixer add(Fixable<T> fix) {
        fixes.add(fix);
        return this;
    }

    public List<Fixable<T>> list() {
        return fixes;
    } 
    
    @Override
    public Streamer<T> fix(Streamer<T> stream) {
        return stream.map(new Function<T,T>() {
            @Override
            public T apply(T data) {
                for (Fixable<T> fixer : fixes) {
                    data = fixer.fix(data);
                }
                return data;
            }
            
        });
    }
    
    public <S>Streamer<T> fix_bind(Binder<T,S> binder, Streamer<T> stream) {
        return stream.map(new Function<T,T>() {
            @Override
            public T apply(T data) {
                for (Fixable<T> fixer : fixes) {
                    S xdata = binder.unit(data);
                    data = binder.bind(xdata, (a) -> fixer.fix(a));
                }
                return data;
            }
            
        });
    }
}
