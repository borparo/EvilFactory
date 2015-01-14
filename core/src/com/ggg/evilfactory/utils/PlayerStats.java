package com.ggg.evilfactory.utils;

/**
 * Created by borja on 14-8-30.
 */
public class PlayerStats
{
    public static long PLAYER_SAVED_MONEY = 0;
    public static long TOTAL_STORE =  0;
    public static long CURRENT_SCORE = 0;
    public static int PRODUCT_STOCK_TARGET = 0;
    public static int PRODUCT_STOCK_CURRENT = 0;

    public static boolean MUSIC_ON = true;
    public static boolean SOUND_FX = true;
    public static boolean LEVEL_COMPLETE = false;
    public static boolean TUTORIAL_COMPLETE = true;

    public static boolean SAMPLE_WEAPON = true; // assembled with tutorial
    public static boolean SAMPLE_GADGET = false;//Unlocked when TUTORIAL_COMPLETE is true
    public static boolean SAMPLE_SUIT = false; //Unlocked when TUTORIAL_COMPLETE is true
    public static boolean FIRST_PRODUCT = false;//unlocked when first level is complete.
    public static boolean SECOND_PRODUCT = false; //unlocked when FIRST_PRODUCT us true.
    public static boolean THIRD_PRODUCT = false;

    public static boolean FIRST_MODEL = false; //first model ever created in any level
    public static boolean BOMB_CATCHER = false; // deactivate 10 bombs
    public static boolean KING_MIDAS = false; // finish level with 1000 or more.
    public static boolean TOTAL_DISASTER = false; //finish level with negative points
    public static boolean PERSISTANT = false; // create 7 or more stock models in one game.
    public static boolean PEE_PAUSE = false; //hold the game in pause for 5 min.
    public static boolean MUM_CALLED = false; // game paused because an incoming call
    public static boolean WELCOME = false; //start the game for first time.
    public static boolean MONEY_MONEY_MONEY = false; // finish the level with 500 coins or more
    public static boolean THE_DISCOVER = false; // discover new materials
    public static boolean THE_INVENTOR = false; // assemble new creations.


}
