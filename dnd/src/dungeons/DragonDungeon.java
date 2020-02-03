package dungeons;

public class DragonDungeon extends Dungeon {
    public DragonDungeon(int x, int y) {
        super(x, y);
        type = "dragon";
        hasDragon = true;
    }
}
