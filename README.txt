Treasure Hunt Game
Treasure Hunt Game is a Java application that allows players to explore a randomly generated dungeon and collect treasures.

Requirements
Java 8 or higher
Maven

Installation
1. Clone the repository
git clone https://github.com/example/treasure-hunt.git

2.Build the project
cd treasure-hunt
mvn clean install

3.Run the game
java -jar target/treasure-hunt-1.0.jar

Usage
When you start the game, you will be prompted to enter the size of the dungeon (width and height). You will also be prompted to enter the degree of interconnectivity. Once the dungeon is generated, a random starting point and ending point will be chosen.

To move the player, enter one of the following commands:
north
east
south
west

To pick up treasure, enter the command "pickup".

Configuration
The following properties can be configured in the config.properties file:

treasurePercentage: the percentage of caves that will contain treasure (default is 20)
treasureTypes: a comma-separated list of treasure types (default is "diamond,ruby,sapphire")
wrappingDungeon: whether the dungeon should wrap around (default is true)
minPathLength: the minimum length of the path between the start and end points (default is 5)
maxAttempts: the maximum number of attempts to generate a dungeon with the specified constraints (default is 100)

Contributing
Contributions are welcome! Please create a pull request with your changes.
