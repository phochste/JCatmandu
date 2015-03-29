/*
 *  Copyright (c) 2011 . Patrick Hochstenbach <Patrick.Hochstenbach@gmail.com>
 */
package librecat.org.catmandu;

/**
 *
 * @author hochsten
 * @param <T>
 */
public interface Identifiable<T> {
    public T      clone();
    public String getIdentifier();
    public void   setIdentifier(String T);
}
