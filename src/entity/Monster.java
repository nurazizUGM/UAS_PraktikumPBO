package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Monster extends Entity {
    BufferedImage image1, image2;

    public Monster(GamePanel gp, int x, int y) {
        super(gp);
        bounds = new Rectangle(8, 24, 32, 32);
        speed = 1;

        this.x = x;
        this.y = y;
        getImage();
    }

    public void getImage() {
        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/resources/monster/monster_1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/resources/monster/monster_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        if (spriteNum == 1) {
            image = image1;
        } else {
            image = image2;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
