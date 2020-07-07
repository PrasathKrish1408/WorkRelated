import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StripeOperation {
	public static void main(String[] args) {
		StripeOperation operation = new StripeOperation();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			StripeOperationTemplate action = null;
			do {
				System.out.println("Choose one of the below Operations\n1.Create Charge\n2.Refund Charge\n3.Create Customer");
				int choice = Integer.parseInt(reader.readLine());
				 action = operation.getOpertaion(choice);
			} while (action == null);
			action.setConnectionAndRequestProperty();
			action.sendRequest();
			action.getAndProcessResponse();
		} catch (NullPointerException | NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	    finally {
	    	try {
				reader.close();
			} catch (IOException e) {
			}
	    }
	}
	public StripeOperationTemplate getOpertaion(int choice) {
		StripeOperationTemplate action = null;
		switch (choice) {
		case 1:
			action= new CreateCharge();
			break;
		case 2:
			action= new RefundCharge();
			break;
		case 3:
			action= new CreateCustomer();
			break;
		default:
			System.out.println("Invalid Choice. ");
			break;
		}
		return action;
	}

}
