package ru.shifu.jmm;
/**
 * AppJMM.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 19.11.2018.
 **/
public class AppJMM {
    /**
     * Флаг.
     */
    static boolean ready = false;
    /**
     * Данные.
     */
    static String data;

    public static void main(String[] args) {
        /**
         * Запускаем первый поток:
         * спит секунду (готовлю данные),
         * зате выставляю данные.
         * ставлю флаг что данные готовы.
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(1000);
                data = "start";
                ready = true;
            }
        }).start();
        /**
         * Запускаем поток два:
         * в цикле крутим до тех пор пока не готово.
         * выводим данные.
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!ready) {
                    data = "finish";
                }
                System.out.println(data);

            }
        }).start();
    }

    /**
     * Вынес метод sleep.
     * @param time время сна.
     */
    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
