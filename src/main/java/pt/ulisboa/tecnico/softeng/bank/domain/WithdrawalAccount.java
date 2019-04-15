package pt.ulisboa.tecnico.softeng.bank.domain;

public class WithdrawalAccount extends Account {

    public WithdrawalAccount(Bank bank, String id, int money) {
        super(bank, id);
        setBalance(money);
    }

    @Override
    public void withdraw(int d) {
        setBalance(getBalance() - d);
    }

}
