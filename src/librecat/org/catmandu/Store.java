package librecat.org.catmandu;

/**
 *
 * @author hochsten
 * @param <T>
 */
public abstract class Store<T extends Identifiable> {
    private String defaultBag = "data";
    
    public abstract Bag bag();
    public abstract Bag bag(String name);
    
    public String getDefaultBag() {
        return defaultBag;
    }
    
    public void setDefaultBag(String defaultBag) {
        this.defaultBag = defaultBag;
    }
}