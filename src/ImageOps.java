import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/*
 * Classe regroupant les methodes du server pour traiter les images
 */
public class ImageOps {
	public static BufferedImage readImageFromFile(String fileloc) throws IOException {
		BufferedImage img = ImageIO.read(new File(fileloc));
		return img;
	}

	public static void writeImageToFile(String fileName, BufferedImage image) {

		File out = new File(fileName + ".jpg");

		try {
			ImageIO.write(image, "jpg", out);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
