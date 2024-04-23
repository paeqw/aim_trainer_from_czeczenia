package gowno.sraka.aim_trainer_from_czeczenia;

import java.util.HashMap;

public class Costamniewiem { // serio nie wiem jak to nazwac
    private HashMap<String, Shooter> levels;

    public Costamniewiem(HashMap<String, Shooter> levels) {
        this.levels = levels;
    }

    public Shooter getLevel(String levelName) {
        return levels.get(levelName);
    }
}