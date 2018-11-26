package ru.shifu.bomberman;

import org.junit.Test;

/**
 * BomberManGameTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 26.11.2018.
 **/
public class BomberManGameTest {

    @Test
    public void whenMovesAndRestedInBorderThenNextMove() throws InterruptedException {
        BomberManGame game = new BomberManGame(5, 7);
        game.startGames();
    }
}