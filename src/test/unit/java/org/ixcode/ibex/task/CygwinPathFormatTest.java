package org.ixcode.ibex.task;

import org.ixcode.ibex.task.CygwinPathFormat;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;


public class CygwinPathFormatTest {

    @Test
    public void convertsWindowsPathsToCygwinPaths() {
        String windowsPath = "C:\\Users\\bobby\\work\\jobby\\foo\\bar";

        String cygwinPath = new CygwinPathFormat().format(windowsPath);

        assertThat(cygwinPath).isEqualTo("/c/Users/bobby/work/jobby/foo/bar");
    }

}