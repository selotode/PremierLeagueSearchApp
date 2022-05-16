package PartialExam;

import java.io.*;
import java.net.*;
import java.util.List;

public class ServerMain {
    // Class for connection with client, database and handling data
    public static void main(String argv[]) throws Exception {

        Database db = new Database(); // Creating object from class Database

        String clientMessage; // String for client message

        ServerSocket socket = new ServerSocket(8181); //Creating server socket

        Socket connectionSocket = socket.accept(); // Try Connection
        BufferedReader fromTheClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); // Creating Buffered Reader for data from client
        DataOutputStream toTheClient = new DataOutputStream(connectionSocket.getOutputStream()); // Creating Output stream for sending data to client

        Teams teamsData = new Teams(db.getTeamsData(), db.getColumns()); //->
        // Creating new object from Teams class, contains the whole table from database and column names

        while (true) {
            clientMessage = fromTheClient.readLine(); // Read message from client

            System.out.println(clientMessage); // Print message in console

            String[] parts = clientMessage.split(" "); //Split message by empty spaces

            if (parts[0].equals("up")) { // Check for up - user and password
                if (db.login(parts[1], parts[2])) //Check in database if the username and password are a match
                    toTheClient.writeBytes("up" + " " + "true" + '\n');
                else
                    toTheClient.writeBytes("up" + " " + "false" + '\n');

            } else if (parts[0].equals("create")) { // Check for create - if the client requested a username with a password to be created
                if (!db.create(parts[1], parts[2])) // Check in database if new user can be created
                    toTheClient.writeBytes("created " + "Failed to create username and password!" + '\n');
                else
                    toTheClient.writeBytes("created" + " Created username: " + parts[1] + '\n');
            } else if (parts[0].equals("table")) { // Check for table, if the client requested the table with a search term, or the entire table
                List<Team> teamsList = db.getTeamsTable(parts[1]); //get list with possible matches,
                for (Team team : teamsList) {
                    toTheClient.writeBytes("data " + db.getColumns() + " " + team + '\n');
                }
                if (teamsList.isEmpty()) {
                    toTheClient.writeBytes("data " + db.getColumns() + " " + "empty" + '\n');
                }

            } else if (parts[0].equals("dataRequest")) { //Check for dataRequest, this sends the client the entire database table only to fill an object
                toTheClient.writeBytes("request " + teamsData.toString() + '\n');
            }

        }
    }
}