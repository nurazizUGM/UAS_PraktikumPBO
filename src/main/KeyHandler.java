package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        } else if (code == KeyEvent.VK_A) {
            leftPressed = true;
        } else if (code == KeyEvent.VK_S) {
            downPressed = true;
        } else if (code == KeyEvent.VK_D) {
            rightPressed = true;
        } else if (code == KeyEvent.VK_ESCAPE) {
            gp.isPaused = !gp.isPaused;
        }

        if (gp.isMenu) {
            switch (code) {
                case KeyEvent.VK_W:
                    gp.ui.menu--;
                    if (gp.ui.menu < 0) {
                        gp.ui.menu = 1;
                    }
                    break;
                case KeyEvent.VK_S:
                    gp.ui.menu++;
                    if (gp.ui.menu > 1) {
                        gp.ui.menu = 0;
                    }
                    break;

                case KeyEvent.VK_ENTER:
                    if (gp.ui.menu == 0) {
                        gp.isMenu = false;
                    } else if (gp.ui.menu == 1) {
                        System.exit(0);
                    }
                    break;
            }
        }

        if (gp.isGameOver || gp.isFinished) {
            if (code == KeyEvent.VK_ENTER) {
                gp.restart();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        } else if (code == KeyEvent.VK_A) {
            leftPressed = false;
        } else if (code == KeyEvent.VK_S) {
            downPressed = false;
        } else if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
