package main;

import entity.Monster;
import object.Chest;
import object.Portal;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.objects[0] = new Chest(2 * gp.tileSize, 3 * gp.tileSize);
        gp.objects[1] = new Chest(6 * gp.tileSize, 3 * gp.tileSize);
        gp.objects[2] = new Portal(15 * gp.tileSize, 5 * gp.tileSize);
        gp.objects[3] = new Portal(15 * gp.tileSize, 6 * gp.tileSize);
    }

    public void setMonster() {
        gp.monsters[0] = new Monster(gp, 10 * gp.tileSize, 5 * gp.tileSize);
        gp.monsters[1] = new Monster(gp, 8 * gp.tileSize, 5 * gp.tileSize);
        System.out.println("Monster set");
    }
}
