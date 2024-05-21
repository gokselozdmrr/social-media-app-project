package me.taciozturk;

import java.util.ArrayList;
import java.util.List;

public class Search {
    public List<User> search(String query, UserList userList) {
        List<User> filtredusers = new ArrayList<>();
        for (User user : userList.getAllUsers()){
            if (user.getName().toLowerCase().contains(query.toLowerCase())){
                filtredusers.add(user);
            }
        }

        return filtredusers;
    }

    public List<Group> search(String query) {
        List<Group> filtredGroups = new ArrayList<>();

        for (Group group : Group.getGroups()){
            if (group.getName().toLowerCase().contains(query.toLowerCase())){
                filtredGroups.add(group);
            }
        }

        return filtredGroups;
    }
}
