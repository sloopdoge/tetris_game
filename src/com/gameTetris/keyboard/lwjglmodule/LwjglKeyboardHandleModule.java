package com.gameTetris.keyboard.lwjglmodule;

import org.lwjgl.input.Keyboard;
import com.gameTetris.keyboard.KeyboardHandleModule;
import com.gameTetris.main.ShiftDirection;

// Считуємо данні із клавіатури за допомогою LWJGL

public class LwjglKeyboardHandleModule implements KeyboardHandleModule {

    // Чи був нажатий ESC
    private boolean wasEscPressed;

    /**
     * Чи був запрос на поворот фігури за останню ітерацію
     * Зараз означає "Была ли за последнюю итерацию нажата стрелка вверх"
     */
    private boolean wasRotateRequested;

    /**
     * Напрям, в якому потрібно пересунути фігуру
     * Якщо не потрібно, то значення ShiftDirection.AWAITING
     */
    private ShiftDirection shiftDirection;

     // Зчитуємо дані із останньої ітерації

    @Override
    public void update() {
        resetValues();

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                switch(Keyboard.getEventKey()){
                    case Keyboard.KEY_ESCAPE:
                        wasEscPressed = true;
                        break;
                    case Keyboard.KEY_UP:
                        wasRotateRequested = true;
                        break;
                    case Keyboard.KEY_LEFT:
                        shiftDirection = ShiftDirection.LEFT;
                        break;
                    case Keyboard.KEY_RIGHT:
                        shiftDirection = ShiftDirection.RIGHT;
                        break;
                }
            }
        }
    }

    // Обнуляємо данні, для наступних ітерацій

    private void resetValues() {
        wasEscPressed = false;
        wasRotateRequested = false;
        shiftDirection = ShiftDirection.AWAITING;
    }

    /**
     * @return інформація чи був нажатий ESCAPE за останню ітерацію
     */
    @Override
    public boolean wasEscPressed() {
        return wasEscPressed;
    }

    /**
     * @return напрям, в якому потрібно перемістити фігуру
     * Якщо не потрібно, то повертаємо ShiftDirection.AWAITING.
     */
    @Override
    public ShiftDirection getShiftDirection() {
        return shiftDirection;
    }

    /**
     * @return true, якщо потрібно повернути фігуру
     */
    @Override
    public boolean wasRotateRequested() {
        return wasRotateRequested;
    }

    /**
     * @return true, якщо потрібно прискорити фігуру
     */
    @Override
    public boolean wasBoostRequested() {
        return Keyboard.isKeyDown(Keyboard.KEY_DOWN);
    }


}
