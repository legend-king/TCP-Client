package org.example
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    Thread{ server() }.start()
}

fun server() {
    val server = ServerSocket(9999)

    val client = server.accept()
    while (true){
        val output = PrintWriter(client.getOutputStream(), true)
        val input = BufferedReader(InputStreamReader(client.inputStream))
        val content = input.readLine()
        if (content.equals("Exit")){
            break
        }
        output.println(content)
    }
    client.close()

}