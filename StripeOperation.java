public class StripeOperation {
	public static void main(String[] args) {
		StripeOperation operation = new StripeOperation();
		StripeOperationTemplate action = operation.getOpertaion("CreateCustomer");
		action.setConnectionAndRequestProperty();
		action.sendRequest();
		action.getAndProcessResponse();
	}
	public StripeOperationTemplate getOpertaion(String Inp) {
		StripeOperationTemplate action = null;
		switch (Inp) {
		case "CreateCharge":
			action= new CreateCharge();
			break;
		case "CreateCustomer":
			action= new CreateCustomer();
			break;
		default:
			action= new RetriveBalance();
			break;
		}
		return action;
	}

}
