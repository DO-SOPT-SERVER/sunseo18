FROM amd64/amazoncorretto:17

WORKDIR /app

COPY ./build/libs/SecondSeminar-0.0.1-SNAPSHOT.jar /app/secondSeminar.jar

CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=deploy", "secondSeminar.jar"]