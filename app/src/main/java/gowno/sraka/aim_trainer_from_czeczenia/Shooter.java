package gowno.sraka.aim_trainer_from_czeczenia;

public class Shooter {
    private long time;
    private int hitsCount;
    private String difficultyName;
    private int hitSize;

    public Shooter(long time, int hitsCount, String difficultyName, int hitSize) {
        this.time = time;
        this.hitsCount = hitsCount;
        this.difficultyName = difficultyName;
        this.hitSize = hitSize;
    }

    public long getTime() {
        return time;
    }

    public int getHitsCount() {
        return hitsCount;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public int getHitSize() {
        return hitSize;
    }
}