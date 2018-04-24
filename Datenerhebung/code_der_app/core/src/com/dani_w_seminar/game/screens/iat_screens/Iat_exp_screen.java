package com.dani_w_seminar.game.screens.iat_screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dani_w_seminar.game.Dani_w_seminar;

/**
 * Created by root on 19.07.17.
 */

public class Iat_exp_screen implements Screen {

    private Dani_w_seminar game;
    private SpriteBatch batch;
    private Camera cam;
    private Viewport viewport;

    private Stage stage;
    private Table table;
    private TextButton exp_Button;

    private Skin skin;



    public Iat_exp_screen(final Dani_w_seminar game){

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
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.font.getData().setScale(.55f, .55f);

        skin.add("default", textButtonStyle);


        exp_Button = new TextButton("Start", textButtonStyle);

        exp_Button.getLabel().setWrap(true);
        final Screen screen = this;
        exp_Button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("expScreen touchUp");
                game.screen_season_end(screen);
                return false;
            }
        });


        exp_Button.setFillParent(true);
        stage.addActor(exp_Button);

        cam.update();

    }


    @Override
    public void show() {

        /*final Screen screen = this;
        exp_Button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("expScreen touchUp");
                game.screen_season_end(screen);
                return false;
            }
        });*/
        Gdx.input.setInputProcessor(stage);


        float sate = game.getCurrent_iat_state();
        if (sate == .5f){
            exp_Button.setText(
                    "Schritt 1 von 7:\n" +
                            "Im Folgenden wird Ihre Implizite Einstellung " +
                            "gegenüber der Marke Luma Delikatessen gemessen. " +
                            "Dazu bitte ich Sie gleich die Dinge, die zu Luma " +
                            "Delikatessen gehören, mithilfe des links Knopfes " +
                            "zu Luma Delikatessen zuzuordnen und  die Dinge, die " +
                            "mit einer herkömmlichen Metzgerei in " +
                            "Verbindung stehen, mithilfe des rechts Knopfes " +
                            "den Herkömmlichen Metzgereien zuzuordnen. " +
                            "Sollten Sie einen Fehler machen, wird ein rotes " +
                            "Kreuz auftauchen. Dann drücken Sie einfach " +
                            "den anderen Knopf um weiterzumachen. Versuchen Sie so schnell wie " +
                            "möglich zu sein und dabei möglichst wenig Fehler zu machen\n\n" +
                            "Um Fortzufahren Berühren Sie Bitte den Bildschirm");
        }else if(sate == 1.5f){
            exp_Button.setText(
                    "Schritt 2 von 7:\n" +
                            "Nun bitte ich Sie die Dinge, die "+ Iat_task_screen.e1 +" sind, nach links einzuordnen und " +
                            "die Dinge, die " + Iat_task_screen.e2 + " sind nach rechts einzuordnen. Versuchen Sie so schnell wie " +
                            "möglich zu sein und dabei möglichst wenig Fehler zu machen\n\n" +
                            "Um Fortzufahren Berühren Sie bitte den Bildschirm");
        }else if(sate == 2.5f){
            exp_Button.setText(
                    "Schritt 3 von 7:\n" +
                            "Nun bitte ich Sie sowohl Dinge, die " + Iat_task_screen.e1 + " oder " + Iat_task_screen.e2 + " sind, " +
                            "als auch Dinge, die zu Luma Delikatessen oder zu Herkömmlichen Metzgereien " +
                            "gehören, einzuordnen. Versuchen Sie so schnell wie " +
                            "möglich zu sein und dabei möglichst wenig Fehler zu machen\n\n" +
                            "Um Fortzufahren Berühren Sie bitte den Bildschirm"
            );
        }else if(sate == 3.5f){
            exp_Button.setText("Schritt 4 von 7\n" +
                    "\n\n" +
                    "Um Fortzufahren Berühren Sie bitte den Bildschirm");
        }else if(sate == 4.5f){
            exp_Button.setText("Schritt 5 von 7\n" +
                    "\n\n" +
                    "Um Fortzufahren Berühren Sie bitte den Bildschirm");
        }else if(sate == 5.5f){
            exp_Button.setText("Schritt 6 von 7\n" +
                    "\n\n" +
                    "Um Fortzufahren Berühren Sie bitte den Bildschirm"
            );
        }else if(sate == 6.5f){
            exp_Button.setText("Schritt 7 von 7\n" +
                    "\n\n" +
                    "Um Fortzufahren Berühren Sie bitte den Bildschirm");
        }else if(sate == 7.5f){
            exp_Button.setText("Herzlichen Glückwunsch!\n" +
                    "Ihre implizite Einstellung gegenüber der Marke Luma Delikatessen wurde erfolgreich gemessen " +
                    "und ihre Daten werden nun vollkommen anonym ausgewertet. " +
                    "Nun werden ihnen noch ein paar Fragen zur ihrer expliziten Einstellung gestellt werden. " +
                    "Lassen Sie sich nun bei der Beantwortung dieser Fragen bitte möglichst viel Zeit." +
                    "Anschließend können sie sich die Auswertung ihrer Daten ansehen." +
                    "\n\n" +
                    "Um Fortzufahren Berühren Sie bitte den Bildschirm");
        }else{
            exp_Button.setText("Waring: Iat Exp orderd with invalid iat state, iat_state: ");
            System.out.println("Waring: Iat Exp orderd with invalid iat state, iat_state: ");

        }

    }

    @Override
    public void render(float delta) {

        cam.update();
        stage.act();

        Gdx.gl.glClearColor(.1f, 1f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.draw();
        batch.end();



        //System.out.println(exp_Button.isPressed());
    }

    @Override
    public void resize(int width, int height) {
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
       // if (stage != null)
            //stage.dispose();

    }
}
