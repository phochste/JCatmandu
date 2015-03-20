package librecat.org.catmandu.importer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import librecat.org.catmandu.Generator;
import librecat.org.catmandu.Importer;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author hochsten
 */
public class JSONImporter extends Importer {
    private final String path;
   
    public JSONImporter(String path) {
        this.path = path;
    }
    
    @Override
    public Generator<JSONObject> generator() {
        final BufferedReader br;
        
        try {
            br = new BufferedReader(new FileReader(path));
        }
        catch (Exception e) {
            return null;
        }
           
        return new Generator<JSONObject>() {
            @Override
            public JSONObject next() {
                try {
                String line = br.readLine();
                if (line == null) {
                    return null;
                }
                
                return new JSONObject(line);
            }
            catch (IOException | JSONException e) {
                return null;
            }
            }
        };
    }   
}
