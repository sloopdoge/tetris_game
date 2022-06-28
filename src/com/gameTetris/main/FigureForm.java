
package com.gameTetris.main;

import com.gameTetris.graphics.TpReadableColor;

import java.util.Random;

/**
 * Поєднуємо усі параметри фігури, що залежать від її форми
 * Зараз: маска координат, колір
 */
public enum FigureForm {

    I_FORM (CoordMask.I_FORM, TpReadableColor.BLUE),
    J_FORM (CoordMask.J_FORM, TpReadableColor.ORANGE),
    L_FORM (CoordMask.L_FORM, TpReadableColor.YELLOW),
    O_FORM (CoordMask.O_FORM, TpReadableColor.RED),
    S_FORM (CoordMask.S_FORM, TpReadableColor.AQUA),
    Z_FORM (CoordMask.Z_FORM, TpReadableColor.PURPLE),
    T_FORM (CoordMask.T_FORM, TpReadableColor.GREEN);

    // Маска координат (задає геом форму)
    private CoordMask mask;

    // Колір цієї фігури
    private TpReadableColor color;

    /**
     * Конструктор
     *
     * @param mask Маска координат (задає геом форму)
     * @param color Колір характерної фігури
     */
    FigureForm(CoordMask mask, TpReadableColor color){
        this.mask = mask;
        this.color = color;
    }

    // Масив із всіма обєктами цього enum (для реалізації getRandomColor())
    private static final FigureForm[] formByNumber = {I_FORM, J_FORM, L_FORM, O_FORM, S_FORM, Z_FORM, T_FORM,};

    /**
     * @return Маска координат даної форми
     */
    public CoordMask getMask(){
        return this.mask;
    }

    /**
     * @return Колір цієї фігури
     */
    public TpReadableColor getColor(){
        return this.color;
    }

    /**
     * @return Случайний обєкт цього enum. Тобто форма
     */
    public static FigureForm getRandomForm() {
        int formNumber = new Random().nextInt(formByNumber.length);
        return formByNumber[formNumber];
    }

}
