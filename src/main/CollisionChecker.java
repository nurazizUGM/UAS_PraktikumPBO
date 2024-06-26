package main;

import java.awt.Rectangle;

import entity.Entity;
import entity.Monster;
import object.SuperObject;

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

    public SuperObject checkObject(Entity entity) {
        SuperObject collidedObject = null;
        for (SuperObject object : gp.objects) {
            if (object != null) {
                // get entity's bounds
                Rectangle entityBounds = new Rectangle(entity.bounds.x + entity.x, entity.bounds.y + entity.y,
                        entity.bounds.width, entity.bounds.height);

                // get object's bounds
                Rectangle objectBounds = new Rectangle(object.x + object.bounds.x,
                        object.y + object.bounds.y, object.bounds.width,
                        object.bounds.height);

                // System.out.println(object.name + "Bounds: " + objectBounds);
                // System.out.println("entityBounds: " + entityBounds);

                switch (entity.direction) {
                    case "up":
                        entityBounds.y -= entity.speed;
                        if (entityBounds.intersects(objectBounds)) {
                            entity.collisionOn = true;
                            collidedObject = object;
                        }
                        break;
                    case "down":
                        entityBounds.y += entity.speed;
                        if (entityBounds.intersects(objectBounds)) {
                            entity.collisionOn = true;
                            collidedObject = object;
                        }
                        break;
                    case "left":
                        entityBounds.x -= entity.speed;
                        if (entityBounds.intersects(objectBounds)) {
                            entity.collisionOn = true;
                            collidedObject = object;
                        }
                        break;
                    case "right":
                        entityBounds.x += entity.speed;
                        if (entityBounds.intersects(objectBounds)) {
                            entity.collisionOn = true;
                            collidedObject = object;
                        }
                        break;
                }
            }
        }
        return collidedObject;
    }

    public Monster checkMonster(Entity entity) {
        Monster collidedMonster = null;
        for (Monster monster : gp.monsters) {
            if (monster != null && !monster.isDied) {
                // get entity's bounds
                Rectangle entityBounds = new Rectangle(entity.bounds.x + entity.x, entity.bounds.y + entity.y,
                        entity.bounds.width, entity.bounds.height);

                // get monster's bounds
                Rectangle objectBounds = new Rectangle(monster.x + monster.bounds.x,
                        monster.y + monster.bounds.y, monster.bounds.width,
                        monster.bounds.height);

                switch (entity.direction) {
                    case "up":
                        entityBounds.y -= entity.speed;
                        if (entityBounds.intersects(objectBounds)) {
                            entity.collisionOn = true;
                            collidedMonster = monster;
                        }
                        break;
                    case "down":
                        entityBounds.y += entity.speed;
                        if (entityBounds.intersects(objectBounds)) {
                            entity.collisionOn = true;
                            collidedMonster = monster;
                        }
                        break;
                    case "left":
                        entityBounds.x -= entity.speed;
                        if (entityBounds.intersects(objectBounds)) {
                            entity.collisionOn = true;
                            collidedMonster = monster;
                        }
                        break;
                    case "right":
                        entityBounds.x += entity.speed;
                        if (entityBounds.intersects(objectBounds)) {
                            entity.collisionOn = true;
                            collidedMonster = monster;
                        }
                        break;
                }
            }
        }
        return collidedMonster;
    }
}
