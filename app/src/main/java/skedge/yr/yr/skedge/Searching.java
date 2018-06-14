package skedge.yr.yr.skedge;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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
import java.util.Vector;
import java.util.concurrent.ExecutionException;

public class Searching extends AppCompatActivity {
    public Spinner spinnerD;
    public AutoCompleteTextView coursename;
    public ArrayAdapter<CharSequence> adapterD;
    public String CourseName,day;
    public Button button;
    public String [] courses;
    public String JSON_STRING, jjson_string;
    public String json_complete;
    public JSONArray jsonArray;
    public JSONObject jsonObject;
    public  ArrayAdapter arrayAdapter;
    public StudyClassAdapter studyClassAdapter;
    public ListView lv;
    public int checking=5;
    public TextView DayTextS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.skedge.R.layout.activity_searching);

            DayTextS = (TextView) findViewById(com.example.skedge.R.id.TextDayS);
            spinnerD = (Spinner)findViewById(com.example.skedge.R.id.SpinDay1);
            coursename = (AutoCompleteTextView) findViewById(com.example.skedge.R.id.AutoCom);
            button = (Button)findViewById(com.example.skedge.R.id.WhereButton);
            CourseName="none";
            lv = (ListView) findViewById(com.example.skedge.R.id.listView2);
             Calendar calendar = Calendar.getInstance();
             int day1 = calendar.get(Calendar.DAY_OF_WEEK);
             int currentHour = calendar.get(Calendar.HOUR_OF_DAY);


            Vector<String> arr = new Vector<>() ;
             arr=max("");

            String []arr1 = new String[arr.size()];
            for (int i=0;i<arr.size();i++)
            {
               arr1[i]=arr.get(i);
            }
            arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,arr1);

            adapterD= ArrayAdapter.createFromResource(this, com.example.skedge.R.array.days, com.example.skedge.R.layout.whitetext);
             adapterD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            coursename.setThreshold(1);
            coursename.setAdapter(arrayAdapter);



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
                    DayTextS.setText(day);
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

        coursename.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                CourseName = (String)parent.getItemAtPosition(position);


            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CourseName.equals("none"))
                {
                    CourseName=coursename.getText().toString();
                }
                exp(v);

            }
        });

    }

    public void SearchForMe(View v, String courseName, String day) {


    }

    public Vector<String> max(String course)
    {
        Vector<String> res = new Vector<>();
        BackgroundTask3 myTask = new BackgroundTask3();
        try {
            res = myTask.execute(course).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return res;

    }
    public class BackgroundTask3 extends AsyncTask<String,Void, Vector<String>>
    {

        String complete_url;
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute( Vector<String> result) {
            /*
        String finalValue;
          json_capacity = result;
            finalValue=parseMax();
            */
            super.onPostExecute(result);

        }

        @Override
        protected void onPreExecute() {
            complete_url = "http://www.skedge.co.il/ANAutoComplete.php";
        }

        @Override
        protected  Vector<String> doInBackground(String... params) {
            String name;
            name = params[0];
            Vector<String> finalValue = new Vector<>() ;


            try {
                URL url = new URL (complete_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8");
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
                json_complete = stringBuilder.toString().trim();
                finalValue=parseJSON();
                //Toast.makeText(getApplicationContext(),"finalValue is" + finalValue,Toast.LENGTH_LONG).show();
                return finalValue;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }
    public  Vector<String> parseJSON()
    {
        Vector<String> finalValue = new Vector<>();
        if (json_complete==null)
        {
            Toast.makeText(getApplicationContext(),"first get json",Toast.LENGTH_LONG).show();
        }
        try {
            JSONObject jo;
            jo = new JSONObject(json_complete);
            jsonArray = jo.getJSONArray("server_response");
            int count =0;
            String Cname;
            while (count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                Cname = JO.getString("name");
                finalValue.add(count,Cname);
            //    Toast.makeText(getApplicationContext(),"first get json",Toast.LENGTH_LONG).show();
                count++;
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalValue;
    }

    public void exp(View view)
    {

        BackgroundTask2 backgroundTask = new BackgroundTask2();

        backgroundTask.execute(CourseName,day);




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
            //Toast.makeText(getApplicationContext(),"json "+jjson_string,Toast.LENGTH_LONG).show();
            parseJSON1(jjson_string);
            if (pd != null)
            {
                pd.dismiss();
            }



        }

        @Override
        protected void onPreExecute() {
            param_url = "http://www.skedge.co.il/ANSearchingClass.php";
            pd = new ProgressDialog(Searching.this);
            pd.setMessage("נו מה "+CourseName+" עכשיו?! מכביד...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String name,dayS;
            name= params[0];
            dayS = params[1];
            //Log.d("day",dayS);

            try {
                URL url = new URL (param_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("dayS","UTF-8")+"="+URLEncoder.encode(dayS,"UTF-8")+"&";
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

    public void parseJSON1(String json)
    {

        if (json==null)
        {
            Toast.makeText(getApplicationContext(),"first get json",Toast.LENGTH_LONG).show();
        }
        try {

            JSONObject jo;
            jo = new JSONObject(jjson_string);
            jsonArray = jo.getJSONArray("server_response");
            int count =0;
            String name,build,cls,hourF,hourT,day1;
            studyClassAdapter = new StudyClassAdapter(this, com.example.skedge.R.layout.row_study);
            lv.setAdapter(studyClassAdapter);

            while (count<jsonArray.length())
            {

                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("name");
                build = JO.getString("build");
                cls = JO.getString("cls");
                hourT = JO.getString("hourT");
                hourF = JO.getString("hourF");
                day1 = JO.getString("day1");
                StudyClass studyClass = new StudyClass(name,build,cls,hourF,hourT,day1);
                studyClassAdapter.add(studyClass);
                //Toast.makeText(getApplicationContext(),"count is" + count,Toast.LENGTH_SHORT).show();
                count++;
            }


            //checking=count;
            //Toast.makeText(getApplicationContext(),"checking is" + checking,Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
