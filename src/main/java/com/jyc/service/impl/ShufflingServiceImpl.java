package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.ShufflingDAO;
import com.jyc.service.ShufflingService;

@Service
public class ShufflingServiceImpl extends BaseServiceImpl implements ShufflingService {
	@SuppressWarnings("unused")
	@Autowired
	private ShufflingDAO dao;

}
