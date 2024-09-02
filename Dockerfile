# Use JRE 21 LTS 
FROM eclipse-temurin:21-jre

ARG APP_NAME=back-a-deux-pas \
    APP_VERSION=1.0.0-SNAPSHOT \
    APP_UID=1001

# create app user to avoid running app as root and install ca-certificates and clean apt cache
RUN useradd -U -m -d /app/ -s /bin/bash -u ${APP_UID} app-user && \
    apt install -y ca-certificates && \
    apt clean

# switch to app-user and its home directory
USER app-user
WORKDIR /app

# copy application jar to /app/ directory and change owner (chown) to app-user
COPY --chown=${APP_UID}:${APP_UID} ./target/${APP_NAME}-${APP_VERSION}.jar /app/ 

# make app and tmp directories writable when container is running in readonly
VOLUME /app /tmp

# Set Env vars needed for CMD
ENV APP_NAME=${APP_NAME}
ENV APP_VERSION=${APP_VERSION}

# set default command to start application and expose application port
CMD ["sh", "-c", "java -Dspring.profiles.active=prod -jar /app/${APP_NAME}-${APP_VERSION}.jar"]
EXPOSE 8081

