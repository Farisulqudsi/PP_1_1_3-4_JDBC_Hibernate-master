package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();

        userDao.saveUser("Johnny", "Depp", (byte) 20);
        userDao.saveUser("Mike", "Tyson", (byte) 25);
        userDao.saveUser("Tom", "Cruise", (byte) 31);
        userDao.saveUser("Tobey", "Maguire", (byte) 38);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();userDao.dropUsersTable();
    }
}
