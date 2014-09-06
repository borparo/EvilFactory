package com.ggg.evilfactory.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ggg.evilfactory.screens.GameScreen;
import com.ggg.evilfactory.screens.HelpScreen;
import com.ggg.evilfactory.screens.SettingsScreen;
import com.ggg.evilfactory.utils.Constants;
import com.ggg.evilfactory.screens.TitleScreen;

public class Application extends Game
{
	public SpriteBatch batch;
	public OrthographicCamera camera;
    public Rectangle viewport;
    public BitmapFont gameFont;
    public BitmapFont scoreFont;
    public BitmapFont textFont;
    public Vector3 touchPosition;
    public Music gameMusicTrack;

    public TitleScreen title;
    public HelpScreen help;
    public SettingsScreen settings;
    public GameScreen game;

    private boolean paused;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        touchPosition = new Vector3();

        title = new TitleScreen(this);
        help = new HelpScreen(this);
        settings = new SettingsScreen(this);
        game = new GameScreen(this);

        paused = false;

        gameFont = new BitmapFont(Gdx.files.internal(Constants.ASSETS_PATH + "gameFont.fnt"));
        scoreFont = new BitmapFont(Gdx.files.internal(Constants.ASSETS_PATH + "scoreFont.fnt"));
        textFont = new BitmapFont(Gdx.files.internal(Constants.ASSETS_PATH + "scoreFont.fnt"));


        gameMusicTrack = Gdx.audio.newMusic(Gdx.files.internal(Constants.MUSIC_PATH + "theBuilder.mp3"));
        gameMusicTrack.setLooping(true);

        setScreen(title);

	}

	@Override
	public void render () {
		super.render();
	}

    @Override
    public void resize(int width, int height)
    {
        //calculate new viewport
        float aspectRatio = (float)width / (float)height;
        float scale = 1f;
        Vector2 crop = new Vector2 (0f, 0f);

        if(aspectRatio > Constants.ASPECT_RATIO )
        {
            scale = (float)height /Constants.VIEWPORT_HEIGHT;
            crop.x = (width - Constants.VIEWPORT_WIDTH * scale) / 2;
        }
        else if(aspectRatio < Constants.ASPECT_RATIO)
        {
            scale = (float)width / Constants.VIEWPORT_WIDTH;
            crop.y = (height - Constants.VIEWPORT_HEIGHT * scale) / 2;

        }
        else
        {
            scale = (float)width / Constants.VIEWPORT_WIDTH;
        }

        float w = Constants.VIEWPORT_WIDTH * scale;
        float h = Constants.VIEWPORT_HEIGHT * scale;

        viewport = new Rectangle(crop.x, crop.y, w, h);

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
        gameMusicTrack.dispose();
        batch.dispose();
    }

    public boolean isPaused()
    {
        return paused;
    }

    public void setPaused(boolean paused)
    {
        this.paused = paused;
    }
}
