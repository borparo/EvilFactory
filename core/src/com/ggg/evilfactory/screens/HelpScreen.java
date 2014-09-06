package com.ggg.evilfactory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.ggg.evilfactory.game.Application;

/**
 * Created by borja on 14-8-27.
 */
public class HelpScreen implements Screen
{
    final Application game;

    public HelpScreen(final Application game)
    {
        this.game = game;
    }
    @Override
    public void render(float delta)
    {
        // update camera
        game.camera.update();


        // set viewport
        Gdx.gl.glViewport((int) game.viewport.x, (int) game.viewport.y,
                (int) game.viewport.width, (int) game.viewport.height);

        Gdx.gl.glClearColor(0.65f, 0.65f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();

        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();

        game.batch.end();
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
