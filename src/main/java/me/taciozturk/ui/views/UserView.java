package me.taciozturk.ui.views;

import me.taciozturk.Observer.IObserver;
import me.taciozturk.User;
import me.taciozturk.UserList;
import me.taciozturk.ui.components.panels.PanelFactory;

import javax.swing.*;
import java.awt.*;

public class UserView extends JPanel {

    public UserView(int width, int height, User user, UserList userList) {
        PanelFactory panelFactory = new PanelFactory(width,height,user,userList);
        JPanel leftPanel = panelFactory.create(PanelFactory.PanelType.LEFT);
        JPanel midPanel = panelFactory.create(PanelFactory.PanelType.MID);
        JPanel rightPanel = panelFactory.create(PanelFactory.PanelType.RIGHT);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(midPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);

        //Arkadaş eklediğimizde panellerdeki güncellemeleri yapabilmek için panelleri observer olarak ekliyoruz.
        userList.addObserver((IObserver) midPanel);
        userList.addObserver((IObserver) rightPanel);


        this.setSize(width, height);
    }
}
