FROM tapimages.us.enableiot.com:8080/tap-base-java:java8-jessie

ADD apache-tomcat-7.0.70.tar.gz .
RUN ls -lah

ENV TOMCAT_VERSION=7.0.70
ENV CATALINA_HOME=apache-tomcat-${TOMCAT_VERSION}

RUN mkdir -vp /config/

# ADD REAL_CONFIG/uaa.yml /config/

EXPOSE 8080

ENV UAA_CONFIG_PATH=/config/
ENV LOGIN_CONFIG_PATH=/config/

RUN rm -r ${CATALINA_HOME}/webapps/*
COPY uaa/build/libs/cloudfoundry-identity-uaa-*.war ${CATALINA_HOME}/webapps/ROOT.war

CMD $CATALINA_HOME/bin/catalina.sh run
