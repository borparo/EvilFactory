package com.ggg.evilfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.ggg.evilfactory.game.AbstractPiece;
import com.ggg.evilfactory.game.Assets;
import com.ggg.evilfactory.utils.Constants;
import com.ggg.evilfactory.utils.GameStats;

/**
 * Created by borja on 14-8-29.
 */
public class Bomb extends AbstractPiece
{


    public Bomb()
    {
        display();
        sprite.setPosition(getPositionX(), getPositionY());
        setSpeed(MathUtils.random(GameStats.BOMB_SPEED_MIN, GameStats.BOMB_SPEED_MAX));
        setAddPoints(GameStats.BOMB_ADD_POINTS);
        setTakePoints(GameStats.BOMB_SUBSTRACT_POINTS);
    }

    @Override
    public void display()
    {
        sprite = new Sprite (Assets.manager.get(Constants.ASSETS_PATH + "bomb.png", Texture.class));
        setName("bomb");

    }

}
