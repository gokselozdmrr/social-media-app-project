package me.taciozturk.ui.components.cards;

import javax.swing.*;
import java.awt.*;

public class PostCard extends JPanel {

    public PostCard(String userPost) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JTextArea post = new JTextArea(userPost);
        post.setEditable(false);
        post.setLineWrap(true);
        post.setWrapStyleWord(true);
        post.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        this.add(post);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));


    }
}
