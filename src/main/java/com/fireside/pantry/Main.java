package com.fireside.pantry;

import com.fireside.pantry.app.UserManager;
import com.fireside.pantry.util.objects.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
         App.main(args);

        List<User> users = UserManager.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }
}