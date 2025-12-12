import java.util.*;
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}

class BankAccount {
    private int accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(int accNo, String name, double balance) {
        this.accountNumber = accNo;
        this.accountHolderName = name;
        this.balance = balance;
    }

    private void increase(double amt) {
        balance += amt;
    }

    private void decrease(double amt) {
        balance -= amt;
    }

    public void deposit(double amt) {
        increase(amt);
        System.out.println("Deposited: " + amt);
    }

    public void withdraw(double amt) throws InsufficientBalanceException {
        if (balance < amt)
            throw new InsufficientBalanceException("Insufficient Balance");

        decrease(amt);
        System.out.println("Withdrawn: " + amt);
    }

    public void printAccountDetails() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Balance        : " + balance);
    }

    class SavingsAccount {
        public void withdraw(double amt) throws InsufficientBalanceException {
            if (balance < amt)
                throw new InsufficientBalanceException(
                        "SavingsAccount: Insufficient Balance");

            decrease(amt);
            System.out.println("Savings Withdraw: " + amt);
        }

        public void printDetails() {
            System.out.println("Account Type   : Savings");
            printAccountDetails();
        }
    }
}


public class Day1 {
    public static void main(String[] args) {

        BankAccount baseAccount =
                new BankAccount(101, "Punit", 50000);

        BankAccount.SavingsAccount savings =
                baseAccount.new SavingsAccount();

        try {
            baseAccount.deposit(10000);
            savings.withdraw(15000);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        savings.printDetails();
    }
}
