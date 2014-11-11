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
 * Created by borja on 14-8-29.
 */
public class SuitPiece extends AbstractPiece
{
    public SuitPiece()
    {
        display();
        sprite.setPosition(getPositionX(),getPositionY());
        setSpeed(MathUtils.random(GameStats.PIECE_SPEED_MIN, GameStats.PIECE_SPEED_MAX));
        setAddPoints(GameStats.PIECE_ADD_POINTS);
        setTakePoints(GameStats.PIECE_SUBSTRACT_POINTS);
    }
    @Override
    public void display()
    {
        switch(MathUtils.random(0,4))
        {
            case 0:
                sprite = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "s_Helmet.png", Texture.class));
                setName("helmet");
                break;
            case 1:
                sprite = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "s_Jacket.png", Texture.class));
                setName("jacket");
                break;
            case 2:
                sprite = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "s_Gloves.png", Texture.class));
                setName("gloves");
                break;
            case 3:
                sprite = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "s_Pants.png", Texture.class));
                setName("pants");
                break;
            case 4:
                sprite = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "s_Boots.png", Texture.class));
                setName("boots");
                break;
        }
    }
}
