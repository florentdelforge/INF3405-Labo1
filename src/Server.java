import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static ServerSocket listener;

	public static void main(String[] args) throws Exception {
		// Compteur incremente
		int clientNumber = 0;

		// Adresse et port server
		String serverAddress = "127.0.0.1";
		int serverPort = 5000;

		// Creation de la connexion pour communiquer avec les clients
		listener = new ServerSocket();
		listener.setReuseAddress(true);
		InetAddress serverIP = InetAddress.getByName(serverAddress);

		// Association adresse et port a connection
		listener.bind(new InetSocketAddress(serverIP, serverPort));

		System.out.format("The server is running on %s:%d%n", serverAddress, serverPort);

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
}
