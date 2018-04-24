package com.dani_w_seminar.game.desktop;

import com.dani_w_seminar.game.Dani_w_seminar;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by root on 08.09.17.
 */

public class Desktop_File_Manager implements com.dani_w_seminar.game.File_manager {


    private String currentState;
    private String currentPackage;

    private float stateStartTime;
    private float stateEndTime;

    private int question_id;

    private String all;

    @Override
    public void init(Dani_w_seminar game){
        //get last test id
        int current_test_id = 0; // = last_test_id + 1
        Date currentDate = new Date();
        all = "";
        currentPackage = "";
        currentPackage += "\n//NEW TEST";
        currentPackage += "float test_id = " + current_test_id + "   //TEST_ID: " + current_test_id + "\n";
        currentPackage += "Date date = " + currentDate + "    //DATE: " + currentDate.toString();

        save_package();
    }

    @Override
    public void end_test() {
        currentPackage += "\nTEST END";
        save_package();
        send_data();

    }

    @Override
    public float state_change(String state) {
        float time_needed_for_state = 0;

        if (stateStartTime == 0){
            stateStartTime = System.nanoTime() * (float)Math.pow(10, -9) * (float)Math.pow(10, -9);
        }else{
            stateEndTime = System.nanoTime() * (float)Math.pow(10, -9);
            time_needed_for_state = stateEndTime - stateStartTime;
            currentPackage += "    time needed for state: " + time_needed_for_state + "\n";
            currentPackage += "STATE END";
            save_package();
            stateStartTime = System.nanoTime() * (float)Math.pow(10, -9);
        }


        question_id = 0;
        currentPackage = "";
        currentPackage += "\n\nNEW STATE: " + state  + "\n";

        return time_needed_for_state;

    }

    @Override
    public void add_to_iat_package(boolean answer_correct, float time_needed, float iat_state) {
        //System.out.println("ADD TO IAT PACKAGE: \n    answer_correct: " + answer_correct + "\n    time_needed: " + time_needed);
        if (question_id == 0){
            currentPackage += "ArrayMap<Float, Float> map" + iat_state + "= new ArrayMap(40);";
        }


        String l1 = "";
        String l2 = "";
        Float answer_correct_float = 0f;
        if (question_id < 9) {
            l1 = " ";
        }
        if (answer_correct) {
            l2 = " ";
            answer_correct_float = 1f;
        }



        //ArrayMap<Float, Float> map = new ArrayMap(40);

        currentPackage += "      map" + Math.round(iat_state) + ".put(" + answer_correct_float + ", " + time_needed + ");";
        currentPackage += "//    " + question_id + l1 + "  |  " + "answer_correct: " + answer_correct + l2 + "  |  time_needed: " + time_needed +"\n";

        question_id ++;
    }

    @Override
    public void save_package(){

        System.out.println("current package: " + currentPackage);

        all += currentPackage;
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/home/daniel/Documents/Schule/W_Seminar/test_results.txt", true)));
            out.println(currentPackage);
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }


    }

    @Override
    public void save_qu_package(String answerPackage) {
        all += "\n" + answerPackage;
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/home/daniel/Documents/Schule/W_Seminar/test_results.txt", true)));
            out.println(answerPackage);
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    @Override
    public void  load_video(){
        System.out.println("load video, \n    not ;)");
    }


    @Override
    public void send_data(){

  /*
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"dinoham223@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "IAT Test");
        i.putExtra(Intent.EXTRA_TEXT   , all);
        try {
            mActivity.startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(mActivity, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
*/

        Email from = new Email("test@example.com");
        String subject = "IAT Test";
        Email to = new Email("dinoham223@gmail.com");
        Content content = new Content("text/plain", all);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            System.out.println("mail: " + request.getBody());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
        }





    }


}
