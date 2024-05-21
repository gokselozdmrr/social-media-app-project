package me.taciozturk.ui.components.panels;

import me.taciozturk.Group;
import me.taciozturk.Search;
import me.taciozturk.User;
import me.taciozturk.UserList;
import me.taciozturk.ui.components.cards.GroupCard;
import me.taciozturk.ui.views.UserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class LeftPanel extends JPanel implements ICreatePanel {

    private User user;
    private int width;
    private int height;
    private JTextField searchField;
    private JPanel searchResultPanel;
    private List<Group> searchResults;
    private ArrayList<Group> groups ;

    public LeftPanel(int _width, int _height, User _user, ArrayList<Group> groups ) {
        super();
        this.user = _user;
        this.width = _width;
        this.height = _height;
        this.searchResults = new ArrayList<>();
        this.groups = groups;
    }
    

    @Override
    public LeftPanel create() {

        int panelWidth = width * 2 / 8;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(panelWidth, height));

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

        // Read the avatar image, resize and add to JLabel to use it as a component in JPanel

        JLabel avatar = new JLabel();
        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        avatar.setAlignmentY(Component.CENTER_ALIGNMENT);
        avatar.setIcon(user.getAvatar());
        avatar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4, true));
        avatar.setOpaque(false);
        this.add(avatar);

        JLabel username = new JLabel(user.getName());
        username.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(username);

        this.add(new JSeparator(SwingConstants.HORIZONTAL));

        JLabel title = new JLabel("GROUPS");
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);

        this.add(new JSeparator(SwingConstants.HORIZONTAL));

        user.getGroups().forEach(e->{
            GroupCard groupCard = new GroupCard(Group.getGroup(e));
            this.add(groupCard);

        });
        JButton logOut = new JButton("Log out");
        logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(logOut);

        logOut.addActionListener(e->{
            System.exit(0);
        });

        return this;
    }

    private void performSearch() {
        String query = searchField.getText().toLowerCase();
        searchResultPanel.removeAll();
        searchResults = new Search().search(query);

        for (Group resultGroups : searchResults) {
            JLabel groupNameLabel = new JLabel(resultGroups.getName());
            JButton groupActionButton;
            if (!user.getGroups().contains(resultGroups.getGroupID())) {
                groupActionButton = new JButton("Join Group");
            }else {
                groupActionButton = new JButton("Leave Group");
            }

            groupActionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!user.getGroups().contains(resultGroups.getGroupID())){
                        user.joinGroup(resultGroups);
                    }else {
                        user.leaveGroup(resultGroups);
                    }
                    removeAll();
                    create();
                    revalidate();
                    repaint();

                }

            });

            searchResultPanel.add(groupNameLabel);
            searchResultPanel.add(groupActionButton);
        }

        searchResultPanel.revalidate();
        searchResultPanel.repaint();
    }

}
