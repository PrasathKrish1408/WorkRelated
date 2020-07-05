public class PrintOperations {
 
	public static void print(ChargeObject charge_Object) {
		System.out.println("Charge Details\nCharge ID : "+charge_Object.id+" Currency : "+charge_Object.currency +" Amount : "+charge_Object.amount
	    		+ "\nDescription : "+charge_Object.description +" Customer : "+charge_Object.customer +" Status : "+charge_Object.status
	    		+"\nPayment Intent : "+charge_Object.payment_intent +" Is Disputed : "+charge_Object.disputed
	    		);
	}
	public static void print(RefundObject refund_Object) {
		System.out.println("Refund Details\nRefund ID : "+refund_Object.id+" Charge ID : "+refund_Object.charge +"Refund Amount : "+refund_Object.amount
	    		+ "\nReason : "+refund_Object.reason +" Status : "+refund_Object.status
	    		);
	}
}
