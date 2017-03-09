package GUI;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Musheling {

	public static String xmlName = "src/gui";
	public String source;
	public static boolean mushTest = false;
	public Musheling(String src){
		source = src;
	}
	
	public Currencies  unMarshaling() throws JAXBException 
	{
		try
		{
	    JAXBContext jaxbContext = JAXBContext.newInstance(Currencies.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	     
	    //We had written this file in marshaling example
	    Currencies curs = (Currencies) jaxbUnmarshaller.unmarshal( new File(xmlName + "/"+ source) );
	    Currency cur = new Currency("Shekel", 1, "ILS", "Israel", Double.parseDouble("1"),Double.parseDouble("0"));
	    curs.getCurrencies().add(cur);
	    mushTest = true;
	    return curs;
		}
		catch(Exception exc)
		{
				  
		}
		return null;
	}
}
	    

	   

