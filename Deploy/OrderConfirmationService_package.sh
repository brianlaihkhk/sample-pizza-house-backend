#!/bin/bash

rm -rf ../OrderConfirmationService/target/
mkdir ../OrderConfirmationService/target/
mkdir ../OrderConfirmationService/target/lib/

cd ../OrderConfirmationService/

mvn clean package spring-boot:repackage -Dmaven.test.skip=true  -Djasypt.encryptor.password="91644449-aab7-4d59-b892-9034ebe1c007"