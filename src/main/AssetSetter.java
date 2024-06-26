package main;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import entity.Monster;
import object.Chest;
import object.Portal;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(int mapNumber) {
        try {
            BufferedReader objectReader = new BufferedReader(
                    Files.newBufferedReader(Paths.get("src/resources/maps/" + mapNumber + "/object.txt")));
            int i = 0;
            while (true) {
                String line = objectReader.readLine();
                if (line == null) {
                    break;
                }
                String[] numbers = line.split(" ");
                int x = Integer.parseInt(numbers[1]) * gp.tileSize;
                int y = Integer.parseInt(numbers[2]) * gp.tileSize;

                switch (numbers[0]) {
                    case "chest":
                        gp.objects.add(new Chest(x, y));
                        break;
                    case "portal":
                        gp.objects.add(new Portal(x, y));
                        break;
                    case "monster":
                        gp.monsters.add(new Monster(gp, x, y));
                        break;
                }

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
