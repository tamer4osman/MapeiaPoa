FROM tomcat:9.0.0-jre8

COPY build/libs/mapeia-poa-0.1.0.war /usr/local/tomcat/webapps/mapeia-poa-0.1.0.war
