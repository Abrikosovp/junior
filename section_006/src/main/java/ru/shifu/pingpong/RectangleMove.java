package ru.shifu.pingpong;

import javafx.scene.shape.Rectangle;
/**
 * RectangleMove.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 29.10.2018.
 **/
public class RectangleMove implements Runnable {
    private final Rectangle rect;


    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {

        boolean checkForX = false;
        boolean checkForY = false;

        while (true) {

            if (this.rect.getX() == 0) {
                checkForX = false;
            } else if (this.rect.getX() == 300) {
                checkForX = true;
            }

            if (this.rect.getY() == 0) {
                checkForY = false;
            } else if (this.rect.getY() == 300) {
                checkForY = true;
            }

            if (checkForX) {
                this.rect.setX(this.rect.getX() - 1);
            } else {
                this.rect.setX(this.rect.getX() + 1);
            }

            if (checkForY) {
                this.rect.setY(this.rect.getY() - 1);
            } else {
                this.rect.setY(this.rect.getY() + 1);
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Вы вышли ил игры Ping-Pong");
            }
        }
    }
}

