package dungeons;

public class FightDungeon extends Dungeon {
    public FightDungeon(int x, int y) {
        super(x, y);
        type = "fight";
        hasFight = true;
    }
}
