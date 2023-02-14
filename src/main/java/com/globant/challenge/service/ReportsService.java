package com.globant.challenge.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.challenge.repository.TransactionRepository;

@Service
public class ReportsService {

	private static final Logger LOG = LoggerFactory.getLogger(ReportsService.class);

	@Autowired
	private TransactionRepository transactionRepository;

	public List<Object[]> reportsTransactions() {
		return transactionRepository.transactionsReportByDates();
	}
}
