package org.ixcode.ibex;

public interface BuildLog {

    public void printTitle(String format, Object... parameters);

    public void println(String format, Object... parameters);

    void printlndirect(String line);


}