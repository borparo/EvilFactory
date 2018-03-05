package com.ggg.evilfactory.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.ggg.evilfactory.objects.*;
import com.ggg.evilfactory.utils.PlayerStats;
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
    private RandomXS128 random;
    ParticlesManager particlesManager;

    Sound explosion = Assets.manager.get(Constants.SOUNDFX_PATH + "explosion.wav");
    Sound coin = Assets.manager.get(Constants.SOUNDFX_PATH + "coin.wav");
    Sound pickUpYes = Assets.manager.get(Constants.SOUNDFX_PATH + "pickUpYes.wav");
    Sound pickUpNo = Assets.manager.get(Constants.SOUNDFX_PATH + "pickUpNo.wav");

    private long pieceSpawnTime;
    private long spawnTimeMin;
    private long spawnTimeMax;
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
    private float addOnSpeed = 450.0f;

    /**
     * Calls the already created main application to use it's delta time, camera, batch etc...
     * PlayerScore is initialized for first time here, inside method will call it's current
     * value to start a new label
     *
     * @param game is the main game application
     */
    public WorldControl(final Application game)
    {
        this.game = game;
        setPlayerScore(0);
        setPlayerCoins(0);
        random = new RandomXS128(TimeUtils.millis());
        worldInit();
    }

    private void worldInit()
    {
        assembledPieces = new ArrayList<String>();//will compare this list to the blueprint list
        pieceFactory = new PieceFactory();
        particlesManager = new ParticlesManager();
        spawnTimeMin = 1500;
        spawnTimeMax = 3000;
        //setPlayerScore(getPlayerScore());
        setProductTarget(MathUtils.random(1, 15));
        setProductStock(0);

        if(getPlayerLifes() == 0)
        {
            setPlayerLifes(3);

            life1 = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "life.png", Texture.class));
            life2 = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "life.png", Texture.class));
            life3 = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "life.png", Texture.class));
        }
        else
        {
            setPlayerLifes(getPlayerLifes());
        }


        //set first bluePrint
        blueprint = new BluePrint();//the blueprint that contains the list of pieces to match

       //position lifes sprites

        life1.setPosition(700, 1500);
        life2.setPosition(800, 1500);
        life3.setPosition(900, 1500);

        //background characters
        minion1 = new Minion();
        minion1.setPosition(new Vector2(Constants.VIEWPORT_WIDTH / 2 - Constants.BUTTONS_OFFSET, 200));


        minion2 = new Minion();
        minion2.getAnimationSprite().setSize(minion2.getAnimationSprite().getWidth() * 0.7f,
                minion2.getAnimationSprite().getHeight() * 0.7f);
        minion2.setPosition(new Vector2(Constants.VIEWPORT_WIDTH / 2 +  Constants.BUTTONS_OFFSET / 2, 750));



        sillyMinion = new Minion();
        sillyMinion.setAnimationSprite(new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "minion_silly.png", Texture.class)));
        sillyMinion.createFrames();
        sillyMinion.setPosition(new Vector2(Constants.VIEWPORT_WIDTH / 2, 455));


    }

    public void update(float deltaTime)
    {
        //when time is done create randomly a piece of type (1 = weapon, 2 = suit, 3 = gadget,, 4 = coin,  0 = bomb)
        if (TimeUtils.millis() - pieceSpawnTime > MathUtils.random(spawnTimeMin, spawnTimeMax))
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


            //For pieces, if  they are touched, delete it and add name to the assembled parts list
            // always they are not a coin or a bomb. If its coin add coin value, if bomb it's a bomb
            // before it explodes add points, else take away one life and points.
            if (Gdx.input.justTouched())
            {
                game.getTouchPosition().set(Gdx.input.getX(), Gdx.input.getY(), 0);
                game.getCamera().unproject(game.getTouchPosition());

                if (game.getTouchPosition().x > piece.getSprite().getX() &&
                        game.getTouchPosition().x < piece.getSprite().getX() + piece.getSprite().getWidth()  &&
                        game.getTouchPosition().y > piece.getSprite().getY() &&
                        game.getTouchPosition().y < (piece.getSprite().getY() + piece.getSprite().getHeight()))
                {

                    iter.remove();

                    //COINS. Add points and money. Play sound

                    if (piece instanceof Coin)
                    {
                        setPlayerCoins(getPlayerCoins() + piece.getAddPoints());
                        setPlayerScore(getPlayerScore() + piece.getAddPoints());

                        ParticlesManager.coinGotten.setPosition(piece.getPositionX(), 150);
                        ParticlesManager.coinGotten.start();
                        if(PlayerStats.SOUND_FX)
                        {
                            coin.play(1.0f);
                        }
                    }

                    //check active blueprint, add points if piece is not yet assembled, else take points from player
                    //piece name is in blueprint and is not a bomb, add it's name to assembling list
                    if (blueprint.blueprintParts.contains(piece.getName()) &&
                            !assembledPieces.contains(piece.getName()) &&
                            !(piece instanceof Bomb) &&
                            !(piece instanceof Coin))
                    {
                        assembledPieces.add(piece.getName());
                        setPlayerScore(getPlayerScore() + piece.getAddPoints());
                        setPlayerCoins(getPlayerCoins() + 1);

                        if(PlayerStats.SOUND_FX)
                        {
                            pickUpYes.play(1.0f);
                        }
                    }
                    //piece name is in blueprint but already assembled and is not a bomb
                    else if (assembledPieces.contains(piece.getName()) &&
                            !(piece instanceof Bomb) && !(piece instanceof Coin))
                    {
                        setPlayerScore(getPlayerScore() - piece.getTakePoints());

                        if(PlayerStats.SOUND_FX)
                        {
                            pickUpNo.play(1.0f);
                        }
                    }
                    //piece name is not in blue print and is not a bomb
                    else if (!blueprint.blueprintParts.contains(piece.getName()) &&
                            !(piece instanceof Bomb) && !(piece instanceof Coin))
                    {
                        setPlayerScore(getPlayerScore() - piece.getTakePoints());

                        if(PlayerStats.SOUND_FX)
                        {
                            pickUpNo.play(1.0f);
                        }
                    }
                    //if we click on a bomb before explosion add points. TODO BONUSES id:0 gh:3 ic:gh
                    else if (piece instanceof Bomb)
                    {
                        ParticlesManager.bombSave.setPosition(piece.getPositionX(), 75);
                        ParticlesManager.bombSave.start();
                        setPlayerScore(getPlayerScore() + piece.getAddPoints());

                        if(PlayerStats.SOUND_FX)
                        {
                            pickUpYes.play(1.0f);
                        }
                    }


                    //order list
                    Collections.sort(assembledPieces);

                    //compare list. if equal, add one object to stock and clear assembling list
                    if (assembledPieces.equals(blueprint.blueprintParts))
                    {
                        setProductStock(getProductStock() + 1);
                        assembledPieces.clear();
                        piece.setSpeed(piece.getSpeed() + addOnSpeed);
                        if (spawnTimeMax > spawnTimeMin)
                        {
                            spawnTimeMax -= (spawnTimeMax / getProductTarget());
                        }
                        else if(spawnTimeMax <= spawnTimeMin)
                        {
                            spawnTimeMax = spawnTimeMin;
                        }
                    }

                    //level complete TODO- ADD BONUSES FOR PLAYER
                    if (getProductStock() == getProductTarget())
                    {
                        PlayerStats.LEVEL_COMPLETE = true;
                        setPlayerScore(getPlayerScore() + 300);
                    }

                    if (PlayerStats.LEVEL_COMPLETE) //TODO check achievements completed- id:1 gh:4 ic:gh
                    {
                        game.setPaused(true);
                        //TODO save money to file. Display level complete message. id:2 gh:5 ic:gh
                        PlayerStats.PLAYER_SAVED_MONEY += getPlayerCoins();
                        PlayerStats.CURRENT_SCORE += getPlayerScore();
                        game.setScreen(game.store);
                        worldInit();
                        PlayerStats.LEVEL_COMPLETE = false;

                    }
                }
            }

            //BOMB EXPLOSION
            // it sets the player lifes after exploding. When game is over,it pauses it.
            if (TimeUtils.millis() - bombExplodingTime > MathUtils.random(1500, 3000))
            {
                if (piece instanceof Bomb)
                {

                    ParticlesManager.bombExplosion.setPosition(piece.getSprite().getX() + piece.getSprite().getWidth() / 2, 150);
                    ParticlesManager.bombExplosion.start();

                    if(PlayerStats.SOUND_FX)
                    {
                        explosion.play(1.0f);
                    }

                    setPlayerScore(getPlayerScore() - piece.getTakePoints());
                    setPlayerLifes(getPlayerLifes() - 1);
                    setPlayerCoins(getPlayerCoins() - piece.getTakePoints());
                    if (getPlayerCoins() <= 0)
                    {
                        setPlayerCoins(0);
                    }

                    //set lifes textures

                    if (playerLifes == 2)
                    {
                        life1.setTexture(Assets.manager.get(Constants.ASSETS_PATH + "lifeNo.png", Texture.class));
                    }
                    else if (playerLifes == 1)
                    {
                        life2.setTexture(Assets.manager.get(Constants.ASSETS_PATH + "lifeNo.png", Texture.class));
                    }
                    else if (playerLifes <= 0) //game over
                    {
                        life3.setTexture(Assets.manager.get(Constants.ASSETS_PATH + "lifeNo.png", Texture.class));
                        setPlayerCoins(0);

                        //setGameOverScreen

                        game.setScreen(game.gameOver);



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
        switch (random.nextInt(5))
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
