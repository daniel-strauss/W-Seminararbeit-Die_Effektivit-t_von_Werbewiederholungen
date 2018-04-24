package com.dani_w_seminar.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dani_w_seminar.game.Dani_w_seminar;

import static java.lang.Thread.sleep;

/**
 * Created by root on 19.07.17.
 */

public class Questionnaire_screen implements Screen {


    private Dani_w_seminar game;
    private SpriteBatch batch;
    private Viewport viewport;

    private Stage stage;
    private Table table;

    private Label questionLabel;

    private TextButton answer1_button;
    private TextButton answer2_button;
    private TextButton answer3_button;
    private TextButton answer4_button;
    private TextButton answer5_button;

    private TextButton sRow_answer6_button;
    private TextButton sRow_answer7_button;
    private TextButton sRow_answer8_button;
    private TextButton sRow_answer9_button;
    private TextButton sRow_answer10_button;

    private TextButton[] answerButtons;


    private String currentQuestionString;
    private int currentQuestionIndex;
    private int currentAnswer;

    private String[] allQuestions;
    private Array<String[]> allAnswers;


    private String answer_package;

    public Questionnaire_screen(Dani_w_seminar game){
        this.game = game;
        viewport = game.viewport;
        batch = game.getBatch();

        answer_package = "QUESTIONAIRE START: \n";
        allQuestions = new String[]{
                "Wie alt sind Sie etwa?",
                "Sie sind: ",
                "Haben Sie schonmal einen impliziten Assoziationstest gemacht?",
                "Kannten Sie den Werbefilm schon?",
                "Essen Sie gerne Fleisch?",
                "Haben Sie schon einmal Fleisch im Internet bestellt?",
                "Ziehen Sie es in Erwägung Fleisch bei \n" +
                        "Luma Delikatessen zu bestellen?",
                "Wie hoch, glauben Sie, ist der Aufwand \n" +
                        "bei Luma Delikatessen etwas zu bestellen.",
                "Wie, glauben Sie, schmeckt Fleisch von \n" +
                        "Luma Delikatessen im Vergleich zu Frischfleisch?\n" +
                        "Fleisch von Luma Delikatessen schmeckt ..."
        };
        allAnswers = new Array<String[]>(){};
        allAnswers.add(new String[]{"jünger als 20", "21-30", "31-40", "41-50", "51 - 60", "61 - 70", "älter als 70"});
        allAnswers.add(new String[]{"Weiblich.", "Männlich"});
        allAnswers.add(new String[]{"Ja.", "Nein"});
        allAnswers.add(new String[]{ "Ja.", "Nein."});
        allAnswers.add(new String[]{"ziemlich gerne", "gerne", "weiß nicht", "eher nicht", "gar nicht"});
        allAnswers.add(new String[]{"Ja.", "Nein."});
        allAnswers.add(new String[]{"Ja sehr", "schon", "weiß nicht", "eher nicht", "gar nicht"});
        allAnswers.add(new String[]{"sehr hoch", "hoch", "weiß nicht", "nicht so hoch", "gar nicht hoch"});
        allAnswers.add(new String[]{"viel besser als Frischfleisch.",
                "besser als Frischfleisch.",
                "gleich gut wie Frischfleisch.",
                "schlechter als Frischfleisch.",
                "viel Schlechter als Frischfleisch.",
                "weiß nicht"});
//allAnswers.add();
        //allAnswers.add(new String[]{ });



        currentQuestionIndex = 0;
        setup_Ui();



    }


    private void setup_Ui(){

        stage = new Stage();
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);
        stage.setViewport(viewport);
        Gdx.input.setInputProcessor(stage);


        Skin skin = new Skin();
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
        textButtonStyle.down = skin.newDrawable("white", Color.GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.GRAY);
        textButtonStyle.over = skin.newDrawable("white", Color.GRAY);
        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.font.getData().setScale(.5f, .5f);

        skin.add("default", textButtonStyle);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.background = skin.newDrawable("white", Color.BLACK);
        labelStyle.fontColor = Color.WHITE;
        labelStyle.font = skin.getFont("default");
        skin.add("default", labelStyle);

        questionLabel = new Label("none", skin);
        //questionLabel.setWrap(true);

        answer1_button = new TextButton("1", textButtonStyle);
        answer2_button = new TextButton("2", textButtonStyle);
        answer3_button = new TextButton("3", textButtonStyle);
        answer4_button = new TextButton("4", textButtonStyle);
        answer5_button = new TextButton("5", textButtonStyle);
        sRow_answer6_button = new TextButton("6", textButtonStyle);
        sRow_answer7_button = new TextButton("7", textButtonStyle);
        sRow_answer8_button = new TextButton("8", textButtonStyle);
        sRow_answer9_button = new TextButton("9", textButtonStyle);
        sRow_answer10_button = new TextButton("10", textButtonStyle);


        answerButtons = new TextButton[10];
        answerButtons[0] = answer1_button;
        answerButtons[1] = answer2_button;
        answerButtons[2] = answer3_button;
        answerButtons[3] = answer4_button;
        answerButtons[4] = answer5_button;
        answerButtons[5] = sRow_answer6_button;
        answerButtons[6] = sRow_answer7_button;
        answerButtons[7] = sRow_answer8_button;
        answerButtons[8] = sRow_answer9_button;
        answerButtons[9] = sRow_answer10_button;


        answer1_button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("answer button pressed");
                next_question(1);
                return true;
            }
        });;
        answer2_button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("answer button pressed");
                next_question(2);
                return true;
            }
        });;
        answer3_button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("answer button pressed");
                next_question(3);
                return true;
            }
        });;
        answer4_button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("answer button pressed");
                next_question(4);
                return true;
            }
        });;
        answer5_button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("answer button pressed");
                next_question(5);
                return true;
            }
        });
        sRow_answer6_button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("answer button pressed");
                next_question(6);
                return true;
            }
        });
        sRow_answer7_button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("answer button pressed");
                next_question(7);
                return true;
            }
        });;

        sRow_answer8_button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("answer button pressed");
                next_question(8);
                return true;
            }
        });;
        sRow_answer9_button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("answer button pressed");
                next_question(9);
                return true;
            }
        });;
        sRow_answer10_button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("answer button pressed");
                next_question(10);
                return true;
            }
        });;





        /*questionLabel.setScale(2,2);

        answer1_button.setScale(.10f,.10f);
        answer2_button.setScale(10,10);
        answer3_button.setScale(10,10);
        answer4_button.setScale(10,10);
        answer5_button.setScale(10,10);
*/

        table.add(questionLabel).colspan(allAnswers.get(currentQuestionIndex).length);

        table.row();


        table.add(answer1_button).expand(true, false);
        table.add(answer2_button).expand(true, false);
        table.add(answer3_button).expand(true, false);
        table.add(answer4_button).expand(true, false);
        table.add(answer5_button).expand(true, false);

        table.row();
        table.row();

        table.add(sRow_answer6_button).expand(true, false);
        table.add(sRow_answer7_button).expand(true, false);
        table.add(sRow_answer8_button).expand(true, false);
        table.add(sRow_answer9_button).expand(true, false);
        table.add(sRow_answer10_button).expand(true, false);

        table.setDebug(true);

        setup_next_question();

    }


    private void next_question(int answer) {
        System.out.println("next question");
        currentAnswer = answer;
        add_answer();

        if (currentQuestionIndex == allQuestions.length - 1) {
            save_answers();
            game.screen_season_end(this);
        } else {
            currentQuestionIndex++;
            setup_next_question();
        }
    }

    private void setup_next_question(){

        String[] current_answers = allAnswers.get(currentQuestionIndex);
        Array<TextButton> current_buttons = new Array<TextButton>();


        int i = 0;
        for (String answer : current_answers){

            TextButton currentButton = answerButtons[i];
            currentButton.setText(answer);
            current_buttons.add(currentButton);

            i ++;
        }


        questionLabel.setText(allQuestions[currentQuestionIndex]);




        table.clearChildren();
        table.add(questionLabel).colspan(allAnswers.get(currentQuestionIndex).length).minHeight(50);

        table.row();//.height(40);

        i = 0;
        for (TextButton button : current_buttons){
            //if (currentQuestionIndex == 7) {
                table.row().height(17);
            //}
            table.add(button).expand(true, false);

            i ++;



        }

    }

    private void add_answer(){

        currentQuestionString = allQuestions[currentQuestionIndex];
        String answer = allAnswers.get(currentQuestionIndex)[currentAnswer - 1];

        answer_package +=   "  int " + "answer_" + currentQuestionIndex + " = " + currentAnswer + ";   //" + currentQuestionString + " : " + answer + "\n";


    }


    private void save_answers(){

        answer_package += "\nQUESTIONAIRE END";

        System.out.println("qu save orderd, qu package: " + answer_package);
        game.file_manager.save_qu_package(answer_package);



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        stage.act();
        game.cam.update();

        Gdx.gl.glClearColor(1, 1f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(game.cam.combined);
        batch.begin();
        stage.draw();
        batch.end();

        //game.cam.zoom += delta;


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
        if (stage != null){
            stage.dispose();
        }

    }



}
