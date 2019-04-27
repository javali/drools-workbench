FROM jboss/drools-workbench-showcase:7.15.0.Final
LABEL MAINTAINER="Jan van Lil"

# change default GIT root directory
USER root
RUN mkdir -p $JBOSS_HOME/.gitrepo
RUN chown jboss:jboss $JBOSS_HOME/.gitrepo
ADD assets/standalone-full-drools.xml $JBOSS_HOME/standalone/configuration/standalone-full-drools.xml
RUN chown jboss:jboss $JBOSS_HOME/standalone/configuration/standalone-full-drools.xml

# switch back to application user
USER jboss
