package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.GoodsPictureDAO;
import com.jyc.service.GoodsPictureService;

@Service
public class GoodsPictureServiceImpl extends BaseServiceImpl implements GoodsPictureService {

	@Autowired
	private GoodsPictureDAO dao;

}
