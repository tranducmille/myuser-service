package com.xtivia.subprojects

import com.moowork.gradle.node.util.PlatformHelper
import org.gradle.api.*
import org.gradle.file.*
import org.gradle.api.tasks.*

class NpmInstallTask extends DefaultTask {
    String dir;

    @Input
    public File getPackageFile() {
        return  new File(new File(this.dir), 'package.json')
    }

    @OutputDirectory
    public File getNodeModulesFile() {
        return new File(new File(this.dir), 'node_modules')
    }

    @TaskAction
    def npmInstall() {
        log.info(dir)
        def npmCmd = 'npm'+(PlatformHelper.INSTANCE.isWindows()?'.cmd':'')
        didWork = true
        this.project.exec({
            workingDir = dir
            commandLine npmCmd, 'install'
        })
    }

    private static final org.gradle.api.logging.Logger log = org.gradle.api.logging.Logging.getLogger(NpmInstallTask.class)
}
