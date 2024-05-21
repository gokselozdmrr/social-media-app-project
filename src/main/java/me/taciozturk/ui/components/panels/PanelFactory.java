package me.taciozturk.ui.components.panels;

import me.taciozturk.Group;
import me.taciozturk.User;
import me.taciozturk.UserList;

import javax.swing.*;

public class PanelFactory {
    private int width;
    private int height;
    private User user;
    private UserList userList;

    public PanelFactory(int _width, int _height, User _user, UserList _userList) {
        this.user = _user;
        this.width = _width;
        this.height = _height;
        this.userList = _userList;
    }
    public enum PanelType {
        LEFT, MID, RIGHT
    }

    public JPanel create(PanelType type) {
        switch (type) {
            case LEFT ->  {
                return new LeftPanel(this.width,this.height,this.user, Group.getGroups()).create();
            }
            case MID ->  {
                return new MidPanel(this.width,this.height,this.user,userList).create();
            }
            case RIGHT ->  {
                return new RightPanel(this.width,this.height,this.user,userList).create();
            }
            default -> {
                throw new IllegalArgumentException("Panel type not supported");
            }
        }
    }
}
