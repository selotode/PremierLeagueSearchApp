import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.Arrays;


public class ClientMain {
    // Class for connecting to server and communication

    public static void main(String argv[]) throws Exception {

        String receivedMessage; // String for message from server

        Socket clientSocket = new Socket("127.0.0.1", 8181); // Attempt to create socket with IP and port
        DataOutputStream toTheServer = new DataOutputStream(clientSocket.getOutputStream()); // Creating Output stream for sending data to server
        BufferedReader fromTheServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  // Creating Buffered Reader for data from server

        GUI gui = new GUI(toTheServer); // Create new GUI object
        TeamData td = new TeamData(); // Create new TeamData object


        while (true) {

            receivedMessage = fromTheServer.readLine(); // Check for message from server

            System.out.println(receivedMessage); // Print out message from server in console

            String parts[] = receivedMessage.split(" "); // Split message by blank spaces

            if (parts[0].equals("request")) { // Check for request sent back from server
                td.addColumnsNames(Arrays.copyOfRange(parts, 1, parts.length)); //Add data to object of TeamData
            } else if (parts[0].equals("up")) { // Check for up - user and password from server, if login is valid, create app window
                if (parts[1].equals("true")) {
                    gui.close();
                    gui.openAppWindow();
                    toTheServer.writeBytes("table " + "empty " + '\n'); // Request entire table from server
                } else
                    JOptionPane.showMessageDialog(null, "Incorrect username or password , Please try again"); // ->
                    // If username and password is incorrect show error message

            } else if (parts[0].equals("data")) { // Check for data - get results of search from server
                if (gui.getSearchFlag()) { // If search was pressed clear entire table
                    td.clearData();
                    gui.setSearchFlag(false);
                }
                if(parts[2].equals("empty")) // If no results are returned handle
                {
                    gui.deleteTable();
                    gui.setScrollPanel(new String[0][0], td.getColumnsData());
                    gui.updateTable();
                    continue;
                }
                // Add data to object of TeamData
                td.addData(Arrays.copyOfRange(parts, 1, parts.length));
                gui.deleteTable();
                gui.setScrollPanel(td.getData(), td.getColumnsData());
                gui.updateTable();
            } else if (parts[0].equals("created")) // Check for created - if the username was created send message to client
                JOptionPane.showMessageDialog(null, String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)));
        }
    }

}