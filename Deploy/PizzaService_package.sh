#!/bin/bash

rm -rf ../PizzaService/target/
mkdir ../PizzaService/target/
mkdir ../PizzaService/target/lib/

cd ../PizzaService/

mvn clean  package spring-boot:repackage  -Dmaven.test.skip=true