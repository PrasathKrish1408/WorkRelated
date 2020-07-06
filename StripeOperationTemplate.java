
public interface StripeOperationTemplate {
	public void setConnectionAndRequestProperty();
	public void sendRequest();
	public void getAndProcessResponse();
}
