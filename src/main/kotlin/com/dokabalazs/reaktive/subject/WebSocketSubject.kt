package com.dokabalazs.reaktive.subject

import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI
import java.util.concurrent.LinkedBlockingQueue

class WebSocketSubject(url: String) : Subject<String>() {
    private var connected = false
    private val queue = LinkedBlockingQueue<String>(100);

    private val connection: WebSocketClient = object : WebSocketClient(URI(url)) {
        override fun onOpen(handshakedata: ServerHandshake?) {
            connected = true
            queue.forEach { connection.send(it) }
        }

        override fun onClose(code: Int, reason: String?, remote: Boolean) {
            println("Closed WS: ${if (reason == "") "Closed by client" else reason}")
            connected = false
        }

        override fun onMessage(message: String?) = emit(message ?: "")

        override fun onError(ex: Exception?) {
            println("Error occurred in WS: ${ex?.message}")
            close()
            connected = false
        }

    }

    init {
        connection.connect()
    }

    override fun next(value: String) {
        if (connected) {
            connection.send(value)
        } else {
            queue.add(value)
        }
    }

    public override fun close() {
        super.close()
        connection.closeBlocking()
    }
}