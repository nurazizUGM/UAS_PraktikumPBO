package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    GamePanel gp;
    Font arial_40 = new Font("Arial", Font.PLAIN, 40);

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        if (gp.isPaused) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String message = "PAUSED";
            g2.drawString(message, gp.screenWidth / 2 - g2.getFontMetrics().stringWidth(message) / 2,
                    gp.screenHeight / 2 + g2.getFontMetrics().getHeight() / 4);
        } else if (gp.isFinished) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String message = "FINISHED";
            g2.drawString(message, gp.screenWidth / 2 - g2.getFontMetrics().stringWidth(message) / 2,
                    gp.screenHeight / 2 + g2.getFontMetrics().getHeight() / 4);
        }
    }
}
