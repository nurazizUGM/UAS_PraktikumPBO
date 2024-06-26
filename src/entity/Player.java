package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import object.Chest;
import object.SuperObject;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;
    public boolean haveWeapon = false;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        x = 50;
        y = 50;
        speed = 2;
        direction = "down";

        bounds = new Rectangle(8, 24, 32, 32);
        getImage();
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/p_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/p_up_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/p_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/p_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/p_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/p_right_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/p_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/p_down_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        boolean isMoving = false;
        if (keyHandler.upPressed && y > speed) {
            isMoving = true;
            direction = "up";
        } else if (keyHandler.downPressed && y < gp.screenHeight - gp.tileSize - speed) {
            isMoving = true;
            direction = "down";
        } else if (keyHandler.leftPressed && x > speed) {
            isMoving = true;
            direction = "left";
        } else if (keyHandler.rightPressed && x < gp.screenWidth - gp.tileSize - speed) {
            isMoving = true;
            direction = "right";
        }

        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        SuperObject collidedObject = gp.collisionChecker.checkObject(this);

        // check if the player collides with an object
        if (collidedObject != null) {
            switch (collidedObject.name) {
                case "chest":
                    Chest chest = (Chest) collidedObject;
                    if (!chest.isOpen) {
                        chest.open(this);
                        System.out.println("open chest");
                    }
                    break;
                case "portal":
                    gp.isFinished = true;
                    break;
                default:
                    break;
            }
        }
        if (collisionOn == false && isMoving) {
            switch (direction) {
                case "up":
                    y -= speed;
                    break;
                case "down":
                    y += speed;
                    break;
                case "left":
                    x -= speed;
                    break;
                case "right":
                    x += speed;
                    break;
            }
        }

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            spriteCounter++;
            if (spriteCounter > 6) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
