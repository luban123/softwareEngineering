package com.example.bottomdemo.utils;

import android.util.Log;

import com.example.bottomdemo.login.Courses;
import com.example.bottomdemo.login.Finish_qd;
import com.example.bottomdemo.login.Qdrecord;
import com.example.bottomdemo.login.Setqd;
import com.example.bottomdemo.login.Student;
import com.example.bottomdemo.login.Student_info;
import com.example.bottomdemo.login.User;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库工具类：连接数据库用、获取数据库数据用
 * 相关操作数据库的方法均可写在该类
 */
public class DBUtils {
    private static String driver = "com.mysql.jdbc.Driver";//MySQL 驱动
    private static String url = "jdbc:mysql://112.124.21.19/attendance";//MYSQL数据库连接Url
    private static String user = "wushaoxin";//用户名
    private static String password = "12345Wsx/";//密码

    private static Connection getConn(){

        Connection connection = null;
        try{
            Class.forName(driver);// 动态加载类
            //String ip = "192.168.3.23";// 写成本机地址，不能写成localhost，同时手机和电脑连接的网络必须是同一个
            //String ip = "10.0.2.2";
            // 尝试建立到给定数据库URL的连接
            connection = DriverManager.getConnection(url,user,password);

        }catch (Exception e){
            e.printStackTrace();
        }

        return connection;
}
    //登录
    public static Map<String, String> Login(User user) {//教师登录
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select * from teacher where tphone ='" + user.getTphone()
                    + "' and tpassword ='" + user.getTpassword() + "'";

            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }
                conn.close();
                st.close();
                res.close();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, String> Stu_LOGIN(Student stu) {//教师登录
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select * from student where sphone ='"+stu.getSphone()
                    + "' and spassword ='" + stu.getSpassword() + "'";
            System.out.println(sql);
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }
                conn.close();
                st.close();
                res.close();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //学生注册
    public static boolean Register(Student student) {
        boolean b=false;
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql="INSERT INTO student VALUES ('"+student.getSno()
                    +"','"+student.getSname()
                    +"','"+student.getSphone()
                    +"','"+student.getSpassword()
                    +"','')";
            System.out.println(sql);
            st.execute(sql);
            b=true;
            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    //根据phone查询学生信息
    public static Map<String, String> Register_Phone(Student student) {
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select * from student where sphone ='"
                    + student.getSphone() + "'";
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }
                conn.close();
                st.close();
                res.close();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //根据id查询学生信息
    public static Map<String, String> Register_id(Student student) {
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select * from student where sno ='"
                    + student.getSno() + "'";
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }
                conn.close();
                st.close();
                res.close();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //添加课程
    public static boolean Insert_Course(Courses courses) {
        boolean b=false;
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql="INSERT INTO courses VALUES ('"+courses.getCid()
                    +"','"+courses.getCname()
                    +"','"+courses.getTid()
                    +"',NULL,NULL)";
            System.out.println(sql);
            st.execute(sql);
            b=true;
            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    //根据cid查询课程信息
    public static Map<String, String> Select_Course(Courses courses) {
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select * from courses where cid ='"
                    + courses.getCid()+ "'";
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }
                conn.close();
                st.close();
                res.close();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //根据tid查询课程信息
    public static List<Courses> Select_CourseByTid(String tid) {
        List <Courses>list=new ArrayList();
        Connection conn = getConn();
        Courses courses=null;
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select * from courses where tid ='"
                    +tid+ "'";
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                courses=new Courses();
                courses.setCid(res.getString("cid"));
                courses.setCname(res.getString("cname"));
                courses.setTid(res.getString("tid"));
                list.add(courses);
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //根据tid,cid查询学生名单通过的信息
    public static List<Student> Select_StudentByTidAndCid(String tid,String cid) {
        List <Student>list=new ArrayList();
        Connection conn = getConn();
        Student student=null;
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select sname,sno,sphone from student where sno in " +
                    "(select sno from student where sno in " +
                    "(SELECT sno FROM manage_student_course where cid = '"
                    +cid+ "'AND tid = '"
                    +tid+"'AND status=1))";
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                student=new Student();
                student.setSname(res.getString("sname"));
                student.setSno(res.getString("sno"));
                student.setSphone(res.getString("sphone"));
                list.add(student);
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //根据tid在签到表中查询该老师的课程
    public static List<Courses> Select_CourseByQDXQ(String tid) {
        List <Courses>list=new ArrayList();
        Connection conn = getConn();
        Courses courses=null;
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql="select courses.cid,cname from courses,set_qd " +
                    "where courses.cid = set_qd.cid and courses.tid = '"+tid+"'";
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                courses=new Courses();
                courses.setCid(res.getString("cid"));
                courses.setCname(res.getString("cname"));
                list.add(courses);
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //计算某课程中已经签到的人数
    public static int Count_finish(String cid) {
        int count=0;
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select count(*) from qdrecord where cid='"+cid+"' and qteachermsg=1" ;
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                count=res.getInt(1);
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return count;
    }

    //计算课程查询课程人数
    public static int Count_People(String cid) {
        int count=0;
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select count(*) from manage_student_course where cid='"+cid+"' and status=1" ;
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                count=res.getInt(1);
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return count;
    }
    //计算课程中未处理的人数
    public static int Count_Chuli_People(String cid) {
        int count=0;
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select count(*) from manage_student_course where cid='"+cid+"' and status=0" ;
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                count=res.getInt(1);
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return count;
    }


    //根据cid查询签到记录中的学生名单
    public static List<Qdrecord> Select_QdrecordByCid(String cid) {
        List <Qdrecord>list=new ArrayList();
        Connection conn = getConn();
        Qdrecord qdrecord=null;
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "SELECT qid,student.sname,student.sno,qdrecord.cid," +
                    "qdrecord.qtime,qdrecord.qteachermsg,qdrecord.lng,qdrecord.lat,qdrecord.address " +
                    "FROM qdrecord,student WHERE qdrecord.sno=student.sno " +
                    "AND qdrecord.cid='"
                    +cid+ "'";
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                qdrecord=new Qdrecord();
                qdrecord.setQid(res.getString("qid"));
                qdrecord.setSname(res.getString("sname"));
                qdrecord.setSno(res.getString("sno"));
                qdrecord.setCid(res.getString("cid"));
                qdrecord.setQtime(res.getTimestamp("qtime"));
                qdrecord.setIng(res.getString("lng"));
                qdrecord.setLat(res.getString("lat"));
                qdrecord.setQteachermsg(res.getString("qteachermsg"));
                qdrecord.setAddress(res.getString("address"));
                list.add(qdrecord);
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //根据tid查询所有的课程名
    public static List<String> Select_CourseNameByTid(String tid) {
        List <String>list=new ArrayList();
        Connection conn = getConn();
        String a;
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select cname from courses where tid ='"
                    +tid+ "'";
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                a=res.getString("cname");
                list.add(a);
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //根据课程名查询是否已经存在该课程的签到设置
    public static Map<String, String> Select_isSetqd(String cname) {
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select set_qd.cid,cname from courses,set_qd " +
                    "where courses.cid = set_qd.cid and courses.tid and cname='"
                    +cname+"'";
            ResultSet res = st.executeQuery(sql);
            System.out.println(sql);
            System.out.println(res.getRow());
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }
                conn.close();
                st.close();
                res.close();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //根据cname查cid信息
    public static String cnameBycid(String cname) {
        Connection conn = getConn();
        String a=null;
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select cid from courses where cname ='"
                    + cname+ "'";
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                while (res.next())
                {
                    a=res.getString("cid");
                }
                System.out.println(a);
                conn.close();
                st.close();
                res.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return a;
    }

    //插入设置签到
    public static boolean Insert_setqd(Setqd setqd) {
        boolean b=false;
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql="INSERT INTO set_qd VALUES ('"+setqd.getCid()
                    +"','"+setqd.getStarttime()
                    +"','"+setqd.getContinue_time()
                    +"','"+setqd.getLng()
                    +"','"+setqd.getLat()
                    +"','"+setqd.getAddress()
                    +"')";
            System.out.println(sql);
            st.execute(sql);
            b=true;
            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    //根据tid,cid查询学生名单还未通过的学生信息
    public static List<Student> Select_Choice_StudentByTidAndCid(String tid,String cid) {
        List <Student>list=new ArrayList();
        Connection conn = getConn();
        Student student=null;
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select sname,sno,sphone from student where sno in " +
                    "(select sno from student where sno in " +
                    "(SELECT sno FROM manage_student_course where cid = '"
                    +cid+ "'AND tid = '"
                    +tid+"'AND status=0))";
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                student=new Student();
                student.setSname(res.getString("sname"));
                student.setSno(res.getString("sno"));
                student.setSphone(res.getString("sphone"));
                list.add(student);
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //同意学生加入课程
    public static boolean Accept_Student(String sno,String cid) {
        boolean b=false;
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql="UPDATE manage_student_course SET status = 1 WHERE sno = '"+sno
                    +"' AND cid='"+cid+"'";
            System.out.println(sql);
            st.executeUpdate(sql);
            b=true;
            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    //拒绝学生加入课程
    public static boolean Reject_Student(String sno,String cid) {
        boolean b=false;
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql="UPDATE manage_student_course SET status = 2 WHERE sno = '"+sno
                    +"' AND cid='"+cid+"'";
            System.out.println(sql);
            st.executeUpdate(sql);
            b=true;
            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    //插入签到时将该课程所有学生插入到签到记录
    public static boolean Insert_ALL_Stu(String cid) {
        boolean b=false;
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();

            String sql="insert into qdrecord (sno,cid,qtime,lng,lat,qteachermsg) " +
                    "SELECT sno,cid,null,0,0,0 FROM manage_student_course where cid = '"+cid+"' And status=1";
            System.out.println(sql);
            st.execute(sql);
            b=true;
            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    //根据sno查询学生已有课程的全部信息
    public static List<Student_info> SelectHaveCourseBysno(String sno) {
        List <Student_info>list=new ArrayList();
        Connection conn = getConn();
        Student_info student_info=null;
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select sno,teacher.tid,tname,courses.cid,cname from" +
                    " teacher,manage_student_course,courses where " +
                    "manage_student_course.tid=teacher.tid AND " +
                    "manage_student_course.cid=courses.cid AND " +
                    "sno='"+sno+"' ANd status=1;";
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                student_info=new Student_info();
                student_info.setSno(res.getString("sno"));
                student_info.setTid(res.getString("tid"));
                student_info.setTname(res.getString("tname"));
                student_info.setCid(res.getString("cid"));
                student_info.setCname(res.getString("cname"));
                list.add(student_info);
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    //根据sno查询学生没有课程信息
    public static List<Student_info> SelectJoinCourse(String sno) {
        List <Student_info>list=new ArrayList();
        Connection conn = getConn();
        Student_info student_info=null;
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "select courses.cid,cname,courses.tid,tname from " +
                    "courses,teacher where courses.cid not in (select " +
                    "cid from manage_student_course where sno ='"+sno+"') " +
                    "AND courses.tid=teacher.tid;";
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                student_info=new Student_info();
                student_info.setSno(sno);
                student_info.setTid(res.getString("tid"));
                student_info.setTname(res.getString("tname"));
                student_info.setCid(res.getString("cid"));
                student_info.setCname(res.getString("cname"));
                list.add(student_info);
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //申请加入课程
    public static boolean join_Insert_Course(Student_info info) {
        boolean b=false;
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql="INSERT INTO manage_student_course VALUES ('"+info.getSno()
                    +"','"+info.getCid()
                    +"','"+info.getTid() +"',0)";
            System.out.println(sql);
            st.execute(sql);
            b=true;
            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    //申请加入课程
    public static List<Student_info> join_Insert_Course_Handle(String sno) {
        List <Student_info>list=new ArrayList();
        Connection conn = getConn();
        Student_info student_info=null;
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "SELECT manage_student_course.sno,manage_student_course.cid," +
                    "cname,manage_student_course.tid,tname,status " +
                    "FROM manage_student_course,courses,teacher WHERE " +
                    "manage_student_course.cid=courses.cid AND " +
                    "teacher.tid=manage_student_course.tid and " +
                    "sno='"+sno+"' ANd (status=2 or status=0);";
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                student_info=new Student_info();
                student_info.setSno(res.getString("sno"));
                student_info.setTid(res.getString("tid"));
                student_info.setTname(res.getString("tname"));
                student_info.setCid(res.getString("cid"));
                student_info.setCname(res.getString("cname"));
                student_info.setStatus(res.getString("status"));
                list.add(student_info);
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //根据学号查询已经完成签到的记录
    public static List<Finish_qd> Finish_qd_by_sno(String sno) {
        List <Finish_qd>list=new ArrayList();
        Connection conn = getConn();
        Finish_qd student_info=null;
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "SELECT set_qd.cid,cname,qtime,continue_time " +
                    "FROM qdrecord,set_qd,courses where sno='"+sno+"' " +
                    "And qteachermsg=1 AND qdrecord.cid=set_qd.cid AND " +
                    "courses.cid=qdrecord.cid;";
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                student_info=new Finish_qd();
                student_info.setCid(res.getString("cid"));
                student_info.setCname(res.getString("cname"));
                student_info.setQtime(res.getTimestamp("qtime"));
                student_info.setContinue_time(res.getString("continue_time"));
                list.add(student_info);
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    //根据学号查询待完成签到的记录
    public static List<Finish_qd> Wait_qd_by_sno(String sno) {
        List <Finish_qd>list=new ArrayList();
        Connection conn = getConn();
        Finish_qd student_info=null;
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "SELECT set_qd.cid,cname,starttime,continue_time " +
                    "FROM qdrecord,set_qd,courses where sno='"+sno+"' " +
                    "And qteachermsg=0 AND qdrecord.cid=set_qd.cid AND " +
                    "courses.cid=qdrecord.cid;";
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                student_info=new Finish_qd();
                student_info.setCid(res.getString("cid"));
                student_info.setCname(res.getString("cname"));
                student_info.setQtime(res.getTimestamp("starttime"));
                student_info.setContinue_time(res.getString("continue_time"));
                list.add(student_info);
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }


    //签到成功
    public static boolean Update_QD_info(String sno,String qtime,String cid,String address){
        boolean b=false;
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();

            String sql="UPDATE qdrecord,set_qd SET qteachermsg = 1,qtime=now(),qdrecord.address='"+address+"' " +
                    "WHERE (sno = '"+sno+"' AND set_qd.cid='"+cid+"' AND starttime=" +
                    "'"+qtime+"' AND qdrecord.cid=set_qd.cid)";
            System.out.println(sql);
            st.execute(sql);
            b=true;
            conn.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    //教师端查看签到详情显示地点
    public static String View_Place(String cid) {
        String count=null;
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "SELECT address FROM `set_qd` where cid="+cid ;
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                count=res.getString("address");
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return count;
    }
    //计算某课程中已经签到的人数
    public static String Start_Time(String cid) {
        String starttime=null;
        Connection conn = getConn();
        if (conn != null){
            Log.d("数据库连接状况", "Login: 成功");
        }else {
            Log.d("数据库连接状况", "Login: 失败");
        }
        try {
            Statement st = conn.createStatement();
            String sql= "SELECT starttime FROM `set_qd` where cid='"+cid+"'" ;
            ResultSet res = st.executeQuery(sql);
            while (res.next())
            {
                starttime=res.getString("starttime");
            }
            System.out.println(sql);
            conn.close();
            st.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return starttime;
    }


}