package ru.shifu.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
/**
 * BomberMan.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 26.11.2018.
 **/
public class BomberManGame {
    private final ReentrantLock[][] bord;
    private final BomberMan bomberMan;
    private final int size;
    private int numberSteps;
    private boolean isRunner = true;

    public BomberManGame(final int size, int numberSteps) {
        this.bord = new ReentrantLock[size][size];
        this.bomberMan = new BomberMan(0, 0);
        this.size = size;
        this.numberSteps = numberSteps;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.bord[i][j] = new ReentrantLock();
            }
        }
        System.out.println(bomberMan);
    }

    /**
     * Меод ход BomberMen.
     * BomberMan совершает ход в пустую ячейку.
     * перед перед тем как совершить ход он проверяет ячейку на lock ,
     * в течении 500 сек ждет пока она unlock, иначе пробует сделать ход в другую ячейку.
     * @param source из ячейки которой мы совершаем шаг.
     * @param dist в ячейку которую мысовершаем шаг.
     * @return
     * @throws InterruptedException
     */
   public boolean move(Cell source, Cell dist) throws InterruptedException {
        int souX = source.getPosX();
        int souY = source.getPosY();
        int desX = dist.getPosX();
        int desY = dist.getPosY();

        boolean result = false;
       System.out.println(String.format("BomberMan делает ход из %s в %s", source, dist));

       if (!(desX < 0 || desX > this.size - 1 || desY < 0 || desY > this.size - 1)) {
           if (this.bord[desX][desY].tryLock(500, TimeUnit.MILLISECONDS)) {
               bord[souX][souY].unlock();
               bomberMan.setPosition(dist);
               numberSteps--;
               result = true;
               System.out.println("BomberMen сделал очередной шаг");
           } else {
               System.out.println("Шаг невозможен - позиция занята");
           }
       } else {
           System.out.println("BomberMan сделать такой шаг не может");
       }
       if (numberSteps == 0) {
           this.isRunner = false;
           System.out.println("Шагов больше нет");
       }
       return result;
   }

    /**
     * Метод вычисляет ячейку для следующего шага.
     * @param source стартовая ячейка.
     * @return dist - конечная ячейка
     */
   private Cell nextStep(Cell source) {
        int deltaX = 0;
        int deltaY = 0;
        int random = (int) (Math.random() * 100);

        if (random < 25) {
            deltaX = 1;
        } else if (random < 50) {
            deltaY = 1;
        } else if (random < 75) {
            deltaX = -1;
        } else {
            deltaY = -1;
        }
        return new Cell(source.getPosX() + deltaX, source.getPosY() + deltaY);
   }

   public void startGames() throws InterruptedException {
        Thread bomberMove = new Thread("BomberMan") {
            @Override
            public void run() {
                Cell source = bomberMan.getPosition();
                bord[source.getPosY()][source.getPosY()].lock();
                while (isRunner) {
                    try {
                        source = bomberMan.getPosition();
                        move(source, nextStep(source));
                        sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println("BomberMan error");
                    }

                }
                System.out.println("The end");
            }
        };
        bomberMove.start();
        bomberMove.join();
   }
}
