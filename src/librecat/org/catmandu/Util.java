package librecat.org.catmandu;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author hochsten
 */
public final class Util {
    public static String upcase(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1); 
    }
    
    public static final <T> Importer<T> createImporter(String name, Object ... args) {
        String classname = "librecat.org.catmandu.importer." + upcase(name) + "Importer";
 
        Importer importer;
        
        try {
            Class  cl = Class.forName(classname);
            Constructor[] cons = cl.getConstructors();
            Constructor cont = null;
            
            boolean found = false;
            for (Constructor con : cons) {
                if (con.getParameterCount() == args.length) {
                    cont = con;
                    found = true;
                }
            }
            
            if (! found) {
                throw new RuntimeException("Can't find constructor for " + classname);
            }
            
            importer = (Importer<T>) cont.newInstance(args);
        }
        catch (ClassNotFoundException | 
               IllegalAccessException | 
               InstantiationException | 
               InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        
        return importer;
    }
        
    public static final <T> Fixable<T> createFixer(String name, Object ... args) {
        String classname = "librecat.org.catmandu.fix." + upcase(name) + "Fixer";
 
        Fixable fixer;
        
        try {
            Class  cl = Class.forName(classname);
            Constructor[] cons = cl.getConstructors();
            Constructor cont = null;
            
            boolean found = false;
            for (Constructor con : cons) {
                if (con.getParameterCount() == args.length) {
                    cont = con;
                    found = true;
                }
            }
            
            if (! found) {
                throw new RuntimeException("Can't find constructor for " + classname);
            }
            
            fixer = (Fixable<T>) cont.newInstance(args);
        }
        catch (ClassNotFoundException | 
               IllegalAccessException | 
               InstantiationException | 
               InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        
        return fixer;
    }
    
    public static final <T> Exporter<T> createExporter(String name, Object ... args) {
        String classname = "librecat.org.catmandu.exporter." + upcase(name) + "Exporter";
 
        Exporter exporter;
        
        try {
            Class  cl = Class.forName(classname);
            Constructor[] cons = cl.getConstructors();
            Constructor cont = null;
            
            boolean found = false;
            for (Constructor con : cons) {
                if (con.getParameterCount() == args.length) {
                    cont = con;
                    found = true;
                }
            }
            
            if (! found) {
                throw new RuntimeException("Can't find constructor for " + classname);
            }
            
            exporter = (Exporter<T>) cont.newInstance(args);
        }
        catch (ClassNotFoundException | 
               IllegalAccessException | 
               InstantiationException | 
               InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        
        return exporter;
    }
    
    public static final <T,S> Binder<T,S> createBinder(String name, Object ... args) {
        String classname = "librecat.org.catmandu.bind." + upcase(name) + "Binder";
 
        Binder binder;
        
        try {
            Class  cl = Class.forName(classname);
            Constructor[] cons = cl.getConstructors();
            Constructor cont = null;
            
            boolean found = false;
            for (Constructor con : cons) {
                if (con.getParameterCount() == args.length) {
                    cont = con;
                    found = true;
                }
            }
            
            if (! found) {
                throw new RuntimeException("Can't find constructor for " + classname);
            }
            
            binder = (Binder<T,S>) cont.newInstance(args);
        }
        catch (ClassNotFoundException | 
               IllegalAccessException | 
               InstantiationException | 
               InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        
        return binder;
    }
}
