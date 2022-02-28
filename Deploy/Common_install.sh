#!/bin/bash

rm -rf ../Common/target/
mkdir ../Common/target/
mkdir ../Common/target/lib/

cd ../Common/

mvn clean package install

# cp ../../tpx-engine-exchange-core/target/exchange-core-0.4.4-SNAPSHOT-jar-with-dependencies.jar ../../tpx-engine-backend/target/lib/

# mvn install:install-file -Dfile=../../tpx-engine-backend/target/lib/exchange-core-0.4.4-SNAPSHOT-jar-with-dependencies.jar -DgroupId=com.pizzahouse.common -DartifactId=exchange-core -Dversion=0.2-SNAPSHOT -Dpackaging=jar -DgenerationPom=true
