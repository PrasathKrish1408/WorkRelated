
public interface StripeRetrivalTemplate {
	public void setConnectionAndRequestProperty();
	public void setConnectionAndRequestProperty(String chargeId);
	public StripeObject getResponseObject();
}
