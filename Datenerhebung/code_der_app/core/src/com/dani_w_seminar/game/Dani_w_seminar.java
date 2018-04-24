package com.dani_w_seminar.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.dani_w_seminar.game.screens.Clip_screen;
import com.dani_w_seminar.game.screens.iat_screens.Iat_exp_screen;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.badlogic.gdx.math.MathUtils.random;



public class Dani_w_seminar extends Game{

	private SpriteBatch batch;
	public OrthographicCamera cam;
	public StretchViewport viewport;


	public PlayVideo player;
	public com.dani_w_seminar.game.File_manager file_manager;

	public Dani_w_seminar(PlayVideo playVideo, com.dani_w_seminar.game.File_manager file_manager) {

		player = playVideo;
		this.file_manager = file_manager;

	}

	private enum State{start, showClip, iat, qu, result}
	private float IatState;  // 0 == null, .5, 1.5 ,2 .5, 3.5, 4.5, 5.5, 6.5 = exp_state, 1, 2, 3, 4, 5, 6, 7, p_state
	private State current_state;

    private Iat_exp_screen iat_exp_screen;
    private com.dani_w_seminar.game.screens.iat_screens.Iat_task_screen iat_task_screen;

	public int playtimes;

	public Screen clipScreen;

    private float current_iat_state;


	public float c1_c2_ratio;
	public float iat_result;
	public boolean set_c1_e1 = false;
	public float c1_e1_time;
	public boolean set_c1_e2 = false;
	public float c1_e2_time;

	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		viewport = new StretchViewport(200, 200, cam);

		viewport.setCamera(cam);
		cam.position.set(viewport.getWorldWidth(), viewport.getWorldHeight() / 2, 0);


		batch.setProjectionMatrix(cam.combined);

		iat_task_screen = new com.dani_w_seminar.game.screens.iat_screens.Iat_task_screen(this);
		iat_exp_screen = new com.dani_w_seminar.game.screens.iat_screens.Iat_exp_screen(this);

		file_manager.init(this);


		current_state = State.start;
		current_iat_state = 0;

		random.nextBoolean();
		random.nextBoolean();
		if (random.nextBoolean()){
			playtimes = 1;
		}else{
			playtimes = 3;
		}
		file_manager.save_qu_package("int playtimes = " + playtimes);


		try {
			setScreen(new com.dani_w_seminar.game.screens.Start_screen(this));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			clipScreen = new Clip_screen(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}



		//FirebaseCrash.report(new Exception("My first Android non-fatal error Bitch"));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}

	public void screen_season_end(Screen screen){
		System.out.println("game: screen season end");


		screen.dispose();



		if (current_state == State.start){
			current_state = State.showClip;

			//current_state = State.iat;
			//current_iat_state = 6;

            setScreen(clipScreen);

		}else if (current_state == State.showClip || (current_iat_state < 7.5 && current_state == State.iat)){//maybe you should replace 6 with 7
            current_state = State.iat;
            current_iat_state += .5;
			System.out.println("			current iat state: " + current_state);
            if (current_iat_state % 1 == 0) {   //current_iat_state == p state
				System.out.println("set iat task screen");
				setScreen(iat_task_screen);
			}else {
				System.out.println("set iat exp screen");
				setScreen(iat_exp_screen);
            }

        }else if (current_state == State.iat && current_iat_state == 7.5f){
			current_state = State.qu;
			setScreen(new com.dani_w_seminar.game.screens.Questionnaire_screen(this));
			current_iat_state = 0;
		}else if (current_state == State.qu){
			current_state = State.result;
			file_manager.end_test();
			setScreen(new com.dani_w_seminar.game.screens.Result_screen(this));
		}else if (current_state == State.result){
			current_state = State.start;
			System.out.println("new start screen");

			try {
				setScreen(new com.dani_w_seminar.game.screens.Start_screen(this));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}



		float last_stateTime = 0;
        if (current_state == State.iat)
			last_stateTime = file_manager.state_change(current_state.toString() + current_iat_state);
		else
			last_stateTime = file_manager.state_change(current_state.toString());




		if (set_c1_e1){
			System.out.println("set_c1_e1_time to: " + last_stateTime);
			c1_e1_time = last_stateTime;
			set_c1_e1 = false;
		}else if(set_c1_e2){
			System.out.println("set_c1_e2_time to: " + last_stateTime);
			c1_e2_time = last_stateTime;

			c1_c2_ratio = c1_e1_time/c1_e2_time;
			System.out.println("c1_c2_ratio: " + c1_c2_ratio);


			if (c1_c2_ratio > 1){
				iat_result = -c1_c2_ratio + 1;
			}else{
				iat_result = (1/c1_c2_ratio) - 1;
			}

			file_manager.save_qu_package("\nfloat c1_c2_ratio = " + c1_c2_ratio + ";");
			file_manager.save_qu_package("\nfloat iat_result =  "+ iat_result + ";");



			set_c1_e2 = false;
		}

	}



    public float getCurrent_iat_state() {
        return current_iat_state;
    }

	public SpriteBatch getBatch() {return batch;}
}
