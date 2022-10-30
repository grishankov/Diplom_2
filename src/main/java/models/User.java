package models;

import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;

public class User {
    public String email;
    public String password;
    public String name;
    private static final int MAX_EMAIL_LENGTH = 20;
    private static final int MAX_PASSWORD_LENGTH = 25;
    private static final int MAX_NAME_LENGTH = 30;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public User() {
    }

    private static String getRandomString(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

    public static @NotNull String getRandomEmail() {
        return getRandomString(MAX_EMAIL_LENGTH / 2) + "@" + getRandomString(MAX_EMAIL_LENGTH / 4) + ".ru";
    }

    public static @NotNull String getRandomPassword() {
        return getRandomString(MAX_PASSWORD_LENGTH);
    }

    public static @NotNull String getRandomName() {
        return getRandomString(MAX_NAME_LENGTH);
    }

    public static User getRandomUserFull() {
        User user = new User();
        user.setEmail(getRandomEmail());
        user.setPassword(getRandomPassword());
        user.setName(getRandomName());
        return user;
    }

    public static User getRandomUserWithoutName() {
        User user = new User();
        user.setEmail(getRandomEmail());
        user.setPassword(getRandomPassword());
        user.setName(null);
        return user;
    }

    public String toJson() {
        return new GsonBuilder().serializeNulls().create().toJson(this);
    }
}
