package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Monster extends Entity {
    BufferedImage image1, image2;
    public boolean isDied = false;

    public Monster(GamePanel gp, int x, int y) {
        super(gp);
        bounds = new Rectangle(8, 24, 32, 32);
        speed = 1;

        this.x = x;
        this.y = y;
        getImage();
    }

    public void kill() {
        isDied = true;
        collisionOn = false;
    }

    public void getImage() {
        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/resources/monster/monster_1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/resources/monster/monster_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (!isDied) {

            int randMove = new Random().nextInt(4);

            switch (randMove) {
                case 0:
                    direction = "right";
                    x += speed;
                    break;
                case 1:
                    direction = "left";
                    x -= speed;
                    break;
                case 2:
                    direction = "up";
                    y += speed;
                    break;
                case 3:
                    direction = "down";
                    y -= speed;
                    break;
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (!isDied) {
            BufferedImage image = null;
            if (spriteNum == 1) {
                image = image1;
            } else {
                image = image2;
            }
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        }
    }

}
