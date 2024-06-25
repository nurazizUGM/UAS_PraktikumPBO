package main;

import java.awt.Rectangle;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.x + entity.bounds.x;
        int entityRightWorldX = entity.x + entity.bounds.x + entity.bounds.width;
        int entityTopWorldY = entity.y + entity.bounds.y;
        int entityBottomWorldY = entity.y + entity.bounds.y + entity.bounds.height;
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                entity.collisionOn = gp.tileManager.tiles[tileNum1].collision
                        || gp.tileManager.tiles[tileNum2].collision;
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                entity.collisionOn = gp.tileManager.tiles[tileNum1].collision
                        || gp.tileManager.tiles[tileNum2].collision;
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                entity.collisionOn = gp.tileManager.tiles[tileNum1].collision
                        || gp.tileManager.tiles[tileNum2].collision;
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                entity.collisionOn = gp.tileManager.tiles[tileNum1].collision
                        || gp.tileManager.tiles[tileNum2].collision;
                break;
        }
    }

    public int checkObject(Entity entity) {
        int objectIndex = -1;
        for (int i = 0; i < gp.objects.length; i++) {
            if (gp.objects[i] != null) {
                // get entity's bounds
                Rectangle entityBounds = new Rectangle(entity.bounds.x + entity.x, entity.bounds.y + entity.y,
                        entity.bounds.width, entity.bounds.height);

                // get object's bounds
                Rectangle objectBounds = new Rectangle(gp.objects[i].x + gp.objects[i].bounds.x,
                        gp.objects[i].y + gp.objects[i].bounds.y, gp.objects[i].bounds.width,
                        gp.objects[i].bounds.height);

                switch (entity.direction) {
                    case "up":
                        entityBounds.y -= entity.speed;
                        if (entityBounds.intersects(objectBounds)) {
                            entity.collisionOn = true;
                            objectIndex = i;
                        }
                        break;
                    case "down":
                        entityBounds.y += entity.speed;
                        if (entityBounds.intersects(objectBounds)) {
                            entity.collisionOn = true;
                            objectIndex = i;
                        }
                        break;
                    case "left":
                        entityBounds.x -= entity.speed;
                        if (entityBounds.intersects(objectBounds)) {
                            entity.collisionOn = true;
                            objectIndex = i;
                        }
                        break;
                    case "right":
                        entityBounds.x += entity.speed;
                        if (entityBounds.intersects(objectBounds)) {
                            entity.collisionOn = true;
                            objectIndex = i;
                        }
                        break;
                }
            }
        }
        return objectIndex;
    }
}
