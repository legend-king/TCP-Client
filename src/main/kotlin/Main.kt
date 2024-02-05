package org.example
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket


fun main() {
    val server = ServerSocket(9999)
    while(true){
        val client = server.accept()
        Thread{ server(client) }.start()
    }
}

fun server(client: Socket) {
    val fileName = "img.jpeg"
    val input = BufferedReader(InputStreamReader(client.inputStream))
    var buffer = byteArrayOf()
    var line = input.readLine()
    while (!line.isNullOrEmpty())
    {
        println(line)
        buffer += line.toByte()
        line = input.readLine()
    }
    File(fileName).writeBytes(buffer)
}
