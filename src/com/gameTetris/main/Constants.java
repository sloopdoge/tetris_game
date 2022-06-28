package com.gameTetris.main;

import com.gameTetris.graphics.TpReadableColor;

// Зберігає основні данні гри у public static final полях

public class Constants {

	// Розмір однієї плитки
	public static final int CELL_SIZE = 24;

	// Кількість ячейок по горизонталі та вертикалі
    public static final int COUNT_CELLS_X = 10;
    public static final int COUNT_CELLS_Y = 2 * COUNT_CELLS_X;

    // Невидимий простір, в якому створюються фігури
    public static final int OFFSET_TOP = 3;

	//параметри вікна
    public static final int SCREEN_WIDTH = COUNT_CELLS_X *CELL_SIZE;
    public static final int SCREEN_HEIGHT = COUNT_CELLS_Y *CELL_SIZE;
    public static final String SCREEN_NAME = "TETRIS";

    // У скільки раз потрібно збільшити швидкість
    public static final int BOOST_MULTIPLIER = 3;

    // На скільки клітинок зміщується фігура за секунду
    public static final int MOVEDOWNS_PER_SECOND = 3;

    // Скільки ігрових циклів за секунду
    public static final int FPS = 60;

    // За скільки циклів фігура зміститься на 1 клітинку
    public static final int FRAMES_PER_MOVE = FPS / MOVEDOWNS_PER_SECOND;

    // Колір, яких заповнюємо пустоту
    public static final TpReadableColor EMPTINESS_COLOR = TpReadableColor.BLACK;

	// Скільки блоків заповненні на початку
    public static final int BLOCKS_INITIAL_LEVEL = COUNT_CELLS_Y / 3;

    // Макс і Мін блоків створених в лінії на початку
    public static final int MISSNG_BLOCKS_IN_INITIAL_LINE_MIN = COUNT_CELLS_X/3;
    public static final int MISSNG_BLOCKS_IN_INITIAL_LINE_MAX = COUNT_CELLS_X/2;

    // Максимальна ширина
    public static final int MAX_FIGURE_WIDTH = 4;
}
