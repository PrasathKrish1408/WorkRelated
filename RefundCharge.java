import java.io.OutputStream;

import javax.net.ssl.HttpsURLConnection;
import com.google.gson.Gson;

public class RefundCharge {
	public static void main(String[] args) throws Exception {
	    URLConnection urlCon = new URLConnection();
	    HttpsURLConnection con = urlCon.getConnection("/v1/refunds");
	    con.setRequestMethod("POST");
	    con.setDoOutput(true);
	    con.setDoInput(true);
	    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    String parameters = "amount=100&reason=requested_by_customer&charge=ch_1H081a4RvqsL3Td9NaUcOvsx";
	    OutputStream os = con.getOutputStream();
	    os.write(parameters.getBytes());
	    int responseCode = con.getResponseCode();
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
