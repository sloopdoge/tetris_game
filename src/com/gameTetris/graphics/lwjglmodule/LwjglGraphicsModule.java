package com.gameTetris.graphics.lwjglmodule;

import com.gameTetris.graphics.GraphicsModule;
import com.gameTetris.graphics.TpReadableColor;
import com.gameTetris.main.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import static org.lwjgl.opengl.GL11.*;
import static com.gameTetris.main.Constants.*;

/**
 * Реалізація ігрового модуля через LWJGL.
 */
public class LwjglGraphicsModule implements GraphicsModule {

    /**
     * Конструктор.
     * Двіжок і необхідні поля модуля.
     */
    public LwjglGraphicsModule() {
        initOpengl();
    }

    private void initOpengl() {
        try {
            /* Розмір вікна */
            Display.setDisplayMode(new DisplayMode(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));

            /* Імя вікна */
            Display.setTitle(Constants.SCREEN_NAME);

            /* Свторюємо вікно */
            Display.create();
        } catch (LWJGLException e) {
            ErrorCatcher.graphicsFailure(e);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Constants.SCREEN_WIDTH,0, Constants.SCREEN_HEIGHT,1,-1);
        glMatrixMode(GL_MODELVIEW);

		/* Для текстур */
        glEnable(GL_TEXTURE_2D);

		/* Прозрачність */
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		/* Білий фоновий текст */
        glClearColor(1,1,1,1);
    }



    /**
     * Зображуємо ячейку
     *
     * @param x Координата X
     * @param y Координата Y
     * @param color Колір ячейки
     */
    private void drawCell(int x, int y, Color color) {
        glColor3ub(color.getRedByte(), color.getGreenByte(), color.getBlueByte());

        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(x,y+ Constants.CELL_SIZE);
        glTexCoord2f(1,0);
        glVertex2f(x+ Constants.CELL_SIZE,y+ Constants.CELL_SIZE);
        glTexCoord2f(1,1);
        glVertex2f(x+ Constants.CELL_SIZE, y);
        glTexCoord2f(0,1);
        glVertex2f(x, y);
        glEnd();
    }

    /**
     * Зображуємо ігрове поле
     *
     * @param field Ігрове поле, яке потрібно зобразити
     */
    @Override
    public void draw(GameField field) {
        glClear(GL_COLOR_BUFFER_BIT);

        for(int x = 0; x < COUNT_CELLS_X; x++){
            for(int y = 0; y < COUNT_CELLS_Y; y++){
                TpReadableColor color = field.getColor(x,y);
                drawCell(x*CELL_SIZE, y*CELL_SIZE, convertColor(color));
            }
        }

        Figure figure = field.getFigure();
        TpReadableColor color = figure.getColor();
        for(Coord coord : figure.getCoords()){
            drawCell(coord.x * CELL_SIZE, coord.y * CELL_SIZE, convertColor(color));
        }

        Display.update();
    }

    /**
     * Вертає білий колір, якщо ніяких дій не виконуємо
     *
     * @param color Колір із створене enum
     * @return Color з яким працює LWJGL
     */
    private Color convertColor(TpReadableColor color) {
        switch (color){
            case RED:
                return new Color(ReadableColor.RED);
            case GREEN:
                return new Color(ReadableColor.GREEN);
            case BLUE:
                return new Color(ReadableColor.BLUE);
            case AQUA:
                return new Color(ReadableColor.CYAN);
            case YELLOW:
                return new Color(ReadableColor.YELLOW);
            case ORANGE:
                return new Color(ReadableColor.ORANGE);
            case PURPLE:
                return new Color(ReadableColor.PURPLE);
            case BLACK:
                return new Color(ReadableColor.BLACK);
            default:
                return new Color(ReadableColor.WHITE);
        }
    }

    /**
     * @return true, якщо нажато хрестик
     */
    @Override
    public boolean isCloseRequested() {
        return Display.isCloseRequested();
    }

    /**
     * Знищуємо вікно
     */
    @Override
    public void destroy() {
        Display.destroy();
    }

    /**
     * Ігра призупиняється, якщо метод викликався, більше ніж 1/ФПС в секунду
     */
    @Override
    public void sync(int fps) {
        Display.sync(fps);
    }
}
