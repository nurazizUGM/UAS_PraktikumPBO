package object;

import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class Portal extends SuperObject {
    public boolean collision = true;

    public Portal(int x, int y) {
        this.name = "portal";
        this.x = x;
        this.y = y;
        this.bounds = new Rectangle(8, 8, 32, 32);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/portal.png"));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
