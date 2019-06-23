package lesson_5;


import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private static CyclicBarrier start;
    private static CountDownLatch cdlFinish;
    private static CountDownLatch cdlReady;

    static {
        CARS_COUNT = 0;
        cdlFinish = MainClass.cdlFinish;
        cdlReady = MainClass.cdlReady;
        start = MainClass.start;
    }

    String getName() {
        return name;
    }
    int getSpeed() {
        return speed;
    }
    Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            // все готовы
            cdlReady.countDown();
            System.out.println(this.name + " готов");
            // все на старте
            start.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        final ArrayList<Stage> stages = race.getStages();
        for (Stage stage : stages) {
            stage.go(this);
        }
        // финишировали
        cdlFinish.countDown();

    }}