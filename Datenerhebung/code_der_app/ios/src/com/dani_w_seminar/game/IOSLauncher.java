package com.dani_w_seminar.game;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

import java.io.IOException;

public class IOSLauncher extends IOSApplication.Delegate {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        return new IOSApplication(new Dani_w_seminar(new PlayVideo() {

            public void playVideo(Dani_w_seminar g) {

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
        }), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
}