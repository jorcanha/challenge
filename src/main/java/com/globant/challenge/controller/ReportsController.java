package com.globant.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.challenge.service.ReportsService;

@RestController
@RequestMapping("reportes")
public class ReportsController {

	private static final Logger LOG = LoggerFactory.getLogger(ReportsController.class);
	
	@Autowired
	private ReportsService reportsService;
}
