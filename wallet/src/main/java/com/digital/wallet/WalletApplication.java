package com.digital.wallet;

import java.util.Arrays;
import java.util.Date;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.digital.wallet.models.Customer;
import com.digital.wallet.models.Transaction;
import com.digital.wallet.models.Wallet;
import com.digital.wallet.repositories.CustomerRepository;
import com.digital.wallet.repositories.TansactionRepository;
import com.digital.wallet.repositories.WalletRepository;

@SpringBootApplication
public class WalletApplication implements ApplicationRunner {
	private final CustomerRepository customerRepository;
	private final WalletRepository walletRepo;
	private final TansactionRepository transactionRepo;

	public WalletApplication(CustomerRepository customerRepository, WalletRepository walletRepo,
			TansactionRepository transactionRepo) {
		this.customerRepository = customerRepository;
		this.walletRepo = walletRepo;
		this.transactionRepo = transactionRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Init Customers .....");
		Transaction t1 = new Transaction(1, 10f, new Date());
		Transaction t2 = new Transaction(2, 11f, new Date());
		Transaction t3 = new Transaction(3, 13f, new Date());
		Wallet w1 = new Wallet(1L, 100D);
		Wallet w2 = new Wallet(2L, 100D);
		Wallet w3 = new Wallet(3L, 100D);

		// walletRepo.saveAll(Arrays.asList(w1, w2, w3));
		t1.setWalletSender(w1.getWalletId());
		t1.setWalletReciever(w2.getWalletId());
		w1.setTransactions(Arrays.asList(t1, t2));

		t2.setWalletSender(w1.getWalletId());
		t2.setWalletReciever(w2.getWalletId());
		w2.setTransactions(Arrays.asList(t1, t2, t3));

		t3.setWalletSender(w2.getWalletId());
		t3.setWalletReciever(w3.getWalletId());
		w3.setTransactions(Arrays.asList(t3));

		// transactionRepo.saveAll(Arrays.asList(t1, t2, t3));
		Customer c1 = new Customer(1L, "Jean", "KD", "devmedk@gmail.com", "1234", 123456789L, Arrays.asList(w1));
		Customer c2 = new Customer(2L, "Mira", "Hoka", "mira@gmail.com", "1234", 987654321L, Arrays.asList(w2));
		Customer c3 = new Customer(3L, "Jamal", "CHIRA", "jamal@gmail.com", "1234", 987651234L, Arrays.asList(w3));
		w1.setWalletHolder(c1);
		w2.setWalletHolder(c2);
		w3.setWalletHolder(c3);

		customerRepository.saveAll(Arrays.asList(c1,c2,c3));

	}

}
