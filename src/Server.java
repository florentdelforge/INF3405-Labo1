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

/*
 * Une thread qui se charge de traiter la demande de chaque client sur un socket particulier
*/

private static class ClientHandler extends Thread
{
	private Socket socket;
	private int clientNumber;
	
	public ClientHandler(Socket socket, int clientNumber)
	{
		this.socket = socket; 
		this.clientNumber = clientNumber;
		System.out.println("New connection with client#"+clientNumber+"at"+socket);
	}

	/*
	 * Une thread se charge d'envoyer au client un message de bienvenue
	*/
	public void run()
	{
		try
		{
			// Création d'un canal sortant pour e nvoyer des messages au client
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			// Envoi d'un message au client
			out.writeUTF("Hello from server - you are client#"+clientNumber);
			
		} catch (IOException e)
		{
			System.out.println("Error handling client#"+clientNumber+":"+e);
		}
		finally
		{
			try
			{
				// Fermeture de la connexion avec le client
				socket.close();
			}
			catch (IOException e)
			{
				System.out.println("Couldn't close a socket, what's going on?");
			}
			System.out.println("Connection with client#"+clientNumber+"closed");
		}
	}
}
