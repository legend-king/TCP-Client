package org.example
import java.io.*
import java.net.ServerSocket;
import java.util.*

fun main() {
    println("Application running on ${Thread.currentThread().name}")
    val port = 9999
    val targetPath = "downloads"
    val server = ServerSocket(port)
    println("Server listening on port $port")

    downloadFile(port, targetPath, server)
}

fun downloadFile(port: Int, targetPath: String, server: ServerSocket){
    try {
        while (true){
            val client = server.accept()
            println("Request running on ${Thread.currentThread().name}")
            println("Client connected: ${client.inetAddress.hostAddress}")
            val inputStream = DataInputStream(client.getInputStream())
            val comparisonValue:Byte = 10
            val fileName:ByteArray = ByteArray(1000)
            var x = inputStream.readByte()
            var j=0
            while (x!=comparisonValue){
                fileName[j]=x
                j+=1
                x=inputStream.readByte()
            }
            println(fileName.decodeToString(0,j))
            val actualFileName = fileName.decodeToString(0,j)
            var byteContent = inputStream.readNBytes(100000000)
            val outputFile = File("db/${renameFileWithUUID(actualFileName)}")

            while (byteContent.isNotEmpty()){
                outputFile.appendBytes(byteContent)
                byteContent = inputStream.readNBytes(100000000)
            }
            println("Client ${client.inetAddress.hostAddress} disconnected")
        }
    } catch (e: Exception){
        e.printStackTrace()
    }

}

fun renameFileWithUUID(filename: String): String{
    val name = filename.split(".")
    val uuidFile = "${name[0]}-${UUID.randomUUID()}.${name[1]}"
    return uuidFile
}