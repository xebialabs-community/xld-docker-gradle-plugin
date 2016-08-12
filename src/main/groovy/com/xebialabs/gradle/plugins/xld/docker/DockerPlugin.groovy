package com.xebialabs.gradle.plugins.xld.docker

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Exec

class DockerPlugin implements Plugin<Project> {
    public static final String COMPILE_DOCKER_TASK_NAME = "compileDocker"
    public static final String RUN_DOCKER_TASK_NAME = "runDocker"

    @Override
    void apply(Project project) {
        // Apply the base plugin to get cleaning behaviour
        project.apply plugin: "base"

        DockerPluginExtension dockerPluginExtension = project.extensions.create("xldDocker", DockerPluginExtension)

        project.afterEvaluate {
            Task compileTask = createDockerTask(project, COMPILE_DOCKER_TASK_NAME, ["run", "-v", project.getRootDir().absolutePath + ":/data", "-v", System.getProperty("user.home") + "/.xlgradle:/root/.gradle", "xebialabs/xld_dev_compile:" + dockerPluginExtension.version])
            Task runTask = createDockerTask(project, RUN_DOCKER_TASK_NAME, ["run", "-p", "4516:4516", "-v", project.getRootDir().absolutePath + ":/data", "-v", System.getProperty("user.home") + "/xl-licenses:/license", "xebialabs/xld_dev_run:" + dockerPluginExtension.version])
            runTask.dependsOn compileTask
        }
    }


    private static Task createDockerTask(Project project, String taskName, Iterable<?> taskArgs) {
        return project.tasks.create(taskName, Exec).configure {
            executable "docker"
            args(taskArgs)
            workingDir project.getProjectDir()
        }
    }
}