package me.taciozturk.Authentication;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import me.taciozturk.User;
import me.taciozturk.UserList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Login {

    public Login() {}

    public User login(String email, String password, UserList userList) throws NoSuchAlgorithmException, FileNotFoundException {
        List<User> users = userList.getAllUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                if (user.isPasswordValid(password)) {
                    return user;
                }
            }
        }

        System.out.println("Invalid email or password");
        return null;
    }

}

