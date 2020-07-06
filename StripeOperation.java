public class StripeOperation {
	public static void main(String[] args) {
		StripeOperation operation = new StripeOperation();
		StripeOperationTemplate action = operation.getOpertaion("CreateCharge");
		action.setConnectionAndRequestProperty();
		action.sendRequest();
		action.getAndProcessResponse();
	}
	public StripeOperationTemplate getOpertaion(String Inp) {
		if(Inp.equals("CreateCharge"))
			return new CreateCharge();
		return new RetriveBalance();
	}

}
