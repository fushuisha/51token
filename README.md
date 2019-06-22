# 51token



mvn -DskipTests=true clean package

java -Dspring.profiles.active=prd -jar 51token-0.1-SNAPSHOT.jar > catalina.out  & 

