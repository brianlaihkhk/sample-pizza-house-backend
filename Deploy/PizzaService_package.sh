#!/bin/bash

rm -rf ../PizzaService/target/
mkdir ../PizzaService/target/
mkdir ../PizzaService/target/lib/

cd ../PizzaService/

mvn clean  package spring-boot:repackage  -Dmaven.test.skip=true -Djasypt.encryptor.password="91644449-aab7-4d59-b892-9034ebe1c007"