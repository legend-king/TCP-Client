//package org.example
//import java.io.*
//import java.net.ServerSocket
//import java.net.Socket
//import java.nio.file.Files
//import java.nio.file.Paths
//import kotlin.reflect.typeOf
//
////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//fun main() {
//    val server = ServerSocket(9999)
//
//
////    Thread{ server() }.start()
//    while (true) {
//        val client = server.accept()
//        Thread{ server(client) }.start()
//    }
//}
//fun server(client: Socket) {
////    val server = ServerSocket(9999)
////
////    val client = server.accept()
////    while (true){
//        val output = PrintWriter(client.getOutputStream(), true)
//        val fileName = BufferedReader(InputStreamReader(client.inputStream)).readLine()
//
////        val content = fileName.readLine()
//        println(fileName)
//        var fileContent = BufferedReader(InputStreamReader(client.inputStream)).readLine()
////        println(fileContent)
//
//    if (!fileContent.isNullOrEmpty()){
//        var dummy:ByteArray = ByteArray(fileContent.split(' ').size)
//        var j=0
//        fileContent=fileContent.replace('[',' ')
//        fileContent=fileContent.replace(',',' ')
//        fileContent=fileContent.replace(']',' ')
//        fileContent = fileContent.strip()
//        for (i in fileContent.split("  ")){
//            dummy.set(j,i.toByte())
//            j+=1
//        }
//        Files.write(Paths.get(fileName),dummy)
//    }
////        print(fileContent)
//
//
//        output.println("File Received")
////    }
//    client.close()
//
//}
//
//fun saveByteArrayToFile(byteArray: ByteArray, fileName: String) {
//    val file = FileOutputStream(fileName)
//    file.write(byteArray)
//    file.close()
//}

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
    val fileName = BufferedReader(InputStreamReader(client.inputStream)).readLine()
    val input = BufferedReader(InputStreamReader(client.inputStream))
    var buffer = byteArrayOf()
    var line = input.readLine()
    while (!line.isNullOrEmpty())
    {
//        println(line)
        buffer += line.toByte()
        line = input.readLine()
    }
    File(fileName).writeBytes(buffer)
}