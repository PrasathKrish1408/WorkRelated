import java.io.IOException;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.sql.SQLException;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.Gson;

public class CreateCharge implements StripeOperationTemplate{
	URLConnection urlCon;
	HttpsURLConnection con;
	public void setConnectionAndRequestProperty() {
		urlCon = new URLConnection();
	     try {
			con = urlCon.getConnection("/v1/charges");
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
		 String parameters = "amount=2200&currency=cad&source=tok_visa&description=Test_Charge_Creation_012";
		    OutputStream os;
			try {
				os = con.getOutputStream();
				os.write(parameters.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		    
	}
	public void getAndProcessResponse() {
		DatabaseOperations db = null;
		try {
			int responseCode = con.getResponseCode();
			if(responseCode == HttpsURLConnection.HTTP_OK) {
		    	Gson gs = new Gson();
			    ChargeObject charge_Object = gs.fromJson(urlCon.getResponseString(), ChargeObject.class);
			    PrintOperations.print(charge_Object);
			    db = new DatabaseOperations();
			    System.out.println("Rows Inserted : "+ db.insert(charge_Object));
				System.out.println("Closed...");
		    }
		    else
		    {
		    	System.out.println(responseCode);
		    }
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
	    	try {
	    		urlCon.closeURLConnection();
				db.closeDbConnection();
			} catch (NullPointerException | SQLException e) {
			}
		}
		    
	}
}