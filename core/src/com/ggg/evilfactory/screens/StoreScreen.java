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
 * Created by borja on 14-11-3.
 */
public class StoreScreen extends AbstractScreen
{
    private Sprite back;
    private Sprite bg;

    public StoreScreen(final Application game)
    {
        super(game);
        back = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_left.png", Texture.class));
        bg = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "bgStore.png", Texture.class));

    }

    @Override
    public void update(float delta)
    {

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl20.glClearColor(0.55f, 0.55f, 0.85f, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getCamera().update();

        game.getBatch().setProjectionMatrix(game.getCamera().combined);

        game.getBatch().begin();

        bg.draw(game.getBatch());
        back.draw(game.getBatch());

        game.getBatch().end();

    }


}
