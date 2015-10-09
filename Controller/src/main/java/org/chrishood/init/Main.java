package org.chrishood.init;

import org.chrishood.model.gameobject.Entity;
import org.chrishood.model.gameobject.GameObject;
import org.chrishood.model.gameobject.components.LocationComponent;
import org.chrishood.model.mappingsystem.Dungeon;
import org.chrishood.model.mappingsystem.TileMap;
import org.chrishood.model.mappingsystem.TileRepository;
import org.chrishood.model.mappingsystem.TileSet;
import org.chrishood.model.mappingsystem.builder.IMapBuilder;
import org.chrishood.model.mappingsystem.builder.StreamMapBuilder;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import org.chrishood.util.Point;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Chris on 10/2/2015.
 */
public class Main {

    static char heroChar = '@';
    static char villainChar = '&';

    public static void main(String args[]) throws IOException {
        ConsoleSystemInterface console = null;
        try {
            WSwingConsoleInterface.xdim = 100;
            WSwingConsoleInterface.ydim = 100;
            console = new WSwingConsoleInterface();
        } catch (ExceptionInInitializerError e) {
            System.out.println("*** Unable to initialize interface.");
            e.printStackTrace();
            System.exit(-1);
        }

        InputStream is = Main.class.getResourceAsStream("Tile.json");
        long startTime = System.currentTimeMillis();
        TileRepository.getInstance().initialize(is);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        List<String> tileIds = new ArrayList<>();
        tileIds.add("grass");
        tileIds.add("tree");
        tileIds.add("stone");
        TileSet tileSet = new TileSet(tileIds);
        IMapBuilder mapBuilder = new StreamMapBuilder(tileSet, Main.class.getResourceAsStream("TestMap.txt"));

        TileMap m = mapBuilder.build();
        List<TileMap> floors = new ArrayList<>();
        floors.add(m);
        Dungeon dungeon = new Dungeon(floors);
        GameObject g1 = new GameObject(0, "guy1");
        GameObject g2 = new GameObject(1, "guy2");
        g1.addComponent(new LocationComponent(g1, new Point(1, 1), 0));
        g2.addComponent(new LocationComponent(g1, new Point(5, 5), 0));
        dungeon.addGameObject(g1);
        dungeon.addGameObject(g2);
        ((LocationComponent) g1.getGameComponent(LocationComponent.class.getName())).setFloorLocation(new Point(2, 1));
        Random rand = new Random();

        int heroX = 1;
        int heroY = 1;
        int villainX = 8;
        int villainY = 8;

        Entity hero = new Entity("Hero", 100, 100, 18, 10, 5);
        Entity villain = new Entity("Villain", 50, 50, 10, 10, 6);

        boolean stop = false;
        while (!stop) {
            console.cls();
            printMap(m, console);
            console.print(villainX, villainY, villainChar, ConsoleSystemInterface.RED);
            console.print(heroX, heroY, heroChar, ConsoleSystemInterface.WHITE);
            console.print(60, 0, "Hero Hp: " + hero.getMaxHealth() + "/" + hero.getHealth());
            console.print(60, 1, "Villain Hp: " + villain.getMaxHealth() + "/" + villain.getHealth());
            console.refresh();
            CharKey dir = console.inkey();
            if (dir.isUpArrow() && m.getTile(heroY - 1, heroX).isPassable()) {
                heroY--;
            }
            if (dir.isDownArrow() && m.getTile(heroY + 1, heroX).isPassable()) {
                heroY++;
            }
            if (dir.isLeftArrow() && m.getTile(heroY, heroX - 1).isPassable()) {
                heroX--;
            }
            if (dir.isRightArrow() && m.getTile(heroY, heroX + 1).isPassable()) {
                heroX++;
            }
            if (dir.code == CharKey.a) {
                if (adjacent(heroX, villainX, heroY, villainY)) {
                    if (attack(hero, villain)) {
                        villainChar = '=';
                    }
                } else {
                    System.out.println("Can not attack villain");
                }
            }
            if (dir.code == CharKey.q) {
                stop = true;
            }

            if (villain.getHealth() > 0) {
                if (adjacent(heroX, villainX, heroY, villainY)) {
                    if (attack(villain, hero)) {
                        heroChar = '=';
                    }
                } else {
                    int villainDir = rand.nextInt(4);
                    if (villainDir == 0) {
                        if (m.getTile(villainY - 1, villainX).isPassable()) {
                            villainY--;
                        }
                    }
                    if (villainDir == 1) {
                        if (m.getTile(villainY + 1, villainX).isPassable()) {
                            villainY++;
                        }
                    }
                    if (villainDir == 2) {
                        if (m.getTile(villainY, villainX - 1).isPassable()) {
                            villainX--;
                        }
                    }
                    if (villainDir == 3) {
                        if (m.getTile(villainY, villainX + 1).isPassable()) {
                            villainX++;
                        }
                    }
                }
            }
        }
        m = null;
        console = null;
        System.exit(0);
    }

    public static boolean attack(Entity actor, Entity target) {
        int damage = actor.attack(target);
        if (damage > -1) {
            System.out.println(actor.getName() + " hit " + target.getName() + " for " + damage + " damage.");
            if (target.getHealth() <= 0) {
                System.out.println(target.getName() + " is dead.");
                return true;
            }
        } else {
            System.out.println(actor.getName() + " missed " + target.getName());
        }
        return false;
    }

    public static boolean adjacent(int x1, int x2, int y1, int y2) {
        int xDist = Math.abs(x1 - x2);
        int yDist = Math.abs(y1 - y2);
        return (xDist == 0 && yDist == 1) || (xDist == 1 && yDist == 0);
    }

    public static void printMap(TileMap map, ConsoleSystemInterface console) {
        for (int i = 0; i < map.getySize(); ++i) {
            for (int k = 0; k < map.getxSize(); ++k) {
                console.print(k, i, map.getTile(i, k).getDisplay(), map.getTile(i, k).getColor());
            }
        }
    }
}
