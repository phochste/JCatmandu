package librecat.org.catmandu.fix;

import librecat.org.catmandu.Fixable;

/**
 *
 * @author hochsten
 */
public class StringAppend implements Fixable<String> {
    private final String str;
    
    public StringAppend(String str) {
        this.str = str;
    }
    
    @Override
    public String fix(String data) {
        return data.concat(str);
    }
}
