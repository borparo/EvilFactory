package com.ggg.evilfactory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ggg.evilfactory.game.Application;
import com.ggg.evilfactory.game.Assets;
import com.ggg.evilfactory.utils.Constants;
import com.ggg.evilfactory.utils.GameStats;
import com.ggg.evilfactory.utils.PlayerStats;

/**
 * Created by borja on 14-8-27.
 */
public class SettingsScreen extends AbstractScreen
{

    Sprite bg;
    Sprite back;
    Sprite easy;
    Sprite hard;
    Sprite normal;

    public SettingsScreen(final Application game)
    {
        super(game);
        initSettingsScreen();

    }

    @Override
    public void update(float delta)
    {
        // SETTINGS BUTTONS
        if (Gdx.input.justTouched())
        {
            game.getTouchPosition().set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCamera().unproject(game.getTouchPosition());

            //BACK BUTTON
            if (game.getTouchPosition().x > back.getX() &&
                    game.getTouchPosition().x < back.getX() + back.getWidth() &&
                    game.getTouchPosition().y > back.getY() &&
                    game.getTouchPosition().y < (back.getY() + back.getHeight()))
            {
                game.setScreen(game.title);
            }

            //MUSIC YES BUTTON
            if (game.getTouchPosition().x > Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET / 2 &&
                    game.getTouchPosition().x < Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET + 100 &&
                    game.getTouchPosition().y > 1000 &&
                    game.getTouchPosition().y < 1200)
            {
                PlayerStats.MUSIC_ON = true;
                game.gameMusicTrack.play();
            }

            //MUSIC NO BUTTON
            if (game.getTouchPosition().x > Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET * 2 &&
                    game.getTouchPosition().x < Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET * 2 + 200 &&
                    game.getTouchPosition().y > 1000 &&
                    game.getTouchPosition().y < 1200)
            {
                game.gameMusicTrack.pause();
                PlayerStats.MUSIC_ON = false;
            }

            //FX YES BUTTON
            if (game.getTouchPosition().x > Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET / 2 &&
                    game.getTouchPosition().x < Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET / 2 + 200 &&
                    game.getTouchPosition().y > 700 &&
                    game.getTouchPosition().y < 900)
            {
                PlayerStats.SOUND_FX = true;
            }

            //FX NO BUTTON
            if (game.getTouchPosition().x > Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET * 2 &&
                    game.getTouchPosition().x < Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET * 2 + 200 &&
                    game.getTouchPosition().y > 700 &&
                    game.getTouchPosition().y < 900)
            {
                PlayerStats.SOUND_FX = false;
            }

            //EASY BUTTON
            if (game.getTouchPosition().x > easy.getX() &&
                    game.getTouchPosition().x < easy.getX() + easy.getWidth() &&
                    game.getTouchPosition().y > easy.getY() &&
                    game.getTouchPosition().y < easy.getY() + easy.getHeight())
            {
                game.setEasyStats();
                setDifficultyLevelColor();
            }

            //NORMAL
            if (game.getTouchPosition().x > normal.getX() &&
                    game.getTouchPosition().x < normal.getX() + normal.getWidth() &&
                    game.getTouchPosition().y > normal.getY() &&
                    game.getTouchPosition().y < normal.getY() + normal.getHeight())
            {
                game.setNormalStats();
                setDifficultyLevelColor();
            }

            //HARD
            if (game.getTouchPosition().x > hard.getX() &&
                    game.getTouchPosition().x < hard.getX() + hard.getWidth() &&
                    game.getTouchPosition().y > hard.getY() &&
                    game.getTouchPosition().y < hard.getY() + hard.getHeight())
            {
                game.setHardStats();
                setDifficultyLevelColor();
            }
        }
    }

    private void initSettingsScreen()
    {
        bg = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "bgGame.png", Texture.class));
        back = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_left.png", Texture.class));
        back.setPosition(30, 30);
        easy = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_easy.png", Texture.class));
        //easy.setScale(0.5f);
        easy.setPosition(300, 300);
        normal = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_normal.png", Texture.class));
        //normal.setScale(0.5f);
        normal.setPosition(Constants.VIEWPORT_WIDTH / 2 - normal.getWidth() / 2, 300);
        hard = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_hard.png", Texture.class));
        // hard.setScale(0.5f);
        hard.setPosition(1750, 300);
        setDifficultyLevelColor();
    }

    /**
     * Changes the color of teh difficulty level buttons
     */
    private void setDifficultyLevelColor()
    {
        if (GameStats.DIFFICULTY_EASY == true)
        {
            easy.setColor(Color.WHITE);
            normal.setColor(Color.DARK_GRAY);
            hard.setColor(Color.DARK_GRAY);
        }

        else if (GameStats.DIFFICULTY_NORMAL == true)
        {
            easy.setColor(Color.DARK_GRAY);
            normal.setColor(Color.WHITE);
            hard.setColor(Color.DARK_GRAY);
        }
        else if (GameStats.DIFFICULTY_HARD == true)
        {
            easy.setColor(Color.DARK_GRAY);
            normal.setColor(Color.DARK_GRAY);
            hard.setColor(Color.WHITE);
        }
    }

    @Override
    public void render(float delta)
    {
        // update camera
        game.getCamera().update();

        update(delta);


        // set viewport
        Gdx.gl.glViewport(game.getViewport().getScreenX(), game.getViewport().getScreenY(),
                game.getViewport().getScreenWidth(), game.getViewport().getScreenHeight());

        Gdx.gl.glClearColor(0.65f, 0.65f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //game.camera.update();

        game.getBatch().setProjectionMatrix(game.getCamera().combined);

        game.getBatch().begin();


        bg.draw(game.getBatch());
        back.draw(game.getBatch());

        game.textFont.draw(game.getBatch(), "Music:", Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2, 1200);
        game.textFont.draw(game.getBatch(), "YES", Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET / 2, 1200);
        game.textFont.draw(game.getBatch(), "NO", Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET * 2, 1200);

        game.textFont.draw(game.getBatch(), "SoundFX:", Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2, 900);
        game.textFont.draw(game.getBatch(), "YES", Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET / 2, 900);
        game.textFont.draw(game.getBatch(), "NO", Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET * 2, 900);

        easy.draw(game.getBatch());
        normal.draw(game.getBatch());
        hard.draw(game.getBatch());

        game.getBatch().end();
    }

    @Override
    public void show()
    {
        setDifficultyLevelColor();
    }

}
