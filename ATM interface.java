import java.util.*;
import java.lang.*;

public class Interface{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);  //init Scanner
        Bank theBank = new Bank("State Bank Of India");  //init bank
        User aUser = (theBank).addUser("Aarsh","Dhokai","1111"); //add user ,which also creates a savings account
        Account newAccount = new Account("Checking",aUser,theBank); //add a checking account for our user 
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);
        User curUser;
        while (true){
            curUser = Interface.mainMenuPrompt(theBank,sc);  //stay in the login prompt until successful login
            Interface.printUserMenu(curUser,sc);  //stay in main menu until user quits
        }
    }

    public static User mainMenuPrompt(Bank theBank,Scanner sc){
        //init
        String userID;
        String pin;
        User authUser;
        //prompt the user for user id and pincombo until a correct one is reached

        do{
            System.out.println("\n\nWelcome to “+theBank.getName()+”\n\n");
            System.out.print("Enter User ID : ");
            userID = sc.nextLine();
            System.out.println();
            System.out.print("Enter Pin : ");
            pin = sc.nextLine();

            authUser = theBank.userLogin(userID,pin);  //try to get the user object corresponding to th ID and pin Combo 

            if(authUser == null){
                System.out.println("incorrect User ID/Pin, Please try again");
            }
        }while(authUser==null);//continue login until successful login
        return authUser;
    }

    public static void printUserMenu(User theUser,Scanner sc){
        theUser.printAccountSummary();  //print a summary of user’s account
        //init choice
        int choice;
        //user menu
        do{

            System.out.println("Welcome "+theUser.getFirstName()+" What would you like to do");
            System.out.println("1>Show Transaction History");
            System.out.println("2>withdraw");
            System.out.println("3>Deposite");
            System.out.println("4>Transfer");
            System.out.println("5>Quit");
            System.out.println();
            System.out.println("Enter Your Choice : ");
            choice = sc.nextInt();
            if(choice<1 || choice>5){
                System.out.println("Invalid Choice"+" Please choose between 1-5 ");  
            } 
        }while(choice<1 || choice>5);
        
        //process the choice
        switch(choice){

            case 1:
                Interface.showTransactionHistory(theUser,sc);
                break;

            case 2:
            	Interface.withdrawFunds(theUser,sc);
                break;

            case 3:
            	Interface.depositeFunds(theUser,sc);
                break;

            case 4:
            	Interface.transferFunds(theUser,sc);
                break;

            case 5:
                 //gobble up rest of previous input
                 sc.nextLine();
                 break;

        }

        //redisplay this menu unless user quit

        if(choice != 5){

        	Interface.printUserMenu(theUser,sc);

        }

    }

    private static void depositeFunds(User theUser, Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	private static void withdrawFunds(User theUser, Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	public static void showTransactionHistory(User theUser,Scanner sc){
        int theAct;
        //get account whose history to look at
        do{
            System.out.printf("Enter the number (1-%d) of the account\n"+" whose transaction you waant to see : ",theUser.numAccounts());
            theAct = sc.nextInt()-1;
            if(theAct < 0 || theAct >= theUser.numAccounts()){
                System.out.println("Invalid account. please try again…..");
            }
        }while(theAct < 0 || theAct >= theUser.numAccounts());
        //Print transaction history
        theUser.printActTransHistory(theAct);
    }

    public static void transferFunds(User theUser,Scanner sc){

        //intits

        int fromAct;
        int toAct;
        double amount;
        double actBal;

        //get the account to transfer from

        do{
            System.out.printf("Enter the number (1-%d) of the account\n"+"to transfer from:",theUser.numAccounts());
            fromAct = sc.nextInt()-1;
            if(fromAct < 0 || fromAct >= theUser.numAccounts()){
                System.out.println("Invalid account. please try again…..");
            }
        }while(fromAct < 0 || fromAct >= theUser.numAccounts());
        actBal = theUser.getAccountBalance(fromAct);  //get the account to transfer to 

        do{
            System.out.printf("Enter the number (1-%d) of the account\n"+"to transfer to:",theUser.numAccounts());
            toAct = sc.nextInt()-1;
            if(toAct < 0 || toAct >= theUser.numAccounts()){
                System.out.println("Invalid account. please try again…..");
            }
        }while(toAct < 0 || toAct >= theUser.numAccounts());   //get the amount to traansfer

        do{
            System.out.println("Enter the amount to transfer (max than $”+actBal+”) : $");
            amount = sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than zero");
            }else if(amount > actBal){
                System.out.println("Amount must not be greater than \n"+"balance of $"+actBal);
            }
        }while(amount < 0 || amount>actBal);  //do the transfer
    }
