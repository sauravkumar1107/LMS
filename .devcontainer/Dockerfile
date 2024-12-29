# Use an appropriate JDK image
FROM maven:3.9.4-openjdk-17

# Set the working directory
WORKDIR /workspace

# Copy the project files into the container
COPY . /workspace

# Install dependencies
RUN mvn dependency:resolve

# Expose the application port
EXPOSE 8080

# Default command (can be overridden by Docker Compose)
CMD ["mvn", "spring-boot:run"]
