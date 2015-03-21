package librecat.org.catmandu.bind;

import java.util.function.Function;
import librecat.org.catmandu.Binder;

/**
 *
 * @author hochsten
 * @param <T>
 */
public class Maybe<T> extends Binder<T,Maybe<?>> {
    private final T t;
    
    public Maybe() {
        this.t = null;
    }
    
    private Maybe(T t) {
        this.t = t;
    }
    
    public T value() {
        return t;
    }
    
    public static <T> Maybe<T> just(T t) {
        return new Maybe<>(t);
    }
    
    public static <T> Maybe<T> nothing() {
        return new Maybe<>(null);
    }

    @Override
    public Maybe<?> unit(T data) {
        if (data == null) return nothing();
        else return just(data);
    }

    @Override
    public T bind(Maybe<?> xdata, Function<T, T> fixer) {
        if (xdata == null || xdata.value() == nothing().value()) {
            System.err.println("Ignored:" + fixer);
            return null;
        } 
  
        try {
            return fixer.apply((T) xdata.value());
        } catch (Exception e) {
            System.err.println("Caught: " + e + " :-)");
            return (T) nothing().value();
        }
    }    
}
