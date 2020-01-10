import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BaseDonnee {
	
	
/* readFromFile
 * INPUT : location d'un fichier txt a lire tel que chaque lien contient utilisateur motDePasse
 * OUTPUT : LinkedHashMap contenant l'information du fichier
 * */
	public Map<String,String> readFromFile(String fileLoc) {
		Map<String, String> users = new LinkedHashMap<String, String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileLoc));

			String line;
			String[] values;

			while (((line = in.readLine()) != null)) {
				line = in.readLine();
				values = line.trim().split("\\s+");
				
				String Username = values[0];
				String Password = values[1];

				users.put(Username, Password);
			}
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}
}
