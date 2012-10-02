package org.ixcode.ibex.task;

import org.ixcode.ibex.BuildLog;
import org.ixcode.ibex.BuildTask;
import org.ixcode.ibex.dependency.DependencyRepo;
import org.ixcode.ibex.dependency.MavenArtifact;
import ixcode.platform.io.RelativeFile;
import org.ixcode.ibex.BuildLog;

public class Publish implements BuildTask {

    private DependencyRepo repo;
    private final MavenArtifact artifact;
    private final RelativeFile artifactFile;

    public Publish(DependencyRepo repo,
                   MavenArtifact artifact,
                   RelativeFile artifactFile) {
        this.repo = repo;

        this.artifact = artifact;
        this.artifactFile = artifactFile;
    }


    @Override public void execute(BuildLog buildLog) {
        repo.publishArtifact(artifact, artifactFile, buildLog);
    }
}