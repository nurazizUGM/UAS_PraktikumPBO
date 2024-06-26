package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import entity.Monster;
import entity.Player;
import object.Object;
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

    public UI ui = new UI(this);
    Thread gameThread = new Thread(this);
    KeyHandler keyHandler = new KeyHandler(this);

    public TileManager tileManager = new TileManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);
    public List<Object> objects = new ArrayList<>();
    public List<Monster> monsters = new ArrayList<>();
    public int timeElapsed = 0;
    public int score = 0;
    public int finalScore = 0;
    public int killedMonsters = 0;

    public boolean isMenu = true;
    public boolean isPaused = false;
    public boolean isGameOver = false;
    public boolean isFinished = false;
    int mapNumber;

    GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        System.out.println("Map Number: " + mapNumber);
    }

    public void setupGame() {
        mapNumber = new Random().nextInt(1, 3);
        tileManager.load(mapNumber);
        assetSetter.setObject(mapNumber);
        player.respawn();

        killedMonsters = score = timeElapsed = 0;
        finalScore = -1;
        isPaused = isGameOver = isFinished = false;

        System.out.println("Game start with map " + mapNumber);
    }

    public void restart() {
        isMenu = false;
        objects.clear();
        monsters.clear();
        setupGame();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / maxFps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        long nextFrameTime = System.currentTimeMillis() + 1000;
        // int frames = 0;
        while (gameThread != null) {
            if (isFinished && finalScore == -1 && timeElapsed != 0) {
                finalScore = score * 10000 / timeElapsed / 60;
            }

            if (System.nanoTime() >= nextDrawTime) {
                nextDrawTime += drawInterval;
                // frames++;
                update();
                repaint();
            }

            if (System.currentTimeMillis() >= nextFrameTime) {
                timeElapsed++;
                nextFrameTime += 1000;
                // System.out.println("FPS: " + frames);
                // frames = 0;
            }

        }
    }

    public void update() {
        if (!isPaused && !isGameOver && !isFinished && !isMenu) {
            player.update();
            monsters.forEach(m -> m.update());
        }
        if (player.HP == 0) {
            isGameOver = true;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (!isMenu) {
            tileManager.draw(g2);
            objects.forEach(o -> o.draw(g2, this));
            monsters.forEach(m -> m.draw(g2));
            player.draw(g2);
        }

        ui.draw(g2);
        g2.dispose();
    }
}
