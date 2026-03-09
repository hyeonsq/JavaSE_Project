package mylab.bank.control;

import mylab.bank.entity.*;
import mylab.bank.exception.*;

public class BankDemo {

    public static void main(String[] args) {
        Bank bank = new Bank();

        String acc1 = null;
        String acc2 = null;
        String acc3 = null;

        try {
            System.out.println("=== 계좌 생성 ===");

            acc1 = bank.createSavingsAccount("홍길동",10000,3.0);
            acc2 = bank.createCheckingAccount("김철수",20000,5000);
            acc3 = bank.createSavingsAccount("이영희",30000,2.0);

            bank.printAllAccounts();

            System.out.println("\n=== 입금/출금 테스트 ===");

            bank.deposit(acc1,5000);
            bank.withdraw(acc2,3000);

            System.out.println("\n=== 이자 적용 테스트 ===");

            SavingsAccount sa =
                    (SavingsAccount) bank.findAccount(acc1);
            sa.applyInterest();

            System.out.println("\n=== 계좌 이체 테스트 ===");

            bank.transfer(acc3,acc2,5000);

            bank.printAllAccounts();

        }
        catch(Exception e){
            System.out.println("예외 발생: " + e.getMessage());
        }

        try{
            bank.withdraw(acc2,6000);
        }
        catch(Exception e){
            System.out.println("예외 발생: " + e.getMessage());
        }

        try{
            bank.withdraw(acc2,7000);
        }
        catch(Exception e){
            System.out.println("예외 발생: " + e.getMessage());
        }

        try{
            bank.deposit("AC9999",1000);
        }
        catch(Exception e){
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}