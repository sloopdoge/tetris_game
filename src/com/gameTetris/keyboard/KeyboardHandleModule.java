
package com.gameTetris.keyboard;

import com.gameTetris.main.ShiftDirection;

/**
 * Параметри, які потрібна зчитати із клавіатури
 */
public interface KeyboardHandleModule {

    // Зчитування останіх дій

    void update();

    /**
     * @return Повертає інформацію чи був нажатий ESCAPE за останню ітерацію
     */
    boolean wasEscPressed();

    /**
     * @return Напрям, куди потрібно перемістити фігуру
     * Якщо нічого не нажато, то повертаємо ShiftDirection.AWAITING.
     */
    ShiftDirection getShiftDirection();

    /**
     * @return true, якщо потрбіно повернути фігуру.
     */
    boolean wasRotateRequested();

    /**
     * @return true, якщо потрібно прискорити фігуру
     */
    boolean wasBoostRequested();
}
