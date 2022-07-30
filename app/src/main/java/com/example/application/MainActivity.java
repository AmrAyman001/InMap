package com.example.application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import android.os.Vibrator;

import java.security.spec.ECField;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements IBaseGpsListner {

   static double[] x = {31.3109567/*1*/,31.3107625/*2*/,31.3103726/*3*/,31.3111443/*4*/,31.3111801/*5*/,31.3112345/*6*/,31.3115381/*7*/,31.3103989/*8*/
           ,31.3108790/*9*/,31.3110626/*10*/,31.3108888/*11*/,31.3112414/*12*/,31.3111363/*13*/,31.3113385/*14*/,31.3113167/*15*/,31.3111334/*16*/
           ,31.3111632/*17*/,31.3112374/*18*/,31.3109174/*19*/,31.3111615/*20*/,31.3111781/*21*/,31.3114869/*22*/,31.3116152/*23*/,31.3115330/*24*/
           ,31.3115106/*25*/,31.3119435/*26*/,31.3116080/*27*/,31.3117097/*28*/,31.3113035/*29*/,31.3114642/*30*/,31.3116542/*31*/,31.3112663/*32*/
           ,31.3106888/*33*/,31.3107158/*34*/,31.3103778/*35*/,31.3101861/*36*/,31.3098117/*37*/,31.3093122/*38*/,31.3093248/*39*/,31.3093637/*40*/
           ,31.3090145/*41*/,31.3088215/*42*/,31.3086187/*43*/,31.3085195/*44*/,31.3086367/*45*/,31.3094946/*46*/,31.3093760/*47*/,31.3096860/*48*/
           ,31.3101028/*49*/,31.3105780/*50*/,31.3096831/*51*/,31.3090094/*52*/,31.3107106/*53*/,31.3124877/*54*/,31.3121646/*55*/,31.3100429/*56*/
           ,31.3088796/*57*/,31.3080194/*58*/,31.3079057/*59*/,31.3105127/*60*/,31.3078853/*61*/,31.3076450/*62*/,31.3073150/*63*/,31.3082520/*64*/
           ,31.3089148/*65*/,31.3088169/*66*/,31.3085485/*67*/,31.3115167/*68*/,31.3117836/*69*/,31.3116378/*70*/,31.3121491/*71*/,31.3124373/*72*/
           ,31.3123900/*73*/,31.3121053/*74*/,31.3113998/*75*/,31.3111360/*76*/,31.3101563/*77*/,31.3104342/*78*/,31.3109398/*79*/,31.3110503/*80*/
           ,31.3101913/*81*/,31.3093056/*82*/,31.3090346/*83*/,31.3087673/*84*/,31.3084605/*85*/,31.3104073/*86*/,31.3107344/*87*/,31.3101056/*88*/
           ,31.3099724/*89*/
   };
// ,31.3091772/*90*/,31.3114121/*91*/,31.3114485/*92*/,31.3108656/*93*/,31.3109446/*94*/,31.3104047/*95*/,31.3109698/*96*/
   static double[] y = {30.0643354/*1*/,30.0645922/*2*/,30.0647317/*3*/,30.0649523/*4*/,30.0653911/*5*/,30.0659604/*6*/,30.0654166/*7*/,30.0667614/*8*/
           ,30.0671584/*9*/,30.0671604/*10*/,30.0672160/*11*/,30.0672898/*12*/,30.0678645/*13*/,30.0676573/*14*/,30.0679661/*15*/,30.0666357/*16*/
           ,30.0664496/*17*/,30.0660308/*18*/,30.0662991/*19*/,30.0664265/*20*/,30.0660617/*21*/,30.0661539/*22*/,30.0665985/*23*/,30.0666481/*24*/
           ,30.0664322/*25*/,30.0663420/*26*/,30.0659417/*27*/,30.0657824/*28*/,30.0661143/*29*/,30.0658474/*30*/,30.0652225/*31*/,30.0647340/*32*/
           ,30.0638127/*33*/,30.0637932/*34*/,30.0643012/*35*/,30.0644624/*36*/,30.0653435/*37*/,30.0657224/*38*/,30.0657097/*39*/,30.0653958/*40*/
           ,30.0649901/*41*/,30.0646053/*42*/,30.0644272/*43*/,30.0643048/*44*/,30.0644430/*45*/,30.0647736/*46*/,30.0647813/*47*/,30.0649204/*48*/
           ,30.0650663/*49*/,30.0639863/*50*/,30.0650512/*51*/,30.0641362/*52*/,30.0653120/*53*/,30.0678920/*54*/,30.0665817/*55*/,30.0623921/*56*/
           ,30.0627150/*57*/,30.0626684/*58*/,30.0625286/*59*/,30.0658984/*60*/,30.0634744/*61*/,30.0637121/*62*/,30.0646914/*63*/,30.0656661/*64*/
           ,30.0659169/*65*/,30.0654076/*66*/,30.0650465/*67*/,30.0675584/*68*/,30.0681823/*69*/,30.0686017/*70*/,30.0688026/*71*/,30.0672814/*72*/
           ,30.0676439/*73*/,30.0676392/*74*/,30.0656550/*75*/,30.0642559/*76*/,30.0649472/*77*/,30.0650414/*78*/,30.0655229/*79*/,30.0657915/*80*/
           ,30.0650622/*81*/,30.0648865/*82*/,30.0644463/*83*/,30.0642683/*84*/,30.0634895/*85*/,30.0627679/*86*/,30.0633942/*87*/,30.0652845/*88*/
           ,30.0652999/*89*/};
// 30.0648051/*90*/,30.0651977/*91*/,30.0651752/*92*/,30.0648125/*93*/,30.0644182/*94*/,30.0646914/*95*/,30.0644282/*96*/
   static String[] z = {"Engineering Building CS"/*1*/,"Engineering Building A"/*2*/,"Building Architecture"/*3*/,"Engineering Building B"/*4*/,"Engineering Building C"/*5*/,"Engineering Building D"/*6*/,"Engineering Building G"/*7*/,"CLINIC Building"/*8*/
   ,"Next To IMO Building A"/*9*/,"Next To IMO Building B"/*10*/,"IMO Building"/*11*/,"Next to IMO and Maritime Building"/*12*/,"Carlette Cafee"/*13*/,"Next To Pharmacy Building"/*14*/,"Pharmacy Building"/*15*/,"In Between planetarium and Integrated simulation assembler"/*16*/
   ,"Planetarium"/*17*/,"In Between Copy Service Building and Planetarium"/*18*/,"In Between Planetarium and Stadium "/*19*/,"Next To Planetarium"/*20*/,"Clinic Street"/*21*/,"Next To Copy service"/*22*/,"Teus Cafee A"/*23*/,"Teus Cafee B"/*24*/,"Next To Teus Cafee"/*25*/
   ,"Bus Station"/*26*/,"Engineering Cafe"/*27*/,"Next To Engineering Cafe A"/*28*/,"Next To Engineering Cafe B"/*29*/,"Cilantro Cafee"/*30*/,"In Between Engineering Building  G Street  and Academy wall"/*31*/,"Parking and starbucks Cafee"/*32*/,"The Parking Of Teaching Assistants Next to Library A"/*33*/
   ,"The Parking Of Teaching Assistants Next to Library B"/*34*/,"In Between Stadium and Engineering Building A"/*35*/,"In Between Stadium and Engineering Building B"/*36*/,"Next To Regional Information Center Building"/*37*/
   ,"Mosque"/*38*/,"Next To Mosque"/*39*/,"Next to Restaurant"/*40*/,"Restaurant"/*41*/,"In Between Restaurant and Bank El kahera"/*42*/,"Next To Bank El kahera A"/*43*/,"Next To Bank El kahera B"/*44*/,"Bank El kahera"/*45*/
   ,"Admission"/*46*/,"Next To Admission QNP A T M"/*47*/,"Next To Admission CIB A T M"/*48*/,"Smart Cafee"/*49*/,"Next To Library"/*50*/,"Next To Network Building"/*51*/,"Next To Presidential Building"/*52*/,"Brazilian Cafee"/*53*/
   ,"Next To Maritime Transport Building"/*54*/,"Next To Industrial Services Complex Building"/*55*/,"In Between GYM Street and Academy wall"/*56*/,"In Between Stadium and parking Gate"/*57*/,"The Main Gate Of Academy  A"/*58*/
   ,"The Main Gate Of Academy  B"/*59*/,"Next To Stadium"/*60*/,"Parking Of lecturers Next to The Main Gate Of Academy"/*61*/,"In Between The Student Housing Building And The Academy Wall"/*62*/,"In Between The Student Housing Building And Plant Nursery"/*63*/
   ,"In Between Parking Of Buses And Laundry Building"/*64*/,"Next To Mosque Street"/*65*/,"Parking of Restaurant Next to  Restaurant"/*66*/,"You are now close to GYM ,Bank El kahera , The Main Store And The Restaurant"/*67*/
   ,"Next To Maritime Transport Institute"/*68*/,"In Between the Maritime Transport Examinations Building and the Pharmacy Building, Next to The Pharmacy Parking"/*69*/,"The Pharmacy Parking In Between The Pharmacy Building And Institute of Preparatory Studies"/*70*/
   ,"Sub Gate Of Academy"/*71*/,"Next To Fisheries Building"/*72*/,"Next To Maritime Transport Building A"/*73*/,"In Between Training center and swimming pools"/*74*/,"Cilantro Cafee A"/*75*/,"In Between Cs Building And the Academy wall"/*76*/
   ,"In Between Smart Cafee and Architecture Building"/*77*/,"In Between Architecture Building And Stadium"/*78*/,"In Between Engineering Building C And Stadium"/*79*/,"In Between Engineering Building D And Stadium"/*80*/,"Smart Cafee"/*81*/
   ,"Next to The Main Gate Of Restaurant"/*82*/,"In Between Restaurant And Presidential Building"/*83*/,"Next to The Bank El kahera Stairs"/*84*/,"In Between Presidential Building And Student Housing Building"/*85*/
   ,"In Between Sport Area And Academy Wall"/*86*/,"Next To Text Book"/*87*/,"In Between Smart Cafee And Stadium"/*88*/,"Smart Cafee"/*89*/};

//,"In Between Engineering Building D and Engineering Building C"/*90*/
//   ,"In Between Engineering Building C and Engineering Building G"/*91*/,"Engineering Student Council"/*92*/,"In Between Engineering Building A and Engineering Building B"/*93*/,"In Between Engineering Building A and Engineering Building CS"/*94*/
//   ,"In Between Engineering Building A and Building Architecture"/*95*/,"Engineering Building CS"/*96*/

    public TextView latitude_textView;
    //public TextView longitude_textView;

    static public TextView Euclidean_textView;
    static public TextView manhattan_textView;
    private static final int PERMISSON_LOCATION = 1000;
    public TextView counter_textView;
    static public TextView voice_textView;
    public TextView DoorNum_textView;

    public ToggleButton toggleButton;

    public Button speak_button;
    String Flag = "Done" ;

    public LottieAnimationView lottieAnimationView;
    public LottieAnimationView lottieAnimationViewaccebt;
    public LottieAnimationView lottieAnimationViewhelp;

    public double latitude,longtitude;
    static public int number = 53;
    String voice_text;
    static String Text;
    LocationManager locationManager;

    private  Handler  myHandler = new Handler();

    TextToSpeech textToSpeech;
    TextToSpeech textToSpeech2;


    //@android.support.annotation.RequiresApi(api = Build.VERSION_CODES.CUPCAKE)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleButton = findViewById(R.id.toggleButton);

        latitude_textView = findViewById(R.id.Latitude);
        Euclidean_textView = findViewById(R.id.Euclidean);
        manhattan_textView = findViewById(R.id.Manhattan);
        voice_textView = findViewById(R.id.Voice);
        speak_button = findViewById(R.id.Speak);

        lottieAnimationView = findViewById(R.id.map);
        lottieAnimationViewaccebt = findViewById(R.id.accebt);
        lottieAnimationViewhelp = findViewById(R.id.help);

        lottieAnimationView.animate().setDuration(500).setStartDelay(8000);
        lottieAnimationView.setRepeatCount(Animation.INFINITE);

        lottieAnimationViewaccebt.animate().setDuration(500).setStartDelay(8000);
        lottieAnimationViewaccebt.setRepeatCount(Animation.INFINITE);

        lottieAnimationViewhelp.animate().setDuration(500).setStartDelay(8000);
        lottieAnimationViewhelp.setRepeatCount(Animation.INFINITE);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }



        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                // if No error is found then only it will run
                if(i!=TextToSpeech.ERROR){
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });



        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textToSpeech.speak("Hello,Can I Help You.Please click on the below Speak button and say yes and Please check if you allow permission to the location. if you don't know how just say the word help", TextToSpeech.QUEUE_FLUSH, null);
            }
        }, 3000);





        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION )
                != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION} , PERMISSON_LOCATION);
            Toast.makeText(getApplicationContext(),"Permisson Not Done", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(getApplicationContext(),"Permisson Done", Toast.LENGTH_SHORT).show();
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                Timer();
            }
        }, 0, 40000);

//toggle button
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    showlocation();
                    textToSpeech.speak("Good Morning",TextToSpeech.QUEUE_FLUSH,null);
                }else{
                    Stop();
                    textToSpeech.speak("Good By",TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });

//        textToSpeech.speak("Can I Help you",TextToSpeech.QUEUE_FLUSH,null);


    }

    public void voiceautomation(View view) {
        Intent voiceauto = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voiceauto.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voiceauto.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault());
        voiceauto.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak..");
        super.startActivityForResult(voiceauto,1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String ric ="RIC is a division of the Arab Academy for Science and Technology founded" +
                " in 2001 and located in Alexandria, Egypt. There has been enormous revolutionary " +
                "changes in the field of human knowledge, especially the field of informatics which is " +
                "supervised by Dr Gamal Mokhtar. This was born from the desire of wanting to promote the talents " +
                "of AAST students and the creation of a broad base of students " +
                "who can catch up with the increasing development of informatics and robotics worldwide";
        if (requestCode == 1 && resultCode == RESULT_OK) {
            voice_textView.setText(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
            voice_text=voice_textView.getText().toString();
            voice_text=voice_text.toLowerCase();
            if (voice_text.equals("get location")) {
                 showlocation();
            }else if(voice_text.equals("stop")){
                Stop();
                textToSpeech.speak("Good By, Sir",TextToSpeech.QUEUE_FLUSH,null);
            }
            else if (voice_text.equals("yes") ||voice_text.equals("repet") ){
                textToSpeech.speak("Hello, my name is Siri and I am glad to meet you. I will be your mentor at the Academy." +
                        " Let me tell you about the application. At the bottom, there is the speaker button. If you want to know where" +
                        " you are, just say get location, and if you want to stop, say the word “stop.” If you want to hear this again," +
                        " say the word Repeat and thank you",TextToSpeech.QUEUE_FLUSH,null);
            }
            else if (voice_text.equals("siri what time is it now") ){
                Calendar calendar =Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh.mm a");
                String data1 = simpleDateFormat.format(calendar.getTime());
                textToSpeech.speak(data1,TextToSpeech.QUEUE_FLUSH,null);
            }
            else if (voice_text.equals("siri what is the date of today") ){
                Calendar calendar =Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE,DDD,MMM,yyyy");
                String data1 = simpleDateFormat.format(calendar.getTime());
                textToSpeech.speak(data1,TextToSpeech.QUEUE_FLUSH,null);
            }
            else if (voice_text.equals("thank you siri") ){

                textToSpeech.speak("You're welcome",TextToSpeech.QUEUE_FLUSH,null);
            }
            else if (voice_text.equals("help") ){

                textToSpeech.speak("open settings then applications and finally check your permission ",TextToSpeech.QUEUE_FLUSH,null);
            }
            else if (voice_text.equals("regional information") ){

                textToSpeech.speak(ric,TextToSpeech.QUEUE_FLUSH,null);
            }
            else if (voice_text.equals("dean college of computing") ){

                textToSpeech.speak("Professor Ayman Adel. his office at room 310",TextToSpeech.QUEUE_FLUSH,null);
            }
            else if (voice_text.equals("head of computer science") ){

                textToSpeech.speak("Professor Mohamed Faroouk, his office at room 209",TextToSpeech.QUEUE_FLUSH,null);
            }
            else {
                textToSpeech.speak("Please clarify your words",TextToSpeech.QUEUE_FLUSH,null);
            }

        }
    }

@SuppressLint("MissingPermission")
    public void showlocation()
    {
        locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            lottieAnimationViewhelp.setVisibility(View.INVISIBLE);
            latitude_textView.setText("...Loading location ");
            lottieAnimationView.setVisibility(View.VISIBLE);
            lottieAnimationViewaccebt.setVisibility(View.INVISIBLE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,0,this);

        }else
        {
            textToSpeech.speak("Please Open The GPS",TextToSpeech.QUEUE_FLUSH,null);
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }

    }
    private void Stop(){
        locationManager.removeUpdates(this);
        Euclidean_textView.setVisibility(View.INVISIBLE);
        latitude_textView.setText("...Stopping location");
        latitude_textView.setVisibility(View.VISIBLE);
        lottieAnimationView.setVisibility(View.INVISIBLE);
        lottieAnimationViewaccebt.setVisibility(View.INVISIBLE);

    }



    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longtitude = location.getLongitude();

        String Voice = GetAddress(latitude,longtitude);
        lottieAnimationViewhelp.setVisibility(View.INVISIBLE);
        latitude_textView.setVisibility(View.INVISIBLE);
        lottieAnimationView.setVisibility(View.INVISIBLE);
        Euclidean_textView.setText(""+Voice);

        Euclidean_textView.setVisibility(View.VISIBLE);
        lottieAnimationViewaccebt.setVisibility(View.VISIBLE);
        textToSpeech.speak(Voice,TextToSpeech.QUEUE_FLUSH,null);

    }

    // Calculate Minimum Adrress
    public static String GetAddress(double Latitude,double Longtitude ){
        double[] minx = new double[x.length];
        double[] miny = new double[y.length];

        for(int i=0;i< 1 ;i++)
        {
            for (int j =0 ;j< x.length;j++){
                double sumxy = Math.sqrt(Math.pow(Latitude-x[j],2)+ Math.pow(Longtitude-y[j],2))*Math.pow(10,5);
                minx[j]=sumxy;
            }
        }

        for(int i=0;i< 1 ;i++) {
            for (int j = 0; j < x.length; j++) {
                double sumxy2 = Math.abs(Latitude - x[j]) + Math.abs( Longtitude - y[j]);
                miny[j] = sumxy2;
            }
        }


        int number_E = minumum(minx);
        String VoiceName = z[number_E];
        Euclidean_textView.setText(""+z[number_E]);
        int number_M = minumum(miny);
        manhattan_textView.setText(""+z[number_M]);

        if(number_E == number_M){
            Text = VoiceName;
        }else if(number_E != number_M){
            Text ="Signal is weak";
        }

        return Text;
    }

    // Calculate Minimum Index
    public static int minumum(double Values[])
    {
        if (Values == null || Values.length == 0)
        {
            System.out.println("Empty Array of Values");
            return -1;
        }

        double minVal = Values[0];
        int minIdx = 0;

        for(int idx=1; idx < Values.length; idx++)
        {
            if(Values[idx] < minVal)
            {
                minVal = Values[idx];
                minIdx = idx;
            }
        }
        return minIdx;
    }



    public void Timer(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Calendar calendar =Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE:hh.mm a");
        String data = simpleDateFormat.format(calendar.getTime());
        //System.out.println(data);

        String[] arrOfStr = data.split(":", -2);

        String DayOfweek = arrOfStr[0];
        String Time = arrOfStr[1];
        switch (DayOfweek){
            case "Sat":
                System.out.println("Day = "+DayOfweek);
                System.out.println("Time = "+Time);
                if(Time.equals("08.20 am") || Time.equals("10.20 am") ||Time.equals("12.20 pm") || Time.equals("04.20 pm")){
                    textToSpeech.speak("Hey, you have a lecture in 10 minutes from now, don't be late",TextToSpeech.QUEUE_FLUSH,null);
                    v.vibrate(4000);
                }else if (Time.equals("8.30 am" ) || Time.equals("10.30 am" ) || Time.equals("12.30 pm")){
                    textToSpeech.speak("Hey, Now is the time to start the lecture",TextToSpeech.QUEUE_FLUSH,null);
                }

                break;
            case "Sun":

                if(Time.equals("08.38 PM") || Time.equals("08.55 PM") ||Time.equals("08.56 PM") || Time.equals("08.58 PM")){
                   // Stop();
                    textToSpeech.speak("Hey, you have a lecture in 10 minutes from now, don't be late",TextToSpeech.QUEUE_FLUSH,null);
                }else if (Time.equals("8.30 am" ) || Time.equals("10.30 am" ) || Time.equals("12.30 pm")){
                    textToSpeech.speak("Hey, Now is the time to start the lecture",TextToSpeech.QUEUE_FLUSH,null);
                }
                break;
        }

    }


    @Override
    public void onProviderDisabled(String location) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extra) {

    }

    @Override
    public void onGpsStatusChanged(int event) {

    }
}