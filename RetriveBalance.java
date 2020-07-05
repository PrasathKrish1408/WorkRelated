import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;
public class RetriveBalance {
	public static void main(String[] args) throws Exception {
	    URLConnection urlCon = new URLConnection();
	    HttpURLConnection con = urlCon.getConnection("/v1/balance");
	    con.setRequestMethod("GET");
	    int responseCode = con.getResponseCode();
	    if(responseCode == HttpsURLConnection.HTTP_OK) {
	    	System.out.println(urlCon.getResponseString());
	    }
	    else
	    {
	    	System.out.println(responseCode);
	    	urlCon.closeURLConnection();
	    }
	}
}
