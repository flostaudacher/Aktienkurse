import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
public class main {

	static ArrayList<LocalDate> dates = new ArrayList<>();
	static ArrayList<Double> closeValue = new ArrayList<>();
	static LocalDate day = LocalDate.now().minusMonths(1);
	
	
	static Scanner sc = new Scanner(System.in);
	  public static void main(String[] args) throws MalformedURLException, JSONException, IOException {
		  System.out.println("Welche Aktie möchten sie bekommen? \n"
		  		+ "tsla ... Tesla\n"
		  		+ "ADR ... American Depository Receipt.");
		  String aktie = sc.next();
		  test("aktie");
	     sc.close();
	    }   
	    public static void test(String aktie) throws MalformedURLException, JSONException, IOException {
	        
	        String url="https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=tsla&interval=5min&outputsize=full&apikey=VTRNJJSSMYW3MPD6";
	        JSONObject json = new JSONObject(IOUtils.toString(new URL(url), Charset.forName("UTF-8")));
	        json = json.getJSONObject("Time Series (Daily)");
	     
	        for (int i=0; i<100; i++) {
	            dates.add(LocalDate.parse((CharSequence) json.names().get(i)));
	            closeValue.add(json.getJSONObject(LocalDate.parse((CharSequence)json.names().get(i)).toString()).getDouble("4. close"));
	            System.out.println(closeValue.get(i));
	        }
	        
	       /*
	        *  while(day != LocalDate.now())
	        {
	        	closeValue.add(json.getJSONObject(day.toString()).getDouble("4. close"));
	        	day = day.plusDays(1);
	        }
	        
	        for(int i = 0 ; i < closeValue.size() ; i++)
	        {
	        	System.out.println(closeValue.get(i));
	        }
	        */
	        
	    }
	}