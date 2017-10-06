#!/bin/bash

# Build web app and deploy the generated js and html to webapp

fileDir=`dirname ${BASH_SOURCE[0]}`
startDir=`pwd`

cd $fileDir/..
rootDir=`pwd`
mainDir=$rootDir/src/main
webClientDir=$mainDir/resources/web-client

# Build web app
( cd $webClientDir && npm run build )

echo "Deploying scripts to webapp..."

# Remove build files
rm -r $mainDir/webapp/static/*

# Copy all files
cp -r $webClientDir/build/static/* \
      $mainDir/webapp/static/

# Copy html
looked_for='/static/js/'
replacement='/wso2Example/static/js/'
html=`cat $webClientDir/build/index.html`
html=${html/$looked_for/$replacement}

# Replace index.jsp content
echo $html > $mainDir/webapp/index.jsp

echo "Deployed"

cd $startDir
