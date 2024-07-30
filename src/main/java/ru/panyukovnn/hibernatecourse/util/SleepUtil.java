package ru.panyukovnn.hibernatecourse.util;

import lombok.SneakyThrows;

public class SleepUtil {

    @SneakyThrows
    public static void sleep(int ms) {
        Thread.sleep(ms);
    }
}
