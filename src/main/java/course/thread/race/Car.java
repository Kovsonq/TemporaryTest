package course.thread.race;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class Car implements Runnable {
    private static int CARS_COUNT = 0;
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            log.info(this.name + " готовится");
            Thread.sleep(500 + (long) (new Random().nextInt(800)));
            log.info(this.name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }
}

