package com.xiaozhuge.springbootldap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JdbcDemo {
    private static Connection dbConn = null;
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";//mysql数据库驱动类
    private static final String DBUSER = "root";//数据库用户名
    private static final String DBPASS = "Hc@Cloud01";//数据库密码
    private static final String DBURL = "jdbc:mysql://localhost:3309/studb?characterEncoding=UTF-8";

    public static Connection getLink() {
        try {
            Class.forName(DBDRIVER);
            dbConn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            System.out.println("数据库连接成功");
            return dbConn;
        } catch (Exception e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return dbConn;
    }

    public static void main(String[] args) {
        Connection connection = getLink();
        JdbcDemo jdbcDemo = new JdbcDemo();
        // 新增
        Student student = new Student("测试");
        jdbcDemo.insert(connection,student);
        // 查询
        List<Student> list = jdbcDemo.select(connection);
        System.out.println("总计：" +  list.size());
        // 更新
        jdbcDemo.update(connection);
        // 删除
        jdbcDemo.delete(connection, 1);
    }

    private void insert(Connection dbConn,Student student) {
        String sql = "insert into student (`name`) values (?)";
        try {
            PreparedStatement ps = dbConn.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private List<Student> select(Connection dbConn) {
        String sql = "select * from `student`";
        List<Student> studentList = new ArrayList<>();
        try {
            PreparedStatement ps = dbConn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            int columnCount = resultSet.getMetaData().getColumnCount();
            System.out.println("列数：" + columnCount);
            while (resultSet.next()) {
                // 注意，列的下标是从1开始的
                studentList.add(new Student(resultSet.getString(1), resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return studentList;
    }

    private void update(Connection dbConn){
        String sql = "update `student` set `name` = ? where id = ?";
        try {
            PreparedStatement ps = dbConn.prepareStatement(sql);
            ps.setString(1, "aaa");
            ps.setString(2, "1");
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void delete(Connection dbConn, int id){
        String sql = "delete from `student` where id = ?";
        try {
            PreparedStatement ps = dbConn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static class Student{
        private String id;
        private String name;

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public Student(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}