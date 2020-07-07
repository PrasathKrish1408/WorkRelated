import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ProtocolException;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

public class RetriveCharge implements StripeRetrivalTemplate{
	URLConnection urlCon;
	HttpsURLConnection con;
	@Override
	public void setConnectionAndRequestProperty() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		urlCon = new URLConnection();
	     try {
	    	System.out.println("Enter charge Id");
	 		String chargeId = reader.readLine();
			con = urlCon.getConnection("/v1/charges/"+chargeId);
		    con.setRequestMethod("GET");
		} 
	     catch (ProtocolException e) {
				e.printStackTrace();
			}
	     catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void setConnectionAndRequestProperty(String chargeId) {
		urlCon = new URLConnection();
	     try {
			con = urlCon.getConnection("/v1/charges/"+chargeId);
		    con.setRequestMethod("GET");
		} 
	     catch (ProtocolException e) {
				e.printStackTrace();
			}
	     catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public ChargeObject getResponseObject() {
		ChargeObject charge_Object = null;
		try {
			 int responseCode = con.getResponseCode();
			    if(responseCode == HttpsURLConnection.HTTP_OK) {
			    	Gson gs = new Gson();
			    	charge_Object = gs.fromJson(urlCon.getResponseString(), ChargeObject.class);
			    }
			    else
			    {
			    	System.out.println(responseCode);
			    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
	    	try {
	    		urlCon.closeURLConnection();
			} catch (NullPointerException e) {
			}
		}
		return charge_Object;
	}
}
