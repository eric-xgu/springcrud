package com.ringo.dao;
/*
 * 1、用户查询
 * 2、用户删除
 * 3、用户修改
 * 4、用户增加
 * */

import com.ringo.entry.User;

import java.util.List;

public interface UserDao {
    public int addUser(User user);
    //添加账户返回主键
    public int addUsertoprimarykey(User user);
    //批量添加账户
    public int addUserList(List<User> userlist);
    //查询用户的记录数
    public  int queryUsercount(int userid);
    //查询用户
    public User queryUser(int userid);

    public List<User> queryUserlist(int getid,String name,String usertype);

    public int updateUser(User user);
    public int updateUser(List<User> userlist);

    public int deleteUser(int userid);
    public int deleteUser(int [] id);


}
