#!/bin/bash

rm -rf ../OrderConfirmationService/target/
mkdir ../OrderConfirmationService/target/
mkdir ../OrderConfirmationService/target/lib/

cd ../OrderConfirmationService/

mvn clean package spring-boot:repackage -Dmaven.test.skip=true