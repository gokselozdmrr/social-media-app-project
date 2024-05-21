package me.taciozturk.Authentication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.taciozturk.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Register {

    private List<User> users;
    private String filePath;

    public Register(String filePath) {
        this.filePath = filePath;
        this.users = loadUsers();
    }

    public boolean register(User newUser) {
        // Check if the email is already registered
        for (User user : users) {
            if (user.getEmail().equals(newUser.getEmail())) {
                System.out.println("Email is already registered.");
                return false;
            }
        }

        // Add the new user to the list
        users.add(newUser);

        // Save the updated user list to the file
        saveUsers();

        System.out.println("Registration successful.");
        return true;
    }

    private List<User> loadUsers() {
        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveUsers() {
        try (Writer writer = new FileWriter(filePath)) {
            new Gson().toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
