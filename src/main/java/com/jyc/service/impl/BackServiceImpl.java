package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.BackDAO;
import com.jyc.service.BackService;

@Service
public class BackServiceImpl extends BaseServiceImpl implements BackService {

	@SuppressWarnings("unused")
	@Autowired
	private BackDAO dao;

}
