import java.util.*;

enum CellType {
    CAVE, TUNNEL;
}

class Cell {
    private CellType type;
    private List<Cell> neighbors;
    private List<String> treasures;

    public Cell(CellType type) {
        this.type = type;
        this.neighbors = new ArrayList<>();
        this.treasures = new ArrayList<>();
    }

    public CellType getType() {
        return type;
    }

    public List<Cell> getNeighbors() {
        return neighbors;
    }

    public List<String> getTreasures() {
        return treasures;
    }

    public void addNeighbor(Cell neighbor) {
        neighbors.add(neighbor);
    }

    public void addTreasure(String treasure) {
        treasures.add(treasure);
    }
}


class Dungeon {
    
    private Cell[][] cells;
    private int startCol;
    public int getStartCol() {
        return startCol;
    }

    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    private int startRow;
    public int getStartRow() {
        return startRow;
    }
    private int endRow;
    public int getEndRow() {
        return endRow;
    }
    private int endCol;
    public int getEndCol() {
        return endCol;
    }
    public Dungeon(int numRows, int numCols, int interconnectivity, int treasurePercentage) {
        this.startCol = 0;
        this.endRow = numRows - 1;
        this.endCol = numCols - 1;
        cells = new Cell[numRows][numCols];

        // Initialize cells
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                cells[row][col] = new Cell(CellType.CAVE);
            }
        }

        // Connect cells
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Cell cell = cells[row][col];

                // Connect to neighboring cells
                if (col > 0) {
                    cell.addNeighbor(cells[row][col - 1]);
                    cells[row][col - 1].addNeighbor(cell);
                }
                if (row > 0) {
                    cell.addNeighbor(cells[row - 1][col]);
                    cells[row - 1][col].addNeighbor(cell);
                }
                if (col < numCols - 1) {
                    cell.addNeighbor(cells[row][col + 1]);
                    cells[row][col + 1].addNeighbor(cell);
                }
                if (row < numRows - 1) {
                    cell.addNeighbor(cells[row + 1][col]);
                    cells[row + 1][col].addNeighbor(cell);
                }
            }
        }
        // Make dungeon interconnected
        Random rand = new Random();
            for (int i = 0; i < interconnectivity; i++) {
                int row = rand.nextInt(numRows);
                int col = rand.nextInt(numCols);
                // Choose a random cave to connect
                int x1 = rand.nextInt(row);
                int y1 = rand.nextInt(col);
            while (!isCave(x1, y1)) {
                x1 = rand.nextInt(row);
                y1 = rand.nextInt(col);
            }

        // Choose a random adjacent location to connect it to
        int x2 = x1;
        int y2 = y1;
        while ((x2 == x1 && y2 == y1) || !isPassage(x2, y2)) {
            int dir = rand.nextInt(4);
            switch (dir) {
                case 0: // North
                    x2 = x1;
                    y2 = mod(y1 - 1, col);
                    break;
                case 1: // East
                    x2 = mod(x1 + 1, row);
                    y2 = y1;
                    break;
                case 2: // South
                    x2 = x1;
                    y2 = mod(y1 + 1, col);
                    break;
                case 3: // West
                    x2 = mod(x1 - 1, row);
                    y2 = y1;
                    break;
            }
        }

        // Connect the two locations
        cells[row][col].addNeighbor(cells[x2][y2]);
        cells[x2][y2].addNeighbor(cells[row][col]);
        
        /* cells[endCol].addTreasure(diamond);
        cells[(endCol + 2) % 4].addTreasure(ruby);
        */
    } 
}

    private int mod(int i, int height) {
        return 0;
    }

    private boolean isPassage(int x2, int y2) {
        return false;
    }

    private boolean isCave(int x1, int y1) {
        return false;
    }

    public void enter(Player player) {
    }

	public void setStartLocation(int i, int j) {
        getRow();
        getCol();
	}

    private void getCol() {
    }

    private void getRow() {
    }

    public int getNumRows() {
        return 0;
    }

    public int getInterconnectivity() {
        return 0;
    }

    public void connectLocations(int row1, int col1, int row2, int col2) {
    }

    public int getNumCols() {
        return 0;
    }

    public void addTreasureToCaves(double d, String diamond, String ruby, Object sAPPHIRE) {
    }

    public char[] describeLocation(Object location) {
        return null;
    }

    public boolean isEndLocation(Object location) {
        return false;
    }

    public boolean movePlayer(Player player, MoveDirection move) {
        return false;
    }

    public Object getStartLocation() {
        return null;
    }
}   
