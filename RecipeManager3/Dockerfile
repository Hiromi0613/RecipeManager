# ビルドステージ（MavenでJAR作成）
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 実行ステージ（軽量JDKで実行）
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/RecipeManager3-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
