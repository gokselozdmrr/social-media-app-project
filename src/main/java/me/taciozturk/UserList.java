package me.taciozturk;

import com.google.gson.Gson;
import me.taciozturk.Observer.IAddFriendObserver;
import me.taciozturk.Observer.IObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserList implements IAddFriendObserver {
    private List<IObserver> observers = new ArrayList<>();
    private ArrayList<User> users;
    private int id = 1;

    public UserList() {
        this.users = new ArrayList<User>();
    }

    public void add(User user) {
        user.setId();
        this.users.add(user);
        id++;
    }

    public void remove(User user) {
        this.users.remove(user);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        };
        return null;
    }

    public void addConnection(User user1, User user2) {
        if (user1.getId() != user2.getId() && !user1.getConnections().contains(user2.getId())) {
            getUserById(user1.getId()).addConnection(user2);
            getUserById(user2.getId()).addConnection(user1);
        }

    }

    public void removeConnection(User user1, User user2) {
        getUserById(user1.getId()).removeConnection(user2);
        getUserById(user2.getId()).removeConnection(user1);
    }

    public void dbUpdate(){
        try (Writer writer = new FileWriter("src/main/java/me/taciozturk/users.json")) {
            new Gson().toJson(this.getAllUsers(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.add(observer);

    }

    @Override
    public void notifyObserver(String type) {
        for (IObserver observer : observers) {
            observer.update(type);
        }
    }
}
