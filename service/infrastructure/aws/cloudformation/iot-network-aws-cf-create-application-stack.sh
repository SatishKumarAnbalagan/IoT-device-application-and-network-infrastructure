#!/bin/bash

f () {
    errcode=$? # save the exit code as the first thing done in the trap function
    echo "error $errcode"
    echo "the command executing at the time of the error was"
    echo "$BASH_COMMAND"
    echo "on line ${BASH_LINENO[0]}"
    echo "Exiting the script."
    exit $errcode
}

trap f ERR

## Checking whether the stack-name is passed as an arguement
if [ $# -lt 1 ]; then
  echo "Kindly provide stack name! Script execution stopped."
  exit 1
fi
echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
echo "Creating Application stack with name: $1"
echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"


echo "Displaying all keys!"
for key in `aws ec2 describe-key-pairs --output text | cut -f3`
do
  echo -e $key
done
echo "Choose 1 Key which you want to use!"
read KEY_CHOSEN

echo "Displaying AMI!"
for image in `aws ec2 describe-images --owners self --query 'Images[*].{ID:ImageId}' --output text | cut -f1`
do
  echo -e $image
done
echo "Enter AMI ID"
read amiId

stackList=$(aws cloudformation list-stacks --query 'StackSummaries[?StackStatus != `DELETE_COMPLETE`].{StackName:StackName}')


if [  `echo $stackList | grep -w -c $1 ` -gt 0 ]
then
  echo "Stack with name: $1  exists"
  echo "Stack creation failed"
  echo "Exiting.."
  exit 1
fi



##Creating Stack
#echo "Creating Cloud Stack $1"
response=$(aws cloudformation create-stack --stack-name "$1" --template-body file://iot-network-cf-application.json --parameters ParameterKey="keyname",ParameterValue=$KEY_CHOSEN ParameterKey="AmiId",ParameterValue=$amiId)
echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
echo "Waiting for Stack $1 to be created"
echo "$response"

aws cloudformation wait stack-create-complete --stack-name $1
echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
echo "stack $1 created successfully"