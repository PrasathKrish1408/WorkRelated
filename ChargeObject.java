
public class ChargeObject implements StripeObject{
	String id, currency, customer, description, payment_intent, status ;
	int amount, amount_refunded ;
	boolean disputed;
}	