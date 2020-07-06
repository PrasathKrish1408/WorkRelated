

import com.google.gson.Gson;

public class TestProgram {
public static void main(String[] args)
{
Gson gs = new Gson();
CustomerObject customer_Object = new CustomerObject();
customer_Object.address = new Address();
customer_Object.address.line1 = "13th Street";
customer_Object.address.city = "New york";
customer_Object.address.country ="US";
customer_Object.name = "Robert";
customer_Object.description = "Test_Customer_Creation_001";
System.out.println(gs.toJson(customer_Object).toString());
    
}
}
