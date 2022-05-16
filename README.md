# PremierLeagueSearchApp
App with Login and Search bar for Premier League data

This app implements a client-server architecture. The client requests data from the server and the server checks that data with the database.

The database includes two tables:
  1. Teams - where the entire table for teams is kepts
     - Teams table consists of TeamID, Name, MatchesPlayed, Wins, Draws, Loses, GF, GA, GD, Points LastFiveGames
  2. Passwords - table for username and passwords

The client:
  -The client has 3 class files
  1. ClientMain file that includes connecting to server and communication, creating objects for drawing and table data
  2. GUI file that draws all 3 main windows, Login Window, Username Create Window and Main App Window
  3. TeamData file that handles data sent from the server

The server:
  -The server has 4 class files
  1. ServerMain file that communicates with the client and database, receives login info, data requests and search keywords
  2. Databse file that connects to databse, returns tables ,checks username and password with data from server, checks for search term
  3. Team file that contains data for each row
  4. Teams file that contains data list from entire table
