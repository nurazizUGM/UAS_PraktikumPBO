package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    GamePanel gp;
    Font arial_40 = new Font("Arial", Font.PLAIN, 40);
    public int menu = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        if (gp.isPaused) {
            int y = gp.tileSize * 4;
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String message = "Paused";
            g2.drawString(message, getCenteredX(message, g2), y);

            y += gp.tileSize * 2;
            message = "Resume";
            g2.drawString(message, getCenteredX(message, g2), y);
            if (menu == 0) {
                g2.drawString(">", getCenteredX(message, g2) - 30, y);
            }

            y += gp.tileSize;
            message = "New Game";
            g2.drawString(message, getCenteredX(message, g2), y);
            if (menu == 1) {
                g2.drawString(">", getCenteredX(message, g2) - 30, y);
            }

            y += gp.tileSize;
            message = "Quit";
            g2.drawString(message, getCenteredX(message, g2), y);
            if (menu == 2) {
                g2.drawString(">", getCenteredX(message, g2) - 30, y);
            }
        } else if (gp.isFinished) {
            int y = gp.tileSize * 4;
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String message = "You Win!";
            g2.drawString(message, getCenteredX(message, g2), y);

            y += gp.tileSize;
            message = "Score: " + gp.score;
            g2.drawString(message, getCenteredX(message, g2), y);

            y += gp.tileSize * 2;
            message = "New Game";
            g2.drawString(message, getCenteredX(message, g2), y);
            if (menu == 0) {
                g2.drawString(">", getCenteredX(message, g2) - 30, y);
            }

            y += gp.tileSize;
            message = "Quit";
            g2.drawString(message, getCenteredX(message, g2), y);
            if (menu == 1) {
                g2.drawString(">", getCenteredX(message, g2) - 30, y);
            }
        } else if (gp.isGameOver) {
            int y = gp.tileSize * 4;
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String message = "Game Over";
            g2.drawString(message, getCenteredX(message, g2), y);

            y += gp.tileSize * 2;
            message = "New Game";
            g2.drawString(message, getCenteredX(message, g2), y);
            if (menu == 0) {
                g2.drawString(">", getCenteredX(message, g2) - 30, y);
            }

            y += gp.tileSize;
            message = "Quit";
            g2.drawString(message, getCenteredX(message, g2), y);
            if (menu == 1) {
                g2.drawString(">", getCenteredX(message, g2) - 30, y);
            }
        } else if (gp.isMenu) {
            int y = gp.tileSize * 4;
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String message = "Dungeon Game";
            g2.drawString(message, getCenteredX(message, g2), y);

            y += gp.tileSize * 2;
            message = "New Game";
            g2.drawString(message, getCenteredX(message, g2), y);
            if (menu == 0) {
                g2.drawString(">", getCenteredX(message, g2) - 30, y);
            }

            y += gp.tileSize;
            message = "Quit";
            g2.drawString(message, getCenteredX(message, g2), y);
            if (menu == 1) {
                g2.drawString(">", getCenteredX(message, g2) - 30, y);
            }
        }
    }

    public void action() {
        switch (menu) {
            case 0:
                if (gp.isPaused) {
                    gp.isPaused = false;
                } else {
                    gp.restart();
                }
                break;
            case 1:
                if (gp.isPaused) {
                    gp.restart();
                } else {
                    System.exit(0);
                }
                break;
            case 2:
                System.exit(0);
                break;
        }
    }

    public int getCenteredX(String message, Graphics2D g2) {
        return gp.screenWidth / 2 - g2.getFontMetrics().stringWidth(message) / 2;
    }
}
