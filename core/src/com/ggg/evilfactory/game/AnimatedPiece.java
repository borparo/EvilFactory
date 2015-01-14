package com.ggg.evilfactory.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by borja on 14-10-30.
 */
public class AnimatedPiece extends AbstractPiece
{
    //FIELDS
    private Sprite animationSprite;
    private Texture animationTexture;
    TextureRegion[] frames;
    TextureRegion currentFrame;
    private int frameRows;
    private int frameColumns;
    private Animation animation;
    private Vector2 position;
    private float stateTime;

    //CONSTRUCTORS
    public AnimatedPiece( Texture animationTexture, Sprite animationSprite)
    {
        this.animationTexture = animationTexture;
        this.animationSprite  = animationSprite;

    }

    public AnimatedPiece(Sprite animationSprite, int frameRows, int frameColumns)
    {
        this.animationSprite = animationSprite;
        this.frameRows = frameRows;
        this.frameColumns = frameColumns;
    }

    //METHODS
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

        animation = new Animation(0.1f, frames);
        setStateTime (0);


    }

    @Override
    public void display()
    {
       setAnimationSprite(getAnimationSprite());
    }

    public Sprite getAnimationSprite()
    {
        return animationSprite;
    }

    public void setAnimationSprite(Sprite animationSprite)
    {
        this.animationSprite = animationSprite;
    }

    public Texture getAnimationTexture()
    {
        return animationTexture;
    }

    public void setAnimationTexture(Texture animationTexture)
    {
        this.animationTexture = animationTexture;
    }

    public TextureRegion[] getFrames()
    {
        return frames;
    }

    public void setFrames(TextureRegion[] frames)
    {
        this.frames = frames;
    }

    public TextureRegion getCurrentFrame()
    {
        return currentFrame;
    }

    public void setCurrentFrame(TextureRegion currentFrame)
    {
        this.currentFrame = currentFrame;
    }

    public int getFrameRows()
    {
        return frameRows;
    }

    public void setFrameRows(int frameRows)
    {
        this.frameRows = frameRows;
    }

    public int getFrameColumns()
    {
        return frameColumns;
    }

    public void setFrameColumns(int frameColumns)
    {
        this.frameColumns = frameColumns;
    }

    @Override
    public Vector2 getPosition()
    {
        return position;
    }

    @Override
    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public float getStateTime()
    {
        return stateTime;
    }

    public void setStateTime(float stateTime)
    {
        this.stateTime = stateTime;
    }
}
