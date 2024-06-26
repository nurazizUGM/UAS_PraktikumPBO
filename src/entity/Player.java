package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.AudioPlayer;
import main.GamePanel;
import main.KeyHandler;
import object.Chest;
import object.Object;

public class Player extends Entity implements Action {
    public BufferedImage up1, up2, left1, left2, right1, right2, down1, down2;
    KeyHandler keyHandler;
    public boolean haveWeapon = false;
    private AudioPlayer walkingSound;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        super(gp);
        this.keyHandler = keyHandler;

        speed = 2;
        direction = "down";

        bounds = new Rectangle(8, 24, 32, 32);
        getImage();
        walkingSound = AudioPlayer.playFile("walking.wav", false);
        respawn();
    }

    public void attack(Entity entity) {
        Monster monster = (Monster) entity;
        monster.kill();
    }

    public void respawn() {
        super.respawn();
        haveWeapon = false;
        x = 50;
        y = 50;
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
        Object collidedObject = gp.collisionChecker.checkObject(this);

        // check if the player collides with an object
        if (collidedObject != null) {
            switch (collidedObject.name) {
                case "chest":
                    Chest chest = (Chest) collidedObject;
                    if (!chest.isOpen) {
                        chest.open(this);
                        gp.score += 10;
                    }
                    break;
                case "portal":
                    gp.isFinished = true;
                    break;
                default:
                    break;
            }
        }

        Monster collidedMonster = gp.collisionChecker.checkMonster(this);
        if (collidedMonster != null) {
            if (haveWeapon) {
                attack(collidedMonster);
                gp.score += 50;
                gp.killedMonsters++;
            } else {
                kill();
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

            if (walkingSound.isPaused()) {
                walkingSound.resumeAudio();
            }
        } else {
            if (!walkingSound.isPaused()) {
                walkingSound.pause();
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
