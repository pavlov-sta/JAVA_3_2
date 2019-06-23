package lesson_5;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainClass {
    public static final int CARS_COUNT = 4;
    static final CountDownLatch cdlFinish = new CountDownLatch(CARS_COUNT);
    static final CountDownLatch cdlReady = new CountDownLatch(CARS_COUNT);
    static final CyclicBarrier start = new CyclicBarrier(CARS_COUNT);


    public static void main(String[] args) throws InterruptedException {


        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        // ожидаем готовности учасников
        cdlReady.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        // финишируем
        cdlFinish.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}



