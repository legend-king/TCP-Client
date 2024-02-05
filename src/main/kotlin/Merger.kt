package org.example
import java.io.DataInputStream
import java.io.FileOutputStream
import java.net.ServerSocket
import java.net.Socket

class Merger {
    fun receiveFile(clientSocket: Socket, fileName: String) {

        val inputStream = DataInputStream(clientSocket.getInputStream())
        val fileOutputStream = FileOutputStream(fileName)
        val buffer = ByteArray(1024)
        var bytesRead: Int

        try {
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                fileOutputStream.write(buffer, 0, bytesRead)
            }
            println("File received successfully.")
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fileOutputStream.close()
            inputStream.close()
            clientSocket.close()
        }
    }
}

//Merger()