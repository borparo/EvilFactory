package com.ggg.evilfactory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ggg.evilfactory.game.Application;
import com.ggg.evilfactory.utils.Constants;

/**
 * Created by borja on 14-9-1.
 */
public class TutorialScreen implements Screen
{
    final Application game;
    Sprite bg;
    Sprite piece;
    Sprite blueprint;
    Sprite bomb;

    public TutorialScreen(final Application game)
    {
        this.game = game;
        initTutorial();
    }

    private void initTutorial()
    {
        bg = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "bgGame.png")));
    }

    @Override
    public void render(float delta)
    {

    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void show()
    {

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

    }
}
