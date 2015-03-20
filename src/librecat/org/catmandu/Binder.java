package librecat.org.catmandu;

import java.util.function.Function;

/**
 *
 * @author hochsten
 * @param <T>
 * @param <S>
 */
public abstract class Binder<T,S> implements Bindable<T, S> {

    @Override
    public abstract S unit(T data);

    @Override
    public abstract T bind(S xdata, Function<T, T> fixer);
}
