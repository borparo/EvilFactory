package com.ggg.evilfactory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.ggg.evilfactory.game.Application;
import com.ggg.evilfactory.game.Assets;
import com.ggg.evilfactory.objects.Minion;
import com.ggg.evilfactory.utils.Constants;
import com.ggg.evilfactory.utils.PlayerStats;

/**
 * Created by borja on 14-8-27.
 */
public class TitleScreen extends AbstractScreen
{

    private Texture bgTexture;
    private Texture playTexture;
    private Texture quitTexture;
    private Texture helpTexture;
    private Texture settingsTexture;

    private Sprite bg;

    private Sprite play;
    private Sprite quit;
    private Sprite help;
    private Sprite settings;
    Minion minion;
    Minion sillyMinion;



    public TitleScreen(final Application game)
    {
        super(game);

        bg = new Sprite(Assets.manager.get(Constants.ASSETS_PATH +"bgTitle.png", Texture.class ));
        //buttons
        play = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_play.png", Texture.class));
        quit = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_quit.png", Texture.class));
        help = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_help.png", Texture.class));
        help.setPosition(250,150);
        settings = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_settings.png", Texture.class));
        settings.setPosition(Constants.VIEWPORT_WIDTH - (settings.getWidth() + 150), 100);
        settings.setScale(0.75f);

        minion = new Minion();
        minion.setPosition(new Vector2(100, Constants.VIEWPORT_HEIGHT / 2 - 50));
        sillyMinion = new Minion();
        sillyMinion.setPosition(new Vector2(2300, Constants.VIEWPORT_HEIGHT / 2 - 55));
        sillyMinion.setAnimationSprite(new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "minion_silly.png", Texture.class)));
        sillyMinion.createFrames();
    }

    @Override
    public void update (float delta)
    {
        //PLAY BUTTON
        if(Gdx.input.justTouched())
        {
            game.getTouchPosition().set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCamera().unproject(game.getTouchPosition());

            if(game.getTouchPosition().x > play.getX() &&
                    game.getTouchPosition().x < play.getX() + play.getWidth() &&
                    game.getTouchPosition().y > play.getY()  &&

                    game.getTouchPosition().y < (play.getY() + play.getHeight()))
            {
                //SET PLAY SCREEN
                if (!PlayerStats.TUTORIAL_COMPLETE)
                {
                    game.setScreen(game.tutorial);
                }
                else
                {
                    game.setScreen(game.game);
                }

            }
        }

        //QUIT BUTTON

        if(Gdx.input.justTouched())
        {
            game.getTouchPosition().set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCamera().unproject(game.getTouchPosition());

            if(game.getTouchPosition().x > quit.getX() &&
                    game.getTouchPosition().x < quit.getX() + quit.getWidth() &&
                    game.getTouchPosition().y > quit.getY() &&
                    game.getTouchPosition().y < (quit.getY() + quit.getHeight()))
            {
                Gdx.app.exit();
            }
        }

        //SETTINGS BUTTON

        if(Gdx.input.justTouched())
        {
            game.getTouchPosition().set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCamera().unproject(game.getTouchPosition());

            if( game.getTouchPosition().x > settings.getX() &&
                    game.getTouchPosition().x < settings.getX() + settings.getWidth() &&
                    game.getTouchPosition().y > settings.getY() &&
                    game.getTouchPosition().y < (settings.getY() + settings.getHeight()))
            {
                game.setScreen(game.settings);
            }
        }

        //HELP BUTTON

        if(Gdx.input.justTouched())
        {
            game.getTouchPosition().set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCamera().unproject(game.getTouchPosition());

            if( game.getTouchPosition().x > help.getX() &&
                    game.getTouchPosition().x < help.getX() + help.getWidth() &&
                    game.getTouchPosition().y > help.getY() &&
                    game.getTouchPosition().y < (help.getY() + help.getHeight()))
            {
                game.setScreen(game.help);
            }
        }
    }

    @Override
    public void render(float delta)
    {
        // update camera and method
       game.getCamera().update();
        update(delta);

        // set viewport
        Gdx.gl.glViewport( game.getViewport().getScreenX(),  game.getViewport().getScreenY(),
                game.getViewport().getScreenWidth(), game.getViewport().getScreenHeight());

        Gdx.gl.glClearColor(0.65f, 0.65f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //game.camera.update();

        game.getBatch().setProjectionMatrix(game.getCamera().combined);

        game.getBatch().begin();

        bg.draw(game.getBatch());
        play.draw(game.getBatch());
        quit.draw(game.getBatch());
        help.draw(game.getBatch());
        settings.draw(game.getBatch());

        minion.render(delta, game);
        sillyMinion.render(delta,game);

        game.getBatch().end();



    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void show()
    {
        if(PlayerStats.MUSIC_ON)
        {
            game.gameMusicTrack.play();
        }

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
        /*bg.getTexture().dispose();
        play.getTexture().dispose();
        quit.getTexture().dispose();
        help.getTexture().dispose();
        settings.getTexture().dispose();
        sillyMinion.dispose();
        minion.dispose();*/

    }
}
