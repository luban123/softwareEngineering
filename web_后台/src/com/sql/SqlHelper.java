package com.sql;
import java.sql.*;

import com.DB.DB;
import com.entity.Stu_manage;
import com.entity.Courses;
import com.entity.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlHelper {
	private Connection con=null;
	private int page=10;
public SqlHelper()
{
	con=DB.getConnection();
}
public void destory()
{
	if(con!=null)
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public Teacher ManagerByID(String tphone)//根据手机号，查询该老师全部信息
{
	String sql="select tid,tname,tphone,tpassword from teacher where tphone=?";
	Teacher manager=null;
	try {
		PreparedStatement ps;
		ps=con.prepareStatement(sql);
		ps.setString(1,tphone);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			manager=new Teacher();
			manager.setTphone(rs.getString("tphone"));
			manager.setTid(rs.getString("tid"));
			manager.setTname(rs.getString("tname"));
			manager.setTpassword(rs.getString("tpassword"));
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return manager;
}

public Teacher ManagerByID2(String tid)//根据id号，查询该老师全部信息
{
	String sql="select tid,tname,tphone,tpassword from teacher where tid=?";
	Teacher manager=null;
	try {
		PreparedStatement ps;
		ps=con.prepareStatement(sql);
		ps.setString(1,tid);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			manager=new Teacher();
			manager.setTphone(rs.getString("tphone"));
			manager.setTid(rs.getString("tid"));
			manager.setTname(rs.getString("tname"));
			manager.setTpassword(rs.getString("tpassword"));
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return manager;
}


public Teacher findIdAndPwd(Teacher m){//登录
	String sql = "select tid,tname,tphone,tpassword from teacher where tphone=? and tpassword=?";
	Teacher manager = null;
	try{
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,m.getTphone());
		ps.setString(2,m.getTpassword());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			manager = new Teacher();
			manager.setTphone(rs.getString("tphone"));
			manager.setTid(rs.getString("tid"));
			manager.setTname(rs.getString("tname"));
			manager.setTpassword(rs.getString("tpassword"));
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return manager;
}
public Courses findName(String m){//根据课程id，查询课程姓名和教师id
	String sql = "select * from courses where cid=?";
	Courses courses = null;
	try{
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,m);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			courses = new Courses();
			courses.setCid(rs.getString("cid"));
			courses.setCname(rs.getString("cname"));
			courses.setTid(rs.getString("tid"));
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return courses;
}
public boolean insertManager(Teacher manager) {//注册，往数据库中加入老师信息
	String sql="insert into teacher(tid,tname,tphone,tpassword) values(?,?,?,?)";
			boolean b=false;
			try {
				PreparedStatement ps;
				ps=con.prepareStatement(sql);
				ps.setString(1,manager.getTid());
				ps.setString(2,manager.getTname());
				ps.setString(3,manager.getTphone());
				ps.setString(4,manager.getTpassword());
				ps.executeUpdate();
				b=true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
	return b;
}
public boolean Deletemanage_student(Stu_manage m) {//根据选择的框，删除学生
	// TODO Auto-generated method stub
	String sql="delete from manage_student_course where sno=? AND cid=?";
	boolean b=false;
	try {
		PreparedStatement ps;
		ps=con.prepareStatement(sql);
		ps.setString(1,m.getSno());
		ps.setString(2,m.getCid());
		ps.executeUpdate();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return b;
}


public List<Courses> CourseAll(String tid) {//根据教师号搜索该老师课程信息
	List <Courses>list=new ArrayList();
	String sql="SELECT * FROM courses where tid=?";
	Courses courses=null;
	try {
		PreparedStatement ps;
		ps=con.prepareStatement(sql);
		ps.setString(1,tid);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
		courses=new Courses();
		courses.setCid(rs.getString("cid"));
		courses.setCname(rs.getString("cname"));
		courses.setTid(rs.getString("tid"));
		list.add(courses);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(sql);
	return list;
	// TODO Auto-generated method stub
}

public int findStudnetCount(String cid,String sname) {//查询记录的总数
	String sql="select count(*) from student where sno in (select sno from student where sno in (SELECT sno FROM manage_student_course where cid = ?) and sname like ?);";
	boolean f2 = (sname !=null&& !"".equals(sname));
			try {
				PreparedStatement ps;
				ps=con.prepareStatement(sql);
				ps.setString(1, cid);				
				if(f2){
				ps.setString(2, "%"+sname+"%");
				}
				else {
					ps.setString(2, "%%");
				}
				ResultSet rs = ps.executeQuery();				
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
	catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	return 0;
	}

public List<Map> findStudentPage(int p,String cid,String sname) {//根据姓名和课程编号，教师编号搜索学生
	int start = p*page;
	int m = start+1;
	String sql = "select sname,sno,sphone from student where sno in (select sno from student where sno in (SELECT sno FROM manage_student_course where cid = ?) and sname like ?)";
	sql=sql+" order by CONVERT(sname USING GBK) limit "+start+","+page;
	List <Map>list = new ArrayList();
	try{
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cid);		
		if(sname !=null && !"".equals(sname)){
			ps.setString(2, "%"+sname+"%");
		}
		else {
			ps.setString(2, "%%");
		}
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Map map = new HashMap();
			map.put("order_no", m++);
			map.put("sname", rs.getString("sname"));
			map.put("sno",rs.getString("sno"));
			map.put("sphone",rs.getString("sphone"));
			list.add(map);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return list;
}

public boolean InsertStu(Stu_manage m) {//课程中添加学生
	// TODO Auto-generated method stub
	String sql = "insert into manage_student_course(sno,cid,tid,status)values(?,?,?,?)";
	boolean b=false;
	try {
		PreparedStatement ps;
		ps=con.prepareStatement(sql);
		ps.setString(1, m.getSno());
		ps.setString(2,	m.getCid());
		ps.setString(3, m.getTid());
		ps.setString(4,m.getStatus());
		ps.executeUpdate();
		b=true;
	}catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	return b;
}

public boolean findByIDandCourses(String sno,String cid){//根据学号和课程号，查询是否已存在该学生信息
	String sql = "select * from manage_student_course where sno=? and cid=?";
	boolean b=true;
	try{
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,sno);
		ps.setString(2,cid);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			b=false;
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return b;
}

}
