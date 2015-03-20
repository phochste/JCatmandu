package librecat.org.catmandu;

/**
 *
 * @author hochsten
 * @param <T>
 */
public interface Addable<T> {
    public void add(T data);
    public void commit();
}
