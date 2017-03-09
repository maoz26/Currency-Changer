package GUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;

public class ClientFrame extends JFrame{
	
	// Local variables
	Double result;
	Currencies currenciesList =  null;
	private static final long serialVersionUID = 1L;
	log4j logFile = new log4j();
	String source;
	
	// Frame elements
	JButton btnSwitch;
	JComboBox comboCurrenciesR;
	JComboBox comboCurrenciesL;
	JLabel label;
	JTextField textField;	
	JButton btnConvert;
	JLabel labelFromDesc;
	JLabel labelToDesc;
	String[] comboTypes;
	String[] comboTypes2;
	
	public ClientFrame(String title, String src)
	{
		super(title);

		// Set settings
		this.setSize(500, 190);
		this.setVisible(true);
		this.source = src;
		
		// Call necessary functions
		UpdateCurencies(source);
		createAndShowGUI();
		ServerListenerTread();
	}
	
	// Runs thread listening the server changes
	 private  void ServerListenerTread() {
		 Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(true)
					{
						try {
							Thread.sleep(1000);
							String newSource = ServerFrame.updates;;
							if (newSource != source)
							{
								source = newSource;
								UpdateCurencies(source);
								Convert();
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			t1.start();
	 }
	
	 // Define GUI and show it
	 void createAndShowGUI() {
			// Components define
			btnSwitch = new JButton("Switch!");
			comboCurrenciesR = new JComboBox(comboTypes);
		    comboCurrenciesL = new JComboBox(comboTypes2);
			label = new JLabel("        Result");
			textField= new JTextField(28);		
			btnConvert = new JButton("GO!");
			labelFromDesc = new JLabel("Convert:");
			labelToDesc = new JLabel("To:");
			// Components design
			comboCurrenciesR.setSelectedIndex((currenciesList.getCurrencies().size()-1));
			comboCurrenciesL.setSelectedIndex(0);
			int fontBig = 20;
			int fontMedium = 15;
			int fontSmall = 12;
			label.setFont(new Font("result", Font.PLAIN, fontBig));
			labelFromDesc.setFont(new Font("result", Font.PLAIN, fontMedium));
			labelToDesc.setFont(new Font("result", Font.PLAIN, fontMedium));
			comboCurrenciesR.setFont(new Font("result", Font.PLAIN, fontSmall));
			comboCurrenciesL.setFont(new Font("result", Font.PLAIN, fontSmall));
			// Components behavior
			comboCurrenciesL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox jcmbType2 = (JComboBox) e.getSource();
					String cmbType2 = (String) jcmbType2.getSelectedItem();
					System.out.println(cmbType2);
					logFile.UpdateLogFile("Left combobox pressed.");
				}
			});
			comboCurrenciesR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox jcmbType = (JComboBox) e.getSource();
					String cmbType = (String) jcmbType.getSelectedItem();
					System.out.println(cmbType);
					logFile.UpdateLogFile("Right combobox pressed.");
				}
			});
			btnSwitch.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int index = comboCurrenciesR.getSelectedIndex();
					int index2 = comboCurrenciesL.getSelectedIndex();
					comboCurrenciesR.setSelectedIndex(index2);
					comboCurrenciesL.setSelectedIndex(index);
					logFile.UpdateLogFile("Switch button pressed.");
				}
			});
			btnConvert.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					logFile.UpdateLogFile("Convert Button pressed.");
					try{
					// TODO Auto-generated method stub
					Convert();
					}
					catch(Exception exc)
					{
						label.setText("Error, Invalid input.");
						logFile.UpdateLogFile("Error, Invalid input.");
					}	
				}
			});
			// Define and build panels
			JPanel panelMain = new JPanel();
			panelMain.setLayout(new BorderLayout());
			JPanel panelNorth = new JPanel();
			panelNorth.setLayout(new FlowLayout());
			JPanel panelCenter = new JPanel();
			JPanel panelSouth = new JPanel();
			panelSouth.setLayout(new FlowLayout());
			panelCenter.add(labelFromDesc);
			panelCenter.add(comboCurrenciesR);
			panelCenter.add(labelToDesc);
			panelCenter.add(comboCurrenciesL);
			panelCenter.add(btnSwitch);
			panelNorth.add(textField);
			panelNorth.add(btnConvert);
			panelSouth.add(label);
			panelMain.add(panelNorth, BorderLayout.NORTH);
			panelMain.add(panelCenter, BorderLayout.CENTER);
			panelMain.add(panelSouth, BorderLayout.SOUTH);
			this.add(panelMain);
	 }
	
	// math function of rounding double
	 private static double roundDouble(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

	// converts the inputs to result
	 private void Convert(){
		try{
			int unit1 = currenciesList.getCurrencies().get(comboCurrenciesR.getSelectedIndex()).GetUnit();
			int unit2 = currenciesList.getCurrencies().get(comboCurrenciesL.getSelectedIndex()).GetUnit();
			Double rate1 = currenciesList.getCurrencies().get(comboCurrenciesR.getSelectedIndex()).GetRate();
			Double rate2 = currenciesList.getCurrencies().get(comboCurrenciesL.getSelectedIndex()).GetRate();
			Double relation1 = rate1/unit1;
			Double relation2 = rate2/unit2;
			Double input = Double.parseDouble(textField.getText());
			result = relation1/relation2;
			Double resultEnd = roundDouble(result*input,3);
			String message = textField.getText() + " " + currenciesList.getCurrencies().get(comboCurrenciesR.getSelectedIndex()).GetName() +"s equals to "+ resultEnd.toString() +" " + currenciesList.getCurrencies().get(comboCurrenciesL.getSelectedIndex()).GetName()+"s";
			label.setText(message);
			logFile.UpdateLogFile("converting of: "+ message);
		}
		catch(Exception exc)
		{
			logFile.UpdateLogFile("converting error, the filed is empty");
		}

	}
	
	// Updates the currencies from source
	 private void UpdateCurencies(String source){
	// Read XML to list
			Musheling musheling = new Musheling(source);
		    try {
				 currenciesList = musheling.unMarshaling();
					// Load Currencies to ComboBox
					int size = currenciesList.getCurrencies().size(); 
					String[] tempArrayStrings = new String[size];
					int counter = 0; 
					for (Currency currency : currenciesList.getCurrencies()) {
						tempArrayStrings[counter++] = currency.GetName() + " [" +currency.GetCurrencyCode()+"]";
					}
					comboTypes = tempArrayStrings;
					comboTypes2 = tempArrayStrings;
			} catch (JAXBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
}
