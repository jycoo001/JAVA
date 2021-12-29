package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.OneTypeDAO;
import com.jyc.service.OneTypeService;

@Service
public class OneTypeServiceImpl extends BaseServiceImpl implements OneTypeService {

	@SuppressWarnings("unused")
	@Autowired
	private OneTypeDAO dao;
}
