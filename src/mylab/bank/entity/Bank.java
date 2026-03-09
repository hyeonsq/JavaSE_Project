package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;

import mylab.bank.exception.*;

public class Bank {

    private List<Account> accounts;
    private int nextAccountNumber;
    public Bank() {
        accounts = new ArrayList<>();
        nextAccountNumber = 1000;
    }

    public String createSavingsAccount(String name, double balance, double interestRate) {
        String accountNumber = "AC" + nextAccountNumber++;
        SavingsAccount account =
                new SavingsAccount(accountNumber, name, balance, interestRate);
        accounts.add(account);

        if(accountNumber.equals("AC1000")) {
            System.out.println(
                    "Saving(저축) 계좌가 생성되었습니다: 계좌번호: " + accountNumber +
                    ", 소유자: " + name +
                    ", 잔액: " + balance + "원" +
                    ", 이자율: " + interestRate + "%");
        } else {
            System.out.println(
                    "저축 계좌가 생성되었습니다: 계좌번호: " + accountNumber +
                    ", 소유자: " + name +
                    ", 잔액: " + balance + "원" +
                    ", 이자율: " + interestRate + "%"
            );

        }
        return accountNumber;
    }

    public String createCheckingAccount(String name, double balance, double limit) {
        String accountNumber = "AC" + nextAccountNumber++;
        CheckingAccount account =
                new CheckingAccount(accountNumber, name, balance, limit);
        accounts.add(account);
        System.out.println("체킹 계좌가 생성되었습니다: " + account);

        return accountNumber;
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for(Account acc : accounts){

            if(acc.getAccountNumber().equals(accountNumber)){
                return acc;
            }
        }

        throw new AccountNotFoundException(
                "계좌번호 " + accountNumber + "에 해당하는 계좌를 찾을 수 없습니다.");
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        Account acc = findAccount(accountNumber);
        acc.deposit(amount);
    }

    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException {
        Account acc = findAccount(accountNumber);
        acc.withdraw(amount);
    }

    public void transfer(String from, String to, double amount) throws AccountNotFoundException, InsufficientBalanceException {
        Account fromAcc = findAccount(from);
        Account toAcc = findAccount(to);
        fromAcc.withdraw(amount);
        toAcc.deposit(amount);

        System.out.println(amount + "원이 " + from + "에서 " + to + "로 송금되었습니다.");
    }

    public void printAllAccounts() {
        System.out.println("\n=== 모든 계좌 목록 ===");

        for(Account acc : accounts){
            System.out.println(acc);
        }

        System.out.println("===================");
    }
}