package amrutha.SeleniumFrameaworkDesign;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJsonData 

{	
	public static void main(String[] args) throws IOException, ParseException {
  JSONParser j = new JSONParser();
  String path="C:\\Users\\Amrutha Perumal\\Desktop\\codeTry\\commonData.json";
  //FileInputStream s = new FileInputStream(path);
  FileReader fr = new FileReader(path);
  Object o = j.parse(fr);
  JSONObject jobj = (JSONObject)o;
  String url = (String) jobj.get("password"+ "");
  System.out.println(url);
  
  
	}

}
