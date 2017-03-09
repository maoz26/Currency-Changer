package GUI;

import javax.swing.SwingUtilities;


public class APP{
	
	public static log4j logger = new log4j();
	
	public static void main(String[] args) throws InterruptedException  {
		// TODO Auto-generated method stub
		
		logger.UpdateLogFile("Application is running..");
		
		// invokes singleton server and show the server frame
		RunServerFrame(); // 

		// invokes client and show the client frame 
		RunClientFrame("ClientA", 0);
		//RunClientFrame("ClientB", 1000); // OPTIONAL
		//RunClientFrame("ClientC", 2000);
	}

	// this function invokes server frame
	private static void RunClientFrame(String name, int time){
		try{
				final String clientName = name;
				Thread.sleep(time);
			    SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						new ClientFrame(clientName,"currency.xml");
					}
				});
			    Thread.sleep(time);
		}
		catch(Exception exc)
		{
		}
	}
	
	// this function invokes server frame 
	private static void RunServerFrame(){
		try{

			    SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						ServerFrame.GetInstance();
					}
				});
		}
		catch(Exception exc)
		{
		}
	}
}

