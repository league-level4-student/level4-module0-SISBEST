package _03_Conways_Game_of_Life;

import java.applet.Applet;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SamuelsGameOfLife extends Applet implements ActionListener {
	JFrame f = new JFrame("Samuel's Game Of Life");
	JPanel p = new JPanel();
	JLabel title = new JLabel("Samuel's Game Of Life");
	JLabel splash = new JLabel();
	JButton play = new JButton("Start Game");
	JButton quit = new JButton("Quit Game");

	public static void main(String[] args) {
		SamuelsGameOfLife sgol = new SamuelsGameOfLife();
		sgol.gettoit();
	}

	void gettoit() {
		f.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setUndecorated(true);
		splash.setText(generateSplash());
		title.setFont(new Font("Sans", Font.BOLD, 40));
		p.add(title);
		p.add(splash);
		p.add(play);
		p.add(quit);
		f.add(p);
		play.addActionListener(this);
		quit.addActionListener(this);
		f.setSize(1919, 1080);
		f.setVisible(true);
		while (true) {
			try {
				Thread.sleep(1000);
				splash.setFont(new Font("Cursive", Font.ITALIC, 25));
				Thread.sleep(1000);
				splash.setFont(new Font("Cursive", Font.ITALIC, 20));

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	String generateSplash() {
		String splashtext = "Could not load splash! Seriously, Java?";
		Random r = new Random();
		int sn = r.nextInt(5);
		switch (sn) {
		case 0:
			splashtext = "100203020012929209292939392991919129119002 years old!";

		case 1:
			splashtext = "Now without stickman bob!";
			break;

		case 2:
			splashtext = "Someone's phone is charging now!";
			break;

		case 3:
			splashtext = "Accept the TOS!";
			break;

		case 4:
			splashtext = "stickman bob feels sad.";
			break;
		}
		return splashtext;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source.equals(play)) {
			playSGOL psgol = new playSGOL();
			f.setVisible(false);
		}
		if (source.equals(quit)) {
			System.exit(10);
		}
	}

	void relaunch() {
		playSGOL psgol = new playSGOL();
		f.setVisible(false);
	}
}