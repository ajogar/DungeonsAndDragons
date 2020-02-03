package dungeons;

public class TrapDungeon extends Dungeon {
    public TrapDungeon(int x, int y) {
        super(x, y);
        type = "trap";
        hasTrap = true;
    }
}
