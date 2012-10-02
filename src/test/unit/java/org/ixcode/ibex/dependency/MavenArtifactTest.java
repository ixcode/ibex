package org.ixcode.ibex.dependency;

import org.ixcode.ibex.dependency.MavenArtifact;
import org.junit.Test;

import static org.ixcode.ibex.dependency.MavenArtifact.parseFromString;
import static org.fest.assertions.Assertions.assertThat;

public class MavenArtifactTest {

    @Test
    public void knows_how_to_make_its_jar_filename() {
        MavenArtifact d = parseFromString("org.apache.ant:ant:1.8.1");

        assertThat(d.toJarFileName()).isEqualTo("ant-1.8.1.jar");

    }

   @Test
    public void knows_how_to_make_maven_path() {

        MavenArtifact d = parseFromString("org.apache.ant:ant:1.8.1");


        assertThat(d.toMavenRepositoryPath()).isEqualTo("org/apache/ant/ant/1.8.1/ant-1.8.1.jar");

    }

}