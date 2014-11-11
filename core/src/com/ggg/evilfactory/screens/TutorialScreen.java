package com.ggg.evilfactory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
        piece = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "w_Barrel.png")));
        bomb = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "bomb.png")));
        blueprint = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "bluePrint_weapon.png")));
    }

    @Override
    public void render(float delta)
    {
        // update camera
        game.camera.update();


        // set viewport
        Gdx.gl.glViewport( game.viewport.getScreenX(),  game.viewport.getScreenY(),
                game.viewport.getScreenWidth(), game.viewport.getScreenHeight());

        Gdx.gl.glClearColor(0.65f, 0.65f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();

        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();

        bg.draw(game.batch);

        game.gameFont.draw(game.batch,"Welcome!", Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2, 1350);
        game.gameFont.draw(game.batch,"Help your minions", Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2, 1150);

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
        bg.getTexture().dispose();
        bomb.getTexture().dispose();
        piece.getTexture().dispose();
        blueprint.getTexture().dispose();

    }
}