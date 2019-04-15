package pt.ulisboa.tecnico.softeng.bank.domain;

public class SavingsAccount extends Account {

    public SavingsAccount(Bank bank, String id) {
        super(bank, id);
    }

    public void deposit(int d) throws Exception {
        if (d%100 == 0){
            setBalance(getBalance() + d);
        }
        else{
            throw new Exception("Can't deposit " + d + " $, only multiples of 100 $");
        }
    }

    @Override
    public void withdraw(int d) throws Exception {
        if (getBalance() == d){
            setBalance(getBalance() - d);
        }
        else {
            throw new Exception("Can only withdraw total account balance ( " + getBalance() +" $)");
        }
    }

}
