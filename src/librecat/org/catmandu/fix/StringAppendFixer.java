package librecat.org.catmandu.fix;

import librecat.org.catmandu.Fixable;

/**
 *
 * @author hochsten
 */
public class StringAppendFixer implements Fixable<String> {
    private final String str;
    
    public StringAppendFixer(String str) {
        this.str = str;
    }
    
    @Override
    public String fix(String data) {
        return data.concat(str);
    }
}
