import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // don't need to specify a hostname, it will be the current machine
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");
        Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
        System.out.println("Connection from " + socket + "!");
        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // create a DataInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
        ArrayList<Integer> numbers=new ArrayList<>();
        numbers.add(10);
        numbers.add(1);
        numbers.add(342);
        numbers.add(12);
        objectOutputStream.writeObject(numbers);
        // read the list of messages from the socket
        List<Integer> listOfMessages = (List<Integer>) objectInputStream.readObject();
        System.out.println("Received [" + listOfMessages.size() + "] messages from: " + socket);
        // print out the text of every message
        System.out.println("All messages:");
        System.out.println(listOfMessages);
        System.out.println("Closing sockets.");
        ss.close();
        socket.close();
    }
}
