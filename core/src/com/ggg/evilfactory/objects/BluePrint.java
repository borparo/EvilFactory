package com.ggg.evilfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.ggg.evilfactory.utils.Constants;
import com.ggg.evilfactory.utils.ProductTypes;

import java.util.*;

/**
 * Created by borja on 14-9-3.
 */
public class BluePrint
{


    protected Sprite blueprintSprite;
    public List<String> blueprintParts;

    public BluePrint()
    {
        blueprintParts = new ArrayList<String>();
        initialize();
    }

    /**
     * Will initialize the  product part list values to mimic in the production line.
     * Each piece assembled must be in the values if the ProductTypes, to assemble one
     * product unit.
     */
    public void initialize()
    {
        switch (MathUtils.random(0, 2))
        {
            case 0:
                // create the sprite blueprint and the list of part needed to be assembled.
                blueprintSprite = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "bluePrint_weapon.png")));
                blueprintSprite.setPosition(20, 288);

                for (int i = 0; i < ProductTypes.BasicWeapon.size(); i++)
                {
                    blueprintParts.add(ProductTypes.BasicWeapon.get(i));
                }
                break;
            case 1:
                // create the sprite blueprint and the list of part needed to be assembled.
                blueprintSprite = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "bluePrint_suit.png")));
                blueprintSprite.setPosition(20, 288);

                for (int i = 0; i < ProductTypes.BasicSuit.size(); i++)
                {
                    blueprintParts.add(ProductTypes.BasicSuit.get(i));
                }
                break;
            case 2:
                // create the sprite blueprint and the list of part needed to be assembled.
                blueprintSprite = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "bluePrint_gadget.png")));
                blueprintSprite.setPosition(20, 288);

                for (int i = 0; i < ProductTypes.BasicGadget.size(); i++)
                {
                    blueprintParts.add(ProductTypes.BasicGadget.get(i));
                }
                break;
        }

        Collections.sort(blueprintParts);

    }

    public Sprite getBlueprintSprite()
    {
        return blueprintSprite;
    }

    public void setBlueprintSprite(Sprite blueprintSprite)
    {
        this.blueprintSprite = blueprintSprite;
    }
}
