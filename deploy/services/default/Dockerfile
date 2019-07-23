FROM openjdk:8u131
#Change timezone
RUN ln -fs /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime
RUN dpkg-reconfigure -f noninteractive tzdata
ENV CI_BUILD_URL=$CI_BUILD_URL
EXPOSE 5000
COPY target/*.jar /tmp/
RUN mkdir -p /config
COPY start.sh /tmp/
RUN chmod +x /tmp/start.sh
CMD /tmp/start.sh ${CI_BUILD_NAME}
