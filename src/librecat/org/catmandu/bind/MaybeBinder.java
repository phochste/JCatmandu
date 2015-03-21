package librecat.org.catmandu.bind;

import java.util.function.Function;
import librecat.org.catmandu.Binder;

/**
 *
 * @author hochsten
 * @param <T>
 */
public class MaybeBinder<T> extends Binder<T,MaybeBinder<T>> {
    private final T t;
    
    public MaybeBinder() {
        this.t = null;
    }
    
    private MaybeBinder(T t) {
        this.t = t;
    }
    
    public T value(MaybeBinder<T> xdata) {
        return xdata.t;
    }
    
    public static <T> MaybeBinder<T> just(T t) {
        return new MaybeBinder<>(t);
    }
    
    public static <T> MaybeBinder<T> nothing() {
        return new MaybeBinder<>(null);
    }

    @Override
    public MaybeBinder<T> unit(T data) {
        if (data == null) return nothing();
        else return just(data);
    }

    @Override
    public MaybeBinder<T> bind(MaybeBinder<T> xdata, Function<T, T> fixer) {
        if (xdata == null || value(xdata) == null) {
            return nothing();
        } 
  
        try {
            return just(fixer.apply(value(xdata)));
        } catch (Exception e) {
            return nothing();
        }
    }
    
    public String toString() {
        if (t == null) return "nothing";
        else return "just(" + t.toString() + ")";
    }
}
