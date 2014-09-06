package com.ggg.evilfactory.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.ggg.evilfactory.utils.Constants;

/**
 * Created by borja on 14-9-1.
 */
public class ParticlesManager
{
   public  static ParticleEffect  bombExplosion;
    public static ParticleEffect bombSave;
   public static ParticleEffect coinGotten;

    public ParticlesManager()
    {
        bombExplosion = new ParticleEffect();
        bombSave = new ParticleEffect();
        coinGotten = new ParticleEffect();
        initParticles();
    }

    private void initParticles()
    {
        bombExplosion.load(Gdx.files.internal(Constants.FX_PATH + "bombExplosion"),
                Gdx.files.internal(Constants.FX_PATH));
        bombSave.load(Gdx.files.internal(Constants.FX_PATH + "bombSave"),
                Gdx.files.internal(Constants.FX_PATH));
        coinGotten.load(Gdx.files.internal(Constants.FX_PATH + "coinGotten"),
                Gdx.files.internal(Constants.FX_PATH));
    }
}
