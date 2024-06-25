package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int x, y, speed;
    public BufferedImage up1, up2, left1, left2, right1, right2, down1, down2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle bounds;
    public boolean collisionOn = false;
}
