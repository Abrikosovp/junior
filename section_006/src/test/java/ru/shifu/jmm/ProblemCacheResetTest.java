package ru.shifu.jmm;

import org.junit.Test;

/**
 * ProblemCacheReset.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 19.11.2018.
 **/
public class ProblemCacheResetTest {
    /**
     * счетчик
     */
    private static int counter = 0;
    @Test
    public void when() throws InterruptedException {

        /**
         * Запускаем новый поток ,
         * в теле метода присутствует цикл ,
         * который при каждом витке инкриментирует наш счетчик.
         */
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000_000; i++) {
                    counter++;
                }
            }
        });
        thread.start();
        thread.join();
        /**
         * Усыпляем поток , и ловим в неопределенном моменте
         * т.к результат не детерменированный.
         */
        Thread.sleep(2);

        System.out.println(counter);
    }

}
