package com.globant.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.globant.challenge.model.Account;
import com.globant.challenge.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long>  {

	Transaction findFirstByAccountOrderByDateDesc(Account account);

	@Query(value = ""
			+ "select t.date, p.name, a.account_number, a.account_type, a.initial_balance, a.status, t.amount, t.balance "
			+ "from transaction t "
			+ "inner join account a on a.account_id = t.account_id "
			+ "inner join customer c on c.customer_id = a.customer_id "
			+ "inner join person p on p.person_id = c.person_id "
			+ "order by t.date desc", nativeQuery = true)
	List<Object[]> transactionsReportByDates();
}
