package com.ringo;

import static org.junit.Assert.assertTrue;

import com.ringo.dao.UserDao;
import com.ringo.dao.impl.UserDaoImpl;
import com.ringo.entry.User;
import org.junit.Test;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseTest
{
    @Resource
    private UserDaoImpl userdao;
    @Test
    public void testaddUser(){
//        //准备添加的数据
//        List<User> list=new ArrayList<>();
//        User jackhz=new User(1,"jackma","hz8888");
//        User dg=new User(2,"qiangzi","brother");
//        User te=new User(3,"麻花腾","qb123");
//        list.add(jackhz);
//        list.add(dg);
//        list.add(te);
//        int size=userdao.addUserList(list);

//        int id=5;
//        int row=userdao.queryUsercount(5);

//        User user=userdao.queryUser(5);
//        System.out.println(user.getId());
//        System.out.println(user.getName());
//        System.out.println(user.getPassword());

//        List<User> users=userdao.queryUserlist(1,"jack","");
//        for (User u:users) {
//            System.out.println(Integer.toString(u.getId())+"-");
//        }

       // User user=new User(1,"marina","savarges");
        //userdao.updateUser(user);

//        List<User> list=new ArrayList<>();
//        User user=new User(1,"eric","123456");
//        User user1=new User(2,"lemon","sb");
//        list.add(user);
//        list.add(user1);
//        userdao.updateUser(list);

       // int rs=userdao.deleteUser(7);
       // System.out.println(rs);
        int ids[]={5,6,7,8};
        int row=userdao.deleteUser(ids);
        System.out.println(row);
    }
}
