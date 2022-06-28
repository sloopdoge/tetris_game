package com.gameTetris.graphics;

import com.gameTetris.main.GameField;

/**
 * Дії які повинен виконувати графічний модуль
 */
public interface GraphicsModule {

    /**
     * Зобружуємо ігрове поле
     *
     * @param field Ігрове поле
     */
    void draw(GameField field);

    /**
     * @return true, якщо нажато хрестик
     */
    boolean isCloseRequested();

    /**
     * Якщо модуль потрібно почистити, після дій
     */
    void destroy();

    /**
     * Ігра призупиняється, якщо метод викликався, більше ніж 1/ФПС секунд назад
     */
    void sync(int fps);
}
