package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.TwoTypeDAO;
import com.jyc.service.TwoTypeService;

@Service
public class TwoServiceImpl extends BaseServiceImpl implements TwoTypeService {
	@SuppressWarnings("unused")
	@Autowired
	private TwoTypeDAO dao;

}
