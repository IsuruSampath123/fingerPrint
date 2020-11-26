package finger.print.access;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class Requester {
Socket requestSocket;
ObjectOutputStream out;
ObjectInputStream in;
String message;

Requester() {
}

void run() throws IOException {
    try {
        // 1. creating a socket to connect to the server
        //requestSocket = new Socket("192.168.0.19", 4370);
        requestSocket = new Socket("192.168.8.224", 4370);
        System.out.println("Connected to given host in port 4370");
        // 2. get Input and Output streams
        in = new ObjectInputStream(requestSocket.getInputStream());
        // 3: Communicating with the server
        String line;
        while (true) {
            line = in.readLine();
            if (line != null) {
                System.out.println(line);
            }
        }
    } catch (UnknownHostException unknownHost) {
        System.err.println("You are trying to connect to an unknown host!");

    } catch (IOException ioException) {
        ioException.printStackTrace();

    } catch (Exception Exception) {
        Exception.printStackTrace();

    } finally {
        in.close();
        requestSocket.close();
    }
}

void sendMessage(String msg) {
    try {
        out.writeObject(msg);
        out.flush();
        System.out.println("client: " + msg);

    } catch (IOException ioException) {
        ioException.printStackTrace();
    }
}

public static void main(String args[]) throws IOException {
    Requester client = new Requester();
    client.run();
}
}
