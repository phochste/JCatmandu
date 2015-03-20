package librecat.org.catmandu;

/**
 *
 * @author hochsten
 * @param <T>
 */
public interface Fixable<T> {
    public T fix(T data);
}
