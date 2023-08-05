import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;



public class Main {
    public static void main(String[] args) {
        // Create dungeon with 6 rows and 8 columns
        Dungeon dungeon = new Dungeon(0, 0, 0, 0);

        // Add treasures to 20% of the caves
        dungeon.addTreasureToCaves(0.2, TreasureType.DIAMOND, TreasureType.RUBY, TreasureType.SAPPHIRE);

        // Set the start and end locations
        dungeon.setStartLocation(0, 0);
        dungeon.setStartLocation(5, 7);

        // Make the dungeon interconnected
        Random rand = new Random();
        for (int i = 0; i < dungeon.getInterconnectivity(); i++) {
            int row1 = rand.nextInt(dungeon.getNumRows());
            int col1 = rand.nextInt(dungeon.getNumCols());
            int row2 = rand.nextInt(dungeon.getNumRows());
            int col2 = rand.nextInt(dungeon.getNumCols());
            dungeon.connectLocations(row1, col1, row2, col2);
        }

        // Create a new player with no treasure
        Player player = new Player(null, dungeon.getStartLocation().getRow(), dungeon.getStartLocation().getCol());
        // Enter the dungeon at the start location
        dungeon.enter(player);

        // Game loop
        boolean playing = true;
        while (playing) {
            // Describe player's current location
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));


            // Check if player has reached the end
            if (dungeon.isEndLocation(player.getLocation())) {
                System.out.println("Congratulations! You have reached the end of the dungeon!");
                System.out.println("You collected the following treasures:");
                for (TreasureType treasure : player.getTreasures()) {
                    System.out.println("- " + treasure);
                }
                playing = false;
            } else {
                // Ask player for next move
                MoveDirection move = player.getNextMove();

                // Move player in the chosen direction
                if (dungeon.movePlayer(player, move)) {
                    // Player successfully moved
                    System.out.println("You moved " + move);
                } else {
                    // Player could not move in chosen direction
                    System.out.println("You cannot move " + move + " from this location.");
                }
            }
        }
    }
}

