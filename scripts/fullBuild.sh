#!/bin/bash

# Builds web client and the java API

fileDir=`dirname ${BASH_SOURCE[0]}`
startDir=`pwd`

cd $fileDir/..
rootDir=`pwd`

# Building web client
source $rootDir/scripts/buildWebApp.sh

# Building java API
( cd $rootDir && mvn install )

cd $startDir
