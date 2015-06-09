#!/bin/bash
# This script deploys the Maven site using the POM configuration
# It is triggered only commits to the master branch. Pulls are ignored
#
# Also, it will only deploy if the DEPLOY environment variable is set to 'true'

# Failfast and printing commands
set -ev

if [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$DEPLOY" == "true" ] && [[ "$TRAVIS_BRANCH" == "master" || "$TRAVIS_BRANCH" == "develop" ]]; then

   mvn site site:deploy --settings ~/settings.xml

else

   echo "Maven site won't be deployed"

fi
