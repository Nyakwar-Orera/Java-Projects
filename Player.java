public class Player {
        private String name;
        private int diamonds;
        private int rubies;
        private int sapphires;
        private int row;
        private int col;
        
        public Player(String name, int row, int col) {
            this.name = name;
            this.diamonds = 0;
            this.rubies = 0;
            this.sapphires = 0;
            this.row = row;
            this.col = col;
        }
        
        public Player(Object name2, int row2, int col2) {
        }

        public void moveNorth() {
            this.row--;
        }
        
        public void moveEast() {
            this.col++;
        }
        
        public void moveSouth() {
            this.row++;
        }
        
        public void moveWest() {
            this.col--;
        }
        
        public void addDiamond() {
            this.diamonds++;
        }
        
        public void addRuby() {
            this.rubies++;
        }
        
        public void addSapphire() {
            this.sapphires++;
        }
        
        public String getDescription() {
            String description = "Player " + this.name + " is in room (" + this.row + "," + this.col + ") ";
            if (diamonds > 0) {
                description += "and has " + diamonds + " diamonds. ";
            }
            if (rubies > 0) {
                description += "and has " + rubies + " rubies. ";
            }
            if (sapphires > 0) {
                description += "and has " + sapphires + " sapphires. ";
            }
            return description;
        }

        public Object getLocation() {
            return null;
        }

        public TreasureType[] getTreasures() {
            return null;
        }

        public MoveDirection getNextMove() {
            return null;
        }
    }
    
