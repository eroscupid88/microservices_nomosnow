FROM jboss/keycloak:latest
EXPOSE 8080
COPY ./*.sh /opt/jboss/tools/
#COPY ../mysql_keycloak/*.json /opt/jboss/keycloak/
#CMD ["/bin/bash","-Dkeycloak.profile.feature.upload_scripts=enabled", "-Dkeycloak.import=/opt/jboss/keycloak/nomosnow-realm-export.json"]
ENTRYPOINT ["./opt/jboss/tools/docker-entrypoint.sh"]

