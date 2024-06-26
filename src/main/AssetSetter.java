package main;

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
}
