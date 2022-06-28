package com.gameTetris.graphics;

import java.util.Random;

/**
 * Клас для кольорів
 * Передаються в графічний модуль для зображення ігрового поля
 */
public enum TpReadableColor {
    BLACK, RED, GREEN, BLUE, AQUA, YELLOW, ORANGE, PURPLE;

    // Масив із всіма обєктами цього enum (для реалізації getRandomColor() )
    private static TpReadableColor[] colorByNumber = { BLACK, RED, GREEN, BLUE, AQUA, YELLOW, ORANGE, PURPLE, };

    /**
     * @return Случайний обект цього enum'а
     */
    public static TpReadableColor getRandomColor() {
        int colorNumber = new Random().nextInt(colorByNumber.length);
        return colorByNumber[colorNumber];
    }
}
