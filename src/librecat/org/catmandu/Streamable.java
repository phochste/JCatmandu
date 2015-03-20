package librecat.org.catmandu;

/**
 *
 * @author hochsten
 * @param <T>
 */
public interface Streamable<T> {
    public Generator<T> generator();
}
