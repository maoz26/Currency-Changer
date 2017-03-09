
package GUI;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CURRENCY")
@XmlAccessorType (XmlAccessType.FIELD)
public class Currency {

	public  Currency()
	{}
	public  Currency(String name, int unit, String currencyCode, String country, Double rate, Double change){
		this.name = name;
		this.unit = unit;
		this.currencyCode = currencyCode;
		this.country = country;
		this.rate = rate;
		this.change = change;
	}
	
	
	@XmlElement (name = "NAME")
	String name;
	@XmlElement (name = "UNIT")
	int unit;
	@XmlElement (name = "CURRENCYCODE")
	String currencyCode;
	@XmlElement (name = "COUNTRY")
	String country;
	@XmlElement (name = "RATE")
	Double rate;
	@XmlElement (name = "CHANGE")
	Double change;
	
	String GetName()
	{
		return this.name;
	}
	int GetUnit()
	{
		return this.unit;
	}
	String GetCurrencyCode()
	{
		return this.currencyCode;
	}
	String GetCountry()
	{
		return this.country;
	}
	Double GetRate()
	{
		return this.rate;
	}
	Double GetChange()
	{
		return this.change;
	}
}
