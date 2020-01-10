import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class BaseDonnee {
	
	
/* readFromFile
 * INPUT : location d'un fichier txt a lire tel que chaque lien contient utilisateur motDePasse
 * OUTPUT : LinkedHashMap contenant l'information du fichier
 * */
	public Map<String,String> readFromFile(String fileLoc) {
		Map<String, String> users = new HashMap<String, String>();
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
	
	public void writeToFile(HashMap<String,String> users, String fileLoc) {
		try {
            File file = new File(fileLoc);

            
            file.createNewFile();
            // On ajoute les users depuis le debut du fichier, donc on overwrite? TODO: Verifier
            FileWriter fw = new FileWriter(fileLoc,false);
         
        	
        	for (String i : users.keySet()) {
        		 fw.write( i + " " + users.get(i));
        		 fw.write(System.lineSeparator());
        		}
        	
        	fw.close();
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
		
 
		
	}
}
