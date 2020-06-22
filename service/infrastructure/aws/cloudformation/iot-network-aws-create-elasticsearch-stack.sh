#!/bin/bash

set -e
## Checking whether the stack-name is passed as an arguement
if [ $# -lt 1 ]; then
  echo "Kindly provide stack name! Script execution stopped."
  exit 1
fi
echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
echo "Creating Elasticsearch stack with name: $1"
echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"

stackList=$(aws cloudformation list-stacks --query 'StackSummaries[?StackStatus != `DELETE_COMPLETE`].{StackName:StackName}')
#echo "stacklist is $stackList"


if [  `echo $stackList | grep -w -c $1 ` -gt 0 ]
then
  echo "Stack with name: $1  exists"
  echo "Stack creation failed"
  echo "Exiting.."
  exit 1
fi

echo "Please enter the Name of Elasticsearch domain for sensor data [a-z][a-z0-9]*"
read ES_DOMAIN_NAME


##Creating Stack
#echo "Creating Cloud Stack $1"
response=$(aws cloudformation create-stack --stack-name "$1" --template-body file://iot-network-elasticsearch.json --parameters --parameters ParameterKey="ElasticsearchDomainName",ParameterValue=$ES_DOMAIN_NAME)
echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
echo "Waiting for Stack $1 to be created"
echo "$response"

aws cloudformation wait stack-create-complete --stack-name $1
echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
echo "stack $1 created successfully"
