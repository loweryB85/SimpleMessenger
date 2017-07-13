import java.net.*;
import java.io.*;
 
public class MessengerServer {
    public static void main(String[] args) throws IOException {
         
        //check for proper arguments from the command line
        if (args.length != 1) {
            System.err.println("Incorrect Argument. Use: java MessengerServer <port number>");
            System.exit(1);
        }
         
        int portNumber = Integer.parseInt(args[0]);  //assign port number from arg list
        System.out.println("Simple 1-Way Messenger Server\n");
         
        try (
            //create a new socket and assign buffered reader to socket's input stream
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
            Socket clientSocket = serverSocket.accept();    
            BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
        ) {
            
            String inputLine;   //variable to hold input from socket
            
            //while the input from the socket is not the exit command
            while (!"exit".equals(inputLine = in.readLine())) {
                
                System.out.println(inputLine); //print input from socket
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}