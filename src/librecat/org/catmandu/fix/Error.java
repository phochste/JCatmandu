package librecat.org.catmandu.fix;

import librecat.org.catmandu.Fixable;

/**
 * Fix that throws an error
 * @author hochsten
 * @param <T>
 */
public class Error<T> implements Fixable<T> {

    @Override
    public T fix(T data) {
        throw new UnsupportedOperationException("Error! :-o"); 
    }
}
