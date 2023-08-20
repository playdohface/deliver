# Deliver

This is an example application that tracks deliveries. It has been built in a weekend to demonstrate my knowledge of the Spring Boot/Angular stack.

To run this project, first make sure you have Java (17), Maven(3.9.2), npm(9.6.7) and Angular(16.1) installed.

Build and run it with the following command:
```shell
cd frontend &&\
npm install  &&\
npm run build &&\
cd .. &&\
mvn clean install &&\
java -jar target/deliver-0.0.1-SNAPSHOT.jar

```
You should be able to navigate to http://localhost:8080 in a browser of your choice and find the app running.
Create a delivery, simulate moving it along with the Admin-panel, and track it.

## Security & Persistence

Please note that this application employs no serious security measures and is not meant for real-world use in its current form.
It is configured to use an in-memory database, please adjust the `application.properties` if you want to persist data.

## License

You are free to modify and use this code for non-commercial purposes.

