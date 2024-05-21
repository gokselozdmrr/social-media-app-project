package me.taciozturk.ui;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import me.taciozturk.Authentication.Login;
import me.taciozturk.Authentication.Register;
import me.taciozturk.User;
import me.taciozturk.UserBuilder;
import me.taciozturk.UserList;
import me.taciozturk.ui.views.UserView;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class Application {
    int SCREEN_WIDTH = 1600;
    int SCREEN_HEIGHT = 920;
    JFrame frame = new JFrame();
    public void run(UserList userList,User loginUser) throws FileNotFoundException, NoSuchAlgorithmException {
        List<User> users = userList.getAllUsers();



        UserView userView = new UserView(SCREEN_WIDTH, SCREEN_HEIGHT, loginUser,userList);
        frame.add(userView);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /*
    private UserList generateUsers(UserList userlist) throws FileNotFoundException, NoSuchAlgorithmException {
        UserList userList = new UserList();

        userList.add(user);

        UserBuilder userBuilder = new UserBuilder();
        User user1 = userBuilder.startBuild("Michael Johnson", "michael.johnson@example.com", "michael1")
                .setAvatar("src/main/resources/assets/avatars/man-avatar.png")
                .build();

        Register register = new Register("src/main/java/me/taciozturk/users.json");
        register.register(user1);

        //user1.setAvatar("", SCREEN_WIDTH / 6, SCREEN_HEIGHT / 4);
        user1.addPost("It has long been an axiom of mine that the little things are infinitely the most important.");
        user1.addPost("Facing a mirror you see merely your own countenance; facing your child you finally understand how everyone else has seen you.");
        user1.addPost("Everything has got a moral if you can only find it.");
        user1.addPost("Discovery consists of seeing what everybody has seen and thinking what nobody has thought.");
        user1.addPost("An alcoholic is someone you don't like who drinks as much as you do.");
        user1.addPost("Travel is fatal to prejudice, bigotry, and narrow-mindedness, and many of our people need it sorely on these accounts. Broad, wholesome, charitable views of men and things cannot be acquired by vegetating in one little corner of the earth all one's lifetime.");
        user1.addPost("There are painters who transform the sun to a yellow spot, but there are others who with the help of their art and their intelligence, transform a yellow spot into the sun.");
        user1.addPost("Be a craftsman in speech that thou mayest be strong, for the strength of one is the tongue, and speech is mightier than all fighting.");
        user1.addPost("You can discover more about a person in an hour of play than in a year of conversation.");
        user1.addPost("Given a choice between two theories, take the one which is funnier.");
        user1.addPost("Only as you do know yourself can your brain serve you as a sharp and efficient tool. Know your own failings, passions, and prejudices so you can separate them from what you see.");
        user1.addPost("I live now on borrowed time, waiting in the anteroom for the summons that will inevitably come. And then - I go on to the next thing, whatever it is. One doesn't luckily have to bother about that.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");

        User user2 = userBuilder.startBuild("Bob Smith", "bob.smith@example.com", "bob1")
                .setAvatar("src/main/resources/assets/avatars/man-avatar-2.png")
                .build();

        register.register(user2);



        User user3 = new User("Damien White");
        user3.setAvatar("src/main/resources/assets/avatars/man-avatar-3.png", SCREEN_WIDTH / 6, SCREEN_HEIGHT / 4);
        user3.setEmail("damien.white@example.com");

        User user4 = new User("Mary Brown");
        user4.setAvatar("src/main/resources/assets/avatars/woman.png", SCREEN_WIDTH / 6, SCREEN_HEIGHT / 4);
        user4.setEmail("mary.brown@example.com");

        User user5 = new User("Eve Davis");
        user5.setAvatar("src/main/resources/assets/avatars/woman-2.png", SCREEN_WIDTH / 6, SCREEN_HEIGHT / 4);
        user5.setEmail("eve.davis@example.com");

        User user6 = new User("Anne Moore");
        user6.setAvatar("src/main/resources/assets/avatars/woman-3.png", SCREEN_WIDTH / 6, SCREEN_HEIGHT / 4);
        user6.setEmail("anne.moore@example.com");


        userList.add(user1);
        userList.add(user2);

        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);


        userList.addConnection(user1, user2);

        userList.addConnection(user1, user3);
        userList.addConnection(user1, user4);
        userList.addConnection(user1, user5);
        userList.addConnection(user1, user6);


        return userList;


    }
    */
}
