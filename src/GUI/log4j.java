package GUI;
import org.apache.log4j.Logger;
import java.io.*;
import java.sql.SQLException;


public class log4j{
  /* Get actual class name to be printed on */
  static Logger log = Logger.getLogger(
		  log4j.class.getName());

  public static void main(String[] args)
                throws IOException,SQLException{
  }
  
  public void WriteLogDebug(String str){
	     log.debug(str);
  }
  public void UpdateLogFile(String str){
	  	 log.info(str);  
  }
}