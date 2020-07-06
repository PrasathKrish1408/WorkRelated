import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
public class URLConnection {
	static String baseUrl = "https://api.stripe.com/";
	static String secretKey = "sk_test_gl6VZrATnx5aL6bPR2DuZxyz";
	HttpsURLConnection connection ;
	public HttpsURLConnection getConnection(String endPoint) {
		try {
			URL urlForGetRequest = new URL(baseUrl+""+endPoint);
			connection = (HttpsURLConnection) urlForGetRequest.openConnection();
		    connection.setRequestProperty("Authorization", "Bearer "+secretKey);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	    return connection;
	}
	public String getResponseString() {
		String readLine = null;
		StringBuffer result = new StringBuffer();
		try {
			BufferedReader res = new BufferedReader(new InputStreamReader( connection.getInputStream()));
			while((readLine = res.readLine()) != null)
				result.append(readLine);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		finally {
			closeURLConnection();
		}
		return result.toString();
	}
	public void closeURLConnection() {
		try {
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public int getResponseCode() {
		int responseCode = 0;
		try {
			responseCode = connection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseCode;
	}
}
