package me.taciozturk.ui.components.cards;

import me.taciozturk.Group;
import me.taciozturk.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GroupCard extends JPanel {
    private Group group;

    public GroupCard(Group group) {
        this.group = group;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //JLabel groupcard = new JLabel();

        JLabel name = new JLabel(this.group.getName());
        this.add(name);

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    }

}
