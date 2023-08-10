package com.example.demonatssqs

import io.nats.client.Connection
import io.nats.client.Nats
import io.nats.client.Options
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/publish")
class PublishMessageController {

    @PostMapping
    fun sendMessage(@RequestBody body: MyMessage): ResponseEntity<MyMessage> {
        val natsUrl = "nats://localhost:4222"
        val natsOptions = Options.Builder().server(natsUrl).build()
        val natsConnection: Connection = Nats.connect(natsOptions)

        natsConnection.publish("hello-world-nats", body.text.toByteArray())
        natsConnection.close()

        return ResponseEntity.status(HttpStatus.CREATED).body(body)
    }
}