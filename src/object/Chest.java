package object;

import java.awt.Rectangle;

import javax.imageio.ImageIO;

import entity.Player;

public class Chest extends SuperObject {
    public boolean isOpen = false;

    public Chest(int x, int y, int width, int height, boolean collision) {
        this.name = "Chest";
        this.x = x;
        this.y = y;
        this.bounds = new Rectangle(8, 16, width, height);
        this.collision = collision;
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
