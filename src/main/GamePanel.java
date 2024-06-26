package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Monster;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxFps = 60;

    UI ui = new UI(this);
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler(this);

    public TileManager tileManager = new TileManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);
    public SuperObject objects[] = new SuperObject[10];
    public Monster monsters[] = new Monster[10];

    public boolean isPaused = true;
    public boolean isGameOver = false;
    public boolean isFinished = false;

    GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setMonster();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / maxFps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        long nextFrameTime = System.currentTimeMillis() + 1000;
        int frames = 0;
        while (gameThread != null) {
            if (System.nanoTime() >= nextDrawTime) {
                nextDrawTime += drawInterval;
                frames++;
                update();
                repaint();
            }

            if (System.currentTimeMillis() >= nextFrameTime) {
                nextFrameTime += 1000;
                // System.out.println("FPS: " + frames);
                frames = 0;
            }

        }
    }

    public void update() {
        if (!isPaused && !isGameOver && !isFinished) {
            player.update();
            for (int i = 0; i < monsters.length; i++) {
                if (monsters[i] != null) {
                    monsters[i].update();
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null) {
                objects[i].draw(g2, this);
            }
        }
        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i] != null) {
                monsters[i].draw(g2);
            }
        }

        player.draw(g2);
        ui.draw(g2);
        g2.dispose();
    }
}
