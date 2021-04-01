FROM miiingle.net.misc:liquibase-latest
ADD changelogs /liquibase/changelog
CMD ["sh", "-c", "docker-entrypoint.sh --url=${URL} --username=${USERNAME} --password=${PASSWORD} --classpath=/liquibase/changelog --changeLogFile=changelog.sql update"]