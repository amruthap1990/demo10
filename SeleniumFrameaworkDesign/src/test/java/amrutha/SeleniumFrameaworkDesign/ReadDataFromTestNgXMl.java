package amrutha.SeleniumFrameaworkDesign;

import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ReadDataFromTestNgXMl 
{	
	@Test
	public void sampleData(XmlTest r)
	{
		r.getParameter("Key");
	  System.out.println("Welcome");
	}

}
