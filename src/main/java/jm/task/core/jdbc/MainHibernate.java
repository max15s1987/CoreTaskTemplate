package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceHibImpl;
import jm.task.core.jdbc.util.HibernateUtil;

public class MainHibernate {

    public static void main(String[] args) {

        UserService usH = new UserServiceHibImpl();

        usH.dropUsersTable();
        usH.createUsersTable();

        User user1 = new User("Максим", "Петров", (byte) 33);
        User user2 = new User("Мария", "Петрова", (byte) 29);
        User user3 = new User("Виктория", "Петрова", (byte) 1);
        User user4 = new User("Иван", "Иванов", (byte) 18);

        usH.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.printf("User с именем \"%s\" добавлен в базу данных\n", user1.getName());

        usH.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.printf("User с именем \"%s\" добавлен в базу данных\n", user2.getName());

        usH.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.printf("User с именем \"%s\" добавлен в базу данных\n", user3.getName());

        usH.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.printf("User с именем \"%s\" добавлен в базу данных\n", user4.getName());

        usH.removeUserById(1);

        for (User user : usH.getAllUsers()) {
            System.out.println(user);
        }

        usH.cleanUsersTable();
        usH.dropUsersTable();

        HibernateUtil.shutdown();

    }

}
