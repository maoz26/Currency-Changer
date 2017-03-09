
package GUI;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "CURRENCIES")
@XmlAccessorType (XmlAccessType.FIELD)
public class Currencies {

	
    @XmlElement(name = "CURRENCY")
    private List<Currency> currencies = null;
    
    @XmlElement(name = "LAST_UPDATE")
    private String lastUpdate;
 
    public List<Currency> getCurrencies() {
        return currencies;
    }
 
    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
	
}


