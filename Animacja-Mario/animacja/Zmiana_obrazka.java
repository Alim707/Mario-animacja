package animacja;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Zmiana_obrazka extends Thread {
	private Mario_panel m;

	public Zmiana_obrazka(Mario_panel m) {
		this.m = m;

	}

	@Override
	public void run() {
		while (true) {
			if (m.getM() == Mario.MIEDZY) {
				m.setM(Mario.KROK);

			} else {
				m.setM(Mario.MIEDZY);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
