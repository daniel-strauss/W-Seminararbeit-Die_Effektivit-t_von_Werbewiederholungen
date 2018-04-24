package com.dani_w_seminar.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.dani_w_seminar.game.Dani_w_seminar;

import java.io.IOException;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new Dani_w_seminar(new com.dani_w_seminar.game.PlayVideo() {
                        @Override
                        public void playVideo(Dani_w_seminar game) {

                        }


                }, new com.dani_w_seminar.game.File_manager() {
                        @Override
                        public float state_change(String state) {
                                return 0;
                        }

                        @Override
                        public void add_to_iat_package(boolean answer_correct, float time_needed, float iat_state) {

                        }

                        @Override
                        public void save_qu_package(String answer) {

                        }

                        @Override
                        public void save_package() throws IOException {

                        }

                        @Override
                        public void send_data() {

                        }

                        @Override
                        public void init(Dani_w_seminar game) {

                        }


                        @Override
                        public void end_test() {

                        }

                        @Override
                        public void load_video() {

                        }
                }
                );
        }
}