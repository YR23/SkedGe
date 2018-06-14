package skedge.yr.yr.skedge;

import android.app.ProgressDialog;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class Micro extends AppCompatActivity {

    String urlAddress="https://nadav123.000webhostapp.com/Aget.php";
    String JSON_STRING;
    String jjson_string;
    String json_capacity;
    String json_max;
    JSONObject jsonObject;
    JSONArray jsonArray,jsonArrayMax;
    FreeClassAdapter freeClassAdapter ;
    ListView lv;


    public Spinner spinnerH,spinnerD,spinnerB;
    public ArrayAdapter<CharSequence> adapterH,adapterD,adapterB;
    public String hour,day,building;
    public String Fbuilding,Fclass,Funtil,Fmax;
    public String Fcur;
    public ProgressDialog dialog;
    public GPSTracker gps;
    public Location curr;
    public TextView HourText,DayText,BuildText;
    public TextView microT;
    public Button button;
    public ImageView MicroImage;
    public String micro=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.skedge.R.layout.activity_micro);
        HourText = (TextView) findViewById(com.example.skedge.R.id.TextHourS);
        BuildText = (TextView) findViewById(com.example.skedge.R.id.TextBuildS);
        spinnerH = (Spinner)findViewById(com.example.skedge.R.id.SpinHour1);
        button = (Button)findViewById(com.example.skedge.R.id.MicroButton);
        spinnerB = (Spinner)findViewById(com.example.skedge.R.id.SpinBuild1);
        microT = (TextView)findViewById(com.example.skedge.R.id.TextMicro2);
        MicroImage = (ImageView)findViewById(com.example.skedge.R.id.ImageMicro);
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);


        adapterH= ArrayAdapter.createFromResource(this, com.example.skedge.R.array.hours, com.example.skedge.R.layout.whitetext);
        adapterH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerH.setAdapter(adapterH);
        switch (currentHour) {
            case 8:
                spinnerH.setSelection(0);
                break;
            case 9:
                spinnerH.setSelection(1);
                break;
            case 10:
                spinnerH.setSelection(2);
                break;
            case 11:
                spinnerH.setSelection(3);
                break;
            case 12:
                spinnerH.setSelection(4);
                break;
            case 13:
                spinnerH.setSelection(5);
                break;
            case 14:
                spinnerH.setSelection(6);
                break;
            case 15:
                spinnerH.setSelection(7);
                break;
            case 16:
                spinnerH.setSelection(8);
                break;
            case 17:
                spinnerH.setSelection(9);
                break;
            case 18:
                spinnerH.setSelection(10);
                break;
            case 19:
                spinnerH.setSelection(11);
                break;
            case 20:
                spinnerH.setSelection(12);
                break;
            case 21:
                spinnerH.setSelection(13);
                break;
            case 22:
                spinnerH.setSelection(13);
                break;
            case 23:
                spinnerH.setSelection(13);
                break;
        }
        spinnerH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hour = parent.getItemAtPosition(position).toString();
                HourText.setText(hour);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        adapterB= ArrayAdapter.createFromResource(this, com.example.skedge.R.array.buildingsMicro, com.example.skedge.R.layout.whitetext);
        adapterB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerB.setAdapter(adapterB);
        spinnerB.setSelection(3);
        spinnerB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                building = parent.getItemAtPosition(position).toString();
                BuildText.setText(building);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String b = building.substring(6, 8);
                String h = hour.substring(0,2);
                microz(v,b,h);

            }
        });






        //exp(new View(this.getApplicationContext()));

    }

    public void microz(View view,String B,String H)
    {
        /*
        String sender=null;
        BackgroundTask2 backgroundTask = new BackgroundTask2();
        backgroundTask.execute(building,hour);
        Toast.makeText(getApplicationContext(), micro, Toast.LENGTH_SHORT).show();
        result(micro);
        */
        String res="";
        BackgroundTask2 myTask = new BackgroundTask2();
        try {
            res = myTask.execute(B,H).get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        result(res);




    }
    class BackgroundTask2 extends AsyncTask<String,Void,String>
    {
        public ProgressDialog pd;
        String param_url;
        String build,chour;
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

           // Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            jjson_string = result;
            micro = parseJSON();
            //Toast.makeText(getApplicationContext(),  micro, Toast.LENGTH_SHORT).show();
            if (pd != null)
            {
                pd.dismiss();
            }



        }

        @Override
        protected void onPreExecute() {
            param_url = "http://www.skedge.co.il/ANMicro.php";
            pd = new ProgressDialog(Micro.this);
            pd.setMessage("אתה רעב אה?");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            build= params[0];
            chour = params[1];

            try {
                URL url = new URL (param_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();


                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("building","UTF-8")+"="+URLEncoder.encode(build,"UTF-8")+"&"+
                        URLEncoder.encode("hour","UTF-8")+"="+URLEncoder.encode(chour,"UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                //here is the new
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine())!= null)
                {
                    stringBuilder.append(JSON_STRING+"\n");
                }
                outputStream.close();
                inputStream.close();
                httpURLConnection.disconnect();
                jjson_string = stringBuilder.toString().trim();
                micro = parseJSON();
                return micro;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }

    public String parseJSON()
    {
        String res=null;
        if (jjson_string==null)
        {
            //   Toast.makeText(getApplicationContext(),"first get json",Toast.LENGTH_SHORT).show();
        }
        try {
            JSONObject jo;
            jo = new JSONObject(jjson_string);
            jsonArray = jo.getJSONArray("server_response");
            int count =0;

            while (count<jsonArray.length())
            {

                JSONObject JO = jsonArray.getJSONObject(count);
                res = JO.getString("tot");
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;

    }

    private void result(String m) {

        //Toast.makeText(getApplicationContext(), micro, Toast.LENGTH_SHORT).show();

        switch (m) {
            case "0":
                setRandom(0);
                break;
            case "1":
                setRandom(1);
                break;
            case "2":
                setRandom(2);
                break;
            case "3":
                setRandom(3);
                break;
            case "4":
                setRandom(4);
                break;
            case "5":
                setRandom(5);
                break;
            case "6":
                setRandom(6);
                break;
            case "7":
                setRandom(7);
                break;
            case "8":
                setRandom(8);
                break;
            case "9":
                setRandom(8);
                break;
            case "10":
                setRandom(8);
                break;
            case "11":
                setRandom(8);
                break;
            case "12":
                setRandom(8);
                break;
            case "13":
                setRandom(8);
                break;
            case "14":
                setRandom(8);
                break;
            case "15":
                setRandom(8);
                break;
        }


    }

   public void setRandom(int x)
   {

       switch (x) {
           case 0:
               MicroImage.setBackgroundResource(com.example.skedge.R.drawable.m0);
               microT.setText("למזלך אין תור למיקרו");
               break;
           case 1:
               MicroImage.setBackgroundResource(com.example.skedge.R.drawable.m1);
               microT.setText("יחס של אדם אחד לכל מיקרו");
               break;
           case 2:
               MicroImage.setBackgroundResource(com.example.skedge.R.drawable.m2);
               microT.setText("יחס של שני אנשים לכל מיקרו");
               break;
           case 3:
               MicroImage.setBackgroundResource(com.example.skedge.R.drawable.m3);
               microT.setText("יחס של 3 אנשים לכל מיקרו");
               break;
           case 4:
               MicroImage.setBackgroundResource(com.example.skedge.R.drawable.m4);
               microT.setText("יחס של 4 אנשים לכל מיקרו");
               break;
           case 5:
               MicroImage.setBackgroundResource(com.example.skedge.R.drawable.m5);
               microT.setText("יחס של 5 אנשים לכל מיקרו! לחוץ!");
               break;
           case 6:
               MicroImage.setBackgroundResource(com.example.skedge.R.drawable.m6);
               microT.setText("יחס של 6 אנשים לכל מיקרו! לחוץ מאוד!");
               break;
           case 7:
               MicroImage.setBackgroundResource(com.example.skedge.R.drawable.m8);
               microT.setText("יחס של 7 אנשים למיקרו, עדיף לחכות");
               break;
           case 8:
               MicroImage.setBackgroundResource(com.example.skedge.R.drawable.m8);
               microT.setText("יחס של מעל 7 אנשים למיקרו, עדיף לעבור בניין");
               break;

       }

   }


}
