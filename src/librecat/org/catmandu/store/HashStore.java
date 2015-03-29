package librecat.org.catmandu.store;

import java.util.HashMap;
import java.util.Map;
import librecat.org.catmandu.Bag;
import librecat.org.catmandu.Generator;
import librecat.org.catmandu.Identifiable;
import librecat.org.catmandu.Store;

/**
 *
 * @author hochsten
 * @param <T>
 */
public class HashStore<T extends Identifiable> extends Store<T> {
    private final Map<String,Bag> cache;
    
    public HashStore() {
        this.cache = new HashMap<>();
    }
    
    @Override
    public Bag bag() {
        return bag(getDefaultBag());
    }

    @Override
    public Bag bag(String name) {        
        if (cache.containsKey(name)) {
            return cache.get(name);
        }
        else {
            Bag b = new HashStoreBag<>();
            b.setName(name);
            cache.put(name, b);

            return cache.get(name);
        }
    }
 
    public class HashStoreBag<T extends Identifiable> extends Bag<T> {
        private Map<String, T> cache;
        
        public HashStoreBag() {
            this.cache = new HashMap<>();
        }
        
        @Override
        public Generator<T> generator() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public T get(String identifier) {
            return cache.get(identifier);
        }

        @Override
        public boolean delete(String identifier) {
            if (cache.containsKey(identifier)) {
                cache.remove(identifier);
                return true;
            }
            else {
                return false;
            }
        }

        @Override
        public boolean delete_all() {
            cache = new HashMap<>();
            return true;
        }

        @Override
        public T add(T data) {
            T new_data = (T) data.clone();
            String new_id = generate_id();
            new_data.setIdentifier(new_id);
            return cache.put(new_id, new_data);
        }

        @Override
        public void commit() {}
    }
}
