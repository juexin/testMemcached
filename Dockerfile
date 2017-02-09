FROM docker.io/java
#install testmemcached
ADD testmemcached-0.1.0.jar /app/
ADD start.sh /app/
WORKDIR /app/
EXPOSE 8080
ENTRYPOINT bash start.sh