import java.io.*;
import java.net.*;
 
public class MessengerClient {
    public static void main(String[] args) throws IOException {
         
        //check for proper arguments from the command line
        if (args.length != 2) {
            System.err.println(
                "Incorrect Arguments. Use: java MessengerClient <host name> <port number>");
            System.exit(1);
        }
 
        String hostName = args[0]; //assign the host name from arg list
        int portNumber = Integer.parseInt(args[1]);  //assign port number from arg list
 
        try (
            //create a new socket and assign a buffered reader to standard input
            Socket messengerSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(messengerSocket.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;  //variable for input from user
            
            System.out.println("Simple 1-Way Messenger. \nType 'exit' to quit.\n");  //instruct user how to exit
            
            //while the user is inputting something
            while ((userInput = stdIn.readLine()) != null) {
                
                out.println(userInput);  //send the message through the socket
                
                //check to see if the user wants to exit
                if("exit".equals(userInput))
                {
                    return;  //exit program
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
    }
}