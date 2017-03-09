package GUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ServerFrame extends JFrame {

	log4j logFile = new log4j();
	static String updates = "currency.xml"; 
	private static final long serialVersionUID = 1L;
	private static ServerFrame instance;
	
	private ServerFrame(String title)
	{
		super(title);
		 if (instance == null){
			// Set settings
			 this.setSize(300, 100);
			 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 this.setVisible(true);
		
			String[] sources = {"currency.xml","currency2.xml", "currency3.xml"};
	
			final JComboBox comboSources = new JComboBox(sources);
	
			// Define style of components
			comboSources.setSelectedIndex(0);
			int fontSmall = 12;
			comboSources.setFont(new Font("result", Font.PLAIN, fontSmall));
	
			
			// Define behavior of components
			comboSources.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox jcmbType = (JComboBox) e.getSource();
					String cmbType = (String) jcmbType.getSelectedItem();
					updates = cmbType;
					logFile.UpdateLogFile("Left combobox pressed.");
				}
			});
			
			// Define and build panels
			JPanel panelMain = new JPanel();
			panelMain.setLayout(new BorderLayout());
			JPanel panelNorth = new JPanel();
			panelNorth.setLayout(new FlowLayout());
			panelNorth.add(comboSources);
			panelMain.add(panelNorth, BorderLayout.NORTH);
			this.add(panelMain);
		}
		 else
		 {
			 
		 }
	}
	public static  ServerFrame GetInstance(){
		return  new ServerFrame("Server On");
	}

}
