package com.ggg.evilfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.ggg.evilfactory.game.AbstractPiece;
import com.ggg.evilfactory.utils.Constants;

/**
 * Created by borja on 14-8-28.
 */
public class GadgetPiece extends AbstractPiece
{
    public GadgetPiece()
    {
        display();
        sprite.setPosition(getPositionX(), getPositionY());
        setSpeed(250);
        setAddPoints(25);
        setTakePoints(15);
    }

    @Override
    public void display()
    {
        switch(MathUtils.random(0,3))
        {
            case 0:
                sprite = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "g_Box.png")));
                setName("box");
                break;
            case 1:
                sprite = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "g_Memory.png")));
                setName("memory");
                break;
            case 2:
                sprite = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "g_Board.png")));
                setName("board");
                break;
            case 3:
                sprite = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "g_Battery.png")));
                setName("battery");
                break;
        }
    }






}
