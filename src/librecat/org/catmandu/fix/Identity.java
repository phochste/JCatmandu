package librecat.org.catmandu.fix;

import librecat.org.catmandu.Fixable;

/**
 * Fix that does nothing
 * 
 * @author hochsten
 * @param <T>
 */
public class Identity<T> implements Fixable<T> {

    @Override
    public T fix(T data) {
        return data;
    }    
}
