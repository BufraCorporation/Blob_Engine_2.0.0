package de.bufracorporation.blob_engine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Animation;
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
    private TextButton txtButton_play, txtButton_load;

    private Texture background, cactus, sun, rock;
    private Sprite backgroundSprite, cactusSprite, sunSprite, rockSprite;


    private TextureAtlas GeierAtlas;
    private Animation GeierAnimationScreen, GeierAnimationSide, CoyoteAnimationCorner;
    private float elapsedTime = 0, elapsedTimeCoyote=0;
    private float CoyoteX;

    boolean[] anim = {false, false, false};
    float[] animFrame = {0, 0, 0};


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

        //cactus = new Texture(Gdx.files.internal("Menu/background/kaktus.png"));
        //cactusSprite = new Sprite(cactus);
        //cactusSprite.setBounds(percentage_width(0.85f), -40, percentage_width(0.25f), percentage_height(1f));

        sun = new Texture(Gdx.files.internal("Menu/sun_tra.png"));
        sunSprite = new Sprite(sun);
        sunSprite.setBounds( percentage_width(0.14f), percentage_height(0.4f), percentage_width(0.32f), percentage_width(0.32f));

        rock = new Texture(Gdx.files.internal("Menu/rock_tr.png"));
        rockSprite = new Sprite(rock);
        rockSprite.setBounds(0, 0, percentage_width(0.6f), percentage_width(0.3f));


        GeierAtlas = new TextureAtlas(Gdx.files.internal("Menu/background/vulture/vulture.pack"));

        GeierAnimationScreen = new Animation(1/10f, GeierAtlas.findRegion("vulture_0000"),
                GeierAtlas.findRegion("vulture_0001"), GeierAtlas.findRegion("vulture_0002"),
                GeierAtlas.findRegion("vulture_0003"), GeierAtlas.findRegion("vulture_0004"),
                GeierAtlas.findRegion("vulture_0005"), GeierAtlas.findRegion("vulture_0006"),
                GeierAtlas.findRegion("vulture_0007"));

        GeierAnimationSide = new Animation(1/15f, GeierAtlas.findRegion("sidevulture_0001"),
                GeierAtlas.findRegion("sidevulture_0001"), GeierAtlas.findRegion("sidevulture_0005"),
                GeierAtlas.findRegion("sidevulture_0007"), GeierAtlas.findRegion("sidevulture_0007"),
                GeierAtlas.findRegion("sidevulture_0007"),
                GeierAtlas.findRegion("sidevulture_0005"), GeierAtlas.findRegion("sidevulture_0003"),
                GeierAtlas.findRegion("sidevulture_0001"));

        CoyoteAnimationCorner = new Animation(1/10f, GeierAtlas.findRegion("coyote_0000"),
                GeierAtlas.findRegion("coyote_0000"), GeierAtlas.findRegion("coyote_0000"),
                GeierAtlas.findRegion("coyote_0001"), GeierAtlas.findRegion("coyote_0001"),
                GeierAtlas.findRegion("coyote_0000"), GeierAtlas.findRegion("coyote_0000"),
                GeierAtlas.findRegion("coyote_0001"), GeierAtlas.findRegion("coyote_0001"),
                GeierAtlas.findRegion("coyote_0000"), GeierAtlas.findRegion("coyote_0000"),
                GeierAtlas.findRegion("coyote_0001"), GeierAtlas.findRegion("coyote_0001"),
                GeierAtlas.findRegion("coyote_0000"), GeierAtlas.findRegion("coyote_0000"),

                GeierAtlas.findRegion("coyote_0001"), GeierAtlas.findRegion("coyote_0002"),
                GeierAtlas.findRegion("coyote_0003"), GeierAtlas.findRegion("coyote_0004"),
                GeierAtlas.findRegion("coyote_0005"), GeierAtlas.findRegion("coyote_0006"),
                GeierAtlas.findRegion("coyote_0006"), GeierAtlas.findRegion("coyote_0006"),
                GeierAtlas.findRegion("coyote_0007"), GeierAtlas.findRegion("coyote_0007"),
                GeierAtlas.findRegion("coyote_0008"), GeierAtlas.findRegion("coyote_0008"),
                GeierAtlas.findRegion("coyote_0009"), GeierAtlas.findRegion("coyote_0009"),
                GeierAtlas.findRegion("coyote_0009"), GeierAtlas.findRegion("coyote_0009"),
                GeierAtlas.findRegion("coyote_0009"), GeierAtlas.findRegion("coyote_0009"),
                GeierAtlas.findRegion("coyote_0010"), GeierAtlas.findRegion("coyote_0010"),
                GeierAtlas.findRegion("coyote_0011"), GeierAtlas.findRegion("coyote_0011"),
                GeierAtlas.findRegion("coyote_0012"), GeierAtlas.findRegion("coyote_0012"),
                GeierAtlas.findRegion("coyote_0013"), GeierAtlas.findRegion("coyote_0013"),
                GeierAtlas.findRegion("coyote_0014"), GeierAtlas.findRegion("coyote_0014"),
                GeierAtlas.findRegion("coyote_0015"), GeierAtlas.findRegion("coyote_0015"),
                GeierAtlas.findRegion("coyote_0016"), GeierAtlas.findRegion("coyote_0016"));


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

        elapsedTime += Gdx.graphics.getDeltaTime();
        elapsedTimeCoyote += Gdx.graphics.getDeltaTime();

        drawVultureScreen();
        drawVultureSide();
        drawCoyoteHowl();

        rockSprite.draw(batch);
//        cactusSprite.draw(batch);


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

    public void drawVultureScreen(){
        if( Math.random() < 0.002f && !anim[0]){
            anim[0] = true;
            batch.draw(GeierAnimationScreen.getKeyFrame(elapsedTime, true), percentage_width(0.15f), percentage_height(0.7f), 1, 1 );
        }
        if(anim[0]){
            batch.draw(GeierAnimationScreen.getKeyFrame(elapsedTime, true), percentage_width(0.15f)+0.2f*animFrame[0], percentage_height(0.7f)+animFrame[0]*0.85f, 0f + animFrame[0]*1f, 0f + animFrame[0]*1f);
            animFrame[0]++;
            if(animFrame[0] > 300){
                anim[0] = false;
                animFrame[0] = 0;
            }
        }
    }

    public void drawVultureSide() {
        if (Math.random() < 0.002f && !anim[1]) {
            anim[1] = true;
            batch.draw(GeierAnimationSide.getKeyFrame(elapsedTime, true), percentage_width(1), percentage_height(0.7f));
        }
        if (anim[1]) {
            batch.draw(GeierAnimationSide.getKeyFrame(elapsedTime, true), percentage_width(1) - 2f * animFrame[1], percentage_height(0.7f), 20, 20);
            animFrame[1]++;
            if (animFrame[1] > 600) {
                anim[1] = false;
                animFrame[1] = 0;
            }
        }
    }

    public void drawCoyoteHowl(){
        if (Math.random() < 0.002f && !anim[2]) {
            elapsedTimeCoyote = 0;
            anim[2] = true;
            CoyoteX = percentage_width(1);
            batch.draw(CoyoteAnimationCorner.getKeyFrame(elapsedTimeCoyote, true),  percentage_width(1), 0, percentage_width(0.2f), percentage_width(0.1f));
        }
        if (anim[2]) {
            if (animFrame[2] < 90){
                CoyoteX -= 2;
            }
            else if(animFrame[2] < 240){

            }
            else if(animFrame[2] < 280){
                CoyoteX += 2;}
            animFrame[2]++;
            batch.draw(CoyoteAnimationCorner.getKeyFrame(elapsedTimeCoyote, true),  CoyoteX, 0, percentage_width(0.2f), percentage_width(0.1f));


            if( animFrame[2] > 250 ) {
                anim[2] = false;
                animFrame[2] = 0;
            }
        }
    }
}