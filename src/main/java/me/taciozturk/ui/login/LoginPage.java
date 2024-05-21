package me.taciozturk.ui.login;

import me.taciozturk.Authentication.Login;
import me.taciozturk.User;
import me.taciozturk.UserList;
import me.taciozturk.ui.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

public class LoginPage extends JFrame implements ActionListener {

    private JLabel lblEmail, lblSifre;
    private JTextField txtEmail, txtSifre;
    private JButton btnGirisYap;
    private UserList userList;
    User loginedUser;


    public LoginPage(UserList userList) {
        super("Giriş Ekranı");
        this.loginedUser  = new User();
        this.userList = userList;

        lblEmail = new JLabel("E-Mail:");
        lblSifre = new JLabel("Şifre:");

        txtEmail = new JTextField(20);
        txtSifre = new JPasswordField(20);

        btnGirisYap = new JButton("Giriş Yap");
        btnGirisYap.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));


        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblSifre);
        panel.add(txtSifre);
        panel.add(btnGirisYap);

        add(panel);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String eMail = txtEmail.getText();
        String sifre = txtSifre.getText();
        Login login = new Login();

        try {
            loginedUser = login.login(eMail, sifre, this.userList);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        //Kullanıcı doğru giriş yaparsa uygulama çalıştırılıyor aksi halde hata mesajı bastırılıyor
        if (loginedUser != null) {
            this.dispose();
            Application app = new Application();
            try {
                app.run(userList,loginedUser);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }


        } else {
            // Kullanıcıya hata mesajı gösterme
            JOptionPane.showMessageDialog(null, "Hatalı kullanıcı adı veya şifre!", "Hata", JOptionPane.ERROR_MESSAGE);

        }

    }

    public User getLoginedUser(){
        return loginedUser;
    }


}
