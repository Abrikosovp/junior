package ru.shifu.comparable;
/**
 * User.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru).
 * @version 1.
 * @since 14.10.2018.
 **/
public class User implements Comparable<User> {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    /**
     * Метод сортирует по age
     * @param user age следующего юзера
     * @return Если этот метод возвращает отрицательное число,
     * то текущий объект будет располагаться перед тем, который передается через параметр.
     * Если метод вернет положительное число, то, наоборот, после второго объекта.
     * Если метод возвратит ноль, значит, оба объекта равны.
     */
    @Override
    public int compareTo(User user) {
       return this.age - user.getAge();
    }
}
