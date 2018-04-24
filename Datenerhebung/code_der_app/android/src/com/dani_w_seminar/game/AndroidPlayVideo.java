package com.dani_w_seminar.game;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.google.firebase.crash.FirebaseCrash;

import java.io.File;

/**
 * Created by root on 09.08.17.
 */

public class AndroidPlayVideo implements PlayVideo {

    Activity mActivity;
    private int playsLeft;
    private com.dani_w_seminar.game.Dani_w_seminar game;

    public AndroidPlayVideo(Activity activity){

        System.out.println("init Android play video");

        mActivity = activity;
    }

    public void playVideo(com.dani_w_seminar.game.Dani_w_seminar game) {
        this.game = game;


        //mActivity.startActivity(intent);
        //playsLeft = game.playtimes;

        //File file = new File("/storage/emulated/0/Movies/video.mp4");


        //MediaPlayer mediaPlayer = MediaPlayer.create(mActivity.getApplicationContext(), R.raw.video);
        //mediaPlayer.start(); // no need to call prepare(); create() does that for you



        /*String fileName = "android.resource://"+  mActivity.getPackageName() + "/raw/video.mp4";
        VideoView vv = (VideoView) mActivity.findViewById(R.id.surface);
        vv.setVideoURI(Uri.parse(fileName));
        vv.start();*/


/*        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES) + "/iat/video.mp4");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "video/mp4");
*/
        System.out.println("send firebaseLog");
        FirebaseCrash.log("start Loading video");
        FirebaseCrash.report(new Exception("ooopsi"));
        if (game.playtimes == 1) {
            playsLeft -= 1;

            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS) + "/video.mp4");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "video/mp4");



            mActivity.startActivity(intent);
        } else {
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS) + "/three_videos.mp4");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "video/mp4");

            mActivity.startActivity(intent);

        }



    }
}
