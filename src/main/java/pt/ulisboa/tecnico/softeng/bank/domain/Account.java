package pt.ulisboa.tecnico.softeng.bank.domain;

public abstract class Account extends Account_Base {
    
    public Account(Bank bank, String id) {
        setBank(bank);
        setId(id);
        setBalance(0);
    }

    public void delete() {
        setBank(null);

        deleteDomainObject();
    }

    public abstract void withdraw(int d) throws Exception;

}
