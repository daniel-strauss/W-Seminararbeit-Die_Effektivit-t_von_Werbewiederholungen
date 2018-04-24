package com.dani_w_seminar.game;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.firebase.crash.FirebaseCrash;
import com.dani_w_seminar.game.Dani_w_seminar;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
//import com.sendgrid.SendGrid;
//import com.sendgrid.SendGridException;
/*import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;


/**
 * Created by root on 08.09.17.
 */

public class Android_File_Manager implements File_manager{


    private String currentState;
    private String currentPackage;

    private String all;

    private float stateStartTime;
    private float stateEndTime;

    private int question_id;

    private com.dani_w_seminar.game.Dani_w_seminar game;

    private Activity mActivity;

    public Android_File_Manager(Activity activity){
        mActivity = activity;
    }

    @Override
    public void init(Dani_w_seminar game){

        this.game = game;

        //get last test id
        int current_test_id = 0; // = last_test_id + 1
        Date currentDate = new Date();

        currentPackage = "NEW TEST\n";
        currentPackage += "    TEST_ID: " + current_test_id + "\n";
        currentPackage += "    DATE: " + currentDate.toString();

        all = "";

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
            stateStartTime = System.nanoTime() * (float)Math.pow(10, -9);
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void save_package() {

        System.out.println("current package: " + currentPackage);

        FirebaseCrash.log("save package");
        FirebaseCrash.log("external storage available: " + Environment.getExternalStorageState());
        FirebaseCrash.log("package to save: " + currentPackage);
        System.out.println("check_external_storage available: ");
        boolean externalStorageWritable;
        // Checks if external storage is available for read and write
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            externalStorageWritable = true;
        }
        else {
            externalStorageWritable = false;
            System.out.println("WARNING: externalStorageWritable = false !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }



        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "iat_test.txt");
        if (!file.mkdirs()) {
            System.out.println( "Directory not created");
        }




        all += currentPackage;
        FirebaseCrash.log("create file handle");
        FileHandle handle = Gdx.files.external(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS) + "/iat_test.txt");
        FirebaseCrash.log("filhandle write");
        System.out.println("external storage available: " +  Gdx.files.isExternalStorageAvailable());
        handle.writeString(currentPackage, true);




    }

    @Override
    public void save_qu_package(String answerPackage) {
        all += "\n" + answerPackage;
        FileHandle handle = Gdx.files.external(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS) + "/iat_test.txt");
        handle.writeString(answerPackage, true);
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

       /* Email from = new Email("test@example.com");
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
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        */


        //this one works but you cant use firebase
        System.out.println("sending http request");
        String urlS = "https://api.sendgrid.com/api/mail.send.json";


        String request = "api_user=strammer_max" +
                "&api_key=xkdeJHfjghJHGF343" + //SeWcdtwJSiydycYu12zD-Q.AnO_ED8_VZZIgM_z1Ze-aiAH1kWn73SPmPTTjhCwMto" +
                "&to=dinoham223@gmail.com" +
                "&toname=Daniel" +
                "&subject=IAT_Test__Android" +
                "&text=" + all +
                "&from=info@domain.com";

        String urlSting = urlS + request;

        System.out.println("urlString: " + urlSting);

        String version = "";
        try {
            PackageInfo pInfo = mActivity.getPackageManager().getPackageInfo(mActivity.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }




        try {
            SendGrid sendgrid = new SendGrid("strammer_max", "xkdeJHfjghJHGF343");

            SendGrid.Email email = new SendGrid.Email();

            // Get values from edit text to compose email
            // TODO: Validate edit texts
            email.addTo("dinoham223@gmail.com");
            email.setFrom("info@domain.com");
            email.setSubject("IAT_V-" + version);
            email.setText(all);


            // Attach image
            //if (mUri != null) {
            //    email.addAttachment(mAttachmentName, mAppContext.getContentResolver().openInputStream(mUri));
            //}

            // Send email, execute http request
            SendGrid.Response response = sendgrid.send(email);
            String mMsgResponse = response.getMessage();

            Log.d("SendAppExample", mMsgResponse);

        } catch (SendGridException e) {
            Log.e("SendAppExample", e.toString());
        }

        FirebaseCrash.log(all);
        FirebaseCrash.report(new Exception(all));



        /*
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;



        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are available at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            URL url = new URL(urlS);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                forecastJsonStr = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                forecastJsonStr = null;
            }
            forecastJsonStr = buffer.toString();
            System.out.println("forecast_json_string");
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            forecastJsonStr = null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }


        /*HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(urlSting).build();
        Net.HttpResponseListener httpResponseListener = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                System.out.println("http response: " + httpResponse);
            }

            @Override
            public void failed(Throwable t) {
                System.out.println("http request failed: " + t);
            }

            @Override
            public void cancelled() {
                System.out.println("http cancelled");

            }
        };
        Gdx.net.sendHttpRequest(httpRequest, httpResponseListener);


       // SendGrid sendgrid = new SendGrid(




        char[] all_converter = all.toCharArray();
        String convertetAll = "";
        for (char ch : all_converter){
            if (ch == '\n'){
                ch = '_';
            }else if (ch == ' '){
                ch = '-';
            }
            convertetAll += ch;
        }



        try {

            String url = urlS;
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent","Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = convertetAll;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        }catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }

    @Override
    public void load_video(){
        FirebaseCrash.log("load video");
        FirebaseCrash.log("external storage available: " + Environment.getExternalStorageState());


        /*File afile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MOVIES));
        if (!afile.mkdirs()) {
            System.out.println("Directory not created");
            FirebaseCrash.log("video directory not created");
        }*/
        //System.out.println("movie_path: " + Environment.getExternalStoragePublicDirectory(
        //        Environment.DIRECTORY_MOVIES) + "/video.mp4");

        if (game.playtimes == 1) {

            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS) + "/video.mp4");

            InputStream in = mActivity.getResources().openRawResource(R.raw.video);
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS) + "/video.mp4");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] buff = new byte[1024];
            int read = 0;

            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }else if (game.playtimes == 3) {


            System.out.println("load three videos");
            InputStream in = mActivity.getResources().openRawResource(R.raw.three_videos);
            OutputStream out = null;

            try {
                out = new FileOutputStream(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS) + "/three_videos.mp4");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] buff = new byte[1024];
            int read = 0;

            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }




    }

}


