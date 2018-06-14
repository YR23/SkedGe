package skedge.yr.yr.skedge;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

//import android.support.v7.widget.SearchView;

public class empty extends Activity {


    String JSON_STRING;
    String jjson_string;
    String json_capacity;
    String json_max;
    JSONObject jsonObject;
    JSONArray jsonArray,jsonArrayMax;
    FreeClassAdapter freeClassAdapter ;
    ListView lv;
    Button button,check;

    public Spinner spinnerH,spinnerD,spinnerB;
    public ArrayAdapter<CharSequence> adapterH,adapterD,adapterB;
    public String hour,day,building;
    public String Fbuilding,Fclass,Funtil,Fmax;
    public String Fcur;
    public ProgressDialog dialog;
    public GPSTracker gps;
    public Location curr;
    public TextView HourText,DayText,BuildText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.skedge.R.layout.activity_empty);
        HourText = (TextView) findViewById(com.example.skedge.R.id.TextviewH);
        DayText = (TextView) findViewById(com.example.skedge.R.id.TextviewD);
        BuildText = (TextView) findViewById(com.example.skedge.R.id.TextviewB);
        lv = (ListView) findViewById(com.example.skedge.R.id.listView);
        spinnerH = (Spinner)findViewById(com.example.skedge.R.id.SpinHour);
        spinnerD = (Spinner)findViewById(com.example.skedge.R.id.SpinDay);
        spinnerB = (Spinner)findViewById(com.example.skedge.R.id.SpinBuild);

        Calendar calendar = Calendar.getInstance();
        int day1 = calendar.get(Calendar.DAY_OF_WEEK);
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

        adapterD= ArrayAdapter.createFromResource(this, com.example.skedge.R.array.days, com.example.skedge.R.layout.whitetext);
        adapterD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerD.setAdapter(adapterD);
        switch (day1) {
            case 1:
                spinnerD.setSelection(0);
                break;
            case 2:
                spinnerD.setSelection(1);
                break;
            case 3:
                spinnerD.setSelection(2);
                break;
            case 4:
                spinnerD.setSelection(3);
                break;
            case 5:
                spinnerD.setSelection(4);
                break;
            case 6:
                spinnerD.setSelection(5);
                break;
            case 7:
                spinnerD.setSelection(6);
                break;
        }
        spinnerD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                day = parent.getItemAtPosition(position).toString();
                DayText.setText(day);
                if (day.equals("יום ראשון"))
                    day="1";
                if (day.equals("יום שני"))
                    day="2";
                if (day.equals("יום שלישי"))
                    day="3";
                if (day.equals("יום רביעי"))
                    day="4";
                if (day.equals("יום חמישי"))
                    day="5";
                if (day.equals("יום שישי"))
                    day="6";
                if (day.equals("יום שבת"))
                    day="7";

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterB= ArrayAdapter.createFromResource(this, com.example.skedge.R.array.buildings, com.example.skedge.R.layout.whitetext);
        adapterB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerB.setAdapter(adapterB);
        spinnerB.setSelection(13);
        spinnerB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                building = parent.getItemAtPosition(position).toString();
                BuildText.setText(building);
                building = building.replace("בניין ","");


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });







        //exp(new View(this.getApplicationContext()));

    }


/*

    public void AddContact(View view)
    {

        BackgroundTask1 backgroundTask1 = new BackgroundTask1();
        backgroundTask1.execute(hour,day,building);
        finish();
    }
    class BackgroundTask1 extends AsyncTask<String,Void,String>
    {
        String add_info_url;
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPreExecute() {
            add_info_url = "https://nadav123.000webhostapp.com/yarden1.php";
        }

        @Override
        protected String doInBackground(String... params) {
            String name,email,mobile;
            name=params[0];
            email = params[1];
            mobile = params[2];
            try {
                URL url = new URL (add_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("mobile","UTF-8")+"="+URLEncoder.encode(mobile,"UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return "one row added";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }

*/
    public void exp(View view)
    {

        BackgroundTask2 backgroundTask = new BackgroundTask2();
        backgroundTask.execute(hour,day,building);


    }
    class BackgroundTask2 extends AsyncTask<String,Void,String>
    {
        public ProgressDialog pd;
        String param_url;
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
           // Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            jjson_string = result;
            parseJSON();


            if (pd != null)
            {
                pd.dismiss();
            }

           // Toast.makeText(getApplicationContext(), jjson_string, Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPreExecute() {
            param_url = "http://www.skedge.co.il/ANsearch.php";

           pd = new ProgressDialog(empty.this);
            pd.setMessage("מוצא לך כיתה, אין אין על בניין "+building);
            pd.show();


        }

        @Override
        protected String doInBackground(String... params) {
            String name,email,mobile;
            name= params[0].substring(0,2);
            email = params[1];
            mobile = params[2];
            try {
                URL url = new URL (param_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("start","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("weekDay","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("building","UTF-8")+"="+URLEncoder.encode(mobile,"UTF-8");
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
                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }

    public String max(String build,String cls)
    {
        String res="";
        BackgroundTask3 myTask = new BackgroundTask3();
        try {
            res = myTask.execute(build,cls).get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        return res;

    }
    public class BackgroundTask3 extends AsyncTask<String,Void,String>
    {

        String capacity_url;
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            /*
        String finalValue;
          json_capacity = result;
            finalValue=parseMax();
            */
            super.onPostExecute(result);

        }

        @Override
        protected void onPreExecute() {
            capacity_url = "http://www.skedge.co.il/ANCurrentCapacity.php";
        }

        @Override
        protected String doInBackground(String... params) {
            String cls,build;
            build= params[0];
            cls = params[1];
            String finalValue;


            try {
                URL url = new URL (capacity_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("Fclass","UTF-8")+"="+URLEncoder.encode(cls,"UTF-8")+"&"+
                        URLEncoder.encode("building","UTF-8")+"="+URLEncoder.encode(build,"UTF-8")+"&"+
                        URLEncoder.encode("hour","UTF-8")+"="+URLEncoder.encode(hour,"UTF-8");
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
                json_capacity = stringBuilder.toString().trim();
                finalValue=parseMax();
                //Toast.makeText(getApplicationContext(),"finalValue is" + finalValue,Toast.LENGTH_SHORT).show();
                return finalValue;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }


    public String maxC(String build,String cls)
    {
        String res="";
        BackgroundTask4 myTask = new BackgroundTask4();
        try {
            res = myTask.execute(build,cls).get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        return res;

    }
    public class BackgroundTask4 extends AsyncTask<String,Void,String>
    {

        String Maxcapacity_url;
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            /*
        String finalValue;
          json_capacity = result;
            finalValue=parseMax();
            */
            super.onPostExecute(result);

        }

        @Override
        protected void onPreExecute() {
            Maxcapacity_url = "http://www.skedge.co.il/ANMaxCapacity.php";
        }

        @Override
        protected String doInBackground(String... params) {
            String cls,build;
            build= params[0];
            cls = params[1];
            String finalValue;


            try {
                URL url = new URL (Maxcapacity_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("Fclass","UTF-8")+"="+URLEncoder.encode(cls,"UTF-8")+"&"+
                        URLEncoder.encode("building","UTF-8")+"="+URLEncoder.encode(build,"UTF-8");
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
                json_max = stringBuilder.toString().trim();
                finalValue=parseMax1();
                //Toast.makeText(getApplicationContext(),"finalValue is" + finalValue,Toast.LENGTH_SHORT).show();
                return finalValue;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }

/*
    public void getJSON(View view)
    {
        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(hour,day,building);

    }

    class BackgroundTask extends AsyncTask<String,Void,String>
    {
        String json_url;



        @Override
        protected void onPreExecute()
        {
           json_url = "https://nadav123.000webhostapp.com/Asearch.php";
        }

        protected String doInBackground(String...  params) {
            String name,email,mobile;
            name=params[0];
            email = params[1];
            mobile = params[2];
            try {
                URL url = new URL (json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine())!= null)
                {
                  stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            jjson_string = result;
            Toast.makeText(getApplicationContext(),jjson_string,Toast.LENGTH_SHORT).show();
        }
    }

*/

   public void parseJSON()
    {

            if (jjson_string==null)
            {
             //   Toast.makeText(getApplicationContext(),"first get json",Toast.LENGTH_SHORT).show();
            }
        try {
            JSONObject jo;
            jo = new JSONObject(jjson_string);
            jsonArray = jo.getJSONArray("server_response");
            int count =0;
            String classing,untilF;
            freeClassAdapter = new FreeClassAdapter(this, com.example.skedge.R.layout.row_layout, new FreeClassAdapter.BtnClickListener() {
                @Override
                public void onBtnClick(int position,String bld,String cls) {
                    Intent i = new Intent(getApplicationContext(),CheckIn.class);
                    i.putExtra("bld", bld);
                    i.putExtra("cls", cls);
                    boolean ans=CheckDistance(bld);
                    if (ans==true) {
                        Calendar calendar = Calendar.getInstance();
                        int day1 = calendar.get(Calendar.DAY_OF_WEEK);
                        String dayW = String.valueOf(day1);

                        if (dayW.equals(day))
                        {
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"אי אפשר לבצע צ'ק אין לתאריך השונה מהיום",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"אח שלי אתה רחוק רצח מבניין "+bld,Toast.LENGTH_SHORT).show();
                        Calendar calendar = Calendar.getInstance();
                        int day1 = calendar.get(Calendar.DAY_OF_WEEK);
                        String dayW = String.valueOf(day1);
                        if (dayW.equals(day))
                        {

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"לא מספיק, אתה גם מנסה לעשות צ'ק אין ליום אחר?!",Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            }, new FreeClassAdapter.BtnClickListener1() {
                @Override
                public void onBtnClick(int position, String building, String cls) {
                    Calendar calendar = Calendar.getInstance();
                    int day1 = calendar.get(Calendar.DAY_OF_WEEK);
                    int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                    openDialog1(building,cls,day1,currentHour);

                }
            });
            lv.setAdapter(freeClassAdapter);
            while (count<jsonArray.length())
            {

                JSONObject JO = jsonArray.getJSONObject(count);
                classing = JO.getString("Fclass");
                untilF = JO.getString("Funtil");
                Fcur=max(building,classing);
                Fmax=maxC(building,classing);
                FreeClass freeClass = new FreeClass(building,classing,untilF,Fmax,Fcur);
                freeClassAdapter.add(freeClass);
                count++;


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void openDialog1(final String building, final String cls, final int day1, final int currentHour) {
        final Dialog dialog = new Dialog(empty.this);
        dialog.setContentView(com.example.skedge.R.layout.dialogbrand_layout);
        final EditText textViewUser = (EditText) dialog.findViewById(com.example.skedge.R.id.NoteText);
        Button report = (Button) dialog.findViewById(com.example.skedge.R.id.RepButton);
        dialog.show();
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportEmail(building,cls,day1,currentHour,textViewUser.getText().toString());
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "התקלה התקבלה והנושא יבדק", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void reportEmail(String b,String c,int d,int h,String n)
    {
        String da = String.valueOf(d);
        String ho = String.valueOf(h);
        BackgroundTask5 myTask = new BackgroundTask5();
        myTask.execute(b,c,da,ho,n);


    }
    class BackgroundTask5 extends AsyncTask<String,Void,String>
    {
        String add_info_url;
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPreExecute() {
            add_info_url = "http://www.skedge.co.il/ANReport.php";
        }

        @Override
        protected String doInBackground(String... params) {
            String build,clas,dia,hora,note;
            build=params[0];
            clas = params[1];
            dia = params[2];
            hora = params[3];
            note = params[4];
            try {
                URL url = new URL (add_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("building","UTF-8")+"="+URLEncoder.encode(build,"UTF-8")+"&"+
                        URLEncoder.encode("class","UTF-8")+"="+URLEncoder.encode(clas,"UTF-8")+"&"+
                        URLEncoder.encode("day","UTF-8")+"="+ URLEncoder.encode(dia,"UTF-8")+"&+"+
                        URLEncoder.encode("hour","UTF-8")+"="+ URLEncoder.encode(hora,"UTF-8")+"&+"+
                        URLEncoder.encode("note","UTF-8")+"="+ URLEncoder.encode(note,"UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }

    private boolean CheckDistance(String bld) {

        gps = new GPSTracker(empty.this);
        float distance3 = 0;
        String strAmount = null;


            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            curr = new Location("current");
            curr.setLatitude(latitude);
            curr.setLongitude(longitude);


            Location location1 = new Location("locationA");
            double latbld, lngbld;
            double[] cor = new double[2];
            cor = SetMyCur(bld);
            location1.setLatitude(cor[0]);
            location1.setLongitude(cor[1]);
            distance3 = curr.distanceTo(location1);
            distance3 = (float) (distance3 + distance3 * 0.15);
            strAmount = String.valueOf(distance3);


       // Toast.makeText(getApplicationContext(),"המרחק: "+strAmount,Toast.LENGTH_SHORT).show();
        if (distance3 < 750) {
            return true;
        }
        else {
            return false;
        }
    }

    private double[] SetMyCur(String bld) {
        double [] result = new double[2];
        switch (bld) {
            case "90":
                result[0] = 31.264918;
                result[1] = 34.802905;
                break;
            case "96":
                result[0] = 31.264968;
                result[1] = 34.802044;
                break;
            case "92":
                result[0] = 31.264390;
                result[1] = 34.803337;
                break;
            case "97":
                result[0] = 31.264729;
                result[1] = 34.801481;
                break;
            case "98":
                result[0] = 31.264397;
                result[1] = 34.801479;
                break;
            case "95":
                result[0] = 31.264397;
                result[1] = 34.801479;
                break;
            case "34":
                result[0] = 31.262117;
                result[1] = 34.803933;
                break;
            case "35":
                result[0] = 31.261751;
                result[1] = 34.804096;
                break;
            case "32":
                result[0] = 31.261767;
                result[1] = 34.803476;
                break;
            case "28":
                result[0] = 31.261762;
                result[1] = 34.802803;
                break;
            case "30":
                result[0] = 31.262012;
                result[1] = 34.803120;
                break;
            case "29":
                result[0] = 31.262338;
                result[1] = 34.802798;
                break;
            case "33":
                result[0] = 31.262322;
                result[1] = 34.803471;
                break;
            case "37":
                result[0] = 31.262311;
                result[1] = 34.804120;
                break;
            case "26":
                result[0] = 31.262008;
                result[1] = 34.802395;
                break;
            case "42":
                result[0] = 31.262087;
                result[1] = 34.805580;
                break;
            case "40":
                result[0] = 31.261473;
                result[1] = 34.804719;
                break;
            case "39":
                result[0] = 31.261216;
                result[1] = 34.804708;
                break;
            case "38":
                result[0] = 31.261372;
                result[1] = 34.804172;
                break;
            case "51":
                result[0] = 31.261372;
                result[1] = 34.804172;
                break;
            case "54":
                result[0] = 31.262682;
                result[1] = 34.805051;
                break;
            case "55":
                result[0] = 31.263416;
                result[1] = 34.804869;
                break;
            case "56":
                result[0] = 31.263003;
                result[1] = 34.804526;
                break;
            case "57":
                result[0] = 31.263590;
                result[1] = 34.804526;
                break;
            case "62":
                result[0] = 31.262655;
                result[1] = 34.803389;
                break;
            case "58":
                result[0] = 31.262664;
                result[1] = 34.804043;
                break;
            case "59":
                result[0] = 31.263040;
                result[1] = 34.803979;
                break;
            case "63":
                result[0] = 31.262985;
                result[1] = 34.803400;
                break;
            case "60":
                result[0] = 31.263499;
                result[1] = 34.804108;
                break;
            case "61":
                result[0] = 31.263618;
                result[1] = 34.803861;
                break;
            case "65":
                result[0] = 31.263636;
                result[1] = 34.803432;
                break;
            case "64":
                result[0] = 31.263343;
                result[1] = 34.803432;
                break;
            case "66":
                result[0] = 31.263664;
                result[1] = 34.802863;
                break;
            case "67":
                result[0] = 31.263169;
                result[1] = 34.802809;
                break;
            case "70":
                result[0] = 31.263086;
                result[1] = 34.801961;
                break;
            case "71":
                result[0] = 31.263113;
                result[1] = 34.801264;
                break;
            case "72":
                result[0] = 31.262764;
                result[1] = 34.800803;
                break;
            case "74":
                result[0] = 31.262718;
                result[1] = 34.799537;
                break;
            case "18":
                result[0] = 31.262259;
                result[1] = 34.799644;
                break;
            case "17":
                result[0] = 31.261910;
                result[1] = 34.799717;
                break;
            case "16":
                result[0] = 31.261635;
                result[1] = 34.799814;
                break;
            case "15":
                result[0] = 31.261406;
                result[1] = 34.799825;
                break;
            case "14":
                result[0] = 31.261562;
                result[1] = 34.799417;
                break;
            case "M9":
                result[0] = 31.260231;
                result[1] = 34.803538;
                break;
            case "M5":
                result[0] = 31.259997;
                result[1] = 34.804080;
                break;
            case "M10":
                result[0] = 31.260070;
                result[1] = 34.804445;
                break;
            case "M6":
                result[0] = 31.260203;
                result[1] = 34.803796;
                break;
            case "M7":
                result[0] = 31.260386;
                result[1] = 34.804273;
                break;
            case "M8":
                result[0] = 31.260501;
                result[1] = 34.803838;
                break;

        }

        return result;

    }

    public String parseMax()
    {

        if (json_capacity==null)
        {
            Toast.makeText(getApplicationContext(),"first get json",Toast.LENGTH_SHORT).show();
        }
        try {

            JSONObject jo;
            jo = new JSONObject(json_capacity);
            jsonArrayMax = jo.getJSONArray("server_response");

                JSONObject JO = jsonArrayMax.getJSONObject(0);

                Fcur = JO.getString("Fcur");
                if (Fcur.equals("null")) {
                    Fcur = "0";
                }

                return Fcur;




        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String parseMax1()
    {

        if (json_max==null)
        {
            Toast.makeText(getApplicationContext(),"first get json",Toast.LENGTH_SHORT).show();
        }
        try {

            JSONObject jo;
            jo = new JSONObject(json_max);
            JSONArray jsonArrayMax1 = jo.getJSONArray("server_response");

            JSONObject JO = jsonArrayMax1.getJSONObject(0);

            Fmax = JO.getString("Fmax");
            if (Fmax.equals("null")) {
                Fmax = "0";
            }

            return Fmax;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "0";
    }
}
