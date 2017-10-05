#!/bin/bash

dir="`dirname ${BASH_SOURCE[0]}`/../src/main/resources/web-client"

( cd $dir && npm start )
