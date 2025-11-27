FROM eclipse-temurin:17-jdk

#빌드 시점에 사용될 변수 정의
#JAR FILE은 빌드 컨텍스트 내의 jar 파일 경로를 지정하는 데 사용
ARG JAR_FILE=build/libs/*.jar

#ARG에서 정의한 JAR_FILE 경로의 jar 파일을 컨테이너 내 app.jar로 복사
COPY ${JAR_FILE} app.jar

# gradlew에 실행 권한 부여
RUN chmod +x gradlew
# 프로젝트 빌드
RUN ./gradlew clean build -x test

#컨테이너가 시작될 때 실행될 명령어 정의
ENTRYPOINT ["java","-Dspring.profiles.active=docker", "-jar","app.jar"]