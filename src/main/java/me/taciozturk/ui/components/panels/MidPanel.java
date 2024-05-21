package me.taciozturk.ui.components.panels;

import me.taciozturk.Observer.IObserver;
import me.taciozturk.Post;
import me.taciozturk.Search;
import me.taciozturk.User;
import me.taciozturk.UserList;
import me.taciozturk.ui.components.cards.PostCard;


import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;


public class MidPanel extends JPanel implements ICreatePanel, IObserver {
    private User user;
    private int width;
    private int height;
    private JScrollPane scrollPane;
    private UserList userList;
    private JPanel posts;


    public MidPanel(int _width, int _height, User _user, UserList _userList) {
        super();
        this.user = _user;
        this.width = _width;
        this.height = _height;
        this.userList = _userList;
    }

    @Override
    public MidPanel create() {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(width * 4 / 8, height));

        JPanel writePostPanel = new JPanel();
        writePostPanel.setLayout(new BoxLayout(writePostPanel,BoxLayout.Y_AXIS));
        JTextArea postArea = new JTextArea();
        postArea.setLineWrap(true);
        postArea.setWrapStyleWord(true);
        postArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        postArea.setRows(12);
        postArea.setBackground(new Color(240, 240, 240));
        postArea.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        JScrollPane textPane = new JScrollPane(postArea);
        textPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton sendButton = new JButton("Send Post");
        sendButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        sendButton.addActionListener(_ -> {
            String postText = postArea.getText();
            Post post = new Post(postText,user.getId(),new Date());
            user.addPost(post);
            drawUserPosts(user);

        });
        writePostPanel.add(textPane);
        writePostPanel.add(sendButton);
        writePostPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        writePostPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(writePostPanel);

        this.add(new JSeparator(SwingConstants.HORIZONTAL));

        JLabel title = new JLabel("POSTS");
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.add(title);

        this.add(new JSeparator(SwingConstants.HORIZONTAL));

        posts = new JPanel();
        posts.setLayout(new BoxLayout(posts, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(posts);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel viewPanel = new JPanel(new GridLayout(1,3));
        JButton userView = new JButton("Your Posts");
        userView.setAlignmentX(Component.CENTER_ALIGNMENT);

        userView.addActionListener(e-> {
            this.remove(scrollPane);
            drawUserPosts(user);
            this.add(scrollPane);
            this.revalidate();
            this.repaint();
        });
        JButton friendsView = new JButton("Friends Posts");
        friendsView.setAlignmentX(Component.CENTER_ALIGNMENT);

        friendsView.addActionListener(a->{
            this.remove(scrollPane);
            drawFriendsPosts(user);
            this.add(scrollPane);
            this.revalidate();
            this.repaint();
        });

        JButton allPostsView = new JButton("All Posts");
        allPostsView.setAlignmentX(Component.CENTER_ALIGNMENT);
        allPostsView.addActionListener(x ->{
            this.remove(scrollPane);
            drawPosts(user);
            this.add(scrollPane);
            this.revalidate();
            this.repaint();
        });
        this.add(viewPanel);
        viewPanel.add(userView);
        viewPanel.add(friendsView);
        viewPanel.add(allPostsView);
        drawPosts(user);
        this.add(scrollPane);

        return this;
    }


    public void drawUserPosts(User user) {
        posts.removeAll();

        for (User user1 : userList.getAllUsers()){
            if (user.getPosts() != null && (user.getId() == user1.getId())) {
                user1.getPosts().reversed().forEach(post -> {
                    PostCard postCard = new PostCard(post.getMessage());
                    postCard.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
                    posts.add(postCard);
                    JLabel username = new JLabel(user1.getName());
                    posts.add(username);
                    JSeparator separator = new JSeparator((SwingConstants.HORIZONTAL));
                    posts.add(separator);
                    posts.setBackground(Color.WHITE);
                    JButton removeButton = new JButton("Remove Post");
                    removeButton.setAlignmentX(Component.LEFT_ALIGNMENT);

                    removeButton.addActionListener(e->{
                        user1.removePost(post);
                        userList.notifyObserver("User");
                    });
                    postCard.add(removeButton);

                });
            }
            posts.revalidate();
            posts.repaint();

        }

    }
    public void drawFriendsPosts(User user) {
        posts.removeAll();

        for (User user1 : userList.getAllUsers()){
            if (user.getPosts() != null && (user.getConnections().contains(user1.getId()) && user.getId() != user1.getId())) {
                user1.getPosts().reversed().forEach(post -> {
                    PostCard postCard = new PostCard(post.getMessage());
                    postCard.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
                    posts.add(postCard);
                    JLabel username = new JLabel(user1.getName());
                    posts.add(username);
                    JSeparator separator = new JSeparator((SwingConstants.HORIZONTAL));
                    posts.add(separator);
                    posts.setBackground(Color.WHITE);

                });
            }
            posts.revalidate();
            posts.repaint();

        }

    }
    public void drawPosts(User user) {
        posts.removeAll();

        for (User user1 : userList.getAllUsers()){
            if (user.getPosts() != null && (user.getConnections().contains(user1.getId()) || user.getId() == user1.getId())) {
                user1.getPosts().reversed().forEach(post -> {
                    PostCard postCard = new PostCard(post.getMessage());
                    postCard.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
                    posts.add(postCard);
                    JLabel username = new JLabel(user1.getName());
                    posts.add(username);
                    JSeparator separator = new JSeparator((SwingConstants.HORIZONTAL));
                    posts.add(separator);
                    posts.setBackground(Color.WHITE);

                    if(user1.getId() == user.getId()){
                        JButton removeButton = new JButton("Remove Post");
                        removeButton.setAlignmentX(Component.LEFT_ALIGNMENT);

                        removeButton.addActionListener(e->{
                            user1.removePost(post);
                            userList.notifyObserver("All");

                        });
                        postCard.add(removeButton);
                    }
                });
            }
            posts.revalidate();
            posts.repaint();

        }

    }

    @Override
    public void update(String type) {
        if(type.equals("User")){
            drawUserPosts(user);
        }
        else if(type.equals("All")){
            drawPosts(user);
        }
        else if (type.equals("Friends")){
            drawFriendsPosts(user);
        }

    }

    private class UserListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {

        }
    }

}
