package librecat.org.catmandu.exporter;

import java.io.FileWriter;
import java.io.IOException;
import librecat.org.catmandu.Exporter;
import org.json.JSONObject;

/**
 *
 * @author hochsten
 */
public class JSONExporter extends Exporter<JSONObject> {
    private final FileWriter writer;
    
    public JSONExporter(String path) {
        try {
            this.writer = new FileWriter(path);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void add(JSONObject data) {
        try {
            writer.write(data.toString());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        } 
    }    
}
