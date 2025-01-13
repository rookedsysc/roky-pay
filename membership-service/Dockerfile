FROM openjdk:17
EXPOSE 8080
# palantir.docker 디펜던시에서 docker 명령어를 실행할 때 넘기는 인자
# docker build -t membership-service --build-arg JAR_FILE=build/libs/membership-service-0.0.1-SNAPSHOT.jar .
# build-arg로 넘어온 JAR_FILE을 사용
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
