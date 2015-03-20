package librecat.org.catmandu;

/**
 * Generator creates objects of type T
 * 
 * @author hochsten
 * @param <T>
 */
public interface Generator<T> {
    public T next();
}
