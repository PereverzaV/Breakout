package com.shpp.p2p.cs.vpereverza.assignment4;


import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;

/**
 * Write a game where the ball breaks the bricks.
 * The number of possible levels, object sizes, ball speed, etc. are described in constants.
 * To start the game, click the mouse and wait for the timer to start. Use the mouse to control the racket to hit the ball.
 * According to the basic code, you have 3 attempts to break all the balls.
 * If the player failed to hit the ball with the racket and it left the screen, then 1 life is lost.
 * If all the bricks are broken, the player is considered to have won.
 * If the player has spent all the attempts, and there are bricks left on the screen, this means the player loses.
 */
public class Breakout extends WindowProgram implements Constant {

    GRect paddle = createAnPaddle();

    /**
     * Create a breakout game
     */
    public void run() {
        createGameBreakount();
    }

    /**
     * We create a game in which the player loses if the number of possible misses exceeds the number of possible balls,
     * or wins if he smashes all the dice.
     * Before each round, an appeal to the player is displayed on the screen.
     * After pressing the key on the mouse, the game start timer is read and the round starts.
     * In each round, the player breaks a certain number of bricks, and the method returns this value to record the win.
     * The round ends if the player loses the ball. Therefore, the number of completed rounds increases by 1.
     * And so on until the maximum value is reached, if not all bricks are broken.
     * After the end of the game, a congratulation in case of victory or a message about loss appears on the player's screen.
     */
    private void createGameBreakount() {
        int numberRound = 0;
        double numberBricks = NBRICKS_PER_ROW * NBRICK_ROWS;
        createABlockOfBricks();
        while (numberBricks > 0 && numberRound < MAX_ROUNDS) {
            GImage messege = demonstrationText(numberRound, numberBricks);
            waitForClick();
            remove(messege);
            createTimer();
            numberBricks = createRound(numberBricks);
            numberRound++;
        }
        if (numberRound == 3 || numberBricks == 0) {
            demonstrationText(numberRound, numberBricks);
        }
    }

    /**
     * Before the start of the game, the player sees a timer that counts down from three to one,
     * with a certain pause so that the score has time to adjust to the game.
     */
    private void createTimer() {
        addSecond("images/3.png", "music/gluhoy-stuk-molotka-s-ehom.wav");
        addSecond("images/2.png", "music/gluhoy-stuk-molotka-s-ehom.wav");
        addSecond("images/1.png", "music/gluhoy-stuk-molotka-s-ehom.wav");
        addSecond("images/go.png", "music/gluhoy-stuk-molotka-s-ehom.wav");
    }

    /**
     * Implements an imitation of a stopwatch.
     *
     * @param pathImage path to the image
     * @param pathMusic the way to audio
     */
    private void addSecond(String pathImage, String pathMusic) {
        GImage second = addImage(pathImage, pathMusic);
        pause(1000);
        remove(second);
    }

    /**
     * Create messages for the player.
     * Before each round, report the number of lives to the thread,
     * if all bricks are broken - congratulations,
     * if all attempts are used - report loss
     *
     * @param numberRound  round number
     * @param numberBricks the number of bricks on the screen
     * @return an image with a message for the player
     */
    private GImage demonstrationText(int numberRound, double numberBricks) {
        if (numberBricks == 0) return addImage("images/champion.jpg",
                "music/zvuk-pobedyi-vyiigryisha.wav");

        if (numberRound == 0) return addImage("images/startGame.png",
                "music/plavnyiy-zvuk-melodii-v-igre-40901.wav");

        if (numberRound == MAX_ROUNDS - 2) return addImage("images/2life.png",
                "music/plavnyiy-zvuk-melodii-v-igre-40901.wav");

        if (numberRound == MAX_ROUNDS - 1) return addImage("images/1life.png",
                "music/plavnyiy-zvuk-melodii-v-igre-40901.wav");

        if (numberRound == MAX_ROUNDS) return addImage("images/gameOver.jpg",
                "music/konets-igryi-zvuk-smerti-41555.wav");

        return addImage("images/nextLevel.png",
                "music/plavnyiy-zvuk-melodii-v-igre-40901.wav");
    }

    /**
     * This method displays the image on the screen with the corresponding music
     *
     * @param pathImage path to the image
     * @param pathMusic the way to audio
     * @return image
     */
    private GImage addImage(String pathImage, String pathMusic) {
        GImage message = new GImage(pathImage, ORIENTATION_MESSAGE_X, ORIENTATION_MESSAGE_Y);
        message.setSize(WIDTH_WINDOWS_TEXT, HEIGHT_WINDOWS_TEXT);
        add(message);
        Sound.playSound(pathMusic);
        return message;
    }

    /**
     * Create a method in which the ball is made within the yakran, breaking the bricks on its way.
     * Create a mouse listener that will control the movement of the racket on the screen.
     *
     * @param numberBricks the number of bricks on the screen
     * @return the number of broken bricks in this round
     */
    private double createRound(double numberBricks) {
        addMouseListeners();
        return movementBall(numberBricks);
    }

    /**
     * The mouse listener changes the location of the racket depending on the position of the cursor on the screen
     * if the cursor is within the screen.
     */
    public void mouseMoved(MouseEvent mouse) {
        if (mouse.getX() > PADDLE_WIDTH / 2 && mouse.getX() < getWidth() - PADDLE_WIDTH / 2) {
            paddle.setLocation(mouse.getX() - PADDLE_WIDTH / 2.0, getHeight() - PADDLE_Y_OFFSET);
        }
    }

    /**
     * Create a rectangular racket according to the characteristics of size and position given in constants
     */
    private GRect createAnPaddle() {
        GRect paddle1 = new GRect(getWidth() / 2.0 - PADDLE_WIDTH / 2.0,
                getHeight() - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle1.setFilled(true);
        paddle1.setColor(FILL_COLOR_BALL);
        add(paddle1);
        return paddle1;
    }

    /**
     * Build a block of bricks
     */
    private void createABlockOfBricks() {
        for (int rows = 0; rows < NBRICK_ROWS; rows++) {
            for (int brick = 0; brick < NBRICKS_PER_ROW; brick++) {
                createBrick(rows, brick);
            }
        }
    }

    /**
     * Build bricks by painting them in the right color corresponding to the row number.
     *
     * @param numberRows   row number
     * @param numberBricks brick number
     */
    private void createBrick(int numberRows, int numberBricks) {
        int startX = WIDTH_GAME_BOARD / 2 - (BRICK_WIDTH * NBRICKS_PER_ROW + BRICK_SEP * (NBRICKS_PER_ROW - 1)) / 2;
        GRect brick = new GRect(startX + numberBricks * (BRICK_WIDTH + BRICK_SEP),
                BRICK_Y_OFFSET + numberRows * (BRICK_HEIGHT + BRICK_SEP), BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        if (numberRows == 0 || numberRows == 1) {
            brick.setColor(COLOR_ROWS_0_1);
        }
        if (numberRows == 2 || numberRows == 3) {
            brick.setColor(COLOR_ROWS_2_3);
        }
        if (numberRows == 4 || numberRows == 5) {
            brick.setColor(COLOR_ROWS_4_5);
        }
        if (numberRows == 6 || numberRows == 7) {
            brick.setColor(COLOR_ROWS_6_7);
        }
        if (numberRows == 8 || numberRows == 9) {
            brick.setColor(COLOR_ROWS_8_9);
        }
        add(brick);
    }

    /**
     * Create a ball that moves across the screen.
     * The starting position of the ball is in the center of the screen.
     * Then the ball starts to move in a random direction.
     * The ball bounces off all sides of the screen except the bottom.
     * When the coordinates of the ball fall on the coordinates of the racket,
     * the ball bounces off it (changing its direction).
     * If in the lower part of the screen the ball did not bounce off the racket and leave the lower part of the screen,
     * then the round is considered lost.
     * When the coordinates of any corner of the circle touch the coordinates of a brick
     * we remove the brick from the screen and change the direction of the ball.
     *
     * @param numberBricks the number of bricks on the screen
     * @return the number of removed bricks
     */
    private double movementBall(double numberBricks) {
        int vy = MOVING_THE_BALL_Y;
        double vx = vx();
        GOval ball = ball();
        while (numberBricks > 0 && ball.getY() < HEIGHT_GAME_BOARD) {
            if (ball.getX() < 0 || ball.getX() > WIDTH_GAME_BOARD - BALL_RADIUS * 2) {
                vx = -vx;
            }
            if (ball.getY() < 0) {
                vy = -vy;
            }
            ball.move(vx, vy);
            pause(30);
            GObject collider = getCollidingObject(ball.getX(), ball.getY());
            if (collider != null) {
                if (collider == paddle) {
                    vy = -vy;
                    Sound.playSound("music/game-attack-assault-boxing-fight-43003.wav");
                } else {
                    vy = -vy;
                    remove(collider);
                    Sound.playSound("music/steklo-odinochnyiy-priglushennyiy.wav");
                    numberBricks--;
                }
            }
        }
        remove(ball);
        return numberBricks;
    }

    /**
     * Create a ball in the center of the screen
     */
    private GOval ball() {
        GOval ball = new GOval(WIDTH_GAME_BOARD / 2.0 - BALL_RADIUS, HEIGHT_GAME_BOARD / 2.0 - BALL_RADIUS,
                BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        ball.setColor(FILL_COLOR_BALL);
        add(ball);
        return ball;
    }

    /**
     * Create a method to check the corners of a circle for contact with other objects
     *
     * @param ballX x coordinate of the ball
     * @param ballY y coordinate of the ball
     * @return an object with the same coordinates of the ball
     */
    private GObject getCollidingObject(double ballX, double ballY) {
        if (getElementAt(ballX, ballY) != null) {
            return getElementAt(ballX, ballY);
        } else if (getElementAt(ballX + 2 * BALL_RADIUS, ballY) != null) {
            return getElementAt(ballX + 2 * BALL_RADIUS, ballY);
        } else if (getElementAt(ballX, ballY + 2 * BALL_RADIUS) != null) {
            return getElementAt(ballX, ballY + 2 * BALL_RADIUS);
        } else if (getElementAt(ballX + 2 * BALL_RADIUS, ballY + 2 * BALL_RADIUS) != null) {
            return getElementAt(ballX + 2 * BALL_RADIUS, ballY + 2 * BALL_RADIUS);
        }
        return null;
    }

    /**
     * Create a method that returns the ball's starting X direction.
     * The direction is chosen randomly using a generator.
     */
    private double vx() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        double vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) {
            vx = -vx;
        }
        return vx;
    }
}

