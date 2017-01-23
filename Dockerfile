FROM centos-java
#install testmemcached
ADD testmemcached-0.1.0.jar  /usr/local/app/
WORKDIR /usr/local/app/
EXPOSE 8080
CMD ["java","-jar","testmemcached-0.1.0.jar"]
