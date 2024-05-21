package me.taciozturk.ui.components.panels;

import me.taciozturk.Observer.IObserver;
import me.taciozturk.Search;
import me.taciozturk.User;
import me.taciozturk.UserList;
import me.taciozturk.ui.components.cards.UserCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;


public class RightPanel extends JPanel implements ICreatePanel, IObserver {
    private User user;
    private int width;
    private int height;
    UserList userList;
    private JTextField searchField;
    private JPanel searchResultPanel;
    private List<User> searchResults;



    public RightPanel(int _width, int _height, User _user, UserList _userList) {
        super();
        this.user = _user;
        this.width = _width;
        this.height = _height;
        this.userList = _userList;

    }

    public RightPanel create(){
        this.removeAll();
        BoxLayout boxLayout = new BoxLayout(this,BoxLayout.Y_AXIS);

        this.setLayout(boxLayout);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(width * 2 / 8, height ));

        searchField = new JTextField();
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchField.getPreferredSize().height));
        searchField.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performSearch();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    searchField.setText(""); // Arama çubuğunu temizle
                    searchResultPanel.removeAll(); // Sonuç panelini temizle
                    searchField.revalidate();
                    searchField.repaint();
                    searchResultPanel.revalidate();
                    searchResultPanel.repaint();
                }
            }
        });

        this.add(searchField);

        searchResultPanel = new JPanel();
        searchResultPanel.setLayout(new BoxLayout(searchResultPanel, BoxLayout.Y_AXIS));
        searchResultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(searchResultPanel);

        JLabel title = new JLabel("FRIENDS");
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.add(title);

        this.add(new JSeparator(SwingConstants.HORIZONTAL));

        user.getConnections().forEach(connection -> {
            UserCard card = new UserCard(userList.getUserById(connection));
            card.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.add(card);
        });
        revalidate();
        repaint();
        return this;
    }
    private void performSearch() {
        String query = searchField.getText().toLowerCase();
        searchResultPanel.removeAll();
        searchResults = new Search().search(query, userList);

        for (User resultUser : searchResults) {
            JLabel usernameLabel = new JLabel(resultUser.getName());
            JButton viewProfileButton;
            if(!(user.getConnections().contains(resultUser.getId()))){
                viewProfileButton = new JButton("Add Friend");
            }else{
                viewProfileButton = new JButton("Remove Friend");
            }

            viewProfileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!(user.getConnections().contains(resultUser.getId()))){

                        user.addConnection(resultUser);
                    }else{
                        user.removeConnection(resultUser);
                    }

                    userList.notifyObserver("Friends");
                    userList.notifyObserver("All");
                    revalidate();
                    repaint();

                }

            });

            searchResultPanel.add(usernameLabel);
            searchResultPanel.add(viewProfileButton);
        }

        searchResultPanel.revalidate();
        searchResultPanel.repaint();
    }

    @Override
    public void update(String type) {
        if(type.equals("Friends")){
            create();
        }

    }
}
