# Sample Kotlin Spring API Integrated with Nats and SQS

## About The Project

Project created for studies with the aim of subscribing to a Nats topic and forwarding received messages to an SQS.

### Built With

* [Java - 17](https://www.java.com/pt-BR/)
* [Spring Boot - 3.1.2](https://spring.io/projects/spring-boot)
* [Kotlin - 1.8.22](https://kotlinlang.org/)
* [Nats](https://docs.nats.io/)
* [AWS - SQS](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/java_sqs_code_examples.html)

## How to use

Access the ./docker folder and start the localstack and nats containers with the command ```docker-compose up -d```

With both services running, launch the application and make calls to the available endpoint.

## Contact

To contact us use the options:

* E-mail  : luanbeck@gmail.com