package com.company;

import com.company.characters.Monster;
import com.company.characters.MonsterInstance;
import com.company.characters.Townspeople;
import com.company.items.clothing.ClothingInstance;
import com.company.items.weapons.WeaponInstance;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Level {
    private static int lastId = 0;
    private final int id;
    private final int m;
    private final int n;
    private final ArrayList<Room> rooms;

    public Level(int m, int n, ArrayList<Room> rooms) {
        this.id = lastId++;
        this.m = m;
        this.n = n;
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public static ArrayList<Level> generate() {
        ArrayList<Level> levels = new ArrayList<>();
        SecureRandom secureRandom = new SecureRandom();
        for (int levelId = 0; levelId < 17; levelId++) {
            ArrayList<Room> rooms = new ArrayList<>();
            int m = secureRandom.nextInt(4) + 1;
            int n = secureRandom.nextInt(4) + 1;
            int roomId = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    ArrayList<Door> doors = new ArrayList<>();

                    if (j != 0) {
                        doors.add(new Door(roomId - 1));
                    }

                    if (j != n - 1) {
                        doors.add(new Door(roomId + 1));
                    }
                    ArrayList<Monster> monsters = new ArrayList<>();
                    int x = secureRandom.nextInt(9) + 1;

                    for (int number = 0; number < x; number++) {
                        monsters.add(MonsterInstance.ape());
                    }
                    ArrayList<Townspeople> townspeople = new ArrayList<>();

                    for (int number = 0; number < x / 3; number++) {
                        townspeople.add(new Townspeople("townspeople", WeaponInstance.glassShank(), ClothingInstance.shabbyJerkin(), 10));
                    }
                    String text;

                    if (levelId == 0 && roomId == 0) {
                        text = new RandomText().get(true);
                    } else {
                        text = new RandomText().get();
                    }
                    rooms.add(new Room(roomId++, i, text, doors, monsters, townspeople));
                }
            }

            for (int i = 0; i < m; i++) {
                if (i != m - 1) {
                    int j = secureRandom.nextInt(n);
                    int k = secureRandom.nextInt(n);
                    System.out.println(j + ", " + k);
                    rooms.get(i * n + j).addDoor(new Door((i + 1) * n + k));
                    rooms.get((i + 1) * n + k).addDoor(new Door(i * n + j));
                }
            }
            levels.add(new Level(m, n, rooms));
        }
        for (int i = 0; i < levels.size(); i++) {
            if (i != levels.size() - 1) {
                int m1 = levels.get(i).getM();
                int m2 = levels.get(i + 1).getM();
                int n1 = levels.get(i).getN();
                int n2 = levels.get(i + 1).getN();
                int a1 = secureRandom.nextInt(m1);
                int b1 = secureRandom.nextInt(n1);
                int a2 = secureRandom.nextInt(m2);
                int b2 = secureRandom.nextInt(n2);
                levels.get(i).getRooms().get(a1 * n1 + b1).addDoor(new Door(a2 * n2 + b2, "up"));
                levels.get(i + 1).getRooms().get(a2 * n2 + b2).addDoor(new Door(a1 * n1 + b1, "down"));
            }
        }
        print(levels);
        return levels;
    }

    public static void print(ArrayList<Level> levels) {
        for (Level level : levels) {
            System.out.println("level " + (level.getId() + 1) + " (" + level.getM() + ", " + level.getN() + ")");

            for (Room room : level.getRooms()) {
                System.out.println("    room " + (room.getId() + 1));
                ArrayList<Door> doors = room.getDoors();

                for (int i = 0; i < doors.size(); i++) {
                    if (!doors.get(i).isStair()) {
                        System.out.println("        door (d" + (i + 1) + ")");
                    }
                }

                for (int i = 0; i < doors.size(); i++) {
                    if (doors.get(i).isStair()) {
                        System.out.println("        stair (" + doors.get(i).getWay() + ") (room " + (doors.get(i).getReference() + 1) + ")");
                    }
                }
            }
        }
    }
}