package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       UserService userDao= new UserServiceImpl();
       userDao.dropUsersTable();
        userDao.createUsersTable();
        userDao.saveUser("Max", "Sokolov", (byte) 33);
        userDao.saveUser("Egor", "Drozdov", (byte) 12);
        userDao.saveUser("Anna", "Kostrova", (byte) 24);
        userDao.saveUser("Ola", "Bartich", (byte) 20);
        List < User> userList=userDao.getAllUsers();
        for (User user : userList) {
            System.out.println(user.toString());
        }
userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }

}
