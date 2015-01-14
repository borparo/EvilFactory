package com.ggg.evilfactory.utils;

/**
 * Created by borja on 14-11-5.
 * This class keeps track of the different fields values the game
 * requires for the game play, depending on the difficulty level
 * chosen by the player, the fields for the pieces, coins and bombs
 * will vary. The store fields will be affected for any external
 * event, like sales, promotions, etc...
 */

public class GameStats
{
    public static float PIECE_SPEED_MIN = 300f;
    public static float PIECE_SPEED_MAX = 500f;
    public static float PIECE_SPEED_INCREMENTS = 100f;
    public static int PIECE_ADD_POINTS = 25;
    public static int PIECE_SUBSTRACT_POINTS =15;
    public static float BOMB_SPEED_MIN = 400f;
    public static float BOMB_SPEED_MAX = 800f;
    public static float BOMB_SPEED_INCREMENTS = 100f;
    public static int BOMB_ADD_POINTS = 10;
    public static int BOMB_SUBSTRACT_POINTS = 25;
    public static float COIN_SPEED_MIN = 300f;
    public static float COIN_SPEED_MAX = 700f;
    public static float COIN_SPEED_INCREMENTS = 150f;
    public static int COIN_ADD_POINTS = 15;
    public static int COIN_SUBSTRACT_POINTS = 0;
    public static int FACTORY_UPDATE_1 = 250;
    public static int FACTORY_UPDATE_2 = 500;
    public static int FACTORY_UPDATE_3 = 1000;
    public static int FACTORY_UPDATE_4 = 5000;
    public static int FACTORY_UPDATE_5 = 10000;
    public static int FACTORY_UPDATE_6 = 15000;
    public static boolean DIFFICULTY_EASY = false;
    public static boolean DIFFICULTY_NORMAL = false;
    public static boolean DIFFICULTY_HARD = false;
}
