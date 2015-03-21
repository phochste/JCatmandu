package librecat.org.catmandu.bind;

import java.util.function.Function;
import librecat.org.catmandu.Binder;

/**
 *
 * @author hochsten
 * @param <T>
 */
public class Identity<T> extends Binder<T,T> {    
    @Override
    public T unit(T data) {
        return data;
    }

    @Override
    public T bind(T xdata, Function<T, T> fixer) {
        return fixer.apply((T) xdata);
    }

    @Override
    public T value(T xdata) {
        return xdata;
    }
}
