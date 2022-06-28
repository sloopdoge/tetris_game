package com.gameTetris.main;

import com.gameTetris.graphics.TpReadableColor;
import java.util.Random;
import com.gameTetris.main.Constants.*;

import static com.gameTetris.main.Constants.*;

/**
 * Зберігає дані про поле та фігуру що падає
 * Методи для отримання та зберігання даних
 */
public class GameField {

    // Колір ячейок поля, для пустих використовуємо EMPTINESS_COLOR
    private TpReadableColor[][] theField;

    // Кількість не пустих ячейок
    private int[] countFilledCellsInLine;

    // Інформація падаючої фігури
    private Figure figure;

    /**
     * Генерує початкові блоки і фігуру
     * Ініціалізуємо всі поля класу
     */
    public GameField(){
        spawnNewFigure();

        theField = new TpReadableColor[COUNT_CELLS_X][COUNT_CELLS_Y+OFFSET_TOP];
        countFilledCellsInLine = new int[COUNT_CELLS_Y+OFFSET_TOP];

        Random rnd = new Random();

        // До певнох межі заповнюємо пустими ячейками
        for(int y = 0; y < BLOCKS_INITIAL_LEVEL; y++){

            // Кількість пустих ячейок в лінії
            int missingBlocksCount = MISSNG_BLOCKS_IN_INITIAL_LINE_MIN
                    + rnd.nextInt(MISSNG_BLOCKS_IN_INITIAL_LINE_MAX - MISSNG_BLOCKS_IN_INITIAL_LINE_MIN);

            // X-координати пустих блоків в лінії
            int[] missingBlocksXCoords = new int[missingBlocksCount];

            /*
             * Пусті блоки генеруються зліва направо
             * Кожен наступний блок не може бути ближче до краю на
             * кількість блоків, які осталось згенерувати
             */
            missingBlocksXCoords[0] = rnd.nextInt(COUNT_CELLS_X - (missingBlocksCount - 1) );
            for(int i = 1; i < missingBlocksCount; i++){
                int previousCoord = missingBlocksXCoords[i-1];
                int offset = rnd.nextInt(COUNT_CELLS_X-(previousCoord-1));

                missingBlocksXCoords[i] =  previousCoord + offset;
            }

            /*
             * Якщо Х-координата не попала в missingBlocksXCoords,
             * Створюємо в ній фігуру случайного кольору
             */
            int q = 0;
            for(int x = 0; x < COUNT_CELLS_X; x++){
                if( (q < missingBlocksCount) && (missingBlocksXCoords[q] == x) ) {
                    theField[x][y] = EMPTINESS_COLOR;
                    q++;
                } else {
                    theField[x][y] = TpReadableColor.getRandomColor();
                    countFilledCellsInLine[y]++;
                }
            }
        }

        // Простір, що остався заповнюємо пустими блоками
        for(int y = BLOCKS_INITIAL_LEVEL; y < COUNT_CELLS_Y + OFFSET_TOP; y++){
            for(int x = 0; x < COUNT_CELLS_X; x++){
                theField[x][y] = EMPTINESS_COLOR;
            }
        }
    }

    /**
     * Створюємо фігуру в невидимій зоні
     * X-координата не повинна бути блища до краю ніж ширина фігури
     */
    private void spawnNewFigure(){
       int randomX = new Random().nextInt(COUNT_CELLS_X - MAX_FIGURE_WIDTH);

       this.figure = new Figure(new Coord(randomX, COUNT_CELLS_Y + OFFSET_TOP - 1));
    }

    /**
     * @param x X-координата ячейки, яку перевіряємо
     * @param y Y-координата ячейки, яку перевіряємо
     * @return Чи ячейка з даними коорд пуста
     */
    public boolean isEmpty(int x, int y){
        return (theField[x][y].equals(EMPTINESS_COLOR));
    }

    /**
     * @param x X-координата ячейки, яку перевіряємо
     * @param y Y-координата ячейки, яку перевіряємо
     * @return Колір з даними коорд
     */
    public TpReadableColor getColor(int x, int y) {
        return theField[x][y];
    }

    /**
     * @return Інформація про падаючу фігуру в данний момент
     */
    public Figure getFigure() {
        return figure;
    }

    /**
     * Якщо можливо, то переміщуємо фігуру в сторону shiftDirection
     *
     * @param shiftDirection напрмям для переміщення
     */
    public void tryShiftFigure(ShiftDirection shiftDirection) {
        Coord[] shiftedCoords = figure.getShiftedCoords(shiftDirection);

        boolean canShift = true;

        for(Coord coord: shiftedCoords) {
            if((coord.y<0 || coord.y>=COUNT_CELLS_Y+OFFSET_TOP)
             ||(coord.x<0 || coord.x>=COUNT_CELLS_X)
             || ! isEmpty(coord.x, coord.y)){
                canShift = false;
            }
        }

        if(canShift){
            figure.shift(shiftDirection);
        }
    }

     // Якщо можливо, то повертаємо за год стрілкою

    public void tryRotateFigure() {
        Coord[] rotatedCoords = figure.getRotatedCoords();

        boolean canRotate = true;

        for(Coord coord: rotatedCoords) {
            if((coord.y<0 || coord.y>=COUNT_CELLS_Y+OFFSET_TOP)
                    ||(coord.x<0 || coord.x>=COUNT_CELLS_X)
                    ||! isEmpty(coord.x, coord.y)){
                canRotate = false;
            }
        }

        if(canRotate){
            figure.rotate();
        }
    }

    /**
     * Якщо модна перемістити вниз, переміщуємо
     * Якщо неможливо робимо її статичною, переносимо в список і створюємо нову
     */
    public void letFallDown() {
        Coord[] fallenCoords = figure.getFallenCoords();

        boolean canFall = true;

        for(Coord coord: fallenCoords) {
            if((coord.y<0 || coord.y>=COUNT_CELLS_Y+OFFSET_TOP)
                    ||(coord.x<0 || coord.x>=COUNT_CELLS_X)
                    ||! isEmpty(coord.x, coord.y)){
                canFall = false;
            }
        }

        if(canFall) {
            figure.fall();
        } else {
            Coord[] figureCoords = figure.getCoords();

            /* Флаг, який повідомляє, що лінія буде переміщенна вниз
             * тобто буде знищенна
             */
            boolean haveToShiftLinesDown = false;

            for(Coord coord: figureCoords) {
                theField[coord.x][coord.y] = figure.getColor();

                // Збільшуємо інформацію про статичні фігури
                countFilledCellsInLine[coord.y]++;

                /* Перевіряємо чи заповненна коорд Y
                 * Якщо заповненна, то haveToShiftLinesDown = true
                 */
                haveToShiftLinesDown = tryDestroyLine(coord.y) || haveToShiftLinesDown;
            }

            // Якщо потрібно зміщуємо фігури в пусту клітинку
            if(haveToShiftLinesDown) shiftLinesDown();

            // Створюємо нову форму фігури, яку знущили
            spawnNewFigure();
        }
    }

    // Якщо є пусті лінії, то зміщуємо в них усі лінії, що вище
    private void shiftLinesDown() {

        // Номер пустої лінії
        int fallTo = -1;

        // Перевіряємо ліній зверху, донизу
        for(int y = 0; y < COUNT_CELLS_Y; y++){
            if(fallTo == -1){ // Якщо не знайшли пустих
                if(countFilledCellsInLine[y] == 0) fallTo = y;
            }else { // Якщо знайшли
                if(countFilledCellsInLine[y] != 0){

                    // Зміщуємо
                    for(int x = 0; x < COUNT_CELLS_X; x++){
                        theField[x][fallTo] = theField[x][y];
                        theField[x][y] = EMPTINESS_COLOR;
                    }

                    // Обновляємо мета інформацію
                    countFilledCellsInLine[fallTo] = countFilledCellsInLine[y];
                    countFilledCellsInLine[y] = 0;

                    fallTo++;
                }
            }
        }
    }

    /**
     * Якщо лінія заповенна, знищуємо її але не зміщуємо інші лінії
     *
     * @param y Номер лінії
     * @return чи знищена лінія
     */
    private boolean tryDestroyLine(int y) {
        if(countFilledCellsInLine[y] < COUNT_CELLS_X){
            return false;
        }

        for(int x = 0; x < COUNT_CELLS_X; x++){
            theField[x][y] = EMPTINESS_COLOR;
        }

        countFilledCellsInLine[y] = 0;

        return true;
    }

    /**
     * @return Чи є в невидимій зоні статичні блоки
     */
    public boolean isOverfilled(){
        boolean ret = false;

        for(int i = 0; i < OFFSET_TOP; i++){
            if(countFilledCellsInLine[COUNT_CELLS_Y+i] != 0) ret = true;
        }

        return ret;
    }

}
