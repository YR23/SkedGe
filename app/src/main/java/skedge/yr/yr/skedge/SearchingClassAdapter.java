package skedge.yr.yr.skedge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yardenrotem on 05/04/2017.
 */
public class SearchingClassAdapter extends ArrayAdapter {

    public HashMap<Integer, String> map ;
    List list = new ArrayList();
    public SerchingClass serchingClass;


    public SearchingClassAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(SerchingClass object)
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
        final SearchingClassHolder searchingClassHolder;
        if (row == null)
        {

            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(com.example.skedge.R.layout.search_layout,parent,false);
            searchingClassHolder = new SearchingClassHolder();
            searchingClassHolder.TxtBuilding = (TextView) row.findViewById(com.example.skedge.R.id.TxtBuild);
            searchingClassHolder.TxtClass = (TextView) row.findViewById(com.example.skedge.R.id.TxtClasses);
            searchingClassHolder.TxtFrom = (TextView) row.findViewById(com.example.skedge.R.id.TxtZman);
            searchingClassHolder.TxtName = (TextView) row.findViewById(com.example.skedge.R.id.TxtName);
            row.setTag(searchingClassHolder);
        }

        else
        {
            searchingClassHolder = (SearchingClassHolder)row.getTag();
        }


        serchingClass = (SerchingClass) this.getItem(position);
        searchingClassHolder.TxtBuilding.setText("בניין "+serchingClass.getBuilding());
        searchingClassHolder.TxtClass.setText("כיתה "+serchingClass.getClassroom());
        searchingClassHolder.TxtFrom.setText(serchingClass.getFrom().substring(0,5)+" - "+serchingClass.getTo().substring(0,5) );
        searchingClassHolder.TxtName.setText("קורס "+serchingClass.getName());
                //Toast.makeText(getContext()," for pos " + pos,Toast.LENGTH_LONG).show();
        // Toast.makeText(getContext()," cls " + cls,Toast.LENGTH_LONG).show();
        return row;
    }

    static class SearchingClassHolder
    {

        TextView TxtBuilding,TxtClass,TxtFrom,TxtTo,TxtName;




    }


}
