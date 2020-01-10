import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	private static ServerSocket listener;
	
 public static void main(String[] args) throws Exception
 {
	 // Compteur incremente
	 int clientnumber = 0;
	 
	 // Adresse et port server
	 String serverAddress = "127.0.0.1";
	 int serverPort = 5000;
	 
	 //Creation de la connexion pour communiquer avec les clients
	 listener = new ServerSocket();
	 listener
	 
 }
}
