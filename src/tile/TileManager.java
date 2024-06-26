package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tiles = new Tile[76];

    public int mapTileNum[][];
    public int mapChests[][];

    public BufferedImage chestTile;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
    }

    public void load(int mapNumber) {
        getTileImage();
        try {
            InputStream is = getClass().getResourceAsStream("/resources/maps/" + mapNumber + "/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTileImage() {
        try {
            Stream<Path> stream = Files.walk(Paths.get("src/resources/tiles"));

            // read tiledata
            BufferedReader tileReader = new BufferedReader(
                    new InputStreamReader(getClass().getResourceAsStream("/resources/maps/tiledata.txt")));

            while (true) {
                String line = tileReader.readLine();
                if (line == null) {
                    break;
                }
                int fileNum = Integer.parseInt(line.substring(0, 3));
                boolean collision = Boolean.parseBoolean(tileReader.readLine());
                tiles[fileNum] = new Tile();
                tiles[fileNum].collision = collision;
            }
            stream
                    .filter(Files::isRegularFile)
                    .filter(file -> file.getFileName().toString().endsWith(".png"))
                    .forEach(file -> {
                        int fileNum = Integer.parseInt(file.getFileName().toString().substring(0, 3));
                        try {
                            BufferedImage image = ImageIO.read(file.toFile());
                            tiles[fileNum].image = image;
                        } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                        }
                    });
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            System.out.println("Tiles Loaded!");
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tiles[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            x += gp.tileSize;
            if (x == gp.tileSize * gp.maxScreenCol) {
                x = 0;
                y += gp.tileSize;
            }
            col++;
            if (col == gp.maxScreenCol) {
                col = 0;
                row++;
            }
        }
    }
}
