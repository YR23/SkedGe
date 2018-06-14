package skedge.yr.yr.skedge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yardenrotem on 05/04/2017.
 */
public class StudyClassAdapter extends ArrayAdapter {


    List list = new ArrayList();
    public StudyClass studyClass;


    public StudyClassAdapter(Context context, int resource) {
        super(context, resource);

    }

    public void add(StudyClass object)
    {
        super.add(object);
        list.add(object);

    }
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        StudyClassHolder studyClassHolder;
        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(com.example.skedge.R.layout.row_study,parent,false);
            studyClassHolder = new StudyClassHolder();
            studyClassHolder.TxtName = (TextView) row.findViewById(com.example.skedge.R.id.TxtName);
            studyClassHolder.TxtBuild = (TextView) row.findViewById(com.example.skedge.R.id.TxtBuild);
            studyClassHolder.TxtCls = (TextView) row.findViewById(com.example.skedge.R.id.TxtCls);
            studyClassHolder.TxtDay = (TextView) row.findViewById(com.example.skedge.R.id.TxtDay);
            studyClassHolder.TxtHours = (TextView) row.findViewById(com.example.skedge.R.id.TxtHours);
            row.setTag(studyClassHolder);
        }

        else
        {
            studyClassHolder = (StudyClassHolder)row.getTag();
        }


        studyClass = (StudyClass) this.getItem(position);
        studyClassHolder.TxtBuild.setText("בניין "+studyClass.getBuild());
        studyClassHolder.TxtName.setText("הקורס: "+studyClass.getName());
        studyClassHolder.TxtCls.setText("כיתה "+studyClass.getCls());
        studyClassHolder.TxtHours.setText(studyClass.getHourT().substring(0,5)+" - "+studyClass.getHourF().substring(0,5));
        studyClassHolder.TxtDay.setText(studyClass.getDay());
        return row;
    }


    static class StudyClassHolder
    {
        TextView  TxtName,TxtBuild, TxtCls, TxtDay, TxtHours;
    }


}
