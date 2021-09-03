package com.ringo.dao.impl;

import com.ringo.dao.UserDao;
import com.ringo.entry.User;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Override
    public int addUser(User user) {
        //定义sql语句
        String sql="insert into user(id,username,passwd) values(default,?,?)";
        String [] obj={user.getName(),user.getPassword()};
        int row=jdbcTemplate.update(sql,obj);
        return row;
    }

    @Override
    public int addUsertoprimarykey(User user) {
        //定义sql语句
        String sql="insert into user(id,username,passwd) values(default,?,?)";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            //预编译sql
            PreparedStatement ps=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //设置参数
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            //返回预编译对象
            return ps;
        },keyHolder);
        //得到主键
        int key=keyHolder.getKey().intValue();
        return key;
    }
    //批量添加
    @Override
    public int addUserList(List<User> userlist) {
        //定义sql语句
        String sql="insert into user(id,username,passwd) values(default,?,?)";
        int row=jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User user=userlist.get(i);
                ps.setString(1,user.getName());
                ps.setString(2,user.getPassword());

            }

            @Override
            public int getBatchSize() {
                return userlist.size();
            }
        }).length;
        return row;
    }

    @Override
    public int queryUsercount(int userid) {
        String sql="select count(*) from user where id=?";
        //查询
        int row=jdbcTemplate.queryForObject(sql,Integer.class,userid);
        return row;
    }

    @Override
    public User queryUser(int userid) {
        String sql="select * from user where id=?";
        User user=jdbcTemplate.queryForObject(sql,(ResultSet rs, int rowNum)->{
            User user1=new User();
            user1.setId(rs.getInt("id"));
            user1.setName(rs.getString("username"));
            user1.setPassword(rs.getString("passwd"));
            return user1;
        },userid);
        return user;
    }

    //多条件查询
    @Override
    public List<User> queryUserlist(int getid, String name, String passwdcon) {
        String sql="select * from user where id>?";
        //查询集合
        Object objects[]={getid};
        List<User> list=jdbcTemplate.query(sql, objects,(ResultSet rs, int rowNum) ->{
            User user1=new User();
            user1.setId(rs.getInt("id"));
            user1.setName(rs.getString("username"));
            user1.setPassword(rs.getString("passwd"));
            return user1;
        }
    );
        return list;
    }
    //更新记录
    @Override
    public int updateUser(User user) {
        String sql="update user set username=?,passwd=? where id=?";
        Object [] objects={user.getName(),user.getPassword(),user.getId()};
        int row=jdbcTemplate.update(sql,objects);
        return row;
    }

    //批量更新
    @Override
    public int updateUser(List<User> userlist) {
        String sql="update user set username=?,passwd=? where id=?";
        int row=jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User user=userlist.get(i);
                ps.setString(1,user.getName());
                ps.setString(2,user.getPassword());
                ps.setInt(3,user.getId());
            }

            @Override
            public int getBatchSize() {
                return userlist.size();
            }
        }).length;
        return row;
    }

    //删除，标记删除加一个字段
    @Override
    public int deleteUser(int userid) {
        String sql="delete from user where id=?";
        int row=jdbcTemplate.update(sql,userid);
        return row;
    }

    @Override
    public int deleteUser(int[] id) {
        String sql="delete from user where id=?";
        int row=jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1,id[i]);
            }

            @Override
            public int getBatchSize() {
                return id.length;
            }
        }).length;
        return row;
    }
}
