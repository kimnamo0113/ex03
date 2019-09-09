package com.yi.persistence;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointDaoImpl implements PointDao{
	private static final String namespace = "com.yi.mapper.PointMapper";
	
	@Autowired
	private SqlSession SqlSession;
	
	@Override
	public void updatePoint(String uid, int point) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("point", point);
		SqlSession.update(namespace+".updatePoint",map);
	}
	
}
