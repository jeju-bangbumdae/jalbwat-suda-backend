# Dockerfile (저장소 루트에 위치)

# 1단계: Build stage (Gradle을 사용하여 애플리케이션 빌드)
FROM gradle:jdk17-alpine AS builder

# Docker 이미지 내의 작업 디렉토리를 /app으로 설정합니다.
WORKDIR /app

# 현재 빌드 컨텍스트의 모든 파일을 /app 디렉토리로 복사합니다.
# 이 때 gradlew 파일도 함께 복사됩니다.
COPY . .

# Gradle Wrapper 실행 권한을 부여합니다. (필수)
RUN chmod +x ./gradlew

# Gradle을 사용하여 Spring Boot 애플리케이션을 빌드합니다.
# 'bootJar' 태스크는 실행 가능한 JAR 파일을 생성합니다.
# -x test는 테스트를 건너뛰는 옵션입니다.
RUN ./gradlew bootJar -x test

# 2단계: Runtime stage (최종 애플리케이션 실행)
# 애플리케이션 실행에 필요한 JRE만 포함된 이미지를 사용합니다.
FROM eclipse-temurin:17-jre-alpine

# 최종 애플리케이션의 작업 디렉토리 설정
WORKDIR /app

# 1단계(builder)에서 생성된 실행 가능한 JAR 파일을 복사합니다.
# Gradle은 일반적으로 JAR 파일을 build/libs 폴더에 생성합니다.
# 여기서는 와일드카드를 사용하여 build/libs 내의 모든 .jar 파일을 복사하고 'app.jar'로 이름을 변경합니다.
COPY --from=builder /app/build/libs/*.jar ./

# Spring Boot 애플리케이션이 사용할 포트 (기본 8080)
EXPOSE 8080

# 애플리케이션 실행 명령어
# java -jar 명령어로 Spring Boot 애플리케이션을 실행합니다.
ENTRYPOINT ["java", "-jar", "app.jar"]
