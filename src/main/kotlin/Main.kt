package org.example
import java.io.*
import java.net.ServerSocket
import java.net.Socket
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.reflect.typeOf

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val server = ServerSocket(9999)


//    Thread{ server() }.start()
    while (true) {
//        val socket = server.accept()            // accept a connection
        val client = server.accept()
        Thread{ server(client) }.start()  // create a thread to deal with the client
    }
}

fun byteArrayToFile(byteArray: ByteArray, filePath: String) {
    try {
        val content = byteArray.toString(Charsets.UTF_8)
        val file = File(filePath)
        val writer = FileWriter(file)
        writer.write(content)
        writer.close()
        println("File written successfully.")
    } catch (e: IOException) {
        e.printStackTrace()
        println("Error writing the file.")
    }
}

fun server(client: Socket) {
//    val server = ServerSocket(9999)
//
//    val client = server.accept()
//    while (true){
        val output = PrintWriter(client.getOutputStream(), true)
        val fileName = BufferedReader(InputStreamReader(client.inputStream)).readLine()

//        val content = fileName.readLine()
        println(fileName)
        var fileContent = BufferedReader(InputStreamReader(client.inputStream)).readLine()
//        println(fileContent)

    if (!fileContent.isNullOrEmpty()){
        var dummy:ByteArray = ByteArray(fileContent.split(' ').size)
        var j=0
        fileContent=fileContent.replace('[',' ')
        fileContent=fileContent.replace(',',' ')
        fileContent=fileContent.replace(']',' ')
        fileContent = fileContent.strip()
        for (i in fileContent.split("  ")){
            dummy.set(j,i.toByte())
            j+=1
        }
        Files.write(Paths.get(fileName),dummy)
    }
//        print(fileContent)


        output.println("File Received")
//    }
    client.close()

}

fun saveByteArrayToFile(byteArray: ByteArray, fileName: String) {
    val file = FileOutputStream(fileName)
    file.write(byteArray)
    file.close()
}