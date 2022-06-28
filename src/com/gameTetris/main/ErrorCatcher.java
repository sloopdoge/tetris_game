
package com.gameTetris.main;

// Виловлюємо можливі помилки і виводимо їх

public class ErrorCatcher {
    /**
     * Неправильний параметр
     *
     * @param eparameter опис неправильного параметру
     * @param eclass опис класу, в якому відбулась помилка
     */
    public static void wrongParameter(String eparameter, String eclass) {
        System.err.println("Wrong parameter "+eparameter+" occured in class "+eclass);
        System.exit(-2);
    }

     // Помилка графічного модуля

    public static void graphicsFailure(Exception e) {
        System.err.println("GraphicsModule failed.");
        e.printStackTrace();
        System.exit(-3);
    }

}
