FROM openjdk:8-jdk
EXPOSE 8080

# MySQL Server
ENV MASTER_URL=${MASTER_URL}
ENV MASTER_ID=${MASTER_ID}
ENV MASTER_PW=${MASTER_PW}
ENV SLAVE_URL=${SLAVE_URL}
ENV SLAVE__ID=${SLAVE__ID}
ENV SLAVE__PW=${SLAVE__PW}

# Iamport API Key
ENV IAMPORT_KEY=${IAMPORT_KEY}
ENV IAMPORT_SECRET=${IAMPORT_SECRET}

# Redis Session Server
ENV SESSION_HOST=${SESSION_HOST}
ENV SESSION_PORT=${SESSION_PORT}

# Redis Cache Server
ENV CACHE_HOST=${CACHE_HOST}
ENV CACHE_PORT=${CACHE_PORT}

ADD target/campool-0.0.1-SNAPSHOT.jar webapp.jar

ENTRYPOINT ["java", \
"-Dcampool.datasource.master.jdbc-url=${MASTER_URL}", \
"-Dcampool.datasource.master.username=${MASTER_ID}", \
"-Dcampool.datasource.master.password=${MASTER_PW}", \
"-Dcampool.datasource.slave.jdbc-url=${SLAVE_URL}", \
"-Dcampool.datasource.slave.username=${SLAVE__ID}", \
"-Dcampool.datasource.slave.password=${SLAVE__PW}", \
"-Dcampool.iamport.api.key=${IAMPORT_KEY}", \
"-Dcampool.iamport.api.secret=${IAMPORT_SECRET}", \
"-Dcampool.redis.session.host=${SESSION_HOST}", \
"-Dcampool.redis.session.port=${SESSION_PORT}", \
"-Dcampool.redis.cache.host=${CACHE_HOST}", \
"-Dcampool.redis.cache.port=${CACHE_PORT}", \
"-jar","/webapp.jar"]
