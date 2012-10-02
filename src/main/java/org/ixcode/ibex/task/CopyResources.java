package org.ixcode.ibex.task;

import org.ixcode.ibex.BuildLog;
import org.ixcode.ibex.BuildTask;
import ixcode.platform.io.RelativeFile;
import org.ixcode.ibex.BuildLog;
import org.ixcode.ibex.BuildTask;

public class CopyResources implements BuildTask {
    private final RelativeFile resourceDir;
    private final RelativeFile targetClassesDir;

    public CopyResources(RelativeFile resourceDir, RelativeFile targetClassesDir) {
        this.resourceDir = resourceDir;
        this.targetClassesDir = targetClassesDir;
    }

    @Override public void execute(BuildLog buildLog) {
        buildLog.println("Copying resources from [%s] to [%s]", resourceDir, targetClassesDir);

        targetClassesDir.mkdirs();


        RelativeFile[] subdirs = resourceDir.listSubdirs();

        for (RelativeFile subdir : subdirs) {
            new Copy(subdir, targetClassesDir).execute(buildLog);
        }
    }
}