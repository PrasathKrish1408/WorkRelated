import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		OutputStream os;
			try {
				System.out.println("Enter Customer name");
				String name = reader.readLine();
				System.out.println("Enter Customer email");
				String email = reader.readLine();
				System.out.println("Enter description about Customer");
				String description = reader.readLine();
				String parameter = "name="+name+"&description="+description+"&email="+email;
				os = con.getOutputStream();
				os.write(parameter.getBytes());
			} catch (IOException | NullPointerException e) {
				e.printStackTrace();
			}
		    finally {
		    	try {
					reader.close();
				} catch (IOException e) {
				}
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
