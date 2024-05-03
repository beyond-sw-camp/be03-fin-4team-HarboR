FROM openjdk:11-jdk-slim as build

WORKDIR /Harbor_batch

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar
# 실제 실행할 이미지를 위한 새로운 단계를 시작합니다.
FROM openjdk:11-jre-slim
WORKDIR /Harbor_batch
# 빌드 단계에서 생성된 실행 가능한 jar 파일을 복사합니다.
COPY --from=build /Harbor_batch/build/libs/*.jar Harbor_batch.jar
# 컨테이너가 시작될 때 애플리케이션을 실행합니다.

ENTRYPOINT ["java", "-jar", "Harbor_batch.jar"]
