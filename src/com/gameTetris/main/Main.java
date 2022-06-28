package com.gameTetris.main;

import com.gameTetris.graphics.GraphicsModule;
import com.gameTetris.graphics.lwjglmodule.LwjglGraphicsModule;
import com.gameTetris.keyboard.KeyboardHandleModule;
import com.gameTetris.keyboard.lwjglmodule.LwjglKeyboardHandleModule;

import static com.gameTetris.main.Constants.BOOST_MULTIPLIER;
import static com.gameTetris.main.Constants.FPS;
import static com.gameTetris.main.Constants.FRAMES_PER_MOVE;

public class Main {

    private static boolean endOfGame;// Закінчення гри
    private static GraphicsModule graphicsModule;// Графічний модуль
    private static KeyboardHandleModule keyboardModule;// Модуль клавіатури
    private static GameField gameField;// Ігрове поле
    private static ShiftDirection shiftDirection;// Напрямок для переміщення
    private static boolean isRotateRequested;// Чи потрібно повернути фігуру
    private static boolean isBoostRequested;// Чи потрібно прискорити фігуру
    private static int loopNumber;// Кількість ітерацій в грі

    public static void main(String[] args) {
        initFields();

        while(!endOfGame){
            input();
            logic();

            graphicsModule.draw(gameField);
            graphicsModule.sync(FPS);
        }

        graphicsModule.destroy();
    }

     // Початкові значення полів, на початку гри

    private static void initFields() {
        loopNumber = 0;
        endOfGame = false;
        shiftDirection = ShiftDirection.AWAITING;
        isRotateRequested = false;
        graphicsModule = new LwjglGraphicsModule();
        keyboardModule = new LwjglKeyboardHandleModule();
        gameField = new GameField();
    }

     // Зчитування команд користувача і виконання них в logic().

    private static void input(){

		// Оновлюємо дані модуля вводу
        keyboardModule.update();

		// Зчитуємо дані про напрямок для переміщення падаючої фігури
        shiftDirection = keyboardModule.getShiftDirection();

		// Зчитуємо дані чи хоче користувач повернути фігуру
        isRotateRequested = keyboardModule.wasRotateRequested();

		// Зчитуємо дані чи хоче користувач пришвидшити фігуру
        isBoostRequested = keyboardModule.wasBoostRequested();
		
		// Якщо нажато ESC чи хрестик на вікні, закінчуємо гру
        endOfGame = endOfGame || keyboardModule.wasEscPressed() || graphicsModule.isCloseRequested();
    }

    // Основні дії в грі
    private static void logic(){
       if(shiftDirection != ShiftDirection.AWAITING){

           gameField.tryShiftFigure(shiftDirection);

           shiftDirection = ShiftDirection.AWAITING;
       }

       if(isRotateRequested){

           gameField.tryRotateFigure();

           isRotateRequested = false;
       }

        // Падіння фігури відбувається, якщо loopNumber % FRAMES_PER_MOVE == 0

       if( (loopNumber % (FRAMES_PER_MOVE / (isBoostRequested ? BOOST_MULTIPLIER : 1)) ) == 0) gameField.letFallDown();

        // Збільшуємо кількість ітерацій
       loopNumber = (loopNumber+1)% (FRAMES_PER_MOVE);

        // Якщо поле перевовнено, гру закінчено
       endOfGame = endOfGame || gameField.isOverfilled();
    }

}
