package animacja;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame ramka = new JFrame();
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("Mario_bieg.png"));
		} catch (IOException e) {
			System.out.println("Nie ma obrazka.");
		}
		Mario_panel m = new Mario_panel(img);
		m.setPreferredSize(new Dimension(img.getWidth() * 2, img.getHeight()));
		ramka.add(m);

		ramka.pack();
		ramka.setLocationRelativeTo(null);
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramka.setVisible(true);
		ramka.setResizable(false);

	}

}
