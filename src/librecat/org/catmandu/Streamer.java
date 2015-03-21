package librecat.org.catmandu;

import java.util.function.Consumer;
import java.util.function.Function;
import librecat.org.catmandu.exporter.NullExporter;
import librecat.org.catmandu.fix.StreamableFixer;

/**
 * Class to stream over T. Only for demonstration purposes. 
 * In a real project you want to use the java.util.stream class
 * 
 * @author hochsten
 * @param <T>
 */
public abstract class Streamer<T> implements Streamable<T> {
       
    public int export() {
        return (new NullExporter()).add_many(this);
    }
    
    public int export(final Adder<T> exporter) {
        return exporter.add_many(this);
    }
   
    public Streamer<T> slice(final int start, final int total) {
        return new StreamerFactory<>(new Generator<T>() {
            private final Generator<T> gen = generator();
            private int __start = start;
            private int __total = total;
            
            @Override
            public T next() {
                T data;
                
                if (__total == 0) {
                    return null;
                }
                
                while ((data = gen.next()) != null) {
                    if (__start > 0) {
                        __start -= 1;
                        continue;
                    }
                    if (__total > 0) {
                        __total -= 1;
                    }
                    return data;
                }
                
                return null;
            }
        });
    }
    
    public Streamer<T> tap(final Consumer<T> sub) {
       return new StreamerFactory<>(new Generator<T>() {
           private final Generator<T> gen = generator();
           
           @Override
           public T next() {
               T data;
               
               if ((data = gen.next()) != null) {
                   sub.accept(data);
                   return data;
               }
               
               return null;
           }
       });
    } 
    
    public Streamer<T> map(final Function<T,T> sub) {
        return new StreamerFactory<>(new Generator<T>() {
            private final Generator<T> gen = generator();
      
            @Override
            public T next() {
                T data = gen.next();
                
                if (data == null) {
                    return null;
                }
                
                return sub.apply(data);
            }
        });
    }
    
    public Streamer<T> take(final int num) {
        return slice(0,num);
    }
    
    public Streamer<T> benchmark() {
        return tap(new Consumer<T>() {
            private int  n = 0;
            private final long start_time = System.nanoTime();
            @Override
            public void accept(T t) {
                if (++n % 100 == 0) {
                    double seconds = (System.nanoTime() - start_time)/1e9;
                    System.err.println(
                            String.format("added %9d (%d/sec)", n, Math.round(n/seconds))
                    );
                }
            }
        });
    }
    
    public Streamer<T> fix(String name, Object ... args) {
        return (new StreamableFixer<T>(name,args)).fix(this);
    }
    
    public Streamer<T> fix(Fixable<T> fixer) {
        return (new StreamableFixer<>(fixer)).fix(this);
    }
    
    public Streamer<T> fix(StreamableFixer<T> fixer) {
        return fixer.fix(this);
    }
        
    public <S> Streamer<T> fix_do(Binder<T,S> binder, StreamableFixer<T> fixer) {
        return fixer.fix_do(binder, this);
    }
    
    public <S> Streamer<T> fix_doset(Binder<T,S> binder, StreamableFixer<T> fixer) {
        return fixer.fix_doset(binder, this);
    }
}
