package com.ggg.evilfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.ggg.evilfactory.game.AbstractPiece;
import com.ggg.evilfactory.game.Assets;
import com.ggg.evilfactory.utils.Constants;
import com.ggg.evilfactory.utils.GameStats;

/**
 * Created by borja on 14-8-28.
 */
public class WeaponPiece extends AbstractPiece
{
    public WeaponPiece()
    {
        display();
        sprite.setPosition(getPositionX(),getPositionY());
        setSpeed(MathUtils.random(GameStats.PIECE_SPEED_MIN,GameStats.PIECE_SPEED_MAX));
        setAddPoints(GameStats.PIECE_ADD_POINTS);
        setTakePoints(GameStats.PIECE_SUBSTRACT_POINTS);
    }
    @Override
    public void display()
    {
       switch(MathUtils.random(0,3))
       {
           case 0:
               sprite = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "w_Grip.png", Texture.class));
               setName("grip");
               break;
           case 1:
               sprite = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "w_Hammer.png", Texture.class));
               setName("hammer");
               break;
           case 2:
               sprite = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "w_Trigger.png", Texture.class));
               setName("trigger");
               break;
           case 3:
               sprite = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "w_Barrel.png", Texture.class));
               setName("barrel");
               break;
       }
    }








}
