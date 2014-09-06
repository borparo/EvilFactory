package com.ggg.evilfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ggg.evilfactory.game.Application;
import com.ggg.evilfactory.utils.Constants;

/**
 * Created by borja on 14-9-2.
 */
public class Minion
{
    private Sprite  animationSprite;
    TextureRegion[] frames;
    TextureRegion currentFrame;
    private int frameRows;
    private int frameColumns;



    private Vector2 position;
    Animation idle;
    float stateTime;

    public Minion()
    {
        position = new Vector2(0, 0);
        setFrameColumns(4);
        setFrameRows(1);
        setAnimationSprite(new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "minion.png"))));


        createFrames();


    }

    public Sprite getAnimationSprite()
    {
        return animationSprite;
    }

    public void createFrames()
    {

        TextureRegion[][] temp = TextureRegion.split(animationSprite.getTexture(),
                animationSprite.getTexture().getWidth() / getFrameColumns(),
                animationSprite.getTexture().getHeight() / getFrameRows());
        frames = new TextureRegion[getFrameColumns() * getFrameRows() ];
        int index =0;
        for(int i = 0; i < getFrameRows(); i++)
        {
            for (int j = 0; j < getFrameColumns(); j++)
            {
                frames[index++] = temp[i][j];
            }
        }

        idle = new Animation(0.1f, frames);
        stateTime = 0;
    }

    public void render(float deltaTime, Application game)
    {
        stateTime += deltaTime;
        currentFrame = idle.getKeyFrame(stateTime, true);
        game.batch.draw(currentFrame, position.x, position.y);
    }

    public void setFrameRows(int frameRows)
    {
        this.frameRows = frameRows;
    }

    public void setFrameColumns(int frameColumns)
    {
        this.frameColumns = frameColumns;
    }

    public int getFrameColumns()
    {
        return frameColumns;
    }

    public int getFrameRows()
    {
        return frameRows;
    }

    public void setAnimationSprite(Sprite animationSprite)
    {
        this.animationSprite = animationSprite;
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public void dispose()
    {
        getAnimationSprite().getTexture().dispose();
    }
}
