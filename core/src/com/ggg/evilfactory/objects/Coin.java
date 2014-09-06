package com.ggg.evilfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ggg.evilfactory.game.AbstractPiece;
import com.ggg.evilfactory.game.Application;
import com.ggg.evilfactory.utils.Constants;

/**
 * Created by borja on 14-9-5.
 */
public class Coin extends AbstractPiece
{

    TextureRegion[] frames;
    TextureRegion currentFrame;
    private int frameRows;
    private int frameColumns;
    Animation idle;
    float stateTime;

    public Coin()
    {
        display();
        setPosition(new Vector2(Constants.VIEWPORT_WIDTH, 30));
        setFrameColumns(8);
        setFrameRows(2);
        setAddPoints(15);
        setTakePoints(0);
        setSpeed(200);
        createFrames();
    }

    private void createFrames()
    {
        TextureRegion[][] temp = TextureRegion.split(sprite.getTexture(),
                sprite.getTexture().getWidth() / getFrameColumns(),
                sprite.getTexture().getHeight() / getFrameRows());
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

    public void draw(SpriteBatch batch)
    {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = idle.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, getPositionX(), getPositionY());
    }

    @Override
    public void display()
    {
        sprite =new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "coinAnimation.png")));
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
}
