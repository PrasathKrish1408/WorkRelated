import java.io.OutputStream;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.Gson;

public class CreateCharge {
	public static void main(String[] args) throws Exception {
	    URLConnection urlCon = new URLConnection();
	    HttpsURLConnection con = urlCon.getConnection("/v1/charges");
	    con.setRequestMethod("POST");
	    con.setDoOutput(true);
	    con.setDoInput(true);
	    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    String parameters = "amount=2200&currency=cad&source=tok_visa&description=Test_Charge_Creation_012";
	    OutputStream os = con.getOutputStream();
	    os.write(parameters.getBytes());
	    int responseCode = con.getResponseCode();
	    if(responseCode == HttpsURLConnection.HTTP_OK) {
	    	Gson gs = new Gson();
		    ChargeObject charge_Object = gs.fromJson(urlCon.getResponseString(), ChargeObject.class);
		    PrintOperations.print(charge_Object);
		    DatabaseOperations db = new DatabaseOperations();
			System.out.println("Rows Inserted : "+ db.insert(charge_Object));
			System.out.println("Closed...");
	    }
	    else
	    {
	    	System.out.println(responseCode+"Version 3");
	    	urlCon.closeURLConnection();
	    }
	}
}