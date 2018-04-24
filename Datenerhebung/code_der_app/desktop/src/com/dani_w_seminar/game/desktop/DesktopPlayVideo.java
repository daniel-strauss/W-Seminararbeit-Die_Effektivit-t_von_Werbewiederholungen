package com.dani_w_seminar.game.desktop;

import com.dani_w_seminar.game.Dani_w_seminar;
import com.dani_w_seminar.game.PlayVideo;

/**
 * Created by root on 10.08.17.
 */

public class DesktopPlayVideo implements PlayVideo {

    public DesktopPlayVideo(){
        System.out.println("init play video on desktop");
    }

    @Override
    public void playVideo(Dani_w_seminar game) {

        System.out.println("can't load video on desktop");
        game.clipScreen.resume();
    }
}
