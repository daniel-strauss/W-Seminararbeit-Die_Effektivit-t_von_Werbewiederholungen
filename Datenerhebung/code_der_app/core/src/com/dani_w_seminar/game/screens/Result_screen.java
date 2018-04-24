package com.dani_w_seminar.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.dani_w_seminar.game.Dani_w_seminar;

/**
 * Created by root on 19.07.17.
 */

public class Result_screen implements Screen {
    private Dani_w_seminar game;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private StretchViewport viewport;

    private Stage stage;
    private Skin skin;

    private Label label;



    public Result_screen(final Dani_w_seminar game){
        this.game = game;
        this.batch = game.getBatch();
        this.cam = game.cam;
        this.viewport = game.viewport;


        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);




        skin = new Skin();
        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        skin.add("default", new BitmapFont());

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.background = skin.newDrawable("white", Color.DARK_GRAY);
        //labelStyle.down = skin.newDrawable("white", Color.YELLOW);
        //labelStyle.checked = skin.newDrawable("white", Color.BLUE);
        //labelStyle.over = skin.newDrawable("white", Color.GRAY);
        labelStyle.font = skin.getFont("default");
        skin.add("default", labelStyle);


        final Screen screen = this;

        label = new Label("Start", labelStyle);
        label.setWrap(true);
        label.setText("IAT_ergebnis: " + game.iat_result +
                "\nIst das Ergebnis positiv, so haben sie eine positive automatische Reaktion gegenüber der Marke Luma Delikatessen, " +
                "ist das Ergebnis negativ, so haben sie eine negative. " +
                "Um herauszufinden, wie stark Sie Ihre implizite Einstellung ist müssen noch mehr Testergebnisse von anderen " +
                "Probanden gesammelt werden. Die Durchschnittsergebnisse werden nicht runtergespült, sondern an die Email-Adresse strammermax24@gmx.de " +
                "gesendet werden, welche Sie innerhalb der nächsten drei Wochen mit dem Passwort 12345678 abrufen können. " +
                "Dann können Sie sehen, ob Sie leichter oder schwerer durch Werbung beeinflusst werden als der Durchschnitt. " +
                "Das komplette Ergebnis befindet sich in der Datei Downloads/IAT.txt.\n"+
                "Der Werbefilm befindet sich im Ordner Downloads, falls Sie diesen Löschen möchten, um Speicherplatz frei zu machen.\n" +
                "Ich bedanke mich vielmals für ihre Teilnahme!");




        label.setWidth(viewport.getScreenWidth());
        label.setHeight(viewport.getScreenHeight() * 100);
        ScrollPane scrollPane = new ScrollPane(label);
        scrollPane.setFillParent(true);
        scrollPane.setForceScroll(false, true);

        stage.addActor(scrollPane);


        //cam.zoom = 200;
        cam.update();


        label.setDebug(true);




    }





    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //cam.zoom += delta* 1.6f;
        cam.update();

        stage.act();

        Gdx.gl.glClearColor(1, 1f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        stage.draw();
        batch.end();




    }

    @Override
    public void resize(int width, int height) {
        //viewport.update(width,height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
