package com.action.bean;


import com.entity.Stu_manage;
import com.entity.Courses;
import com.entity.Teacher;
import com.sql.SqlHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionBean {
	public Teacher ManagerById(String manager_id) {
		SqlHelper sqlHelper=new SqlHelper();
		Teacher manager=sqlHelper.ManagerByID(manager_id);
		sqlHelper.destory();
		return manager;
	}
	public Map findStudentByPage(int p,String cid,String sname){//查找学生信息
		Map map = new HashMap();
		SqlHelper sqlHelper = new SqlHelper();
		map.put("list", sqlHelper.findStudentPage(p,cid,sname));
		map.put("rows", sqlHelper.findStudnetCount(cid,sname));
		sqlHelper.destory();
		return map;
	}
	
}
