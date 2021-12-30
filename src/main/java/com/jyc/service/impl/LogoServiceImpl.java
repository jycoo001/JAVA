package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.LogoDAO;
import com.jyc.service.LogoService;

@Service
public class LogoServiceImpl extends BaseServiceImpl implements LogoService {

	@SuppressWarnings("unused")
	@Autowired
	private LogoDAO dao;

}
