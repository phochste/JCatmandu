package librecat.org.catmandu;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import librecat.org.catmandu.exporter.StringExporter;
import librecat.org.catmandu.fix.StringAppendFixer;
import librecat.org.catmandu.importer.IntegerImporter;
import librecat.org.catmandu.importer.JSONImporter;
import librecat.org.catmandu.importer.StringImporter;
import librecat.org.catmandu.parser.ParseException;
import librecat.org.catmandu.parser.Parser;

/**
 *
 * @author hochsten
 */
public class Demo {  
    public static void test_integer_stream() {
        Importer<Integer> int_importer = new IntegerImporter(10);
      
        int_importer.take(5).map((a) -> a * 12).export(new StringExporter());       
    }
    
    public static void test_string_stream() {
        /* String stream */
        Importer<String> string_importer = new StringImporter();
        
        string_importer.benchmark().take(5).export(new StringExporter());
    }
    
    public static void test_fixes() {
        /* String stream */
        Importer<String> string_importer = new StringImporter();
               
        /* Apply fixes to a stream */
        string_importer
                .take(100)
                .fix(new StringAppendFixer("-TEST"))
                .fix(new StringAppendFixer("-OK"))
                .fix("StringAppend","-HAHA")
                .export(new StringExporter());   
        
      
        System.out.println("---------");
        
        string_importer
               .take(12)
               .doset(new librecat.org.catmandu.bind.MaybeBinder<String>(),
                       Arrays.asList(new librecat.org.catmandu.fix.ErrorFixer<>(),
                        new StringAppendFixer("-ALPHA") ,
                        new StringAppendFixer("-BRAVO") ,
                        new StringAppendFixer("-CHARLIE") 
                       )
               )
               .export(new StringExporter());        
    }
    
    public static void test_json_importer() {
        /* Benchmark the JSON importer */
        Importer json_importer = new JSONImporter("data/merge.json");
        json_importer.take(1000).benchmark().export();
    }
    
    public static void test_binder() {
         /* Binder */
        Binder<String,String>
                b_i = Util.createBinder("Identity");
        
        String test    = "PATRICK";
        
        /* Left Identity */
        String test_result  = b_i.bind(b_i.unit("paTrIcK"), a -> a.toUpperCase());
        
        if (test.equals(test_result)) {
            System.out.println("ok " + test + "=" + test_result);
        }
        else {
            System.err.println("nok" + test + "<>" + test_result);
        }
        
        /* Right Identity */
        String test_result2 = b_i.bind("PATRICK", a -> b_i.unit(a));
        
        if (test.equals(test_result2)) {
            System.out.println("ok " + test + "=" + test_result2);
        }
        else {
            System.err.println("nok" + test + "<>" + test_result2);
        }
        
        /* Associativity */
        String test_result3 = b_i.bind(
                b_i.unit(b_i.bind(b_i.unit("paTrIcK"), a -> a.toUpperCase())),
                a -> a.replaceAll("PA", "MA"));
        
        String test_result4 = b_i.bind("paTrIcK",
                a -> b_i.bind(a.toUpperCase(), b -> b.replaceAll("PA", "MA")));
        
        if (test_result3.equals(test_result4)) {
            System.out.println("ok " + test_result3 + "=" + test_result4);
        }
        else {
            System.err.println("nok" + test_result3 + "<>" + test_result4);
        }       
    }
    
    public static void test_parser(String test) {
        try {
            List fixes = Parser.parse(new StringReader(test));
            
            Importer<String> string_importer = new StringImporter();
            
            string_importer
                .take(10)
                .benchmark()
                .dorun(
                        Util.createBinder("Identity")
                        , fixes
                )
                .export(new StringExporter()); 
        } catch (ParseException | Error e) {
            System.err.println(test + "==> " + e);
        }
    }
    
    public static void main(String[] args) throws Exception {
        //test_integer_stream();
        //test_string_stream();
        //test_fixes();
        //test_json_importer();
        //test_binder();
        test_parser(
                "stringAppend( \"-OK\"  )\n" + 
                "stringAppend(   '--FOX')\n" +
                "identity()\n" + 
                "stringAppend(17)\n" +
                "stringAppend('OKSTATS')\n" + 
                "stringReplace('date','\"WHAT?!\"')\n"
        );
    }
}
