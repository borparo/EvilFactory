package com.ggg.evilfactory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.ggg.evilfactory.game.Application;
import com.ggg.evilfactory.game.WorldControl;
import com.ggg.evilfactory.game.WorldRender;

/**
 * Created by borja on 14-8-27.
 */
public class GameScreen implements Screen
{
    final Application game;

    private WorldControl worldControl;
    private WorldRender worldRender;

    public GameScreen(final Application game)
    {
        this.game = game;
    }

    @Override
    public void render(float delta)
    { // update camera
        game.camera.update();

        // set viewport
        Gdx.gl.glViewport((int) game.viewport.x, (int) game.viewport.y,
                (int) game.viewport.width, (int) game.viewport.height);

        //update the game if is not paused
        if(!game.isPaused())
        {
            worldControl.update(delta);
        }
        Gdx.gl.glClearColor(0.65f, 0.65f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();

        //render the game
        worldRender.render(delta);

    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void show()
    {
        worldControl = new WorldControl(game);
        worldRender = new WorldRender(worldControl, game);
        Gdx.input.setCatchBackKey(true);
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
        worldRender.dispose();
        Gdx.input.setCatchBackKey(false);
    }
}
