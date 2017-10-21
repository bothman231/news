# get the base image from here
FROM store/oracle/serverjre:8
# create a /docker dir on the unix box
RUN mkdir /docker
# use 
#WORKDIR /unique/docker
WORKDIR /unique/git/news/Java_NewsRestApp1/target
# copy from local c:/unique/docker to /docker on unix box
#copy Java_NewsRestApp1.war /docker
copy Java_NewsRestApp1-0.0.1-SNAPSHOT.war /docker
ENTRYPOINT java -jar /docker/Java_NewsRestApp1.war 1
# docker pull store/oracle/serverjre:8

