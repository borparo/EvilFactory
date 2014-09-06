package com.ggg.evilfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.ggg.evilfactory.game.AbstractPiece;
import com.ggg.evilfactory.utils.Constants;

/**
 * Created by borja on 14-8-29.
 */
public class Bomb extends AbstractPiece
{


    public Bomb()
    {
        display();
        sprite.setPosition(getPositionX(), getPositionY());
        setSpeed(MathUtils.random(250, 400));
        setAddPoints(10);
        setTakePoints(25);
    }

    @Override
    public void display()
    {
        sprite = new Sprite (new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "bomb.png")));
        setName("bomb");
    }

}
