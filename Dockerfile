# Use a base image that includes Java and PostgreSQL
FROM openjdk:17
ADD target/*.jar user_register.jar
# Set the working directory inside the container
#WORKDIR /app

# Copy the Spring Boot application jar file into the container
#COPY target/user_register.jar /app/user_register.jar

# Expose port 8090 for the Spring Boot application
EXPOSE 8090

# Define environment variables for PostgreSQL connection
#ENV DB_URL=jdbc:postgresql://postgres_host:5432/shivan
#ENV DB_USERNAME=postgres
#ENV DB_PASSWORD=shivan

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "user_register.jar"]

#CMD ["java", "-jar", "user_register.jar"]
