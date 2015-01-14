package com.ggg.evilfactory.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;
import com.ggg.evilfactory.objects.ParticlesManager;
import com.ggg.evilfactory.utils.Constants;

/**
 * Created by borja on 14-8-27.
 */


public class WorldRender implements Disposable
{
    private WorldControl worldControl;
    final Application game;

    private Sprite bg;
    private Sprite coin;


    public WorldRender(WorldControl worldControl, Application game)
    {
        this.worldControl = worldControl;
        this.game = game;
        initializeRender();
    }

    private void initializeRender()
    {
        bg = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "bgGame.png", Texture.class));
        coin = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "coin.png", Texture.class));
        coin.setPosition(Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2, 900);
    }

    public void render(float deltaTime)
    {

        game.getBatch().setProjectionMatrix(game.getCamera().combined);
        game.getBatch().begin();

        bg.draw(game.getBatch());
        coin.draw(game.getBatch());

        worldControl.minion1.render(deltaTime, game);
        worldControl.minion2.render(deltaTime, game);
        worldControl.sillyMinion.render(deltaTime, game);


        //render pieces
        for(AbstractPiece piece : worldControl.producedParts)
        {
            if (piece.isClicked(game))
            {
                System.out.println(piece.getAddPoints());
            }
                piece.draw(game.getBatch());
        }

        //render particles
        ParticlesManager.bombExplosion.draw(game.getBatch(), deltaTime);
        ParticlesManager.bombSave.draw(game.getBatch(), deltaTime);
        ParticlesManager.coinGotten.draw(game.getBatch(), deltaTime);

        //render lifes
        worldControl.life1.draw(game.getBatch());
        worldControl.life2.draw(game.getBatch());
        worldControl.life3.draw(game.getBatch());

        //render fonts
        game.getGameFont().draw(game.getBatch(),"Target: " + worldControl.getProductTarget(),
                Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2, 1390);
        game.getGameFont().draw(game.getBatch(), "Stock: " + worldControl.getProductStock(),
                Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET * 2, 1275);
        game.getGameFont().draw(game.getBatch(), "" + worldControl.getPlayerCoins(),
                Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET, 1000);
        game.getScoreFont().draw(game.getBatch(), "" + worldControl.getPlayerScore(),
                Constants.VIEWPORT_WIDTH  - Constants.BUTTONS_OFFSET * 3,
                Constants.VIEWPORT_HEIGHT);




        //show GameOVer text
        if(worldControl.getPlayerLifes() <=0)
        {
            game.setScreen(game.gameOver);
        }

        //render blueprint
        worldControl.blueprint.getBlueprintSprite().draw(game.getBatch());



        game.getBatch().end();
    }

    @Override
    public void dispose()
    {
       /* bg.getTexture().dispose();
        coin.getTexture().dispose();
        ParticlesManager.coinGotten.dispose();
        ParticlesManager.bombExplosion.dispose();
        ParticlesManager.bombSave.dispose();
        worldControl.minion1.dispose();
        worldControl.minion2.dispose();
        worldControl.sillyMinion.dispose();

        for(AbstractPiece piece : worldControl.producedParts)
        {
            piece.dispose();
        }*/

    }
}
