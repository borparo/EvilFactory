package com.ggg.evilfactory.screens;

import com.badlogic.gdx.Screen;
import com.ggg.evilfactory.game.Application;

/**
 * Created by borja on 15-1-8.
 */
public abstract class AbstractScreen implements Screen
{
    final Application game;

    public AbstractScreen(Application game)
    {
       this.game = game;
    }


    public abstract void update( float delta);
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
