#!/bin/bash
# This script deploys the application using the POM cofiguration
# It is triggered only commits to the master or develop branches. Pulls are ignored

echo "Branch " + $TRAVIS_BRANCH

if [ "$TRAVIS_PULL_REQUEST" == "false" ] && [[ "$TRAVIS_BRANCH" == "master" || "$TRAVIS_BRANCH" == "develop" ]]; then

   echo "Deploying Maven site"

   mvn deploy --settings ~/settings.xml

else

   echo "Maven site won't be deployed"

fi
