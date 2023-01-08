package com.shpp.p2p.cs.vpereverza.assignment4;

import java.awt.*;

public interface Constant {
    //Width and height of application window in pixels
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    // Dimensions of game board (usually the same)
    public static final int WIDTH_GAME_BOARD = APPLICATION_WIDTH;
    public static final int HEIGHT_GAME_BOARD = APPLICATION_HEIGHT;

    // Dimensions of the paddle
    public static final int PADDLE_WIDTH = 60;
    public static final int PADDLE_HEIGHT = 10;

    //public static final Color FILL_COLOR_PADDLE= Color.BLACK;
    public static final Color COLOR_ROWS_0_1 = Color.RED;
    public static final Color COLOR_ROWS_2_3 = Color.ORANGE;
    public static final Color COLOR_ROWS_4_5 = Color.YELLOW;
    public static final Color COLOR_ROWS_6_7 = Color.GREEN;
    public static final Color COLOR_ROWS_8_9 = Color.CYAN;

    // Offset of the paddle up from the bottom
    public static final int PADDLE_Y_OFFSET = 30;

    //Number of bricks per row
    public static final int NBRICKS_PER_ROW = 10;

    // Number of rows of bricks
    public static final int NBRICK_ROWS = 10;

    // Separation between bricks
    public static final int BRICK_SEP = 4;

    // Width of a brick
    public static final int BRICK_WIDTH = (WIDTH_GAME_BOARD - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    // Height of a brick
    public static final int BRICK_HEIGHT = 8;

    // Radius of the ball in pixels
    public static final int BALL_RADIUS = 10;
    //Ball color
    public static final Color FILL_COLOR_BALL = Color.BLACK;

    // Offset of the top brick row from the top
    public static final int BRICK_Y_OFFSET = 70;

    // Number of turns
    public static final int MAX_ROUNDS = 3;

    //message size
    public static final int WIDTH_WINDOWS_TEXT = APPLICATION_WIDTH / 3 * 2;
    public static final int HEIGHT_WINDOWS_TEXT = APPLICATION_HEIGHT / 3 * 2;

    // Maximum number of samples to win
    public static final int MOVING_THE_BALL_Y = 10;

    // Orientation of the message to the player along the x and y axes
    public static final int ORIENTATION_MESSAGE_X = WIDTH_GAME_BOARD / 2 - WIDTH_WINDOWS_TEXT / 2;
    public static final int ORIENTATION_MESSAGE_Y = HEIGHT_GAME_BOARD / 2 - HEIGHT_WINDOWS_TEXT / 2;
}
