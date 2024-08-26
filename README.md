# MyShelfie Videogame
## Overview
The program is a digital implementation in `Java` of the <b>MyShelfie</b> tabletop game by <b>Cranio Games</b>.

### Authors
Politecnico di Milano - Prof. Margara section - <b>AM29</b> group
- <b>Alessandro Bellotta</b> (alessandro.bellotta@mail.polimi.it)
- <b>Mattia Pejano</b> (mattia.pejano@mail.polimi.it)
- <b>Nicola Colantuono</b> (nicola.colantuono@mail.polimi.it)
- <b>Andrea Cerfoglia</b> (andrea.cerfoglia@mail.polimi.it)

### License
The project was carried out as part of the 2022/2023 '<b>Software Engineering</b>' course at <b>Politecnico of Milano.</b>

All rights to the MyShelfie game are owned by Cranio Games.

## Project Specifications
The project consisted of developing a digital version of the tabletop game MyShelfie according to the rules described in the `requirements/MyShelfie_Rulebook_ITA.pdf` document.

The program is realized following a Model-View-Controller design pattern: several UML diagrams are provided to clarify the structure and dynamics of the code.

A game instance is made up of a single server and multiple clients, which connect to it.

As part of the grading of the project, a number of possible functionalities could be implemented (these are described in greater detail in the `requirements/requirements.pdf` document. The implemented functionalities are:

- Basic Rules: games of 2-4 players.
- Complete Rules: games of 2-4 players.
- Socket: remote connection to the server.
- CLI: command line game interface.


## Running the Game
The game consists of a single jar file by the name <code>AM29-jar-with-dependencies.jar</code>. It can be found in <code>/deliverables</code>.

This file holds both the Server and the CLI, one of which can be selected when booting.

To run the jar file, use the command

<code>java -jar AM29-jar-with-dependencies.jar</code>

from the command line in the jar's folder.


### Server
1) <code>java -jar AM29-jar-with-dependencies.jar 127.0.0.1 4444</code>
2) Select 1 to start the server.

The machine running the server must be reachable from the clients in order to play the game. To start the server, select the <code>1</code> option when booting.

By default, when launching this command, the server starts to listen for incoming messages on port <code>4444</code> and will accept the connection of any number of clients.


Once launched, the server will print the events' log on the standard output.

### Client
1) <code>java -jar AM29-jar-with-dependencies.jar clientip</code>
clientip is the personal ip of the client, can be found using <code>ipconfig</code> command in the prompt.
2) 
The CLI can be run by selecting the <code>2</code> option when booting.

Losing connection with the server will cause the termination of the client application.

#### Login

After selecting the host ip and port number to connect to, the player will be asked to choose a username.
If no games are currently waiting for more players to join them a new game will be created, and the user will be asked to choose its number of players.

While a player is choosing the number of players for the next game no more players can join the server.
Once the game's size has been decided (and if it is greater than one) any joining players will automatically be added to that game.
The creator and any joining players will be put into a waiting room until the game has reached its full capacity, at which point it will start automatically.

Disconnecting during the login phase will cause the player to be removed from the game.

## File System Structure
* `deliverables`: various explanatory documents, such as a description of the connection protocol and various UMLs. Jar file
* `javadoc`: documentation for the classes and methods generated from the comments in the code
* `requirements`: specifications for the project and ruleset for the game
* `peerreview`: uml and communication protocol peer review files
* `src`: code of the project and tests
