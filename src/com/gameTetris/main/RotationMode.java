
package com.gameTetris.main;

/**
 * Стани фігури після поворотів:
 * не повернули, повернули проти год стрілки 1 раз, 2 раза, 3 раза.
 * 4 раз, фігура вернеться в початкове положення
 */
public enum RotationMode {
    // початкове положення
    NORMAL(0),

    // Положення, після повороту проти год стрілки
    FLIP_CCW(1),

    // Положення, коли фігура дзеркальна
    INVERT(2),

    // Положення, після повороту за год стрілкою, 3 проти год стрліки
    FLIP_CW(3);



    // Кількість поворотів, що стати в дане положення
    private int number;

    /**
     * Конструктор
     *
     * @param number Кількість поворотів, що стати в дане положення
     */
    RotationMode(int number){
        this.number = number;
    }

     // Обєкти enum. Индекс в массиві = полю number
    private static RotationMode[] rotationByNumber = {NORMAL, FLIP_CCW, INVERT, FLIP_CW};

    /**
     * Положення, після повороту за год стрлікою
     * із попереднього положення perviousRotation
     *
     * @param perviousRotation Положення з якого було проведено поворот
     * @return Положення яке стало
     */
    public static RotationMode getNextRotationFrom(RotationMode perviousRotation) {
        int newRotationIndex = (perviousRotation.number + 1) % rotationByNumber.length;
        return rotationByNumber[newRotationIndex];
    }
}
