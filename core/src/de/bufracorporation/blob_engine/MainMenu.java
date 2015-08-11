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

    private TextButton.TextButtonStyle buttonStyle;
    private BitmapFont font;
    private TextureAtlas buttonsAtlas;

    private Texture background, cactus;
    private TextureAtlas back;

    @Override
    public void create() {
        batch = new SpriteBatch();

        skin = new Skin(Gdx.files.internal("json/def.json"), new TextureAtlas(("romans.pack")));


        txtButton_play = new TextButton("Play", skin);
        txtButton_play.setBounds(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2, 200, 60);
        txtButton_load = new TextButton("Load", skin);
        txtButton_load.setBounds(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 2 * 60, 200, 60);

        skin = new Skin(Gdx.files.internal("json/cactus.json"), new TextureAtlas(("Kaktus.pack")));
        txtButton_background = new TextButton("", skin);
        txtButton_background.setBounds(650, -10, 180, 280);

//
        skin = new Skin(Gdx.files.internal("json/back.json"), new TextureAtlas(("Kaktus.pack")));


            backs = new TextButton("", skin);
            backs.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());



    }


    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Gdx.gl.glClearColor(0.4f, 0.6f, 0.3f, 1);

        batch.begin();

        backs.draw(batch, 1);

        txtButton_play.draw(batch, 1);
        txtButton_load.draw(batch, 1);
        txtButton_background.draw(batch, 1);


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