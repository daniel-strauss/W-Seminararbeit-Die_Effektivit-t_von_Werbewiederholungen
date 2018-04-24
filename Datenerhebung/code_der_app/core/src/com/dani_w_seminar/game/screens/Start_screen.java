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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.dani_w_seminar.game.Dani_w_seminar;
//import com.sendgrid.*;
import java.io.IOException;

/**
 * Created by root on 19.07.17.
 */

public class Start_screen implements Screen {

    private Dani_w_seminar game;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private StretchViewport viewport;

    private Stage stage;
    private Skin skin;

    private TextButton startButton;

    private String expS;

    private int rendercount = 0;

    public Start_screen(final Dani_w_seminar game) throws IOException {
        this.game = game;
        this.batch = game.getBatch();
        this.cam = game.cam;
        this.viewport = game.viewport;

        expS = "Gleich wird ihnen ein Werbefilm " + game.playtimes + " mal vorgespielt werden. \n" +
                "Anschließend wird Ihre Einstellung der Marke gegenüber gemessen und es werden Ihnen ein paar Fragen gestellt werden." +
                " Sobald das Video zu ende gespielt hat schließen Sie einfach den Videoplayer." +
                "\nVielen Dank für Ihre Teilnahme!" +
                "\nZum Fortfahren bitte den Bildschirm berühren.";

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
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);



        startButton = new TextButton("Start", textButtonStyle);
        startButton.setText("Lädt das Video...\n Dies braucht etwa 30 Sekunden. \nVielen Dank für ihre Gedult");
        startButton.getLabel().setWrap(true);

        stage.addActor(startButton);
        startButton.setFillParent(true);


        //cam.zoom = 200;
        cam.update();



        /*
        // using SendGrid's Java Library
        // https://github.com/sendgrid/sendgrid-java

        System.out.println("sending http request");
        String urlSting = "https://api.sendgrid.com/api/mail.send.json";


        urlSting += "?api_user=strammerMax" +
                "&api_key=xkdeJHfjghJHGF343" +
                "&to=dinoham223@gmail.com" +
                "&toname=Daniel" +
                "&subject=IAT_Test" +
                "&text=testingtextbody" +
                "&from=info@domain.com";



        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlSting).build();
        Net.HttpResponseListener httpResponseListener = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                System.out.println("http response: " + httpResponse);
            }

            @Override
            public void failed(Throwable t) {
                System.out.println("http request failed: " + t);
            }

            @Override
            public void cancelled() {
                System.out.println("http cancelled");

            }
        };
        Gdx.net.sendHttpRequest(httpRequest, httpResponseListener);*/



    }

    private void load_video(){
        game.file_manager.load_video();
        startButton.getLabel().setFontScale(.6f);
        startButton.setText("Gleich wird ihnen ein Werbefilm " + game.playtimes + " mal vorgespielt werden. \n" +
                "Anschließend wird Ihre Einstellung gegenüber der Marke Luma Delikatessen gemessen und es werden Ihnen darauf noch ein paar Fragen gestellt werden. " +
                "Am Ende wird Ihnen dann ihr Ergebnis präsentiert werden und Sie werden sehen ob Sie leicht von Werbung beeinflussbar sind." +
                "Nehmen Sie sich für die Bearbeitung etwa 7 Minuten Zeit. Sollte es eine Unterbrechung geben, starten Sie den Test einfach neu, indem Sie die App schließen und wieder öffnen." +
                "\nVielen Dank für Ihre Teilnahme!" +
                "\nZum Fortfahren bitte den Bildschirm berühren.");







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


        rendercount ++;
        if (rendercount == 20){
            load_video();
        }else if (rendercount == 40){

            final Screen screen = this;
            startButton.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println("touchUp");
                    game.screen_season_end(screen);
                    return false;
                }
            });

        }

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
