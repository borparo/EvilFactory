package com.ggg.evilfactory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
public class SettingsScreen implements Screen
{
    final Application game;
    Sprite bg;
    Sprite back;
    Sprite easy;
    Sprite hard;
    Sprite normal;

    public SettingsScreen(final Application game)
    {
        this.game = game;
        initSettingsScreen();

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
        game.camera.update();


        // set viewport
        Gdx.gl.glViewport(game.viewport.getScreenX(), game.viewport.getScreenY(),
                game.viewport.getScreenWidth(), game.viewport.getScreenHeight());

        Gdx.gl.glClearColor(0.65f, 0.65f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //game.camera.update();

        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();


        bg.draw(game.batch);
        back.draw(game.batch);

        game.textFont.draw(game.batch, "Music:", Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2, 1200);
        game.textFont.draw(game.batch, "YES", Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET / 2, 1200);
        game.textFont.draw(game.batch, "NO", Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET * 2, 1200);

        game.textFont.draw(game.batch, "SoundFX:", Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2, 900);
        game.textFont.draw(game.batch, "YES", Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET / 2, 900);
        game.textFont.draw(game.batch, "NO", Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET * 2, 900);

        easy.draw(game.batch);
        normal.draw(game.batch);
        hard.draw(game.batch);

        game.batch.end();

        // SETTINGS BUTTONS
        if (Gdx.input.justTouched())
        {
            game.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(game.touchPosition);

            //BACK BUTTON
            if (game.touchPosition.x > back.getX() &&
                    game.touchPosition.x < back.getX() + back.getWidth() &&
                    game.touchPosition.y > back.getY() &&
                    game.touchPosition.y < (back.getY() + back.getHeight()))
            {
                game.setScreen(game.title);
            }

            //MUSIC YES BUTTON
            if (game.touchPosition.x > Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET / 2 &&
                    game.touchPosition.x < Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET + 100 &&
                    game.touchPosition.y > 1000 &&
                    game.touchPosition.y < 1200)
            {
                PlayerStats.MUSIC_ON = true;
                game.gameMusicTrack.play();
            }

            //MUSIC NO BUTTON
            if (game.touchPosition.x > Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET * 2 &&
                    game.touchPosition.x < Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET * 2 + 200 &&
                    game.touchPosition.y > 1000 &&
                    game.touchPosition.y < 1200)
            {
                game.gameMusicTrack.pause();
                PlayerStats.MUSIC_ON = false;
            }

            //FX YES BUTTON
            if (game.touchPosition.x > Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET / 2 &&
                    game.touchPosition.x < Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET / 2 + 200 &&
                    game.touchPosition.y > 700 &&
                    game.touchPosition.y < 900)
            {
                PlayerStats.SOUND_FX = true;
            }

            //FX NO BUTTON
            if (game.touchPosition.x > Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET * 2 &&
                    game.touchPosition.x < Constants.VIEWPORT_WIDTH / 2 + Constants.BUTTONS_OFFSET * 2 + 200 &&
                    game.touchPosition.y > 700 &&
                    game.touchPosition.y < 900)
            {
                PlayerStats.SOUND_FX = false;
            }

            //EASY BUTTON
            if (game.touchPosition.x > easy.getX() &&
                    game.touchPosition.x < easy.getX() + easy.getWidth() &&
                    game.touchPosition.y > easy.getY() &&
                    game.touchPosition.y < easy.getY() + easy.getHeight())
            {
                game.setEasyStats();
                setDifficultyLevelColor();
            }

            //NORMAL
            if (game.touchPosition.x > normal.getX() &&
                    game.touchPosition.x < normal.getX() + normal.getWidth() &&
                    game.touchPosition.y > normal.getY() &&
                    game.touchPosition.y < normal.getY() + normal.getHeight())
            {
                game.setNormalStats();
                setDifficultyLevelColor();
            }

            //HARD
            if (game.touchPosition.x > hard.getX() &&
                    game.touchPosition.x < hard.getX() + hard.getWidth() &&
                    game.touchPosition.y > hard.getY() &&
                    game.touchPosition.y < hard.getY() + hard.getHeight())
            {
                game.setHardStats();
                setDifficultyLevelColor();
            }
        }

    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void show()
    {
        setDifficultyLevelColor();
    }

    @Override
    public void hide()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {
        //bg.getTexture().dispose();


    }
}
