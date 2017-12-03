package com.mazegame.graphic;

import com.mazegame.game.*;
import com.mazegame.maze.Maze;
import com.mazegame.maze.MazeNode;
import com.mazegame.maze.MazeNodeType;
import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class GameView extends View {

    private HashMap<Integer, Boolean> keyMap = new HashMap<>();
    public static int UnitSize = 50;
    private Game game;
    private GameInfoView giv = null;
    private ArrayList<Pair<Integer, MazeNode>> nodesToDisplay = new ArrayList<>();
    private BufferedImage sprites;
    private BufferedImage ground;
    private BufferedImage wall;
    private BufferedImage grass;
    private BufferedImage[] playerSprite;
    private BufferedImage[] chestSprite;

    GameView() {
        game = new Game();
        game.start();
        loadSprites();
    }

    GameView(Game gameLoaded) {
        game = gameLoaded;
        loadSprites();
    }

    private final void loadSprites() {
        try {
            sprites = ImageIO.read(getClass().getResourceAsStream("ressources/magecity.png"));
            ground = sprites.getSubimage(32, 0, 32, 32);
            wall = sprites.getSubimage(128, 32, 32, 32);
            grass = sprites.getSubimage(0, 0, 32, 32);

            BufferedImage playerSprites = ImageIO.read(getClass().getResourceAsStream("ressources/player.png"));
            playerSprite = new BufferedImage[] {
                    playerSprites.getSubimage(0, 96, 32, 32),
                    playerSprites.getSubimage(0, 64, 32, 32),
                    playerSprites.getSubimage(0, 0, 32, 32),
                    playerSprites.getSubimage(0, 32, 32, 32)
            };

            BufferedImage chestImg = ImageIO.read(getClass().getResourceAsStream("ressources/chest.png"));
            chestSprite = new BufferedImage[] {
                    chestImg.getSubimage(0,0,32,32),
                    chestImg.getSubimage(32,0,32,32)
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        // Background
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,GamePanel.WINDOW_WIDTH,GamePanel.WINDOW_HEIGHT);

        // middle of the screen
        int rx = GamePanel.WINDOW_WIDTH / 2;
        int ry = GamePanel.WINDOW_HEIGHT / 2;

        // get information
        Level level = game.getLevel();
        Maze maze = level.getMaze();
        Player player = game.getPlayer();

        // display level
        int sizeAroundPlayer = 10;

        for (int y = (int) player.getY() - sizeAroundPlayer; y <= player.getY() + sizeAroundPlayer; y++) {
            if (y < 0 || y >= maze.getHeight()) {
                continue;
            }
            for (int x = (int) player.getX() - sizeAroundPlayer; x <= player.getX() + sizeAroundPlayer; x++) {
                if (x < 0 || x >= maze.getWidth()) {
                    continue;
                }

                // FOG
                int distance = (int) Math.sqrt(Math.pow(x - player.getX(), 2) + Math.pow(y - player.getY(), 2));
                if ( distance <= 0 ) distance = 1;
                if (distance < player.getStrengthLight()) {
                    MazeNode node = maze.getAt(x, y);
                    ArrayList<BufferedImage> img = new ArrayList<>();
                    if (node.getType() != MazeNodeType.EMPTY) {
                        img.add(ground);
                        img.add(wall);
                    } else {
                        img.add(ground);
                        // chests
                        for (Chest c : game.getLevel().getChests()) {
                            if ( c.getX() == x && c.getY() == y ) {
                                img.add( (c.isOpen()) ? chestSprite[1] : chestSprite[0] );
                                break ;
                            }
                        }
                    }


                    int rectX = rx + (x * UnitSize) - (int) (player.getX() * UnitSize);
                    int rectY = ry + (y * UnitSize) - (int) (player.getY() * UnitSize);
                    for (BufferedImage i : img) {
                        graphics.drawImage(i, rectX, rectY, UnitSize, UnitSize, null);
                    }

                    // Exponentielle
                    //graphics.setColor(new Color(0,0,0, 255 - (255/distance)));
                    // LINEAR
                    graphics.setColor(new Color(0,0,0, ((255 / player.getStrengthLight()) * distance)));
                    graphics.fillRect(rectX, rectY, UnitSize, UnitSize);
                }
            }
        }

        // display player
        BufferedImage playerImg = null;
        switch (player.getOrientation()) {
            case EAST:
                playerImg = playerSprite[1];
                break;

            case WEST:
                playerImg = playerSprite[3];
                break;

            case NORTH:
                playerImg = playerSprite[0];
                break;

            case SOUTH:
                playerImg = playerSprite[2];
        }

        graphics.drawImage(playerImg, rx, ry, UnitSize, UnitSize, null);
    }

    @Override
    public void update() {

        if ( keyMap.containsKey(KeyEvent.VK_RIGHT) && keyMap.get(KeyEvent.VK_RIGHT) ) {
            game.moveRight();
        }

        if ( keyMap.containsKey(KeyEvent.VK_LEFT) && keyMap.get(KeyEvent.VK_LEFT) ) {
            game.moveLeft();
        }

        if ( keyMap.containsKey(KeyEvent.VK_UP) && keyMap.get(KeyEvent.VK_UP) ) {
            game.moveTop();
        }

        if ( keyMap.containsKey(KeyEvent.VK_DOWN) && keyMap.get(KeyEvent.VK_DOWN) ) {
            game.moveBottom();
        }

        game.update();

        if ( game.isLevelEnd() ) {
            addView(new NotificationView("Bravo ! Tu es passé level "+ (game.getLevel().getLevel()+1) ));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyMap.put(KeyEvent.VK_RIGHT, true);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keyMap.put(KeyEvent.VK_LEFT, true);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keyMap.put(KeyEvent.VK_UP, true);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keyMap.put(KeyEvent.VK_DOWN, true);
        }

        if (e.getKeyCode() == KeyEvent.VK_F3) {
            if (giv == null) {
                giv = new GameInfoView(game);
                addView(giv);
            } else {
                giv.delete();
                giv = null;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            addView(new InGameMenuView(game));
            //gm.add(new InGameMenuView(this, gm, game));
        }

        if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Item torch = new Torch();
            torch.use(game.getPlayer());
        }

        if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            game.getPlayer().setStrengthLight(game.getPlayer().getStrengthLight() - 1);
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Chest c = game.isPlayerOnChest();
            if ( c != null && !c.isOpen() ) {
                Item i = c.open();
                i.use(game.getPlayer());
                addView(new NotificationView(String.format("Nouvel item: %s", i.getName())));
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_P && game.getPlayer().hasPower()) {
            game.getPlayer().usePower(game);
            addView(new NotificationView("POOWERR!!"));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyMap.put(e.getKeyCode(), false);
    }
}
