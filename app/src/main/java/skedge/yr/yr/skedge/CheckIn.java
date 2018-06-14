package skedge.yr.yr.skedge;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class CheckIn extends AppCompatActivity {
    public Spinner spinnerH,spinnerD,spinnerB,spinnerM;
    public ArrayAdapter<CharSequence> adapterH,adapterD,adapterB,adapterM;
    public String building,hour,day,many,cls;
    public Button here;
    public TextView HourText,ManyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(skedge.yr.yr.skedge.R.layout.activity_check_in);

        spinnerH = (Spinner)findViewById(skedge.yr.yr.skedge.R.id.SpinHour2);
        spinnerM = (Spinner)findViewById(skedge.yr.yr.skedge.R.id.SpinHowMany2);
        here = (Button) findViewById(skedge.yr.yr.skedge.R.id.CheckButton1);
        Calendar calendar = Calendar.getInstance();
        int day1 = calendar.get(Calendar.DAY_OF_WEEK);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        HourText = (TextView) findViewById(skedge.yr.yr.skedge.R.id.TextHour3);
        ManyText = (TextView) findViewById(skedge.yr.yr.skedge.R.id.TextMany2);

        adapterH= ArrayAdapter.createFromResource(this, skedge.yr.yr.skedge.R.array.hours, skedge.yr.yr.skedge.R.layout.whitetext);
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

        adapterM= ArrayAdapter.createFromResource(this, skedge.yr.yr.skedge.R.array.howmany, skedge.yr.yr.skedge.R.layout.whitetext);
        adapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerM.setAdapter(adapterM);

        spinnerM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                many = parent.getItemAtPosition(position).toString();
                ManyText.setText(many);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            building =(String) b.get("bld");
            cls = (String) b.get("cls");
        }
        here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddContact(v);
            }
        });
    }

    public void AddContact(View view)
    {

        String res="";
        BackgroundTask1 myTask = new BackgroundTask1();
        try {
            res = myTask.execute(building,cls,hour,many).get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(),MainActivity2.class);
        startActivity(i);
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
            add_info_url = "http://www.skedge.co.il/ANcheckin.php";
        }

        @Override
        protected String doInBackground(String... params) {
            String build,clas,hora,howmuch;
            build=params[0];
            clas = params[1];
            hora = params[2];
            howmuch = params[3];
            try {
                URL url = new URL (add_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("building","UTF-8")+"="+URLEncoder.encode(build,"UTF-8")+"&"+
                        URLEncoder.encode("class","UTF-8")+"="+URLEncoder.encode(clas,"UTF-8")+"&"+
                        URLEncoder.encode("hour","UTF-8")+"="+ URLEncoder.encode(hora,"UTF-8")+"&+"+
                        URLEncoder.encode("numofstudent","UTF-8")+"="+ URLEncoder.encode(howmuch,"UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return "ביצעת צ'ק אין, לא להבריז אה?";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }
}
