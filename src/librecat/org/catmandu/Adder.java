package librecat.org.catmandu;

import java.util.Collection;

/**
 *
 * @author hochsten
 * @param <T>
 */
public abstract class Adder<T> implements Addable<T> {
    
    public int add_many(T[] t) {
        return add_many(new StreamerFactory<>(t));
    }
    
    public int add_many(Collection<T> coll) {
        return add_many(new StreamerFactory<>(coll));
    }
      
    public int add_many(Streamer<T> stream) {
        Generator<T> generator = stream.generator();
        T data;
        int n = 0;
        while ((data = generator.next()) != null) {
            add(data);
            n++;
        }
        return n;
    }
    
    @Override
    public void commit() {}
}
