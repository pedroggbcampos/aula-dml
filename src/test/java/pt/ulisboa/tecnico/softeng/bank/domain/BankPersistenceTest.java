package pt.ulisboa.tecnico.softeng.bank.domain;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;

public class BankPersistenceTest {

	WithdrawalAccount account1;
	SavingsAccount account2;
	Bank bank;

	@Test
	public void success() {
		atomicProcess();
		atomicAssert();
	}

	@Atomic(mode = TxMode.WRITE)
	public void atomicProcess() {
		try{

			bank = new Bank("Money", "BK01");
			account1 = new WithdrawalAccount(bank, "A125", 100);
			account1.withdraw(10);
			account2 = new SavingsAccount(bank, "A126");
			account2.deposit(100);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Atomic(mode = TxMode.READ)
	public void atomicAssert() {
		bank = Bank.getBankByCode("BK01");

		assertEquals("Money", bank.getName());

		assertEquals(bank.getAccountCount(), 2);

		assertEquals(190,  bank.getTotalBalance());

	}

	@After
	@Atomic(mode = TxMode.WRITE)
	public void tearDown() {

		for (Bank bank : FenixFramework.getDomainRoot().getBankSet()) {
			for (Account account : bank.getAccountSet()) {
				account.delete();
			}
			bank.delete();
		}
	}
}
