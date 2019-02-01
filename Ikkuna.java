import javax.swing.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;

public class Ikkuna extends JFrame {
	
	private JPanel nappulat;
	private DrawArea kuva;
	private JButton funktioButton;
	private JButton asetusButton;
	
	public Ikkuna() {
		
		setTitle("Funktiokuva");
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		rakennaRuutu();

		Container contentPane = getContentPane();
		contentPane.add(nappulat, BorderLayout.PAGE_START);
		contentPane.add(kuva, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private void rakennaRuutu() {
		
		funktioButton = new JButton("Funktio");
		asetusButton = new JButton("Asetukset");
		asetusButton.addActionListener( new NappulanKuuntelija());
		funktioButton.addActionListener( new NappulanKuuntelija());
		kuva = new DrawArea();
		nappulat = new JPanel();
		
		nappulat.setLayout(new BoxLayout(nappulat, BoxLayout.LINE_AXIS));
		nappulat.add(Box.createHorizontalGlue());
		nappulat.add(asetusButton);
		nappulat.add(Box.createRigidArea(new Dimension(10, 0)));
		nappulat.add(funktioButton);
	}
	
	private class NappulanKuuntelija implements ActionListener {
		
		private JTextField input;
		private JTextField input2;
		private JTextField input3;
		
		public NappulanKuuntelija(JTextField input) {
			this.input = input;
		}
		public NappulanKuuntelija(JTextField input, JTextField input2, JTextField input3) {
			this.input = input;
			this.input2 = input2;
			this.input3 = input3;
		}
		
		public NappulanKuuntelija() {
			input = null;
			input2 = null;
			input3 = null;
		}
		
		public void actionPerformed (ActionEvent e) {
			
			String tapahtuma = e.getActionCommand();
			if(tapahtuma.equals("Funktio")) {
				
				FunktioIkkuna f = new FunktioIkkuna();
			}
			
			if(tapahtuma.equals("Laske")) {
				
				int a = 0;
				String syote = new String();
					try {
						syote = input.getText();
						a = kuva.setFunktio(syote);
						if(a == 1) {
							return;
						} else {
							JOptionPane.showMessageDialog(null, "Ei onnistu, syota uusi funktio");
							return;
						}
						
					} catch (NullPointerException npe) {
						JOptionPane.showMessageDialog(null, "Virhe, yrita uudelleen");
						input.setText("");
						return;
					}
			}
			
			if(tapahtuma.equals("Asetukset")) {
				AsetusIkkuna a = new AsetusIkkuna();
			}
			
			if(tapahtuma.equals("Toteuta")) {
				
			}
		}
	}
	
	private class FunktioIkkuna extends JFrame {
		
		private JPanel ruutu;
		private JLabel viesti;
		private JTextField funktioKentta;
		private JButton syotaArvo;
		
		public FunktioIkkuna() {
			
			setTitle("Funktio");
			setSize(300, 200);
			setLocation(300, 300);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			viesti = new JLabel("Anna haluamasi funktio: ");
			funktioKentta = new JTextField(20);
			
			syotaArvo = new JButton("Laske");
			syotaArvo.addActionListener( new NappulanKuuntelija(funktioKentta));
			
			ruutu = new JPanel();
			
			ruutu.add(viesti);
			ruutu.add(funktioKentta);
			ruutu.add(syotaArvo);
			
			add(ruutu);
			
			setVisible(true);
		}
	}
	
	
	private class AsetusIkkuna extends JFrame {
		
		private JPanel ruutu;
		private JLabel vari;
		private JLabel rangeMin;
		private JLabel rangeMax;
		private JTextField variKentta;
		private JTextField rangeKenttaMin;
		private JTextField rangeKenttaMax;
		private JButton laskeTulos;

		
		public AsetusIkkuna() {
			
			setTitle("Asetukset");
			setSize(500, 500);
			setLocation(300, 300);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLayout(new GridLayout(0, 2));
			
			vari = new JLabel("Anna haluamasi vari: ");
			variKentta = new JTextField(10);
			variKentta.setText(Float.toString(kuva.getHue()));
			
			rangeMin = new JLabel("Anna minimiarvo: ");
			rangeKenttaMin = new JTextField(10);
			rangeKenttaMin.setText(Integer.toString(kuva.getRangeMin()));
			
			rangeMax = new JLabel("Anna maksimiarvo: ");
			rangeKenttaMax = new JTextField(10);
			rangeKenttaMax.setText(Float.toString(kuva.getRangeMax()));
			
			laskeTulos = new JButton("Toteuta");
			laskeTulos.addActionListener( new NappulanKuuntelija(variKentta, rangeKenttaMin, rangeKenttaMax));
			
			ruutu = new JPanel();
			
			ruutu.add(vari);
			ruutu.add(variKentta);
			ruutu.add(rangeMin);
			ruutu.add(rangeKenttaMin);
			ruutu.add(rangeMax);
			ruutu.add(rangeKenttaMax);
			ruutu.add(laskeTulos);
			add(ruutu);
			
			setVisible(true);
		}
	}
	
	
	
	
	
	
	
}