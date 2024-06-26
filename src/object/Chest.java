package object;

import javax.imageio.ImageIO;

import entity.Player;

public class Chest extends Object {
    public boolean isOpen = false;
    public boolean collision = true;

    public Chest(int x, int y) {
        this.name = "chest";
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/chest.png"));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void open(Player player) {
        player.haveWeapon = true;
        isOpen = true;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/chest_opened.png"));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
