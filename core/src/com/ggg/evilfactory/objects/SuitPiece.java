package com.ggg.evilfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.ggg.evilfactory.game.AbstractPiece;
import com.ggg.evilfactory.utils.Constants;

/**
 * Created by borja on 14-8-29.
 */
public class SuitPiece extends AbstractPiece
{
    public SuitPiece()
    {
        display();
        sprite.setPosition(getPositionX(),getPositionY());
        setSpeed(250);
        setAddPoints(25);
        setTakePoints(15);
    }
    @Override
    public void display()
    {
        switch(MathUtils.random(0,4))
        {
            case 0:
                sprite = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "s_Helmet.png")));
                setName("helmet");
                break;
            case 1:
                sprite = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "s_Jacket.png")));
                setName("jacket");
                break;
            case 2:
                sprite = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "s_Gloves.png")));
                setName("gloves");
                break;
            case 3:
                sprite = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "s_Pants.png")));
                setName("pants");
                break;
            case 4:
                sprite = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "s_Boots.png")));
                setName("boots");
                break;
        }
    }
}
