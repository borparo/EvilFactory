package com.ggg.evilfactory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ggg.evilfactory.game.Application;
import com.ggg.evilfactory.game.Assets;
import com.ggg.evilfactory.utils.Constants;

/**
 * Created by borja on 14-11-5.
 */
public class DifficultyScreen extends AbstractScreen

{
    private Sprite bg;
    private Sprite easy;
    private Sprite normal;
    private Sprite hard;

    public DifficultyScreen(final Application game)
    {
        super(game);
        initDifficultyScreen();
    }



    private void initDifficultyScreen()
    {
        bg = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "bgTitle.png", Texture.class));
        bg.setColor(Color.DARK_GRAY);
        easy = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_easy.png", Texture.class));
        easy.setPosition(Constants.VIEWPORT_WIDTH / 2 - easy.getWidth() / 2, 1100);
        //easy.setColor(Color.DARK_GRAY);
        normal = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_normal.png", Texture.class));
        normal.setPosition(Constants.VIEWPORT_WIDTH / 2 - normal.getWidth() / 2, 700);
        //normal.setColor(Color.DARK_GRAY);
        hard = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_hard.png", Texture.class));
        hard.setPosition(Constants.VIEWPORT_WIDTH / 2 - hard.getWidth() / 2, 300);
        //hard.setColor(Color.DARK_GRAY);
    }

    @Override
    public void update(float delta)
    {
        //ON TOUCH
        if (Gdx.input.justTouched())
        {
            game.getTouchPosition().set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCamera().unproject(game.getTouchPosition());

            //EASY
            if (game.getTouchPosition().x > easy.getX() &&
                    game.getTouchPosition().x < easy.getX() + easy.getWidth() &&
                    game.getTouchPosition().y > easy.getY() &&
                    game.getTouchPosition().y < easy.getY() + easy.getHeight())
            {
                game.setEasyStats();
                game.setScreen(game.title);
            }
            //NORMAL
            if (game.getTouchPosition().x > normal.getX() &&
                    game.getTouchPosition().x < normal.getX() + normal.getWidth() &&
                    game.getTouchPosition().y > normal.getY() &&
                    game.getTouchPosition().y < normal.getY() + normal.getHeight())
            {
                game.setNormalStats();
                game.setScreen(game.title);
            }
            //HARD
            if (game.getTouchPosition().x > hard.getX() &&
                    game.getTouchPosition().x < hard.getX() + hard.getWidth() &&
                    game.getTouchPosition().y > hard.getY() &&
                    game.getTouchPosition().y < hard.getY() + hard.getHeight())
            {
                game.setHardStats();
                game.setScreen(game.title);
            }
        }
    }

    @Override
    public void render(float delta)
    {
        game.getCamera().update();
        update(delta);

        Gdx.gl.glViewport(game.getViewport().getScreenX(), game.getViewport().getScreenY(),
                game.getViewport().getScreenWidth(), game.getViewport().getScreenHeight());
        Gdx.gl.glClearColor(0.55f, 0.55f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().setProjectionMatrix(game.getCamera().combined);

        game.getBatch().begin();

        bg.draw(game.getBatch());
        easy.draw(game.getBatch());
        normal.draw(game.getBatch());
        hard.draw(game.getBatch());

        game.getBatch().end();
    }
}
