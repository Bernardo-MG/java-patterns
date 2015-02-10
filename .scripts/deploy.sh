#!/bin/bash
# This script deploys the application using the POM cofiguration
# It is triggered only commits to the master or develop branches. Pulls are ignored

if [ "$TRAVIS_PULL_REQUEST" == "false" ] && [[ "$TRAVIS_BRANCH" == "master" || "$TRAVIS_BRANCH" == "develop" ]]; then

   mvn deploy --settings ~/settings.xml

fi
