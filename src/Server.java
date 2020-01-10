import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import javafx.util.Pair; 

public class Server {
	private static ServerSocket listener;

	public static void main(String[] args) throws Exception {
		// Ouverture du scan afin d'avoir un user input
		Scanner scan = new Scanner(System.in);

		// Compteur incremente
		int clientNumber = 0;

		// Adresse et port server
		// Contenu dans une paire 
		Pair<String, Integer> server = getServerInfo(scan);
		System.out.println(server.getKey());
		System.out.println(server.getValue());
		
		BaseDonnee BD = new BaseDonnee();
		HashMap<String, String> users = BD.readFromFile("mockDatabase.txt");
		BD.writeToFile(users, "newDatabase.txt");
	


		// Creation de la connexion pour communiquer avec les clients
		listener = new ServerSocket();
		listener.setReuseAddress(true);
		InetAddress serverIP = InetAddress.getByName(server.getKey());

		// Association adresse et port a connection
		listener.bind(new InetSocketAddress(server.getKey(), server.getValue()));

		System.out.format("The server is running on %s:%d%n", server.getKey(), server.getValue());

		try {
			// A chaque fois qu'un client se connecte on execute la fonciton run de l'objet
			// ClientHandler

			while (true) {
				new ClientHandler(listener.accept(), clientNumber++).start();
			}

		} finally {
			// fermeture de la connexion
			listener.close();
		}

	}

	/**
	 * 
	 */
	public static Pair<String, Integer> getServerInfo(Scanner scan) {
		
		int serverPort;
		String ip;
		
		do {
			System.out.println("Enter server's IP");
			ip = scan.next();
			
		}while(!validIP(ip));
		
		do {
			System.out.println("Enter server's port");
			serverPort = scan.nextInt();
		}while(serverPort < 5000 || serverPort > 5050);
		
		return new Pair(ip,serverPort);

	}

	
	public static boolean validIP(String ip) {
		if (ip == null || ip.isEmpty()) {
			return false;
		}

		String[] ipChunks = ip.split("\\.");
		if (ipChunks.length != 4) {
			return false;
		}

		for (String chunk : ipChunks) {
			int i = Integer.parseInt(chunk);
			if (i < 0 || i > 255)
				return false;

		}
		
		return true;
	}

	/*
	 * Une thread qui se charge de traiter la demande de chaque client sur un socket
	 * particulier
	 */

	private static class ClientHandler extends Thread {
		private Socket socket;
		private int clientNumber;

		public ClientHandler(Socket socket, int clientNumber) {
			this.socket = socket;
			this.clientNumber = clientNumber;
			System.out.println("New connection with client#" + clientNumber + "at" + socket);
		}

		/*
		 * Une thread se charge d'envoyer au client un message de bienvenue
		 */
		public void run() {
			try {
				// Création d'un canal sortant pour e nvoyer des messages au client
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());

				// Envoi d'un message au client
				out.writeUTF("Hello from server - you are client#" + clientNumber);

			} catch (IOException e) {
				System.out.println("Error handling client#" + clientNumber + ":" + e);
			} finally {
				try {
					// Fermeture de la connexion avec le client
					socket.close();
				} catch (IOException e) {
					System.out.println("Couldn't close a socket, what's going on?");
				}
				System.out.println("Connection with client#" + clientNumber + "closed");
			}
		}
	}
}
