import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
public class URLConnection {
	static String baseUrl = "https://api.stripe.com/";
	static String secretKey = "sk_test_gl6VZrATnx5aL6bPR2DuZxyz";
	HttpsURLConnection connection ;
	public HttpsURLConnection getConnection(String endPoint) throws Exception  {
		URL urlForGetRequest = new URL(baseUrl+""+endPoint);
	    connection = (HttpsURLConnection) urlForGetRequest.openConnection();
	    /*For Basic Authentication
	    String basicAuth = "Basic "+Base64.getEncoder().encodeToString(("sk_test_gl6VZrATnx5aL6bPR2DuZxyz:").getBytes());
	    con.setRequestProperty("Authorization", basicAuth);*/
	 //For Bearer Authentication
	    connection.setRequestProperty("Authorization", "Bearer "+secretKey);
	    return connection;
	}
	public String getResponseString() throws IOException {
		String readLine = null;
		StringBuffer result = new StringBuffer();
		BufferedReader res = new BufferedReader(new InputStreamReader( connection.getInputStream()));
    	while((readLine = res.readLine()) != null)
    	result.append(readLine);
    	connection.disconnect();
		return result.toString();
	}
	public void closeURLConnection() {
		connection.disconnect();
	}
}
