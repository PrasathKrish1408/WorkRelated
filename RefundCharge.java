import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ProtocolException;

import javax.net.ssl.HttpsURLConnection;
import com.google.gson.Gson;

public class RefundCharge implements StripeOperationTemplate {
	URLConnection urlCon;
	HttpsURLConnection con;
	public void setConnectionAndRequestProperty() {
		urlCon = new URLConnection();
	     try {
			con = urlCon.getConnection("/v1/refunds");
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
		ChargeObject charge_Object = null;
			try {
				String chargeId = "";
				do {
					System.out.println("Enter charge Id for Refund");
					chargeId = reader.readLine();
					RetriveCharge rc = new RetriveCharge();
					rc.setConnectionAndRequestProperty(chargeId);
					charge_Object = rc.getResponseObject();
					if(charge_Object == null) {
						System.out.println("Invalid Charge Id");
					}
					else {
						PrintOperations.print(charge_Object);
					}
				}while(charge_Object == null);
				String amount = "";
				int amnt = 0;
				do {
					System.out.println("Enter Refund amount");
					amount = reader.readLine();
					amnt = Integer.parseInt(amount);
					if(amnt > (charge_Object.amount - charge_Object.amount_refunded))
						System.out.println("Refund Amount Greater than available charge amount");
				} while (amnt > (charge_Object.amount - charge_Object.amount_refunded));
				String parameter = "amount="+amount+"&reason=requested_by_customer&charge="+chargeId;
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
	    	RefundObject refund_Object = gs.fromJson(urlCon.getResponseString(), RefundObject.class);
		    PrintOperations.print(refund_Object);
		    DatabaseOperations db = new DatabaseOperations();
			System.out.println("Rows Inserted : "+ db.insert(refund_Object));
			System.out.println("Closed...");
	    }
	    else
	    {
	    	System.out.println(responseCode);
	    	urlCon.closeURLConnection();
	    }
	}
}
