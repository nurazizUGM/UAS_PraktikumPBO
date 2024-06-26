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
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String message = "PAUSED";
            g2.drawString(message, getCenteredX(message, g2),
                    gp.screenHeight / 2 + g2.getFontMetrics().getHeight() / 4);
            message = "Press ESC to resume";
            g2.drawString(message, getCenteredX(message, g2), gp.screenHeight / 2 + g2.getFontMetrics().getHeight());
        } else if (gp.isFinished) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String message = "FINISHED";
            g2.drawString(message, getCenteredX(message, g2),
                    gp.screenHeight / 2 + g2.getFontMetrics().getHeight() / 4);
        } else if (gp.isGameOver) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String message = "GAME OVER";
            g2.drawString(message, getCenteredX(message, g2),
                    gp.screenHeight / 2 + g2.getFontMetrics().getHeight() / 4);
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

    public int getCenteredX(String message, Graphics2D g2) {
        return gp.screenWidth / 2 - g2.getFontMetrics().stringWidth(message) / 2;
    }
}
