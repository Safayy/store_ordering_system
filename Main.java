/******************************************************************************

Author: Safa Yousif
Date: 10/10/2019

This program is a store ordering system, implemented using the concept of 
inheritance and abstraction. 

*******************************************************************************/

//Application Program
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);

		String nameTempF, materialTypeTempF;
		int codeTempF, quantityTempF;
		double depositTempF, weightTempF, totalPayment=0;
		boolean deliveryTempF, isWet;
		
		//	1.1	asking for size of array
		System.out.print("Enter number of customers : ");
		int number=input.nextInt();
		
		LaundryService myWashing[]=new LaundryService[number];
		
		for(int i=0;i<number;i++) {
			System.out.print("\nPlease enter your name: ");
			nameTempF=input.next();
			System.out.print("Please enter amount of deposit: ");
			depositTempF=input.nextDouble();
			System.out.print("Please enter material of item(clothe, curtain or carpet): ");
			materialTypeTempF=input.next();
			System.out.print("Please enter weight of item(in kg): ");
			weightTempF=input.nextDouble();
			System.out.print("Would you like delivery(true/false)? :");
			deliveryTempF=input.nextBoolean();
			System.out.print("Please enter code: ");
			codeTempF=input.nextInt();
			System.out.print("Please enter quanity of item: ");
			quantityTempF=input.nextInt();
			System.out.print("Are you wet cleaning? (true/false) :");
			isWet=input.nextBoolean();
			
			
			
			
			if(isWet==true) {
				myWashing[i]=new WetCleaning(nameTempF, codeTempF, depositTempF, deliveryTempF, materialTypeTempF, weightTempF);
				
				if(	"curtain".equalsIgnoreCase(((WetCleaning)myWashing[i]).getMaterialType())) {
					totalPayment+=((WetCleaning)myWashing[i]).payment();
				}
			}
			else {
				myWashing[i]=new DryCleaning(nameTempF, codeTempF, depositTempF, deliveryTempF,quantityTempF);
			}
			
			
			
	}
		
		System.out.println("--------------------------------------RESULTS------------------------------------------\n");
		System.out.println("Total amount of payment for all curtains: " +totalPayment+"\n");
		
		System.out.println("Total amount of payment of all dry cleaning services(including delivery service charge):\n");
		
		System.out.println("CUSTOMER'S NAME\t\t\tTOTAL CHARGE(RM)");
		System.out.println("===============\t\t\t===============");
		for(int i=0;i<number;i++) {
			if(myWashing[i] instanceof DryCleaning) {
				System.out.print(myWashing[i].getName()+"\t\t\t\t");
				
				if(myWashing[i].getDelivery()) {
					System.out.println(myWashing[i].payment()+5);
				}
				else {
					System.out.println(myWashing[i].payment());
				}
			}
		
		}
	}
}






//Parent | LaundryService
abstract class LaundryService {
	private String name;
	private int code;
	private double deposit;
	private boolean delivery;
	
	//Constructor
	public LaundryService (String nameTemp, int codeTemp, double depositTemp, boolean deliveryTemp) {
	
		name=nameTemp;
		code=codeTemp;
		deposit=depositTemp;
		delivery=deliveryTemp;
		
	}
	
	//Accessors
	public String getName() {
		return name;
	}
	public boolean getDelivery() {
		return delivery;
	}
	public double getDeposit() {
		return deposit;
	}
	
	//Abstract Payment Processor 
	abstract double payment();
}







//First Child | WetCleaning
class WetCleaning extends LaundryService {
	private String materialType;
	private double weight;
	
	//Constructor
	public WetCleaning (String nameTemp1, int codeTemp1, double depositTemp1, boolean deliveryTemp1, String materialTypeTemp1, double weightTemp1) {
		super(nameTemp1, codeTemp1, depositTemp1, deliveryTemp1);
		materialType=materialTypeTemp1;
		weight=weightTemp1;		
	}
	
	//Accessor
	public String getMaterialType() {
		return materialType;
	}
	
	//Payment Processor
	public double payment() {
		materialType.toLowerCase();
		switch(materialType) {
		case "clothe":
			return (3.0*weight)-super.getDeposit();
		case "curtain":
			return (5.0*weight)-super.getDeposit();
		case "carpet":
			return (12.0*weight)-super.getDeposit();
		default:
			return 0.0;
		}
	}
}




//Second Child | DryCleaning
class DryCleaning extends LaundryService {
	private int quantity;
	
	//Constructor
	public DryCleaning (String nameTemp2, int codeTemp2, double depositTemp2, boolean deliveryTemp2, int quantityTemp2) {
		super(nameTemp2, codeTemp2, depositTemp2, deliveryTemp2);
		quantity=quantityTemp2;
		
	}
	
	//Payment Processor
	public double payment () {
		if(quantity>=10) {
			return ((25*quantity)*(1-0.25))-super.getDeposit();
		}
		else if(quantity>5) {
			return ((25*quantity)*(1-0.15))-super.getDeposit();
		}
		if(quantity>3) {
			return ((25*quantity)*(1-0.05))-super.getDeposit();
		}
		else {
			return (25*quantity)-super.getDeposit();
		}
	}
}

