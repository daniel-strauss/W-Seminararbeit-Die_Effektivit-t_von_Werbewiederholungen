package com.dani_w_seminar.game.screens.iat_screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.dani_w_seminar.game.File_manager;
import com.dani_w_seminar.game.Dani_w_seminar;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by root on 19.07.17.
 */

public class Iat_task_screen implements Screen{

    private Dani_w_seminar game;

    private SpriteBatch batch;
    private Camera cam;
    private Stage stage;
    private Table table;
    private Skin skin;
    private Label currentStateLabel;

    private String c1;
    private String c2;
    protected static String e1;
    protected static String e2;


    private Label leftUpOrd;
    private Label rightUpOrd;
    private Label leftDownOrd;
    private Label rightDownOrd;

    private Label stimulusLabel;
    private Image stimulusImage;

    private TextButton leftButton;
    private TextButton rightButton;



    private static boolean[] p1;
    private static boolean[] p2;
    private static boolean[] p3;
    private static boolean[] p4;
    private static boolean[] p5;
    private static boolean[] p6;
    private static boolean[] p7;
    private boolean[] current_part;

    private static Array<boolean[]> all_parts;


    /*private Table table;
    private Label upLeft;
    private Label upRight;
    private Label downLeft;
    private Label downRight;
    private Label mid;          //for stimuli
    private Label undeMid;       //for cross when answer is wrong
    private Label step; // e.g. step 2/6
    private Button leftButton;
    private Button rightButton;


    private Button exit;*/



    private final Array<String> verbal_concept1_stimuli = new Array<String>();
    private final Array<String> verbal_concept2_stimuli = new Array<String>();
    private final Array<String> verbal_evaluation1_stimuli = new Array<String>();
    private final Array<String> verbal_evaluation2_stimuli = new Array<String>();

    private final Array<TextureRegion> visual_concept1_stimuli = new Array<TextureRegion>();
    private final Array<TextureRegion> visual_concept2_stimuli = new Array<TextureRegion>();
    private final Array<TextureRegion> visual_evaluation1_stimuli = new Array<TextureRegion>();
    private final Array<TextureRegion> visual_evaluation2_stimuli = new Array<TextureRegion>();



    private Array<String> current_left_verbal_stimuli;
    private Array<String> current_right_verbal_stimuli;

    private Array<TextureRegion> current_left_visual_stimuli;
    private Array<TextureRegion> current_right_visual_stimuli;

    private Image cros;

    boolean using_c;
    boolean c1_left;
    boolean using_e;
    boolean e1_left;
    boolean c_and_e_active;
    boolean saving;


    public static Result_listener result_listener;

    private boolean correct_answer_at_left;

    private int trials_left;

    public Iat_task_screen(Dani_w_seminar game){
        this.game = game;
        cam = game.cam;
        batch = game.getBatch();


        result_listener = new Result_listener();

        trials_left = 0;

        all_parts = new Array<boolean[]>();
        //                 draw c, c1l,  draw e, e1l,  save    40 trials
        p1 = new boolean[]{true,  true, false,  true,  false, false};
        p2 = new boolean[]{false, false, true,  true,  false, false};
        p3 = new boolean[]{true,  true,  true,  true,  false, false};
        p4 = new boolean[]{true,  true,  true,  true,  true,  true };
        p5 = new boolean[]{true, false, false,  true,  false, true };
        p6 = new boolean[]{true, false,  true,  true,  false, false};
        p7 = new boolean[]{true, false,  true,  true,  true,  true };

        all_parts.add(p1);
        all_parts.add(p2);
        all_parts.add(p3);
        all_parts.add(p4);
        all_parts.add(p5);
        all_parts.add(p6);
        all_parts.add(p7);

        cros = new Image(new Texture("white.jpg"));

        load_stimuli();

        init_ui();


    }

    private void  init_ui(){

        stage = new Stage();
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);


        float with = stage.getWidth();


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
        textButtonStyle.down = skin.newDrawable("white", Color.BROWN);
        textButtonStyle.checked = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("white", Color.GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.background = skin.newDrawable("white", Color.BLACK);
        labelStyle.fontColor = Color.WHITE;
        labelStyle.font = skin.getFont("default");
        labelStyle.font.getData().setScale(with/600, with/600);
        skin.add("default", labelStyle);

        labelStyle = new Label.LabelStyle();
        labelStyle.background = skin.newDrawable("white", Color.BLACK);
        labelStyle.fontColor = Color.ORANGE;
        labelStyle.font = skin.getFont("default");
        skin.add("evaluation", labelStyle);

        leftButton = new TextButton("Links", textButtonStyle);
        leftButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                result_listener.stop_messurement();
                //System.out.println("left Tpuch");
                answer_set(true);
                return false;
            }
        });
        rightButton = new TextButton("Rechts", textButtonStyle);
        rightButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                result_listener.stop_messurement();
                //System.out.println("right Tpuch");
                answer_set(false);
                return false;
            }
        });


        currentStateLabel = new Label("currentState", skin);
        leftUpOrd = new Label("leftUpOrd", skin);
        rightUpOrd = new Label("rightDownOrd", skin);
        leftDownOrd = new Label("leftDownOrd", skin.get("evaluation", Label.LabelStyle.class));
        rightDownOrd = new Label("rightDownOrd", skin.get("evaluation", Label.LabelStyle.class));
        stimulusLabel = new Label("stimuus", skin);


        table.add();
        table.add(currentStateLabel).minWidth(stage.getWidth()/2);
        table.add();
        table.row();
        table.add(leftUpOrd).minWidth(stage.getWidth()/4).minHeight(stage.getHeight()/8);
        table.add();
        table.add(rightUpOrd).minWidth(stage.getWidth()/4).minHeight(stage.getHeight()/8);
        table.row();
        table.add(leftDownOrd).minWidth(stage.getWidth()/4).minHeight(stage.getHeight()/8);
        table.add();
        table.add(rightDownOrd).minWidth(stage.getWidth()/4).minHeight(stage.getHeight()/8);
        table.row();
        table.add();
        table.add(stimulusLabel).minWidth(stage.getWidth()/2).minHeight(stage.getHeight()/4);
        table.add();
        table.row();
        table.add(leftButton).minWidth(stage.getWidth()/4).minHeight(stage.getHeight()/4);
        table.add(cros);
        table.add(rightButton).minWidth(stage.getWidth()/4).minHeight(stage.getHeight()/4);


        table.setDebug(true);

    }


    private void load_stimuli(){

        /*//iat pack 1
        c1 = "Luma Delikatessen";
        c2 = "Metzgerei";
        e1 = "Gut;";
        e2 = "Schlecht";



        verbal_concept1_stimuli.addAll(new String[]{
                "Internet", "Bestellung", "Lieferrung", "Internetseite", "Computer",  "Browser", "Online"
        });
        verbal_concept2_stimuli.addAll(new String[]{
            "Geschäft", "Bedienung", "Theke", "Einkaufszettel", "Einzelhandel", "Kasse", "Schaufenster"
        });
        verbal_evaluation1_stimuli.addAll(new String[]{
            "frisch", "delikatesse", "qualität", "zart", "hygenisch", "sicher", "bequem", "köstlich"
        });
        verbal_evaluation2_stimuli.addAll(new String[]{
            "verdorben", "schlecht", "wiederlich", "zäh", "unhygenisch", "unsicher", "warten", "ekelhaft"
        });*/



        //iat pack 2
        c1 = "Luma Delikatessen";
        c2 = "Metzgerei";
        e1 = "Lecker";
        e2 = "Schlecht";
        verbal_concept1_stimuli.addAll(new String[]{
                "Online Metzgerei", "Schockgefrorenes Fleisch", "www.luma-delikatessen.ch", "Abgefahrene Idee", "Schimmelpilz",  "Fleisch aus der ganzen Welt", "Luma Delikatessen"
        });
        verbal_concept2_stimuli.addAll(new String[]{
                "Geschäft", "Bedienung", "Theke", "Frischfleisch", "Einzelhandel", "Kasse", "Schaufenster"
        });
        verbal_evaluation1_stimuli.addAll(new String[]{
                "beste Stücke", "ausgezeichnet", "handverlesen", "perfekt gereift", "beste Fleisch", "genießen", "zart", "köstlich"
        });
        verbal_evaluation2_stimuli.addAll(new String[]{
                "verdorben", "schlecht", "widerlich", "zäh", "unhygienisch", "ungenießbar", "fade", "ekelhaft"
        });


        visual_concept1_stimuli.addAll();
        visual_concept2_stimuli.addAll();
        visual_evaluation1_stimuli.addAll();
        visual_evaluation2_stimuli.addAll();
    }



    @Override
    public void show() {
        current_part = all_parts.get((int)game.getCurrent_iat_state() - 1);

        System.out.println("    iat_task_show, current iat state: " + (int)game.getCurrent_iat_state());

        using_c = current_part[0];
        c1_left = current_part[1];

        using_e = current_part[2];
        e1_left = current_part[3];

        c_and_e_active  = false;
        if (using_c && using_e) c_and_e_active = true;

        if (current_part[4])
            saving = true;
        else
            saving = false;


        if (current_part[5])
            trials_left = 40;
        else
            trials_left = 20;



        setup_stage();
        setup_stimuli();

        next_stimulus();


    }

    private void setup_stimuli(){

        current_left_verbal_stimuli = new Array<String>();
        current_right_verbal_stimuli = new Array<String>();

        current_left_visual_stimuli = new Array<TextureRegion>();
        current_right_visual_stimuli = new Array<TextureRegion>();

        if (using_c){

            if (c1_left){

                current_left_verbal_stimuli.addAll(verbal_concept1_stimuli);
                current_left_visual_stimuli.addAll(visual_concept1_stimuli);
                current_right_verbal_stimuli.addAll(verbal_concept2_stimuli);
                current_right_visual_stimuli.addAll(visual_concept2_stimuli);

            }else{

                current_left_verbal_stimuli.addAll(verbal_concept2_stimuli);
                current_left_visual_stimuli.addAll(visual_concept2_stimuli);
                current_right_verbal_stimuli.addAll(verbal_concept1_stimuli);
                current_right_visual_stimuli.addAll(visual_concept1_stimuli);

            }
        }

        if (using_e){

            if (e1_left){

                current_left_verbal_stimuli.addAll(verbal_evaluation1_stimuli);
                current_left_visual_stimuli.addAll(visual_evaluation1_stimuli);
                current_right_verbal_stimuli.addAll(verbal_evaluation2_stimuli);
                current_right_visual_stimuli.addAll(visual_evaluation2_stimuli);

            }else{

                current_left_verbal_stimuli.addAll(verbal_evaluation2_stimuli);
                current_left_visual_stimuli.addAll(visual_evaluation2_stimuli);
                current_right_verbal_stimuli.addAll(verbal_evaluation1_stimuli);
                current_right_visual_stimuli.addAll(visual_evaluation1_stimuli);

            }
        }


    }

    private void  setup_stage(){
        Gdx.input.setInputProcessor(stage);

        leftUpOrd.setText("");
        rightUpOrd.setText("");
        leftDownOrd.setText("");
        rightDownOrd.setText("");


        // e is above c

        String leftString = "";
        String rightString = "";

        if (c_and_e_active){
            if (c1_left){
                leftUpOrd.setText(c1 + "\noder");
                rightUpOrd.setText(c2 + "oder");
            }else{
                leftUpOrd.setText(c2 + " oder");
                rightUpOrd.setText(c1 + "\noder");
            }


            if (e1_left){
                leftDownOrd.setText(e1);
                rightDownOrd.setText(e2);
            }else{
                leftDownOrd.setText(e2);
                rightDownOrd.setText(e1);
            }



        }else if (using_c) {
            if (c1_left){
                leftUpOrd.setText(c1);
                rightUpOrd.setText(c2);
            }else{
                leftUpOrd.setText(c2);
                rightUpOrd.setText(c1);
            }


        }else if (using_e){
            if (e1_left){
                leftDownOrd.setText(e1);
                rightDownOrd.setText(e2);
            }else{
                leftDownOrd.setText(e2);
                rightDownOrd.setText(c1);
            }
        }





    }



    private void answer_set(boolean left_pressed){
        //System.out.println("answer set");


        boolean answer_correct = check_if_answer_correct(left_pressed);


        if (answer_correct){
            ////System.out.println("    answer correct");
            result_listener.record_correct_answer();
            cros.setDrawable(new SpriteDrawable( new Sprite(new Texture("white.jpg"))));
            next_stimulus();
            trials_left -= 1;
        }else{
            ////System.out.println("    answer incorrect");
            result_listener.record_wrong_answer();
            cros.setDrawable(new SpriteDrawable( new Sprite(new Texture("cross.jpg"))));;
        }



        if(saving)
            package_result();

        //System.out.println("  trials left: " + trials_left);
        if(trials_left < 0) {
            save_package();
            System.out.println("save = " + current_part[4]);
            if (current_part[4]){//if save == true
                if (c1_left) {//if c1_left
                    game.set_c1_e1 = true;
                }else{
                    game.set_c1_e2 = true;
                }


            }
            game.screen_season_end(this);
        }
    }

    private void save_package(){

    }


    private void package_result(){



    }


    private void display_cross(){



    }

    private void  next_stimulus(){
        //System.out.println("next Stimulus");
        boolean nextStimulus_verbal = random.nextBoolean(); //not sur3e weather random.nextBoolean() is the right method

        correct_answer_at_left = random.nextBoolean(); //not sur3e weather random.nextBoolean() is the right method


        //if (nextStimulus_verbal){
        String nextVerbalStimulus;

        if (correct_answer_at_left)
            nextVerbalStimulus = current_left_verbal_stimuli.random();
        else
            nextVerbalStimulus = current_right_verbal_stimuli.random();

        stimulusLabel.setText(nextVerbalStimulus);

        //do gui stuff
        boolean next_stimulus_concept;
        if (verbal_concept1_stimuli.contains(nextVerbalStimulus, false) || verbal_concept2_stimuli.contains(nextVerbalStimulus, false))
            next_stimulus_concept = true;
        else
            next_stimulus_concept = false;





        if ((using_c && !c_and_e_active) || next_stimulus_concept && c_and_e_active)
            stimulusLabel.setStyle(skin.get("default", Label.LabelStyle.class));
        else if ((using_e && !c_and_e_active) || !next_stimulus_concept && c_and_e_active)
            stimulusLabel.setStyle(skin.get("evaluation", Label.LabelStyle.class));



       /* }else{
            TextureRegion nextVisualStimulus;

            if (correct_answer_at_left)
                nextVisualStimulus = current_left_visual_stimuli.random();
            else
                nextVisualStimulus = current_right_visual_stimuli.random();

            //do gui stuff
        }*/



        result_listener.start_messurement();
    }

    private boolean check_if_answer_correct(boolean left_pressed){

        boolean answer_correct;


        if(correct_answer_at_left == left_pressed)
            answer_correct = true;
        else
            answer_correct = false;


        return answer_correct;
    }




    private void  update(float dt){
        //handle_input();


    }


    @Override
    public void render(float delta) {
        update(delta);

        cam.update();

        Gdx.gl.glClearColor(1, 1f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.draw();
        batch.end();
        stage.act();



        if (Gdx.input.isKeyPressed(Input.Keys.N)){
            game.screen_season_end(this);
        }

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

    }



    private class Result_listener{

        private boolean meassuring = false;

        private  boolean last_answer_incorrect;

        private float last_result_time_needed;
        private float start_time;
        private float end_time;

        private boolean last_result_correct_answered;

        private File_manager file_manager;

        public Result_listener(){
            this.file_manager = game.file_manager;
            last_result_time_needed = 0;
        }

        public void time_listener(){



        }

        private void start_messurement(){
            //System.out.println("start_messurement");
            if (meassuring)
                System.out.println("WARNING: meassurement orderd, already measuring");
            else
                meassuring = true;


            start_time = System.nanoTime();

        }

        private void stop_messurement(){
            //System.out.println("stop_messurement");
            if (meassuring)
                meassuring = false;
            else
                //System.out.println("WARNING: meassurement stop order, not a messurement going on");

            end_time = System.nanoTime();
            last_result_time_needed = end_time - start_time;

//            start_time = Float.parseFloat(null);
  //          end_time = Float.parseFloat(null);

        }



        private void record_correct_answer() {
            //System.out.println("record_correct_answer");

            if (!last_answer_incorrect){
                file_manager.add_to_iat_package(true, last_result_time_needed, game.getCurrent_iat_state());
            }else{
                end_time = System.nanoTime();
                last_result_time_needed = end_time - start_time;
                file_manager.add_to_iat_package(false, last_result_time_needed, game.getCurrent_iat_state());
            }

            last_result_time_needed = 0;

            last_answer_incorrect = false;

        }

        private void record_wrong_answer(){
            //System.out.println("record_wrong_answer");

            last_answer_incorrect = true;
        }







        private void get_last_result() {
            if (meassuring)
                System.out.println("WARNING: meassurement going on, you orderd last result. smells like bug, ah?");


        }


    }
}
