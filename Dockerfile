FROM openjdk

WORKDIR /app

COPY target/contFinanceiro-0.0.1-SNAPSHOT.jar /app/contFinanceiro.jar

ENTRYPOINT [ "java","-jar","contFinanceiro.jar" ]
EXPOSE 8080