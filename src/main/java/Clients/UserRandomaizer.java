package Clients;

import Models.User;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class UserRandomaizer {

    public static @NotNull String getRandomEmail(){
        String email = "qwerty" + new Random().nextInt(5)+ "yandex.ru";
        return email;
    }

    public static @NotNull String getRandomPassword(){
        String password = "pass" + new Random().nextInt(5) + "1";
        return password;
    }

    public static @NotNull String getRandomName(){
        String name = "Alex" + new Random().nextInt(5);
        return name;
    }

    public static User getRandomUser(){
        User user = new User(getRandomEmail(), getRandomPassword(), getRandomName());
        return user;
    }
}
