package librecat.org.catmandu;

import java.util.function.Function;

/**
 *
 * @author hochsten
 * @param <T>
 * @param <S>
 */
public interface Bindable<T,S> {
    public S unit(T data);
    public S bind(S xdata, Function<T,T> fixer);
    public T value(S xdata);
}
