FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY . .
# 実行権限を付与
RUN chmod +x ./gradlew
RUN ./gradlew build -x test
CMD ["java", "-jar", "build/libs/quiz-api-0.0.1-SNAPSHOT.jar"]