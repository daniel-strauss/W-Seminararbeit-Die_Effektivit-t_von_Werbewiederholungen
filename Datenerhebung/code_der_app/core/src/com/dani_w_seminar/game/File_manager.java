package com.dani_w_seminar.game;


import java.io.IOException;

/**
 * Created by root on 19.07.17.
 */

public interface File_manager {


    public float state_change(String state);

    public void add_to_iat_package(boolean answer_correct, float time_needed, float iat_state);
    public void save_qu_package(String answer);

    public void save_package() throws IOException;

    public void send_data();

    public void init(Dani_w_seminar game);

    //void init(Dani_w_seminar game);

    public void end_test();


    public void load_video();
}
