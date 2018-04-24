package com.dani_w_seminar.game.screens;

import com.badlogic.gdx.Screen;
import com.dani_w_seminar.game.Dani_w_seminar;


import java.io.FileNotFoundException;

/**
 * Created by root on 19.07.17.
 */

public class Clip_screen implements Screen {

    private Dani_w_seminar game;

    //Activity activity;
    //Intent
    com.dani_w_seminar.game.PlayVideo player;

    public Clip_screen(Dani_w_seminar game) throws FileNotFoundException {
        System.out.println("init clip screen");
        this.game = game;




    }

    @Override
    public void show() {
        game.player.playVideo(game);
        pause();
    }

    @Override
    public void render(float delta){
        System.out.println("end clipScreen");
        game.screen_season_end(this);
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

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }



}
