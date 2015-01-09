package com.ggg.evilfactory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ggg.evilfactory.game.Application;
import com.ggg.evilfactory.game.Assets;
import com.ggg.evilfactory.utils.Constants;

/**
 * Created by borja on 14-8-27.
 */
public class HelpScreen extends AbstractScreen
{
    Sprite helpbg;
    Texture page1;
    Texture page2;
    Sprite back;
    Sprite next;

    public HelpScreen(final Application game)
    {
        super(game);
        page1 = Assets.manager.get(Constants.ASSETS_PATH + "bgHelp01.png");
        page2 = Assets.manager.get(Constants.ASSETS_PATH + "bgHelp02.png");
        helpbg = new Sprite(page1);
        back = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_left.png", Texture.class));
        back.setPosition(30, 30);
        next = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_right.png", Texture.class));
        next.setPosition(Constants.VIEWPORT_WIDTH - (next.getWidth()), 1300);

    }

    @Override
    public void update(float delta)
    {
        // NEXT BUTTON

        if (Gdx.input.justTouched())
        {
            game.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(game.touchPosition);

            if (game.touchPosition.x > next.getX() &&
                    game.touchPosition.x < next.getX() + next.getWidth() &&
                    game.touchPosition.y > next.getY() &&
                    game.touchPosition.y < (next.getY() + next.getHeight()))
            {
                helpbg.setTexture(page2);
                next.setAlpha(0);

            }
        }

        // BACK BUTTON

        if (Gdx.input.justTouched())
        {
            game.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(game.touchPosition);

            if (game.touchPosition.x > back.getX() &&
                    game.touchPosition.x < back.getX() + back.getWidth() &&
                    game.touchPosition.y > back.getY() &&
                    game.touchPosition.y < (back.getY() + back.getHeight()))
            {
                if (helpbg.getTexture() == page1)
                {
                    game.setScreen(game.title);
                }
                else if (helpbg.getTexture() == page2)
                {
                    helpbg.setTexture(page1);
                    next.setAlpha(1);
                }


            }
        }
    }

    @Override
    public void render(float delta)
    {
        // update camera
        game.camera.update();

        update(delta);


        // set viewport
        Gdx.gl.glViewport(game.viewport.getScreenX(), game.viewport.getScreenY(),
                game.viewport.getScreenWidth(), game.viewport.getScreenHeight());

        Gdx.gl.glClearColor(0.65f, 0.65f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();

        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();

        helpbg.draw(game.batch);
        back.draw(game.batch);
        next.draw(game.batch);


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
