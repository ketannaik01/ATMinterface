
import java.util.Scanner;

class BankAccount
{
  private double balance;
  private int accountNumber;
  private int pin;

  public BankAccount (int accountNumber, int pin, double initialBalance)
  {
    this.accountNumber = accountNumber;
    this.pin = pin;
    balance = initialBalance;
  }

  public int getAccountNumber ()
  {
    return accountNumber;
  }

  public double getBalance ()
  {
    return balance;
  }

  public boolean validatePin (int enteredPin)
  {
    return pin == enteredPin;
  }

  public void deposit (double amount)
  {
    if (amount > 0)
      {
	balance += amount;
	System.out.println ("Deposited $" + amount + " successfully.");
      }
    else
      {
	System.out.println ("Invalid deposit amount.");
      }
  }

  public void withdraw (double amount)
  {
    if (amount > 0 && amount <= balance)
      {
	balance -= amount;
	System.out.println ("Withdrawn $" + amount + " successfully.");
      }
    else
      {
	System.out.
	  println ("Invalid withdrawal amount or insufficient balance.");
      }
  }
}

class ATM
{
  private BankAccount account;

  public ATM (BankAccount account)
  {
    this.account = account;
  }

  public void displayMenu ()
  {
    System.out.println ("\nATM Menu:");
    System.out.println ("1. Check Balance");
    System.out.println ("2. Deposit");
    System.out.println ("3. Withdraw");
    System.out.println ("4. Exit");
  }

  public void start ()
  {
    Scanner scanner = new Scanner (System.in);
    boolean running = true;

    while (running)
      {
	displayMenu ();
	System.out.print ("Enter your PIN: ");
	int enteredPin = scanner.nextInt ();

	if (account.validatePin (enteredPin))
	  {
	    int choice;
	    do
	      {
		System.out.print ("\nEnter your choice: ");
		choice = scanner.nextInt ();

		switch (choice)
		  {
		  case 1:
		    System.out.println ("Your balance is $" +
					account.getBalance ());
		    break;
		  case 2:
		    System.out.print ("Enter the amount to deposit: $");
		    double depositAmount = scanner.nextDouble ();
		    account.deposit (depositAmount);
		    break;
		  case 3:
		    System.out.print ("Enter the amount to withdraw: $");
		    double withdrawAmount = scanner.nextDouble ();
		    account.withdraw (withdrawAmount);
		    break;
		  case 4:
		    System.out.
		      println ("Thank you for using the ATM. Goodbye!");
		    running = false;
		    break;
		  default:
		    System.out.
		      println
		      ("Invalid choice. Please select a valid option.");
		  }
	      }
	    while (choice != 4);
	  }
	else
	  {
	    System.out.println ("Invalid PIN. Please try again.");
	  }
      }

    scanner.close ();
  }
}

public class ATMSystem
{
  public static void main (String[]args)
  {
    BankAccount userAccount = new BankAccount (123456, 1234, 1000.0);	// Account number, PIN, and initial balance
    ATM atm = new ATM (userAccount);
      atm.start ();
  }
}
