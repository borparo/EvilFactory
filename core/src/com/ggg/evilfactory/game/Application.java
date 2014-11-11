package com.ggg.evilfactory.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ggg.evilfactory.screens.*;
import com.ggg.evilfactory.utils.Constants;
import com.ggg.evilfactory.utils.GameStats;

public class Application extends Game
{
	public SpriteBatch batch;
	public OrthographicCamera camera;

    public BitmapFont gameFont;
    public BitmapFont scoreFont;
    public BitmapFont textFont;
    public BitmapFont titleFont;

    public Vector3 touchPosition;
    public Music gameMusicTrack;

    public TitleScreen title;
    public HelpScreen help;
    public SettingsScreen settings;
    public GameScreen game;
    public GameOverScreen gameOver;
    public TutorialScreen tutorial;
    public StoreScreen store;
    public DifficultyScreen difficulty;




    private boolean paused;
    public Viewport viewport;

    Sprite gear;




    @Override
	public void create () {

        Assets.load();

        Assets.manager.finishLoading();
        /*while(!Assets.manager.update())
        {
            System.out.println(Assets.manager.getProgress() * 100 + "%");
        }*/

		batch = new SpriteBatch();
		camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.update();

        viewport = new FitViewport(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, camera);

        touchPosition = new Vector3();

        title = new TitleScreen(this);
        help = new HelpScreen(this);
        settings = new SettingsScreen(this);
        game = new GameScreen(this);
        gameOver = new GameOverScreen(this);
        tutorial = new TutorialScreen(this);
        store = new StoreScreen(this);
        difficulty = new DifficultyScreen(this);

        paused = false;



        gameFont = Assets.manager.get(Constants.ASSETS_PATH + "gameFont.fnt", BitmapFont.class);
        scoreFont = Assets.manager.get(Constants.ASSETS_PATH + "scoreFont.fnt", BitmapFont.class);
        textFont = Assets.manager.get(Constants.ASSETS_PATH + "gameOverText.fnt", BitmapFont.class);
        titleFont = Assets.manager.get(Constants.ASSETS_PATH + "gameOverTitle.fnt", BitmapFont.class);

        gameMusicTrack = Assets.manager.get(Constants.MUSIC_PATH + "theBuilder.mp3", Music.class);
        gameMusicTrack.setLooping(true);

        if(Assets.manager.update())
        {
            setScreen(difficulty);

        }




	}

	@Override
	public void render () {

        super.render();

	}

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);

    }

    @Override
    public void pause()
    {
        paused = true;
    }

    @Override
    public void resume()
    {
        paused = false;
    }

    @Override
    public void dispose()
    {
        Assets.dispose();
        gameMusicTrack.dispose();

        batch.dispose();

        help.dispose();
        settings.dispose();
        game.dispose();
        gameOver.dispose();
        title.dispose();
        store.dispose();

        /*titleFont.dispose();
        textFont.dispose();
        gameFont.dispose();
        scoreFont.dispose();*/

    }

    public boolean isPaused()
    {
        return paused;
    }

    public void setPaused(boolean paused)
    {
        this.paused = paused;
    }

    public void setEasyStats()
    {
        GameStats.PIECE_SPEED_MIN = 150.0f;
        GameStats.PIECE_SPEED_MAX = 300.0f;
        GameStats.PIECE_SPEED_INCREMENTS = 50.0f;
        GameStats.PIECE_ADD_POINTS = 10;
        GameStats.PIECE_SUBSTRACT_POINTS = 5;
        GameStats.BOMB_SPEED_MIN = 250.0f;
        GameStats.BOMB_SPEED_MAX = 450.0f;
        GameStats.BOMB_SPEED_INCREMENTS = 75.0f;
        GameStats.BOMB_ADD_POINTS = 15;
        GameStats.BOMB_SUBSTRACT_POINTS = 10;
        GameStats.COIN_SPEED_MIN = 200.0f;
        GameStats.COIN_SPEED_MAX = 350.0f;
        GameStats.COIN_SPEED_INCREMENTS = 50.0f;
        GameStats.COIN_ADD_POINTS = 5;
        GameStats.COIN_SUBSTRACT_POINTS = 0;
        GameStats.DIFFICULTY_EASY = true;
        GameStats.DIFFICULTY_NORMAL = false;
        GameStats.DIFFICULTY_HARD = false;
    }

    public void setNormalStats()
    {
        GameStats.PIECE_SPEED_MIN = 300.0f;
        GameStats.PIECE_SPEED_MAX = 500.0f;
        GameStats.PIECE_SPEED_INCREMENTS = 100.0f;
        GameStats.PIECE_ADD_POINTS = 251;
        GameStats.PIECE_SUBSTRACT_POINTS = 15;
        GameStats.BOMB_SPEED_MIN = 300.0f;
        GameStats.BOMB_SPEED_MAX = 700.0f;
        GameStats.BOMB_SPEED_INCREMENTS = 100.0f;
        GameStats.BOMB_ADD_POINTS = 15;
        GameStats.BOMB_SUBSTRACT_POINTS = 20;
        GameStats.COIN_SPEED_MIN = 300.0f;
        GameStats.COIN_SPEED_MAX = 650.0f;
        GameStats.COIN_SPEED_INCREMENTS = 150.0f;
        GameStats.COIN_ADD_POINTS = 15;
        GameStats.COIN_SUBSTRACT_POINTS = 0;
        GameStats.DIFFICULTY_EASY = false;
        GameStats.DIFFICULTY_NORMAL = true;
        GameStats.DIFFICULTY_HARD = false;
    }

    public void setHardStats()
    {
        GameStats.PIECE_SPEED_MIN = 350.0f;
        GameStats.PIECE_SPEED_MAX = 600.0f;
        GameStats.PIECE_SPEED_INCREMENTS = 150.0f;
        GameStats.PIECE_ADD_POINTS = 20;
        GameStats.PIECE_SUBSTRACT_POINTS = 10;
        GameStats.BOMB_SPEED_MIN = 450.0f;
        GameStats.BOMB_SPEED_MAX = 900.0f;
        GameStats.BOMB_SPEED_INCREMENTS = 150.0f;
        GameStats.BOMB_ADD_POINTS = 25;
        GameStats.BOMB_SUBSTRACT_POINTS = 25;
        GameStats.COIN_SPEED_MIN = 550.0f;
        GameStats.COIN_SPEED_MAX = 850.0f;
        GameStats.COIN_SPEED_INCREMENTS = 100.0f;
        GameStats.COIN_ADD_POINTS = 30;
        GameStats.COIN_SUBSTRACT_POINTS = 0;
        GameStats.DIFFICULTY_EASY = false;
        GameStats.DIFFICULTY_NORMAL = false;
        GameStats.DIFFICULTY_HARD = true;
    }
}
