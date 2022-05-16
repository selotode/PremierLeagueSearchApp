package PartialExam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class GUI {
    // Class for drawing GUI
    String username;
    String password;
    String search;

    JFrame frame;
    JLabel lblUser, lblPassword, lblSearch, lblWelcome;
    JTextField tfUser, tfSearch;
    JPasswordField pfPassword;

    JButton btnRegister, btnCreate, btnLogin, btnSearch, btnLogout;

    JPanel panel;

    JTable jt;
    JScrollPane sp;

    DataOutputStream toTheServer;
    BufferedReader fromTheServer;

    boolean searchFlag = false;

    public GUI(DataOutputStream toTheServer) { // Initialize socket, all variables, login window and add listeners for buttons
        this.toTheServer = toTheServer;
        run();
        createLoginWindow();
        addListeners();

    }

    public void addListeners() { // Function that creates all listeners for all buttons

        btnRegister.addActionListener( //Listener for button Register ->
                // set text fields for example and change Login button to Create button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        tfUser.setText("Enter new username");
                        pfPassword.setText(".......");
                        changeLoginButton();
                    }
                });

        btnCreate.addActionListener( // Listener for button Create ->
                // 1. get data from text fields, 2. check for empty spaces, 3. check for empty fields, 4. send data to server, 5. restore Login button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // (1.)
                        username = tfUser.getText();
                        password = new String(pfPassword.getPassword());

                        // (2.)
                        if (username.split(" ").length > 1 || password.split(" ").length > 1) {
                            JOptionPane.showMessageDialog(null, "Username and Password must not contain spaces!");
                            changeCreateButton();
                            tfUser.setText("");
                            pfPassword.setText("");
                            return;
                        }

                        // (3.)
                        if (username.equals(""))
                            username = "?";
                        if (password.equals(""))
                            password = "?";

                        // (4.)
                        try {
                            toTheServer.writeBytes("create" + " " + username + " " + password + '\n');
                        } catch (IOException ex) {
                        }

                        // (5.)
                        tfUser.setText("");
                        pfPassword.setText("");
                        changeCreateButton();
                    }
                }
        );


        btnLogin.addActionListener( // Listener for button Login ->
                // 1. get data from text fields, 2. check for empty spaces, 3. check for empty fields, 4. send request and data to server
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // (1.)
                        username = tfUser.getText();
                        password = new String(pfPassword.getPassword());

                        // (2.)
                        if (username.split(" ").length > 1 || password.split(" ").length > 1) {
                            JOptionPane.showMessageDialog(null, "Username and Password must not contain spaces!");
                            return;
                        }

                        // (3.)
                        if (username.equals(""))
                            username = "?";
                        if (password.equals(""))
                            password = "?";

                        // (4.)
                        try {
                            toTheServer.writeBytes("dataRequest " + '\n');
                            toTheServer.writeBytes("up" + " " + username + " " + password + '\n');
                        } catch (IOException ex) {
                        }
                    }
                });


        btnSearch.addActionListener(
                new ActionListener() { // Listener for button Search ->
                    // get data from field, and send to server
                    public void actionPerformed(ActionEvent e) {
                        try {
                            search = tfSearch.getText();
                            if (search.equals(""))
                                search = "empty";
                            toTheServer.writeBytes("table " + search + " " + '\n');
                            searchFlag = true;

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );

        btnLogout.addActionListener( // Listener for button Logout ->
                //get data from fields, close current app window and change to login window
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        tfUser.setText("");
                        pfPassword.setText("");
                        close();
                        createLoginWindow();
                        searchFlag = true;
                    }
                }
        );

    }

    public boolean getSearchFlag() { // Function that returns search flag

        return searchFlag;
    }

    public void setSearchFlag(boolean flag){ // Function that sets search flag

        searchFlag = flag;
    }

    public void run() { // Function that initializes all variables

        lblUser = new JLabel("User Name:");
        tfUser = new JTextField(20);
        lblPassword = new JLabel("Password:");
        pfPassword = new JPasswordField(20);
        btnRegister = new JButton("Register");
        btnLogin = new JButton("Login");
        btnCreate = new JButton("Create");
        btnSearch = new JButton("Search");
        btnLogout = new JButton("Logout");
        lblSearch = new JLabel("Search for a team:");
        tfSearch = new JTextField(30);
    }

    public void createLoginWindow() { // Function that creates login window
        frame = new JFrame("Login window");

        lblUser.setBounds(100, 8, 120, 20);

        tfUser.setBounds(100, 27, 193, 28);
        lblUser.setLabelFor(tfUser);


        lblPassword.setBounds(100, 55, 120, 20);

        pfPassword.setBounds(100, 75, 193, 28);
        lblPassword.setLabelFor(pfPassword);


        btnRegister.setBounds(200, 110, 90, 25);

        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBackground(Color.BLACK);


        btnLogin.setBounds(100, 110, 70, 25);

        btnLogin.setForeground(Color.BLACK);
        btnLogin.setBackground(Color.GREEN);


        btnCreate.setBounds(100, 110, 90, 25);

        btnCreate.setForeground(Color.WHITE);
        btnCreate.setBackground(Color.BLUE);


        panel = new JPanel();
        panel.setLayout(null);

        panel.add(lblUser);
        panel.add(tfUser);
        panel.add(lblPassword);
        panel.add(pfPassword);
        panel.add(btnLogin);
        panel.add(btnRegister);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(new Point(500, 300));
        frame.setSize(400, 200);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        update();

    }

    public void changeLoginButton() { // Function that changes Login button to Create button
        lblUser.setText("Create new username");
        lblPassword.setText("Create new password:");
        panel.remove(btnLogin);
        panel.add(btnCreate);
        update();
    }

    public void changeCreateButton() {  // Function that changes Create button to Login button
        lblUser.setText("User Name:");
        lblPassword.setText("Password:");
        panel.remove(btnCreate);
        panel.add(btnLogin);
        update();
    }

    public void deleteTable() { // Function that deletes table
        panel.remove(sp);
        update();
    }

    public void updateTable() { //Function that updates table
        panel.add(sp);
        update();
    }

    public void update() { // Function that checks all GUI variables and redraws screen
        panel.revalidate();
        panel.repaint();
    }

    public void close() { // Function that closes window
        frame.setVisible(false);
        frame.dispose();
    }

    public void openAppWindow() { // Function that creates app window
        frame = new JFrame("App Window");

        lblSearch.setLabelFor(tfSearch);

        lblSearch.setBounds(50, 20, 120, 25);
        tfSearch.setBounds(170, 20, 200, 25);
        btnSearch.setBounds(380, 20, 90, 25);
        btnLogout.setBounds(480, 20, 90, 25);

        jt = new JTable();
        sp = new JScrollPane(jt);
        sp.setBounds(50, 50, 800, 500);

        lblWelcome = new JLabel("Welcome " + username);
        lblWelcome.setBounds(750, 20, 120, 25);

        panel = new JPanel();
        panel.setLayout(null);

        panel.add(lblWelcome);
        panel.add(lblSearch);
        panel.add(tfSearch);
        panel.add(btnSearch);
        panel.add(btnLogout);
        panel.add(sp);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public String getSearch() {
        return tfSearch.getText();
    }

    public String getUsername() {
        return tfUser.getText();
    }

    public String getPassword() {
        return new String(pfPassword.getPassword());
    }

    public void setScrollPanel(String[][] data, String[] columns) { // Function that sets table data to scroll pane
        jt = new JTable(data, columns);
        sp = new JScrollPane(jt);
        sp.setBounds(50, 50, 800, 500);
    }
}
