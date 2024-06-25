package main;

import object.Chest;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.objects[0] = new Chest(2 * gp.tileSize, 3 * gp.tileSize, 32, 32, true);
        gp.objects[1] = new Chest(6 * gp.tileSize, 3 * gp.tileSize, 32, 32, true);
    }
}
