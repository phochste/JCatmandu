package librecat.org.catmandu.bind;

import java.util.function.Function;
import librecat.org.catmandu.Binder;

/**
 *
 * @author hochsten
 * @param <T>
 */
public class Maybe<T> extends Binder<T,Maybe<T>> {
    private final T t;
    
    public Maybe() {
        this.t = null;
    }
    
    private Maybe(T t) {
        this.t = t;
    }
    
    public T value(Maybe<T> xdata) {
        return xdata.t;
    }
    
    public static <T> Maybe<T> just(T t) {
        return new Maybe<>(t);
    }
    
    public static <T> Maybe<T> nothing() {
        return new Maybe<>(null);
    }

    @Override
    public Maybe<T> unit(T data) {
        if (data == null) return nothing();
        else return just(data);
    }

    @Override
    public Maybe<T> bind(Maybe<T> xdata, Function<T, T> fixer) {
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
