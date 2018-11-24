package ru.shifu.nonblocking;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;


public class HashTest {

    @Test
    public void whenHashIncrementTrowExceptions() throws InterruptedException {
        Hash hash = new Hash();
        AtomicReference<Exception> atomic = new AtomicReference<>();

        Thread threadFirst = new Thread(() -> {
            hash.add(new Base(1));
            hash.add(new Base(2));
            hash.add(new Base(3));
            for (int i = 0; i < 300; i++) {
                try {
                    hash.update(new Base(1));
                } catch (OptimisticException e) {
                    atomic.set(e);
                }
            }
        });

        Thread threadSecond = new Thread(() -> {
            hash.add(new Base(1));
            hash.add(new Base(2));
            hash.add(new Base(3));

            for (int i = 0; i < 300; i++) {
                try {
                    hash.update(new Base(1));
                } catch (OptimisticException e) {
                    atomic.set(e);
                }
            }
        });
        threadFirst.start();
        threadSecond.start();
        threadFirst.join();
        threadSecond.join();
        hash.delete(new Base(3));

        Assert.assertThat(atomic.get().getMessage(), is("The current version of the model differs by more than one."));
        Assert.assertThat(hash.getBase(new Base(3)), is((Integer) null));
    }

}