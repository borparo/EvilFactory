package com.ggg.evilfactory.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;
import com.ggg.evilfactory.objects.Coin;
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
        bg = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "bgGame.png")));
        coin = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "coin.png")));
        coin.setPosition(256, 352);
    }

    public void render(float deltaTime)
    {

        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();

        bg.draw(game.batch);
        coin.draw(game.batch);

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


                piece.draw(game.batch);

        }

        //render particles
        ParticlesManager.bombExplosion.draw(game.batch, deltaTime);
        ParticlesManager.bombSave.draw(game.batch, deltaTime);
        ParticlesManager.coinGotten.draw(game.batch, deltaTime);

        //render lifes
        worldControl.life1.draw(game.batch);
        worldControl.life2.draw(game.batch);
        worldControl.life3.draw(game.batch);

        //render fonts
        game.gameFont.draw(game.batch,"Target: " + worldControl.getProductTarget(), 255, 480);
        game.gameFont.draw(game.batch, "Stock: " + worldControl.getProductStock(), 255, 450);
        game.gameFont.draw(game.batch, "" + worldControl.getPlayerCoins(), 300,380);
        game.scoreFont.draw(game.batch, "" + worldControl.getPlayerScore(), 650, 480);




        //show GameOVer text
        if(worldControl.getPlayerLifes() <=0)
        {
            game.textFont.draw(game.batch,"GAME OVER", 350, 400);
            game.textFont.draw(game.batch,"Do you want to play again?", 100, 300);
        }

        //render blueprint
        worldControl.blueprint.getBlueprintSprite().draw(game.batch);



        game.batch.end();
    }

    @Override
    public void dispose()
    {
        bg.getTexture().dispose();
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
        }

    }
}
