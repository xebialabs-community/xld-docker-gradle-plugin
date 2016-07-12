#!/usr/bin/env bash

# Compile plugin
cd /data/
./gradlew clean test assemble -PxlDeployHome=/opt/xld/server