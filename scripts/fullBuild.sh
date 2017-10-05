#!/bin/bash

fileDir=`dirname ${BASH_SOURCE[0]}`
startDir=`pwd`

cd $fileDir/..
rootDir=`pwd`

source $rootDir/scripts/buildWebApp.sh

( cd $rootDir && mvn install )

cd $startDir
