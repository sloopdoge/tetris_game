
package com.gameTetris.main;

// Геометрична форма фігури

public enum CoordMask {
    /*
        [][][][]
    */
    I_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initalCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                        case INVERT:
                            ret[0] = initalCoord;
                            ret[1] = new Coord(initalCoord.x , initalCoord.y - 1);
                            ret[2] = new Coord(initalCoord.x, initalCoord.y - 2);
                            ret[3] = new Coord(initalCoord.x, initalCoord.y - 3);
                            break;
                        case FLIP_CCW:
                        case FLIP_CW:
                            ret[0] = initalCoord;
                            ret[1] = new Coord(initalCoord.x + 1, initalCoord.y);
                            ret[2] = new Coord(initalCoord.x + 2, initalCoord.y);
                            ret[3] = new Coord(initalCoord.x + 3, initalCoord.y);
                            break;
                        default:
                            ErrorCatcher.wrongParameter("rotation", "CoordMask");
                    }

                    return ret;
                }
            }
    ),
    /*
        []
        [][][]
     */
    J_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initalCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                            ret[0] = new Coord(initalCoord.x + 1 , initalCoord.y);
                            ret[1] = new Coord(initalCoord.x + 1, initalCoord.y - 1);
                            ret[2] = new Coord(initalCoord.x + 1, initalCoord.y - 2);
                            ret[3] = new Coord(initalCoord.x, initalCoord.y - 2);
                            break;
                        case INVERT:
                            ret[0] = new Coord(initalCoord.x + 1 , initalCoord.y);
                            ret[1] = initalCoord;
                            ret[2] = new Coord(initalCoord.x, initalCoord.y - 1);
                            ret[3] = new Coord(initalCoord.x, initalCoord.y - 2);
                            break;
                        case FLIP_CCW:
                            ret[0] = initalCoord;
                            ret[1] = new Coord(initalCoord.x + 1, initalCoord.y);
                            ret[2] = new Coord(initalCoord.x + 2, initalCoord.y);
                            ret[3] = new Coord(initalCoord.x + 2, initalCoord.y - 1);
                            break;
                        case FLIP_CW:
                            ret[0] = initalCoord;
                            ret[1] = new Coord(initalCoord.x, initalCoord.y - 1);
                            ret[2] = new Coord(initalCoord.x + 1, initalCoord.y - 1);
                            ret[3] = new Coord(initalCoord.x + 2, initalCoord.y - 1);
                            break;
                        default:
                            ErrorCatcher.wrongParameter("rotation", "CoordMask");
                    }

                    return ret;
                }
            }
    ),
    /*
                []
            [][][]
     */
    L_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initialCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x, initialCoord.y - 2);
                            ret[3] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                            break;
                        case INVERT:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                            break;
                        case FLIP_CCW:
                            ret[0] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x + 2, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y);
                            break;
                        case FLIP_CW:
                            ret[0] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[1] = initialCoord;
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y);
                            break;
                        default:
                            ErrorCatcher.wrongParameter("rotation", "CoordMask");
                    }

                    return ret;
                }
            }
    ),
    /*
        [][]
        [][]
     */
    O_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initialCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    ret[0] = initialCoord;
                    ret[1] = new Coord(initialCoord.x, initialCoord.y - 1);
                    ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                    ret[3] = new Coord(initialCoord.x + 1, initialCoord.y);

                    return ret;
                }
            }
    ),
    /*      [][]
          [][]
     */
    S_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initialCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                        case INVERT:
                            ret[0] = new Coord(initialCoord.x , initialCoord.y - 1);
                            ret[1] = new Coord(initialCoord.x + 1 , initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y);
                            break;
                        case FLIP_CCW:
                        case FLIP_CW:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                            break;
                        default:
                            ErrorCatcher.wrongParameter("rotation", "CoordMask");
                    }

                    return ret;
                }
            }
    ),
    /*
        [][]
          [][]
     */
    Z_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initialCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                        case INVERT:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x + 1 , initialCoord.y);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y - 1);
                            break;
                        case FLIP_CCW:
                        case FLIP_CW:
                            ret[0] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x, initialCoord.y - 2);
                            break;
                        default:
                            ErrorCatcher.wrongParameter("rotation", "CoordMask");
                    }

                    return ret;
                }
            }
    ),
    /*  [][][]
            []
     */
    T_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initialCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y);
                            break;
                        case INVERT:
                            ret[0] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y - 1);
                            break;
                        case FLIP_CCW:
                            ret[0] = initialCoord;;
                            ret[1] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x, initialCoord.y - 2);
                            break;
                        case FLIP_CW:
                            ret[0] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                            break;
                        default:
                            ErrorCatcher.wrongParameter("rotation", "CoordMask");
                    }

                    return ret;
                }
            }
    );

    // Делегат, який обирає алгоритм для generateFigure()

    private interface GenerationDelegate{

        /**
         * За умовними координатами та станом повороту
         * Повертає 4 координати, які відображаються
         *
         * @param initialCoord Умовна координата
         * @param rotation Стан повороту
         * @return 4 реальні координати
         */
        Coord[] generateFigure(Coord initialCoord,  RotationMode rotation);
    }

    /**
     * Делегат, який обирає алгоритм для generateFigure()
     * Особливий для кожного елемента enum
     */
    private GenerationDelegate forms;

    /**
     * Конструктор
     * @param forms Делегат, який обирає алгоритм для generateFigure()
     */
    CoordMask(GenerationDelegate forms){
        this.forms = forms;
    }

    /**
     * За умовними координатами та станом повороту
     * Повертає 4 координати, які відображаються
     *
     * Запрос передається делегату, особливий для кожного елемента enum
     *
     * @param initialCoord Умовна координата
     * @param rotation Стан повороту
     * @return 4 реальні координати
     */
    public Coord[] generateFigure(Coord initialCoord, RotationMode rotation){
        return this.forms.generateFigure(initialCoord, rotation);
    }
}
