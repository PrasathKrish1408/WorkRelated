import java.io.IOException;
import java.io.OutputStream;
import java.net.ProtocolException;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

public class CreateCustomer implements StripeOperationTemplate {
	URLConnection urlCon;
	HttpsURLConnection con;
	public void setConnectionAndRequestProperty() {
		urlCon = new URLConnection();
	     try {
			con = urlCon.getConnection("/v1/customers");
			con.setRequestMethod("POST");	
		    con.setDoOutput(true);
		    con.setDoInput(true);
		    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		} 
	     catch (ProtocolException e) {
				e.printStackTrace();
			}
	     catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendRequest() {
		String parameter = "name=Prasath K&description=Test_Customer_Creation_002&email=prasathkrishvij03@gmail.com";
		OutputStream os;
			try {
				os = con.getOutputStream();
				os.write(parameter.getBytes());
			} catch (IOException | NullPointerException e) {
				e.printStackTrace();
			}
		    
	}
	public void getAndProcessResponse() {
		int responseCode = urlCon.getResponseCode();
		if(responseCode == HttpsURLConnection.HTTP_OK) {
	    	Gson gs = new Gson();
	    	CustomerObject customer_Object = gs.fromJson(urlCon.getResponseString(), CustomerObject.class);
		    PrintOperations.print(customer_Object);
		    DatabaseOperations db = new DatabaseOperations();
		    System.out.println("Rows Inserted : "+ db.insert(customer_Object));
			System.out.println("Closed...");
	    }
	    else
	    {
	    	System.out.println(responseCode);
	    	urlCon.closeURLConnection();
	    }
	}
}
