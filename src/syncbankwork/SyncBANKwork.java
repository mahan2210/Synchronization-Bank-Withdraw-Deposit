/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syncbankwork;

import java.util.Scanner;

class Account{
    public int balance;
    public int accountNo;
    void displayBalance(){
        System.out.println("Account No: " + accountNo +" Balance: "+balance);
    }
    synchronized void withdraw(int amount){
        if (this.balance< amount) {
            System.out.println("Less balance, deposit more...");
            //System.exit(0);
            try {
                wait();
            } catch (Exception e) {
            }
        }
        
        this.balance = balance - amount;
        System.out.println(amount + " is withdrawn");
        displayBalance();
        System.exit(0);
        
    }
    synchronized void deposit(int amount){
        //notify();
        this.balance = balance + amount;
        System.out.println(amount + " is deposited");
        displayBalance();
        notify();
    }
    
          
}
class TransactionWithdraw implements Runnable{
    int amount;
    Account accountY;
    TransactionWithdraw(Account y, int amount){
        accountY= y;
        this.amount = amount;
       new Thread(this).start();/*TransactionWithdraw a = new TransactionWithdraw();
       Thread t1 = new Thread(a);
       t1.start();
       */
         
         
    }
    @Override
    public void run(){
        accountY.withdraw(amount);
    }
}
class TransactionDeposit implements Runnable{
    int amount;
    Account accountX;
    TransactionDeposit(Account x, int amount){
        accountX = x;
        this.amount = amount;
        new Thread(this).start();
        
    }
    @Override
    public void run(){
        accountX.deposit(amount);
    }
}

public class SyncBANKwork {

    public static void main(String[] args) {
        Account acc = new Account();
        acc.balance = 1000;
        acc.accountNo = 2210;
        
        TransactionWithdraw t2;
        TransactionDeposit t1;
        System.out.println("Enter the amount for withdrawing \n");
        
        Scanner obj1 = new Scanner(System.in);
         int withdraw = obj1.nextInt();
        t2= new  TransactionWithdraw(acc,withdraw);  
        
       // System.out.println("Enter the amount for depositing \n");
       int deposit = obj1.nextInt();
        t1= new TransactionDeposit(acc,deposit);
        
        
        
    }
    
}
