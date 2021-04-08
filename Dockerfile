FROM openjdk:8u191-jre-alpine3.8
RUN apk add curl jq

# workspace
WORKDIR /Users/nikunj.bambhroliya@spika.com/SELENIUM_DOCKER_PROJECT

# ADD .jar under target from host
# into this image
ADD target/selenium-docker.jar selenium-docker.jar
ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD target/libs	libs

# in case of any other dependencies ADD them Example: .csv, .json
# ADD Suit/testNG file 

ADD testng.xml	testng.xml

# ADD healthcheck file to image
# ADD healthcheck.sh healthcheck.sh
RUN wget https://s3.amazonaws.com/selenium-docker/healthcheck/healthcheck.sh

# ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST org.testng.TestNG $MODULE

ENTRYPOINT sh healthcheck.sh