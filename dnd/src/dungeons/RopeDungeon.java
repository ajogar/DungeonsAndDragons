package dungeons;

public class RopeDungeon extends Dungeon {
    public RopeDungeon(int x, int y) {
        super(x, y);
        type = "rope";
        hasRope = true;
    }
}
