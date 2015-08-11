package de.bufracorporation.blob_engine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Timer;

import javax.xml.soap.Text;


/**
 * Created by Marc on 08.08.2015.
 */
public class MainMenu extends Game{

    private SpriteBatch batch;
    private Skin skin;
    private TextButton txtButton_play, txtButton_load, txtButton_background;
    private TextButton backs;

    private Texture background, cactus, sun;
    private Sprite backgroundSprite, cactusSprite, sunSprite;

    private TextureAtlas GeierAtlas;
    private Sprite  GeierSprite;
    private int currentFrame=0;

    private String currentGeierKey = new String("vulture_0000");


    boolean[] anim = {false, false, false};
    float ranAn = 0f;


    @Override
    public void create() {
        batch = new SpriteBatch();

        skin = new Skin(Gdx.files.internal("json/def.json"), new TextureAtlas(("romans.pack")));

        txtButton_play = new TextButton("Play", skin);
        txtButton_play.setBounds(percentage_width(0.5f) - 100, percentage_height(0.5f), 200, 60);
        txtButton_load = new TextButton("Load", skin);
        txtButton_load.setBounds(percentage_width(0.5f) - 100, percentage_height(0.5f) - 2 * 60, 200, 60);

        background = new Texture(Gdx.files.internal("Menu/background/back.png"));
        backgroundSprite = new Sprite(background);
        backgroundSprite.setBounds(0, 0, percentage_width(1f), percentage_height(1f));

        cactus = new Texture(Gdx.files.internal("Menu/background/kaktus.png"));
        cactusSprite = new Sprite(cactus);
        cactusSprite.setBounds(percentage_width(0.66f), -10, percentage_width(0.18f), percentage_height(0.6f));

        sun = new Texture(Gdx.files.internal("Menu/sun_tra.png"));
        sunSprite = new Sprite(sun);
        sunSprite.setBounds( percentage_width(0.14f), percentage_height(0.4f), percentage_width(0.32f), percentage_width(0.32f));



        GeierAtlas = new TextureAtlas(Gdx.files.internal("Menu/background/vulture/vulture.pack"));
        TextureAtlas.AtlasRegion geierRegion = GeierAtlas.findRegion(currentGeierKey);
        GeierSprite = new Sprite(geierRegion);
        GeierSprite.setPosition(-GeierSprite.getWidth(), -GeierSprite.getHeight());


        //Scheduling timer for random vultures on the Screen
        Timer.schedule(new Timer.Task() {
            @Override
        public void run() {

                if (!anim[0] && !anim[1] && !anim[2]) {
                    ranAn = ((float) Math.random() * 10f);
                    if (ranAn < 0.03) {
                        anim[0] = true;
                        GeierSprite.setPosition( percentage_width(0.15f), percentage_height(0.7f));
                        GeierSprite.setScale(0.1f, 0.1f);
                    }
                    else if(ranAn < 0.06) {
                        anim[1] = true;
                        GeierSprite.setPosition( percentage_width(1f), percentage_height(0.7f));
                        GeierSprite.setScale(1f, 1f);
                    }

                }

                //animation 1 (alle anderen werden derweil blockiert):
                // 100 frames , geier der neben der Sonne in den Bildschirm fliegt

                if (anim[0]) {

                    currentFrame++;

                    currentGeierKey = String.format("vulture_" + "%04d", currentFrame%8);
                    GeierSprite.setRegion(GeierAtlas.findRegion(currentGeierKey));
                    GeierSprite.setPosition(GeierSprite.getX() + 2, GeierSprite.getY() + 4);
                    GeierSprite.scale(0.1f);

                    if(currentFrame > 100){
                        anim[0] = false;
                        currentFrame = 0;
                    }
                }

                if (anim[1]) {
                    currentFrame++;

                    currentGeierKey = String.format("sidevulture_" + "%04d", currentFrame%8);
                    GeierSprite.setRegion(GeierAtlas.findRegion(currentGeierKey));
                    GeierSprite.setPosition(GeierSprite.getX() - 4, GeierSprite.getY());

                    if(currentFrame > 300){
                        anim[1] = false;
                        currentFrame = 0;

                    }
                }
            }

        }, 0,1/30.0f);

    }

    //return p percent of screen width/height
    private float percentage_width(float p){
        return Gdx.graphics.getWidth() * p;
    }

    private float percentage_height(float p){
        return Gdx.graphics.getHeight() * p;
    }


    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.4f, 0.6f, 0.3f, 1);

        batch.begin();

        backgroundSprite.draw(batch);
        sunSprite.draw(batch);

        txtButton_play.draw(batch, 1);
        txtButton_load.draw(batch, 1);

        cactusSprite.draw(batch);
        GeierSprite.draw(batch);

     //   testGeierSprite.draw(batch);

        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
        skin.dispose();
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

}