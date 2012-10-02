package org.ixcode.ibex;

import org.ixcode.ibex.Module;
import org.ixcode.ibex.logging.ConsoleLog;
import org.junit.Test;

import java.io.File;

import static org.ixcode.ibex.Module.loadModule;
import static org.fest.assertions.Assertions.assertThat;

public class ModuleTest {

    @Test
    public void can_load_from_a_file() throws Exception {
        File moduleDir = new File(".").getCanonicalFile();

        Module module = loadModule(moduleDir, new ConsoleLog());

        assertThat(module.name).isEqualTo("platform-build");
        assertThat(module.repositories.size()).isEqualTo(1);
        assertThat(module.repositories.get(0).location.toURL().toExternalForm()).isEqualTo("http://repo.ixcode.org/artifacts");

        assertThat(module.developmentDeps.size()).isEqualTo(1);
        assertThat(module.developmentDeps.get(0).toString()).isEqualTo("hamcrest:null:null");

        assertThat(module.productionDeps.size()).isEqualTo(6);
        assertThat(module.productionDeps.get(0).toString()).isEqualTo("log4j:log4j:1.2.16");
        assertThat(module.productionDeps.get(1).toString()).isEqualTo("org.ixcode:json-parser:1.0");
    }

    @Test
    public void get_parent_of_current_dir() throws Exception {
        File f = new File(".").getCanonicalFile();

        System.out.println(f.getAbsolutePath());
        System.out.println(f.getParent());

    }
}