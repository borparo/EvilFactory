package com.ggg.evilfactory.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.ggg.evilfactory.objects.*;
import com.ggg.evilfactory.utils.Achievements;
import com.ggg.evilfactory.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by borja on 14-8-17.
 */
public class WorldControl
{
    final Application game;
    private int productTarget;
    private int productStock;
    private long playerScore;
    private int playerCoins;
    ParticlesManager particlesManager;

    Sound explosion = Gdx.audio.newSound(Gdx.files.internal(Constants.SOUNDFX_PATH + "explosion.wav"));
    Sound coin = Gdx.audio.newSound(Gdx.files.internal(Constants.SOUNDFX_PATH + "coin.wav"));
    Sound pickUpYes = Gdx.audio.newSound(Gdx.files.internal(Constants.SOUNDFX_PATH + "pickUpYes.wav"));
    Sound pickUpNo = Gdx.audio.newSound(Gdx.files.internal(Constants.SOUNDFX_PATH + "pickUpNo.wav"));

    private long pieceSpawnTime;
    private long bombExplodingTime;

    protected Sprite life1;
    protected Sprite life2;
    protected Sprite life3;
    private int playerLifes;

    protected BluePrint blueprint; //stores the list of pieces to be completed
    private List<String> assembledPieces;  //stores the list of pieces assembled


    protected List<AbstractPiece> producedParts = new ArrayList<AbstractPiece>();
    // ListIterator<Displayable> iter = producedParts.listIterator();
    protected PieceFactory pieceFactory;

    Minion minion1;
    Minion minion2;
    Minion sillyMinion;

    /**
     * Calls the already created main application to use it's delta time, camera, batch etc...
     * PlayerScore is initialized for first time here, inside method will call it's current
     * value to start a new label
     * @param game is the main game application
     */
    public WorldControl(final Application game)
    {
        this.game = game;
        setPlayerScore(0);
        setPlayerCoins(0);
        worldInit();
    }

    private void worldInit()
    {
        assembledPieces = new ArrayList<String>();//will compare this list to the blueprint list
        pieceFactory = new PieceFactory();
        particlesManager = new ParticlesManager();
        //setPlayerScore(getPlayerScore());
        setProductTarget(MathUtils.random(1, 15));
        setProductStock(0);
        setPlayerLifes(3);


        //set first bluePrint
        blueprint = new BluePrint();//the blueprint that contains the list of pieces to match

        //set up lifes

        life1 = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "life.png")));
        life2 = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "life.png")));
        life3 = new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "life.png")));

        life1.setPosition(550, 450);
        life2.setPosition(580, 450);
        life3.setPosition(610, 450);

        //background characters
        minion1 = new Minion();
        minion1.setPosition(new Vector2(250, 100));

        minion2 = new Minion();
        minion2.getAnimationSprite().setSize(minion2.getAnimationSprite().getWidth() * 0.7f,
                minion2.getAnimationSprite().getHeight() * 0.7f);
        minion2.setPosition(new Vector2(415, 220));


        sillyMinion = new Minion();
        sillyMinion.setAnimationSprite(new Sprite(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "minion_silly.png"))));
        sillyMinion.createFrames();
        sillyMinion.setPosition(new Vector2(400, 145));


    }

    public void update(float deltaTime)
    {
        //when time is done create randomly a piece of type (1 = weapon, 2 = suit, 3 = gadget, 0 = bomb)
        if (TimeUtils.millis() - pieceSpawnTime > MathUtils.random(1500, 3000))
        {
            choosePieceType();

        }

        //animate each piece of the producedParts list from right to left
        //and remove the ones that exit by the left side of teh screen
        for (AbstractPiece piece : producedParts)
        {
            piece.setPositionX(piece.getSpeed(), deltaTime);
        }


        ListIterator iter = producedParts.listIterator();
        while (iter.hasNext())
        {
            AbstractPiece piece = (AbstractPiece) iter.next();

            //delete piece if leaves screen by the left
            if (piece.getSprite().getX() < 0 - piece.getSprite().getWidth())
            {
                iter.remove();
            }

            //tint piece sprite when cursor over it
            if (Gdx.input.getX() > piece.getSprite().getX() &&
                    Gdx.input.getX() < piece.getSprite().getX() + piece.getSprite().getWidth() &&
                    Gdx.input.getY() < Constants.VIEWPORT_HEIGHT - piece.getSprite().getY() &&
                    Gdx.input.getY() > Constants.VIEWPORT_HEIGHT - (piece.getSprite().getY() + piece.getSprite().getHeight()))
            {
                piece.getSprite().setColor(Color.YELLOW);
            }
            else
            {
                piece.getSprite().setColor(Color.WHITE);
            }

            //when piece is touched, delete it and add name to the assembled parts list
            if (Gdx.input.justTouched())
            {
                game.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                game.camera.unproject(game.touchPosition);

                if (Gdx.input.getX() > piece.getSprite().getX() &&
                        Gdx.input.getX() < piece.getSprite().getX() + piece.getSprite().getWidth() &&
                        Gdx.input.getY() < Constants.VIEWPORT_HEIGHT - piece.getSprite().getY() &&
                        Gdx.input.getY() > Constants.VIEWPORT_HEIGHT - (piece.getSprite().getY() + piece.getSprite().getHeight()))
                {

                    iter.remove();

                    //COINS

                    if(piece instanceof Coin)
                    {
                        setPlayerCoins(getPlayerCoins() + piece.getAddPoints());
                        setPlayerScore(getPlayerScore() + piece.getAddPoints());
                        coin.play(1.0f);
                        ParticlesManager.coinGotten.setPosition(piece.getPositionX(), 75);
                        ParticlesManager.coinGotten.start();
                    }

                    //check active blueprint, add points if piece is not yet assembled, else take points from player
                    //piece name is in blueprint and not assembled yet and is not a bomb
                    if (blueprint.blueprintParts.contains(piece.getName()) &&
                            !assembledPieces.contains(piece.getName()) &&
                            !(piece instanceof Bomb) &&
                            !(piece instanceof Coin))
                    {
                        assembledPieces.add(piece.getName());
                        setPlayerScore(getPlayerScore() + piece.getAddPoints());
                        pickUpYes.play(1.0f);
                    }
                    //piece name is in blueprint but already assembled and is not a bomb
                    else if (assembledPieces.contains(piece.getName()) &&
                           !( piece instanceof Bomb ) && !(piece instanceof Coin))
                    {
                        setPlayerScore(getPlayerScore() - piece.getTakePoints());
                        pickUpNo.play(1.0f);
                    }
                    //piece name is not in blue print and is not a bomb
                    else if(!blueprint.blueprintParts.contains(piece.getName()) &&
                            !(piece instanceof  Bomb) && !(piece instanceof Coin))
                    {
                        setPlayerScore(getPlayerScore() - piece.getTakePoints());
                        pickUpNo.play(1.0f);
                    }
                    //if we click on a bomb before explosion add points. TODO BONUSES
                    else if(piece instanceof Bomb)
                    {
                        ParticlesManager.bombSave.setPosition(piece.getPositionX(), 75);
                        ParticlesManager.bombSave.start();
                        setPlayerScore(getPlayerScore() + piece.getAddPoints());
                        pickUpYes.play(1.0f);
                    }


                    //order list
                    Collections.sort(assembledPieces);

                    //compare list. if equal, add one object to stock and clear assembling list
                    if (assembledPieces.equals(blueprint.blueprintParts))
                    {
                        setProductStock(getProductStock() + 1);
                        assembledPieces.clear();
                        piece.setSpeed(piece.getSpeed() + 65);
                    }

                    //level complete TODO- ADD BONUSES FOR PLAYER
                    if(getProductStock() == getProductTarget())
                    {
                        Achievements.LEVEL_COMPLETE = true;
                        setPlayerScore(getPlayerScore() + 300);
                    }

                    if(Achievements.LEVEL_COMPLETE) //TODO check achievements completed-
                    {
                        worldInit();
                        Achievements.LEVEL_COMPLETE = false;

                    }
                }
            }

            //BOMB EXPLOSION
            // it sets the player lifes after exploding. When game is over,it pauses it.
            if (TimeUtils.millis() - bombExplodingTime > MathUtils.random(1500, 3000))
            {
                if (piece instanceof Bomb)
                {

                    ParticlesManager.bombExplosion.setPosition(piece.getSprite().getX() + piece.getSprite().getWidth() / 2, 75);
                    ParticlesManager.bombExplosion.start();

                    explosion.play(1.0f);

                    setPlayerScore(getPlayerScore() - piece.getTakePoints());
                    setPlayerLifes(getPlayerLifes() - 1);
                    setPlayerCoins(getPlayerCoins() - piece.getTakePoints());
                    if(getPlayerCoins() <= 0)
                    {
                        setPlayerCoins(0);
                    }

                    //set lifes textures

                    if (playerLifes == 2)
                    {
                        life1.setTexture(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "lifeNo.png")));
                    }
                    else if (playerLifes == 1)
                    {
                        life2.setTexture(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "lifeNo.png")));
                    }
                    else if (playerLifes <= 0) //game over
                    {
                        life3.setTexture(new Texture(Gdx.files.internal(Constants.ASSETS_PATH + "lifeNo.png")));
                        setPlayerCoins(0);

                        //pause game

                        game.setPaused(true);
                    }

                    iter.remove();

                }

            }

        }



    }

    private void setPlayerLifes(int lifes)
    {
        this.playerLifes = lifes;
    }


    private void choosePieceType()
    {
        switch (MathUtils.random(0, 4))
        {
            case 0:
                producedParts.add(PieceFactory.getPieceType("Weapon"));

                break;
            case 1:
                producedParts.add(PieceFactory.getPieceType("Gadget"));
                break;
            case 2:
                producedParts.add(PieceFactory.getPieceType("Suit"));
                break;
            case 3:
                producedParts.add(PieceFactory.getPieceType("Bomb"));
                bombExplodingTime = TimeUtils.millis();
                break;
            case 4:
                producedParts.add(PieceFactory.getPieceType("Coin"));
                break;

        }

        pieceSpawnTime = TimeUtils.millis();
    }


    public int getProductTarget()
    {
        return productTarget;
    }

    public void setProductTarget(int productTarget)
    {
        this.productTarget = productTarget;
    }

    public int getProductStock()
    {
        return productStock;
    }

    public void setProductStock(int productStock)
    {
        this.productStock = productStock;
    }

    public long getPlayerScore()
    {
        return playerScore;
    }

    public void setPlayerScore(long playerScore)
    {
        this.playerScore = playerScore;
    }

    public int getPlayerLifes()
    {
        return playerLifes;
    }

    public int getPlayerCoins()
    {
        return playerCoins;
    }

    public void setPlayerCoins(int playerCoins)
    {
        this.playerCoins = playerCoins;
    }
}
