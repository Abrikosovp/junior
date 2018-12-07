package ru.shifu.bomberman;

import java.util.ArrayList;
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
    private final ArrayList<Monster> monsters = new ArrayList<>();
    private final BomberMan bomberMan;
    private final int size;
    private int monsterNumber;
    private int monsterOrder;

    public BomberManGame(final int size, int let, int monsterNumber) throws InterruptedException {
        this.bord = new ReentrantLock[size][size];
        this.bomberMan = new BomberMan(0, 0);
        this.size = size;
        this.monsterNumber = monsterNumber;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.bord[i][j] = new ReentrantLock();
            }
        }
        createLet(let);
        System.out.println(this.bomberMan);
    }

    /**
     * Меод ход BomberMen.
     * BomberMan совершает ход в пустую ячейку.
     * перед перед тем как совершить ход он проверяет ячейку на lock ,
     * в течении 500 сек ждет пока она unlock, иначе пробует сделать ход в другую ячейку.
     * @param source из ячейки которой мы совершаем шаг.
     * @param dest в ячейку которую мысовершаем шаг.
     * @return
     * @throws InterruptedException
     */
   public boolean move(Cell source, Cell dest) throws InterruptedException {
        boolean result = false;
       System.out.println(String.format("BomberMan делает ход из %s в %s", source, dest));
           if (this.bord[dest.getPosX()][dest.getPosY()].tryLock(500, TimeUnit.MILLISECONDS)) {
               this.bord[source.getPosX()][source.getPosY()].unlock();
               this.bomberMan.setPosition(dest);
               result = true;
               System.out.println("BomberMen сделал очередной шаг");
           } else {
               System.out.println("Шаг невозможен - позиция занята");
           }
       return result;
   }

    /**
     * Меод ход Monster.
     * Monster совершает ход в пустую ячейку.
     * перед перед тем как совершить ход он проверяет ячейку на lock ,
     * в течении 500 сек ждет пока она unlock, иначе пробует сделать ход в другую ячейку.
     * @param current из ячейки которой мы совершаем шаг.
     * @param dest в ячейку которую мысовершаем шаг.
     * @return true
     * @throws InterruptedException
     */
    private boolean monsterMove(Monster current, Cell dest) throws InterruptedException {
        Cell source = current.getPosition();

        boolean result = false;
        System.out.println(String.format("Monster #%s делает ход из %s в %s", current.getNumber(), source, dest));
        if (this.bord[dest.getPosX()][dest.getPosY()].tryLock(500, TimeUnit.MILLISECONDS)) {
            this.bord[source.getPosX()][source.getPosY()].unlock();
            current.setPosition(dest);
            result = true;
            System.out.println(String.format("Монстер #%s сделал очередной шаг", current.getNumber()));
        } else {
            System.out.println(String.format("Монстер #%s шаг невозможен - позиция занята", current.getNumber()));
        }
        return result;
    }

    /**
     * Метод создает монстров.
     * @param monsterNumber колличество монстров.
     * @throws InterruptedException
     */
    private void createMonster(int monsterNumber) throws InterruptedException {
        Cell cell;
        for (int index = 0; index != monsterNumber; index++) {
            do {
                cell = randomCall();
            } while (!this.bord[cell.getPosX()][cell.getPosY()].tryLock(500, TimeUnit.MILLISECONDS));
            this.monsters.add(new Monster(cell, index));
        }
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

   private Cell randomCall() {
       return new Cell((int) (Math.random() * this.size), (int) (Math.random() * this.size));
   }

    /**
     * Метод создает препядствия.
     * @param let колличество препятствий.
     * @throws InterruptedException
     */
   private void createLet(int let) throws InterruptedException {
       Cell cell;
       for (int i = 0; i < let; i++) {
           do {
               cell = randomCall();
           } while (!this.bord[cell.getPosX()][cell.getPosY()].tryLock(10, TimeUnit.MILLISECONDS));
       }
   }

    /**
     * Метод проверяет возможность следующего хода.
     * @param dest ячейка в которую совершается ход.
     * @return true / false
     */
   public synchronized boolean strokeLimit(Cell dest) {
       return (!(dest.getPosX() < 0
               || dest.getPosX() > this.size - 1
               || dest.getPosY() < 0
               || dest.getPosY() > this.size - 1)
       );
   }

   public void startGames() throws InterruptedException {

        Thread bomberMove = new Thread("BomberMan") {
            @Override
            public void run() {
                Cell source = bomberMan.getPosition();
                bord[source.getPosY()][source.getPosY()].lock();

                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        source = bomberMan.getPosition();
                        if (bord[source.getPosY()][source.getPosY()].hasQueuedThreads()) {
                            Thread.currentThread().interrupt();
                        }

                        Cell desc = nextStep(source);
                        if (strokeLimit(desc)) {
                            move(source, desc);
                        } else {
                            System.out.println("BomberMan ТАКОЙ ХОД НЕ ДОПУСТИМ ");
                        }
                        sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("BomberMan game over");
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };

        Thread monster = new Thread("MonsterMan") {
            Monster tmp;

            @Override
            public void run() {
                try {
                    createMonster(monsterNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    tmp = monsters.get(monsterOrder++);
                    if (monsterOrder == monsterNumber) {
                        monsterOrder = 0;
                    }

                    Cell desc = nextStep(tmp.getPosition());
                    try {
                        if (strokeLimit(desc)) {
                            monsterMove(tmp, desc);
                        } else {
                            System.out.println(String.format("Monster #%s ТАКОЙ ХОД НЕ ДОПУСТИМ ", tmp.getNumber()));
                        }
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread.sleep(1000);
        bomberMove.start();
        monster.setDaemon(true);
        monster.start();
//        bomberMove.join();
       Thread.sleep(20000);
   }
}