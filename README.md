# Preface #

This document describes the functionality provided by the xld docker gradle plugin.

# CI status #

[![Build Status][xld-docker-gradle-travis-image] ][xld-docker-gradle-travis-url]
[![Codacy Badge][xld-docker-gradle-codacy-image] ][xld-docker-gradle-codacy-url]
[![Code Climate][xld-docker-gradle-code-climate-image] ][xld-docker-gradle-code-climate-url]

[xld-docker-gradle-travis-image]: https://travis-ci.org/xebialabs-community/xld-docker-gradle-plugin.svg?branch=master
[xld-docker-gradle-travis-url]: https://travis-ci.org/xebialabs-community/xld-docker-gradle-plugin
[xld-docker-gradle-codacy-image]: https://api.codacy.com/project/badge/Grade/9e735dc2f73e40b3a9295b9fe9f8df31
[xld-docker-gradle-codacy-url]: https://www.codacy.com/app/joris-dewinne/xld-docker-gradle-plugin
[xld-docker-gradle-code-climate-image]: https://codeclimate.com/github/xebialabs-community/xld-docker-gradle-plugin/badges/gpa.svg
[xld-docker-gradle-code-climate-url]: https://codeclimate.com/github/xebialabs-community/xld-docker-gradle-plugin


# Overview #

The xld-docker-gradle plugin allows to startup a Docker container with XLD installed to compile the plugin.
Also you can use `runDocker` to start an XLD container with your plugin preloaded.

# Requirements #

* **XL Deploy** 5.x
* **Gradle** 2.12+

# Installation #

Define on top of the `build.gradle` file:

```
plugins {
  id "com.xebialabs.xld.docker" version "1.2.0"
}
```


For the latest version of the plugin have a look at:
[xld-docker-gradle-plugin](https://plugins.gradle.org/plugin/com.xebialabs.xld.docker)

# Usage #

You can make use of the following gradle tasks

* `compileDocker`
    * `compileVersion`: specifies which version of the [XLD image](https://hub.docker.com/r/xebialabs/xld_dev_compile/tags/) to use.
* `runDocker`
    * `compileVersion`: specifies which version of the [XLD compile image](https://hub.docker.com/r/xebialabs/xld_dev_compile/tags/) to use.
    * `runVersion`: specifies which version of the [XLD run image](https://hub.docker.com/r/xebialabs/xld_dev_run/tags/) to use.
    * `xld_initialize.py`: If your project has a file `src/test/resources/docker/initialize/xld_initialize.py`, this will be run through the CLI. This allows you to create some dummy CI's for testing.
    * The `src/main/resources` folder will be linked into the XLD `ext` folder (so you don't have to restart on script changes)
* `runDockerCompose`
    * A `docker-compose.yml` should be present under `src/test/resources/docker`
    * This task depends on `compileDocker`
* `stopDockerCompose`

# Example #

```
xldDocker {
  compileVersion = 'v5.5.5.4'
  runVersion = 'v5.5.5.5'
  download("xld_community_plugins") {
    src(["https://github.com/xebialabs-community/xld-openshift-plugin/releases/download/v6.1.1/xld-openshift-plugin-6.1.1.xldp",
         "https://github.com/xebialabs-community/xld-docker-plugin/releases/download/5.1.5/xld-docker-plugin-5.1.5.xldp"])
    dest file("src/downloads/plugins")
    acceptAnyCertificate true
  }
}
```

