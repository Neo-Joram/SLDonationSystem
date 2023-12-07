# Use Ubuntu as the base image
FROM ubuntu:latest as builder
LABEL authors="neo"

# Install necessary dependencies
RUN apt-get update && apt-get install -y \
    wget \
    software-properties-common \
    && rm -rf /var/lib/apt/lists/*

# Install GraalVM
RUN apt-get update && apt-get install -y \
    unzip \
    && wget -q https://download.oracle.com/graalvm/17/archive/graalvm-jdk-17.0.9_linux-x64_bin.tar.gz -O /tmp/graalvm.tar.gz \
    && tar -xzf /tmp/graalvm.tar.gz -C /opt \
    && rm /tmp/graalvm.tar.gz

# Set environment variables
ENV GRAALVM_HOME /opt/graalvm/graalvm-ce-java17-17.0.9
ENV PATH ${GRAALVM_HOME}/bin:${PATH}

# Set Maven home
ENV MAVEN_HOME /usr/share/maven

# Set the working directory
WORKDIR /app

# Copy the project files and build
COPY . .
RUN /opt/graalvm/graalvm-ce-java17-17.0.9/bin/gu install native-image
RUN mvn clean package -DskipTests

# Use a smaller base image for the final image
FROM openjdk:17.0.1-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/SvLDSystem-0.0.1-SNAPSHOT.jar SvLDSystem.jar

# Expose the application port
EXPOSE 9090

# Define the entry point
ENTRYPOINT ["java", "-jar", "SvLDSystem.jar"]
