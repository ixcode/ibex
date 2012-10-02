package org.ixcode.ibex.task;

import org.ixcode.ibex.BuildLog;
import org.ixcode.ibex.BuildTask;
import ixcode.platform.io.RelativeFile;
import org.ixcode.ibex.BuildLog;

import static org.ixcode.ibex.task.CygwinExecutable.pathToCygwinExe;
import static org.ixcode.ibex.task.SystemCommand.getOsSpecificFilename;
import static org.ixcode.ibex.task.SystemCommand.isCygwin;

public class Tar implements BuildTask {
    private final RelativeFile tarFile;
    private final RelativeFile dir;

    public Tar(RelativeFile tarFile, RelativeFile dir) {
        this.tarFile = tarFile;
        this.dir = dir;
    }

    @Override public void execute(BuildLog buildLog) {
        buildLog.println("tar'ing up [%s] to [%s]", dir, tarFile);

        String tarCommand = (SystemCommand.isCygwin()) ? pathToCygwinExe("tar.exe") : "tar";

        new SystemCommand(dir.asFile(), "%s cfzv %s .",tarCommand,
                          SystemCommand.getOsSpecificFilename(tarFile.getAbsolutePath())).execute(buildLog);
    }
}