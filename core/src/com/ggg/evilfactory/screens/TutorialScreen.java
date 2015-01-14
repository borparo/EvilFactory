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
public class TutorialScreen extends AbstractScreen
{

    Sprite bg;
    Sprite piece;
    Sprite blueprint;
    Sprite bomb;

    boolean touched;

    public TutorialScreen(final Application game)
    {
        super(game);
        initTutorial();
    }



    private void initTutorial()
    {
        bg = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "bgGame.png")));
        piece = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "w_Barrel.png")));
        bomb = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "bomb.png")));
        blueprint = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "bluePrint_weapon.png")));
        touched = false;

        piece.setPosition(Constants.VIEWPORT_WIDTH,100);
    }

    @Override
    public void update(float delta)
    {
        //touch position

        //move piece from the right to the screen center
        if (piece.getX() > Constants.VIEWPORT_WIDTH - (piece.getWidth() / 2)  )
        {
            piece.setPosition((piece.getX() - 25) * delta, 100);
        }

        if(Gdx.input.isTouched())
        {
            game.getTouchPosition().set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCamera().unproject(game.getTouchPosition());
        }
    }

    @Override
    public void render(float delta)
    {
        // update camera
        game.getCamera().update();


        // set viewport
        Gdx.gl.glViewport( game.getViewport().getScreenX(),  game.getViewport().getScreenY(),
                game.getViewport().getScreenWidth(), game.getViewport().getScreenHeight());

        Gdx.gl.glClearColor(0.65f, 0.65f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getCamera().update();

        game.getBatch().setProjectionMatrix(game.getCamera().combined);

        game.getBatch().begin();

        bg.draw(game.getBatch());

        game.getGameFont().draw(game.getBatch(),"Welcome!", Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2, 1350);
        game.getGameFont().draw(game.getBatch(),"Help your minions", Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2, 1150);

        game.getBatch().end();
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
