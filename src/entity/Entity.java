package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;

public abstract class Entity {
    public int x, y, speed;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle bounds;
    public boolean collisionOn = false;
    public boolean isDied = false;

    GamePanel gp;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public abstract void draw(Graphics2D g2);
}
