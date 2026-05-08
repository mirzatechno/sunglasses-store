FROM tomcat:9.0-jdk17

COPY target/sunglasses-store.war /usr/local/tomcat/webapps/

EXPOSE 8080
