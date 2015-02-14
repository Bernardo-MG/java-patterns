#!/bin/bash
# This script deploys the application using the POM configuration
# It is triggered only commits to the master or develop branches. Pulls are ignored

echo "Version " + $TRAVIS_JAVA_VERSION
echo "Version " + $TRAVIS_JDK_VERSION

if [ "$TRAVIS_PULL_REQUEST" == "false" ] && [[ "$TRAVIS_BRANCH" == "master" || "$TRAVIS_BRANCH" == "develop" ]]; then

   echo "Deploying Maven site"

   mvn deploy --settings ~/settings.xml

else

   echo "Maven site won't be deployed"

fi
