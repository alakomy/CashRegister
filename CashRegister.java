package agnes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/*Cash Register program calculates number of bills in each denomination
*/
public class CashRegister {
	
	 static int[] moneyValue = {20, 10, 5, 2, 1};
	 static int[] moneyQty = new int[5];
	 int registerTotal = 0;
	 static int moneyLeft = 0;

	public static void main(String[] args) throws IOException {
		
		 BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
		 Scanner scanner = new Scanner (System.in);
		 
		System.out.print("Please enter number of bills for each denomination (#$20 #$10 #$5 #$2 #$1) : ");
		
		for(int i=0; i <5; i++) {
			moneyQty[i] = scanner.nextInt();
			
		}
		
		
		registerTotal(moneyValue, moneyQty);
		
		System.out.println("Enter action you would like to perform (put, take, change) in each denomination #$20 #$10 #$5 #$2 #$1");
		System.out.println(" -->");
		
		while(scanner.hasNext()) {		
			registerAction(scanner);
			
		}
		
	}
	
	
	
	/**
	 * registerAction performs calculation based on passed action (put, take or change)
	 * 
	 * @param scan
	 */
	private static void registerAction(Scanner scan){ 
   
		String action = scan.next();
		//if action is put
		if(action.contains("put")) { 
			int first = scan.nextInt();
			int second=scan.nextInt();
			int third=scan.nextInt();
			int fourth=scan.nextInt();
			int fifth = scan.nextInt();
			
			moneyQty[0] = moneyQty[0] + first;
			moneyQty[1] = moneyQty[1] + second;
			moneyQty[2] = moneyQty[2] + third;
			moneyQty[3] = moneyQty[3] + fourth;
			moneyQty[4] = moneyQty[4] + fifth;
			
			registerTotal(moneyValue, moneyQty);	
		}
		//if action is take
		if(action.contains("take")) { 
			int first = scan.nextInt();
			int second=scan.nextInt();
			int third=scan.nextInt();
			int fourth=scan.nextInt();
			int fifth = scan.nextInt();
			
			if((moneyQty[0] - first) > -1) {
				moneyQty[0] = moneyQty[0] - first;
			} else {
				exitMessageForNegativeNumber();	
			}
			if((moneyQty[1] - second) >-1) {
			moneyQty[1] = moneyQty[1] - second;
			} else {
				exitMessageForNegativeNumber();
			}
			if((moneyQty[2] - third) >-1) {
			moneyQty[2] = moneyQty[2] - third;
			} else {
				exitMessageForNegativeNumber();
			}
			
			if((moneyQty[3] - fourth)>-1) {
			moneyQty[3] = moneyQty[3] - fourth;
			} else {
				exitMessageForNegativeNumber();
			}
			
			if((moneyQty[4] - fifth)>-1) {
			moneyQty[4] = moneyQty[4] - fifth;
			} else {
				exitMessageForNegativeNumber();
			}
			registerTotal(moneyValue, moneyQty);
		}
		//if action is change
		if(action.contains("change")) { 
			
			int first = scan.nextInt();
			getChange(first);
			
		}	
	}
	

	private static void exitMessageForNegativeNumber(){
		System.out.println("Sorry, not enough bills");
		System.out.println("Bye");
		System.exit(0);
	}
	
	/**
	 * RegisterTotal 
	 * Calculates and prints out register totals
	 * @param passedMoneyValue
	 * @param passedMoneyQty
	 * @return
	 */
	private static int registerTotal(int[] passedMoneyValue, int[] passedMoneyQty) {
		
		int registerTotal = 0;
		
		for(int i=0; i <5; i++) {
			registerTotal += passedMoneyValue[i]*passedMoneyQty[i];
			
		}

		System.out.println("Total $" + registerTotal);
		System.out.print(passedMoneyQty[0] +" " );
		System.out.print(passedMoneyQty[1] +" ");
		System.out.print(passedMoneyQty[2] +" ");
		System.out.print(passedMoneyQty[3] +" ");
		System.out.print(passedMoneyQty[4] +" ");
		System.out.println(" ");
		return registerTotal;
		
	}
	
	
	
	 /**
	 * getChange 
	 * Calculates change number of bills needed to make a change
	 *
	 * @param change
	 */
	public static void getChange(int change) {

		 int j = 0;
		 int k = 0;
		 moneyLeft = 0 ;
		 
		 while(j <= 4) {
			 int currTotal = moneyValue[j];
			 int denominatioTotal = moneyQty[j];
			 int moneyper = currTotal * denominatioTotal;
			 moneyLeft += currTotal * denominatioTotal;
			 if((moneyper) > 0) {
				 
				 if (currTotal<=change) {
					 int modChange = change%currTotal;
					 
					 while(modChange != 1 && change >0 && moneyQty[j] >0 ) {
						 change = change- currTotal;
						 moneyper = moneyper-currTotal;

						 moneyQty[j] = moneyQty[j]-1;
						 modChange = change%currTotal;
						 
					 }  
					 if(modChange >0 && moneyper >0){
						 change = change - currTotal;
						 moneyper = moneyper-currTotal;
						 moneyQty[j] = moneyQty[j]-1;
									 
					 } 
				 } 
			 } 
			 
			 j++; 
			 
			 }
		 
		 if(change >0 ) {
			 System.out.println("Sorry no change can be given");
			 System.out.println("Bye");
			 System.exit(0);
		 }
		 
		 registerTotal(moneyValue, moneyQty);
			 
		 }
	
	
	
	

}
