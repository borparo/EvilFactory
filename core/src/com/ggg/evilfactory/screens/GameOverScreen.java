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

/**
 * Created by borja on 14-9-11.
 */
public class GameOverScreen extends AbstractScreen
{
    Sprite bg;

    public GameOverScreen(final Application game)
    {
        super(game);
        initGameOver();
    }



    private void initGameOver()
    {
       bg = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "bgGame.png", Texture.class));
       bg.setColor(Color.DARK_GRAY);
    }

    @Override
    public void update(float delta)
    {
        //YES BUTTON
        if(Gdx.input.justTouched())
        {
            game.getTouchPosition().set(Gdx.input.getX(), Gdx.input.getY(),0);
            game.getCamera().unproject(game.getTouchPosition());

            if (game.getTouchPosition().x> - Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2 &&
                    game.getTouchPosition().x < Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2 + 200  &&
                    game.getTouchPosition().y > 550 &&
                    game.getTouchPosition().y < 750 )

            {
                game.setScreen(game.game);
            }
        }

        //NO BUTTON
        if(Gdx.input.justTouched())
        {
            game.getTouchPosition().set(Gdx.input.getX(), Gdx.input.getY(),0);
            game.getCamera().unproject(game.getTouchPosition());

            if (game.getTouchPosition().x > Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET / 2 &&
                    game.getTouchPosition().x < Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET / 2 + 200 &&
                    game.getTouchPosition().y > 550 &&
                    game.getTouchPosition().y < 750 )

            {
                game.setScreen(game.title);
            }
        }
    }

    @Override
    public void render(float delta)
    {
        // update getCamera()
        game.getCamera().update();

        update(delta);

        // set viewport
        Gdx.gl.glViewport( game.getViewport().getScreenX(),  game.getViewport().getScreenY(),
                game.getViewport().getScreenWidth(), game.getViewport().getScreenHeight());

        Gdx.gl.glClearColor(0.65f, 0.65f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        game.getBatch().setProjectionMatrix(game.getCamera().combined);

        game.getBatch().begin();

        bg.draw(game.getBatch());

        game.getTitleFont().draw(game.getBatch(),"GAME OVER", Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2, 1350);
        game.getTextFont().draw(game.getBatch(),"Do you want", Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET, 1150);
        game.getTextFont().draw(game.getBatch(),"to play Again?", Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET, 950);
        game.getTextFont().draw(game.getBatch(), "YES", Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2, 750);
        game.getTextFont().draw(game.getBatch(), "NO", Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET / 2, 750);

        game.getBatch().end();



    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void show()
    {
        game.getGameMusicTrack().pause();
    }

    @Override
    public void hide()
    {
       bg.setColor(Color.WHITE);
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
