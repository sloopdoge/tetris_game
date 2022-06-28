
package com.gameTetris.main;

import com.gameTetris.graphics.TpReadableColor;

 // Зберігає і змінює умовну координату фігури, форму фігури і стан повороту

public class Figure {
    /**
     * Умовна координата фігури. По ній, через маску
     * генеруються реальні координати фігури кожного блоку фігури
     */
    private Coord metaPointCoords;

     // Стан повороту в даний момент

    private RotationMode currentRotation;

     // Форма фигури

    private FigureForm form;

    /**
     * Конструктор
     * Стан повороту за замовчуванням: RotationMode.NORMAL <br>
     * Форма случайна
     *
     * @param metaPointCoords Умовна координа фігури
     */
    public Figure(Coord metaPointCoords){
        this(metaPointCoords, RotationMode.NORMAL, FigureForm.getRandomForm());
    }

    /**
     * @param metaPointCoords Умовна координа фігури
     * @param rotation Стан повороту
     * @param form Форма фігури
     */
    public Figure(Coord metaPointCoords, RotationMode rotation, FigureForm form){
        this.metaPointCoords = metaPointCoords;
        this.currentRotation = rotation;
        this.form = form;
    }

    /**
     * @return Координа реальний ячейок фігури в даний момент
     */
    public Coord[] getCoords(){
        return form.getMask().generateFigure(metaPointCoords, currentRotation);
    }

    /**
     * @return Координа, якби фігуру повернули проти год стрілки
     */
    public Coord[] getRotatedCoords(){
        return form.getMask().generateFigure(metaPointCoords, RotationMode.getNextRotationFrom(currentRotation));
    }

     // Повертаємо фігуру проти год стрілки

    public void rotate(){
        this.currentRotation = RotationMode.getNextRotationFrom(currentRotation);
    }

    /**
     * @param direction Напрям переміщення
     * @return Координа ячейок фігури, якби вона була переміщенна в даному напрямку
     */
    public Coord[] getShiftedCoords(ShiftDirection direction){
        Coord newFirstCell = null;

        switch (direction){
            case LEFT:
                newFirstCell = new Coord(metaPointCoords.x - 1, metaPointCoords.y);
                break;
            case RIGHT:
                newFirstCell = new Coord(metaPointCoords.x + 1, metaPointCoords.y);
                break;
            default:
                ErrorCatcher.wrongParameter("direction (for getShiftedCoords)", "Figure");
        }

        return form.getMask().generateFigure(newFirstCell, currentRotation);
    }

    /**
     * Змінює умовну X-координату фігури
     * для переміщення в заданому напрямку
     *
     * @param direction Напрямок переміщення
     */
    public void shift(ShiftDirection direction){
        switch (direction){
            case LEFT:
                metaPointCoords.x--;
                break;
            case RIGHT:
                metaPointCoords.x++;
                break;
            default:
                ErrorCatcher.wrongParameter("direction (for shift)", "Figure");
        }
    }

    /**
     * @return Координата фігури, якби вона змістилась на 1 ячейку вниз
     */
    public Coord[] getFallenCoords(){
        Coord newFirstCell = new Coord(metaPointCoords.x, metaPointCoords.y - 1);

        return form.getMask().generateFigure(newFirstCell, currentRotation);
    }

    /**
     * Змінює умовну Y-координату фігури
     * для переміщення на 1 ячейку вниз
     */
    public void fall(){
        metaPointCoords.y--;
    }

    /**
     * @return Колір фігури
     */
    public TpReadableColor getColor() {
        return form.getColor();
    }
}
