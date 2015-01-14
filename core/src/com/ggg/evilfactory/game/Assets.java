package com.ggg.evilfactory.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.ggg.evilfactory.utils.Constants;

/**
 * Created by borja on 14-11-3.
 */
public class Assets
{
    public static final AssetManager manager = new AssetManager();


    public static void load()
    {
        //PICTURES

        manager.load(Constants.ASSETS_PATH + "bgTitle.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bg.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bgStore.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bgGame.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bgHelp01.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bgHelp02.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bgOver.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bluePrint_gadget.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bluePrint_suit.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bluePrint_weapon.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bomb.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bombExplosion.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_help.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_left.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_pause.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_play.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_quit.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_easy.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_normal.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_hard.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_right.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_settings.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "coin.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "coinAnimation.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "g_Battery.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "g_Board.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "g_Box.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "g_Memory.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "gameFont.fnt", BitmapFont.class);
        manager.load(Constants.ASSETS_PATH + "gameFont.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "gameOverText.fnt", BitmapFont.class);
        manager.load(Constants.ASSETS_PATH + "gameOverText.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "gameOverTitle.fnt", BitmapFont.class);
        manager.load(Constants.ASSETS_PATH + "gameOverTitle.png", Texture.class);

        manager.load(Constants.ASSETS_PATH + "life.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "lifeNo.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "light.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "minion.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "minion_silly.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "s_Boots.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "s_Gloves.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "s_Helmet.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "s_Jacket.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "s_Pants.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "scoreFont.fnt", BitmapFont.class);
        manager.load(Constants.ASSETS_PATH + "scoreFont.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "w_Barrel.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "w_Grip.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "w_Hammer.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "w_Trigger.png", Texture.class);

        //FX
        manager.load(Constants.FX_PATH + "bombExplosion", ParticleEffect.class);
        manager.load(Constants.FX_PATH + "bombExplosion.png", Texture.class);
        manager.load(Constants.FX_PATH + "bombSave", ParticleEffect.class);
        manager.load(Constants.FX_PATH + "bombSave.png", Texture.class);
        manager.load(Constants.FX_PATH + "coinGotten", ParticleEffect.class);
        manager.load(Constants.FX_PATH + "coinGotten.png", Texture.class);


        //SOUNDS
        manager.load(Constants.SOUNDFX_PATH+ "coin.wav", Sound.class);
        manager.load(Constants.SOUNDFX_PATH + "explosion.wav", Sound.class);
        manager.load(Constants.SOUNDFX_PATH + "pickUpNo.wav", Sound.class);
        manager.load(Constants.SOUNDFX_PATH + "pickUpYes.wav", Sound.class);

        //MUSIC
        manager.load(Constants.MUSIC_PATH + "theBuilder.mp3", Music.class);
    }

    public static void dispose()
    {
        manager.dispose();
    }
}
