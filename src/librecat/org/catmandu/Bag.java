package librecat.org.catmandu;

import java.util.Collection;
import java.util.UUID;

/**
 *
 * @author hochsten
 * @param <T>
 */
public abstract class Bag<T extends Identifiable> extends Streamer<T> implements Storable<T> {
    private String name;
    
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
    
    public String generate_id () {
        return UUID.randomUUID().toString();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
