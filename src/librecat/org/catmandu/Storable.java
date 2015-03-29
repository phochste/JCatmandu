package librecat.org.catmandu;

/**
 *
 * @author hochsten
 * @param <T>
 */
public interface Storable<T> {
    public T add(T data);
    public T get(String identifier);
    public boolean delete(String identifier);
    public boolean delete_all();
    public void commit();
}
