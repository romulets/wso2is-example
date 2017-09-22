#!/bin/bash

webAppDir="`dirname ${BASH_SOURCE[0]}`/../src/main/resources/web-client"
( cd $webAppDir && npm run build )

appDir="`dirname ${BASH_SOURCE[0]}`/../"
( cd $appDir && mvn install )
