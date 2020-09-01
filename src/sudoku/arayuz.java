package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class arayuz implements ActionListener{
	
	 JLabel saniye;
	 int[] tablo;
	 JTextField[] tabloGirdi;
	//static double gecenSure;
	 JButton hesapla;
	 JButton sifirla;
	
	public arayuz()
	{
		ciz();
	}
	
	private void ciz()
	{
		JFrame frame = new JFrame("Sudoku Çözücü");
		GridBagLayout arayuz = new GridBagLayout();
		frame.setLayout(arayuz);
		JPanel tablolar = new JPanel();
		tablolar.setLayout(new GridLayout(9,9));
		tablolar.setMinimumSize(new Dimension(580, 400));
		tablolar.setMaximumSize(new Dimension(580, 400));
		tablolar.setPreferredSize(new Dimension(580, 400));
		
		tabloGirdi = new JTextField[81];
		tablo = new int[81];
		
		GridBagConstraints gridb = new GridBagConstraints();
		
		
		gridb.fill = GridBagConstraints.HORIZONTAL;
		gridb.gridx=0;
		gridb.gridy=0;
		gridb.gridwidth = 3;
		
		frame.add(tablolar,gridb);
		
		
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		for(int i = 0 ; i < 81 ; i ++)
		{
			tabloGirdi[i] = new JTextField();
			tabloGirdi[i].setPreferredSize(new Dimension(500,500));
			//tabloGirdi[i].setMinimumSize(new Dimension(100, 40));
			//tabloGirdi[i].setBounds(10, 10, 100, 40);
			tabloGirdi[i].setColumns(1);
			//tabloGirdi[i].setText("0");
			tabloGirdi[i].setFont(font1);
			tabloGirdi[i].setHorizontalAlignment(JTextField.CENTER);
			tabloGirdi[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
			//panel[panelSayac].add(tabloGirdi[i]);
			tablolar.add(tabloGirdi[i]);
		}
		
		
		hesapla = new JButton("Çöz");
		hesapla.addActionListener(this);
		//hesapla.setSize(300, 200);
		hesapla.setMinimumSize(new Dimension(100, 50));
		hesapla.setMaximumSize(new Dimension(100, 50));
		hesapla.setPreferredSize(new Dimension(100, 50));
		
		gridb.fill = GridBagConstraints.HORIZONTAL;
		gridb.gridwidth = 2;
		gridb.gridx=0;
		gridb.gridy=2;
		gridb.anchor = GridBagConstraints.PAGE_START;
		gridb.insets = new Insets(10,0,0,0);
		gridb.weightx = 2;
		
		frame.add(hesapla,gridb);
		
		sifirla = new JButton("Sýfýrla");
		//hesapla.setSize(300, 200);
		sifirla.addActionListener(this);
		sifirla.setMinimumSize(new Dimension(100, 50));
		sifirla.setMaximumSize(new Dimension(100, 50));
		sifirla.setPreferredSize(new Dimension(100, 50));
		
		gridb.fill = GridBagConstraints.HORIZONTAL;
		gridb.gridwidth = 1;
		gridb.gridx=2;
		gridb.gridy=2;
		gridb.anchor = GridBagConstraints.PAGE_END;
		gridb.insets = new Insets(10,0,0,0);
		gridb.weightx = 1;
		
		frame.add(sifirla,gridb);
		
		saniye = new JLabel();
		gridb.fill = GridBagConstraints.HORIZONTAL;
		gridb.gridx=2;
		gridb.gridy=3;
		gridb.anchor = GridBagConstraints.PAGE_START;
		gridb.insets = new Insets(10, 10, 0, 0);
		gridb.gridwidth = 1;
		gridb.weightx = 0;
		
		frame.add(saniye,gridb);
		
		JLabel imza = new JLabel("Barýþcan Güngör yapýmýdýr...");
		//imza.setSize(100, 10);
		imza.setVisible(true);

		gridb.fill = GridBagConstraints.HORIZONTAL;
		gridb.gridx=0;
		gridb.gridy=3;
		gridb.anchor = GridBagConstraints.PAGE_END;
		gridb.gridwidth = 1;
		gridb.weightx = 0;
		
		frame.add(imza,gridb);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		frame.setBounds((kit.getScreenSize().width-600)/2, (kit.getScreenSize().height-600)/2, 600, 540);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() ==hesapla)
		{
		long baslangic = System.currentTimeMillis();
		cikisZamanlayicisi kacis = new cikisZamanlayicisi();
		kacis.baslat();
		for(int i = 0 ; i < 81 ; i ++)
		{

			if(!tabloGirdi[i].getText().isEmpty())
			{
				tabloGirdi[i].setBackground(Color.lightGray);
				int girdi = Integer.parseInt(tabloGirdi[i].getText());
				tablo[i] = girdi;
			}
			else
			{
				tablo[i]=0;
			}
		}
		sudokuCozucu cozucu = new sudokuCozucu(tablo);
		tablo = cozucu.coz();
		kacis.timertask.cancel();
		
		for(int i = 0 ; i < 81 ; i++)
		{
			tabloGirdi[i].setText(""+tablo[i]);
		}
		
		long son = System.currentTimeMillis();
		double gecenSure = (double)(son-baslangic)/1000;
		saniye.setText("Çözülen süre : " +gecenSure);
		}
		
		if(e.getSource() == sifirla)
		{
			for(int i = 0 ; i < 81 ; i ++)
			{
					tabloGirdi[i].setText("");
					tabloGirdi[i].setBackground(Color.WHITE);
			}
			saniye.setText("");
		}
		
	}
	

}
