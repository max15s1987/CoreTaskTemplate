package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService us = new UserServiceImpl();
        us.dropUsersTable();

        us.createUsersTable();

        User user1 = new User("Максим", "Петров", (byte) 33);
        User user2 = new User("Мария", "Петрова", (byte) 29);
        User user3 = new User("Виктория", "Петрова", (byte) 1);
        User user4 = new User("Иван", "Иванов", (byte) 18);

        us.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.printf("User с именем \"%s\" добавлен в базу данных\n", user1.getName());

        us.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.printf("User с именем \"%s\" добавлен в базу данных\n", user2.getName());

        us.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.printf("User с именем \"%s\" добавлен в базу данных\n", user3.getName());

        us.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.printf("User с именем \"%s\" добавлен в базу данных\n", user4.getName());

        for (User user : us.getAllUsers()) {
            System.out.println(user);
        }

        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
