import java.util.List;

public class Location {
    public static final String Type = null;
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Location other) {
        return this.x == other.x && this.y == other.y;
    }

    public List<Location> getNeighbors() {
        return null;
    }

    public List<Location> getTreasures() {
        return null;
    }
}
