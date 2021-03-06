package org.ixcode.ibex.task;

import org.ixcode.ibex.BuildLog;
import org.ixcode.ibex.BuildTask;
import org.ixcode.ibex.dependency.DependencyRepo;
import org.ixcode.ibex.dependency.MavenArtifact;
import org.ixcode.ibex.Module;
import ixcode.platform.io.RelativeFile;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.lang.System.getProperty;
import static org.apache.log4j.Logger.getLogger;

public class ResolveDependencies implements BuildTask {
    private static final Logger log = getLogger(ResolveDependencies.class);

    private final Module module;
    private final RelativeFile productionLibDir;

    private final List<DependencyRepo> searchRepositories;

    public ResolveDependencies(Module module, RelativeFile productionLibDir, List<DependencyRepo> defaultRepositories) {
        this.module = module;
        this.productionLibDir = productionLibDir;
        this.searchRepositories = buildRepoList(module, defaultRepositories);
    }

    @Override public void execute(BuildLog buildLog) {
        for (MavenArtifact d : module.productionDeps) {
            resolveDependency(d, buildLog);
        }
    }

    private void resolveDependency(MavenArtifact d, BuildLog buildLog) {
        boolean success = false;
        for (DependencyRepo repo : searchRepositories) {
            if (repo.resolveDependencyTo(d, productionLibDir, buildLog)) {
                success = true;
                break;
            }
        }

        if (!success) {
            throw new RuntimeException(format("Could not resolve dependency [%s] in either local or remote repositories", d));
        }
    }


    private static List<DependencyRepo> buildRepoList(Module module, List<DependencyRepo> defaultRepositories) {

        List<DependencyRepo> repoList = new ArrayList<DependencyRepo>(module.repositories);

        for (DependencyRepo defaultRepo : defaultRepositories) {
            repoList.add(0, defaultRepo);
        }

        return repoList;
    }
}