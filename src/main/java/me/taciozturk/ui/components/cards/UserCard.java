package me.taciozturk.ui.components.cards;

import me.taciozturk.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserCard extends JPanel {
    private User user;

    public UserCard(User user) {
        this.user = user;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JLabel avatar = new JLabel();
        avatar.setIcon(new ImageIcon(this.user
                .getAvatar()
                .getImage()
                .getScaledInstance(60,60, Image.SCALE_DEFAULT)));

        avatar.setOpaque(false);
        JLabel name = new JLabel(this.user.getName());
        this.add(avatar);
        this.add(name);

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
