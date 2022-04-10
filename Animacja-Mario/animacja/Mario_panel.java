package animacja;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Klasa s³u¿¹ca rysowaniu Maria na panelu.
 * 
 * @author algie_000
 *
 */
public class Mario_panel extends JPanel implements Runnable {
	private volatile Image obrazki;
	private Image[] wymiana;
	private Thread t;
	/**
	 * Kierunek "k" mowi nam czy Mario idzie w lewo czy w prawo.
	 */
	private KIERUNEK k = KIERUNEK.PRAWO;
	private int pol = 0;
	private volatile Mario m = Mario.STOP;

	public Mario getM() {
		return m;
	}

	public void setM(Mario m) {
		this.m = m;
		if (m == Mario.MIEDZY) {
			obrazki = wymiana[1];

		} else {
			obrazki = wymiana[2];
		}
		repaint();
	}

	public Mario_panel(BufferedImage img) {
		int w = img.getWidth() / 3;
		int h = img.getHeight();
		wymiana = new Image[3];
		wymiana[0] = img.getSubimage(0, 0, w, h);
		wymiana[1] = img.getSubimage(w, 0, w, h);
		wymiana[2] = img.getSubimage(w * 2 + 8, 0, w - 8, h);
		obrazki = wymiana[0];
		t = new Thread(this);
		t.start();
		Zmiana_obrazka z = new Zmiana_obrazka(this);
		z.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		if (k == KIERUNEK.PRAWO) {
			g.drawImage(obrazki, pol, 0, null);
		} else {
			g.drawImage(obrazki, pol + obrazki.getWidth(null), 0, -obrazki.getWidth(null), obrazki.getHeight(null),
					null);

		}

	}

	@Override
	public void run() {

		while (true) {
			if (k == KIERUNEK.PRAWO) {
				pol += 30;
				if (((pol + 30) + obrazki.getWidth(null)) > getWidth()) {
					k = KIERUNEK.LEWO;

				}

			} else {
				pol -= 30;
				if ((pol - 30) < 0) {
					k = KIERUNEK.PRAWO;

				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			repaint();
		}

		// m=Mario.STOP;
		// repaint();
	}

	private enum KIERUNEK {
		PRAWO, LEWO

	}

}
