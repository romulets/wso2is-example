#!/bin/bash

echo "Deploying scripts to webapp..."

# Remove build files
rm -r ../../../main/webapp/static/*

# Copy all files
cp -r build/static/* ../../../main/webapp/static/

# Copy html
looked_for='/static/js/'
replacement='/wso2Example/static/js/'
html=`cat build/index.html`

html=${html/$looked_for/$replacement}
html=${html/'React App'/'Wso2 Example'}

echo $html > ../../../main/webapp/index.jsp

echo "Deployed"
