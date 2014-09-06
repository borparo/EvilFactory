package com.ggg.evilfactory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.ggg.evilfactory.game.Application;
import com.ggg.evilfactory.objects.Minion;
import com.ggg.evilfactory.utils.Constants;

/**
 * Created by borja on 14-8-27.
 */
public class TitleScreen implements Screen
{
    final Application game;
    private Sprite bg;
    private Sprite play;
    private Sprite quit;
    private Sprite help;
    Minion minion;
    Minion sillyMinion;

    public TitleScreen(final Application game)
    {
        this.game = game;
        bg = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH +"bgTitle.png" )));
        play = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "btn_play.png")));
        quit = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "btn_quit.png")));
        minion = new Minion();
        minion.setPosition(new Vector2(100, 200));
        sillyMinion = new Minion();
        sillyMinion.setPosition(new Vector2(700, 200));
        sillyMinion.setAnimationSprite(new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "minion_silly.png"))));
        sillyMinion.createFrames();
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

        bg.draw(game.batch);
        play.draw(game.batch);
        quit.draw(game.batch);

        minion.render(delta, game);
        sillyMinion.render(delta,game);

        game.batch.end();

        //PLAY BUTTON
        if(Gdx.input.justTouched())
        {
            game.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(game.touchPosition);

            if(Gdx.input.getX() > play.getX() &&
                    Gdx.input.getX() < play.getX() + play.getWidth() &&
                    Gdx.input.getY() < Constants.VIEWPORT_HEIGHT - play.getY() &&
                    Gdx.input.getY() > Constants.VIEWPORT_HEIGHT -(play.getY() + play.getHeight()))
            {
                game.setScreen(game.game);
            }
        }

        //QUIT BUTTON

        if(Gdx.input.justTouched())
        {
            game.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(game.touchPosition);

            if(Gdx.input.getX() > quit.getX() &&
                    Gdx.input.getX() < quit.getX() + quit.getWidth() &&
                    Gdx.input.getY() < Constants.VIEWPORT_HEIGHT - quit.getHeight() &&
                    Gdx.input.getY() > Constants.VIEWPORT_HEIGHT -(quit.getY() + quit.getHeight()))
            {
                Gdx.app.exit();
            }
        }

    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void show()
    {
        game.gameMusicTrack.play();

        play.setPosition(Constants.VIEWPORT_WIDTH / 2 - play.getWidth() / 2,
                Constants.VIEWPORT_HEIGHT / 2 - play.getHeight() / 2);
        quit.setPosition(Constants.VIEWPORT_WIDTH / 2 - quit.getWidth() / 2,
                Constants.VIEWPORT_HEIGHT / 2 - (quit.getHeight() / 2 + Constants.BUTTONS_OFFSET));
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
        play.getTexture().dispose();
        quit.getTexture().dispose();
        minion.dispose();

    }
}
