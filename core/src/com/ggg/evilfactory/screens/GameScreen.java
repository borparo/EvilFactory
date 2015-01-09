package com.ggg.evilfactory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.ggg.evilfactory.game.Application;
import com.ggg.evilfactory.game.WorldControl;
import com.ggg.evilfactory.game.WorldRender;
import com.ggg.evilfactory.utils.PlayerStats;

/**
 * Created by borja on 14-8-27.
 */
public class GameScreen extends AbstractScreen
{


    private WorldControl worldControl;
    private WorldRender worldRender;

    public GameScreen(final Application game)
    {
        super(game);
    }

    @Override
    public void update(float delta)
    {

    }

    @Override
    public void render(float delta)
    { // update camera
        game.camera.update();

        // set viewport
        Gdx.gl.glViewport(game.viewport.getScreenX(),  game.viewport.getScreenY(),
                game.viewport.getScreenWidth(), game.viewport.getScreenHeight());

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
    public void show()
    {
        worldControl = new WorldControl(game);
        worldRender = new WorldRender(worldControl, game);
        Gdx.input.setCatchBackKey(true);
        if(!game.gameMusicTrack.isPlaying() && PlayerStats.MUSIC_ON == true)
        {
            game.gameMusicTrack.play();
        }
    }

    @Override
    public void hide()
    {
        worldRender.dispose();
    }


    @Override
    public void dispose()
    {
        worldRender.dispose();
        Gdx.input.setCatchBackKey(false);
    }
}
