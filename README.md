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
  id "com.xebialabs.xld.docker" version "1.0.11"
}
```


For the latest version of the plugin have a look at:
[xld-docker-gradle-plugin](https://plugins.gradle.org/plugin/com.xebialabs.xld.docker)

# Usage #

You can make use of the following gradle tasks

* `compileDocker`
    * `version`: specifies which version of the XLD image to use. Default: 5.5.1.1
* `runDocker`
    * `version`: specifies which version of the XLD image to use. Default: 5.5.1.1
    * `xld_initialize.py`: If your project has a file `src/test/resources/docker/initialize/xld_initialize.py`, this will be run through the CLI. This allows you to create some dummy CI's for testing.
    * The `src/main/resources` folder will be linked into the XLD `ext` folder (so you don't have to restart on script changes)

