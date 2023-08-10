package com.example.demonatssqs

import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator
import io.nats.client.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.SendMessageRequest
import java.net.URI

@SpringBootApplication
class DemoNatsSqsApplication

fun main(args: Array<String>) {
	val natsUrl = "nats://localhost:4222"
	val sqsQueueUrl = "http://localhost:4566/000000000000/hello-world-sqs.fifo"

	// Conecta ao NATS
	val natsOptions = Options.Builder().server(natsUrl).build()
	val natsConnection: Connection = Nats.connect(natsOptions)

	// Conecta ao SQS
	val sqsClient: SqsClient = SqsClient.builder().endpointOverride(URI.create("http://localhost:4566")).build()

	// Cria um dispatcher para o tópico NATS com a lógica de repassar ao SQS
	val dispatcher: Dispatcher = natsConnection.createDispatcher { msg: Message ->
		val messageText = msg.data.decodeToString()

		val sendMessageRequest = SendMessageRequest.builder()
			.messageGroupId("xpto")
			.messageDeduplicationId(UUIDGenerator().toString())
			.queueUrl(sqsQueueUrl)
			.messageBody(messageText)
			.build()

		sqsClient.sendMessage(sendMessageRequest)

		println("Mensagem enviada para SQS: $messageText")
	}

	// Se inscreve no tópico NATS
	dispatcher.subscribe("hello-world-nats")

	// Aguarda por mensagens
	println("Aguardando mensagens do tópico NATS...")

	// Inicia aplicação spring
	runApplication<DemoNatsSqsApplication>(*args)
}