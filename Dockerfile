# Use an official Tomcat 9 image that includes Java 8.
# This becomes the base environment for our application.
FROM tomcat:9-jdk8-temurin

# The COPY instruction takes the .war file that Maven builds
# and places it inside the Tomcat server's special 'webapps' folder.
# The second 'JavaApp.war' renames it to ensure the URL is consistent.
COPY target/JavaApp.war /usr/local/tomcat/webapps/JavaApp.war

# Tell Docker that the container will listen on port 8080 at runtime.
EXPOSE 8080

# This is the command that runs when the container starts.
# It simply starts the Tomcat server in the foreground.
CMD ["catalina.sh", "run"]