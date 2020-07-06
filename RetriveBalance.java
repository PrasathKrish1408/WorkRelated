import java.io.IOException;
import java.net.ProtocolException;
import javax.net.ssl.HttpsURLConnection;
public class RetriveBalance implements StripeOperationTemplate{
	URLConnection urlCon;
	HttpsURLConnection con;
	public void setConnectionAndRequestProperty() {
		urlCon = new URLConnection();
	     try {
			con = urlCon.getConnection("/v1/balance");
		    con.setRequestMethod("GET");
		} 
	     catch (ProtocolException e) {
				e.printStackTrace();
			}
	     catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendRequest() {
		 
	}
	public void getAndProcessResponse() {
		try {
			 int responseCode = con.getResponseCode();
			    if(responseCode == HttpsURLConnection.HTTP_OK) {
			    	System.out.println(urlCon.getResponseString());
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
		    
	}
}
