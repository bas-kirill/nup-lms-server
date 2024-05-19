#!/bin/bash
set -e

repository=$1
imageTag=$2

if [ -z "$1" ]
  then
    echo "No Docker Hub username provided"
    repository=myshx # my repository at DockerHub
fi

if [ -z "$2" ]
  then
    echo 'No imageTag provided. Latest will be used.'
    imageTag=latest
fi

imageFullName=nup-lms-backend:$imageTag
dockerHubImageFullName=$repository/nup-lms-backend:$imageTag

(docker tag $imageFullName $dockerHubImageFullName)
(docker push $dockerHubImageFullName)
