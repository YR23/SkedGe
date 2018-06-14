package skedge.yr.yr.skedge;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yardenrotem on 05/04/2017.
 */
public class FreeClassAdapter extends ArrayAdapter {

    public interface BtnClickListener {
        public abstract void onBtnClick(int position,String building,String cls);
    }
    public interface BtnClickListener1 {
        public abstract void onBtnClick(int position,String building,String cls);
    }
    public HashMap<Integer, String> map ;
    private BtnClickListener mClickListener = null;
    private BtnClickListener1 mClickListener1 = null;
    List list = new ArrayList();
    public FreeClass freeClass,freeClass1;
    public String cls,bld;
    public int pos;

    public FreeClassAdapter(Context context, int resource,BtnClickListener listener,BtnClickListener1 listener1) {
        super(context, resource);
        mClickListener=listener;
        mClickListener1=listener1;
        map = new HashMap<Integer, String>();
    }

    public void add(FreeClass object)
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
        String currS,maxS;
        pos=position;
        View row;
        row = convertView;
        final FreeClassHolder freeClassHolder;
        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(com.example.skedge.R.layout.row_layout,parent,false);
            freeClassHolder = new FreeClassHolder();
            freeClassHolder.TxtBuilding = (TextView) row.findViewById(com.example.skedge.R.id.TxtBuilding);
            freeClassHolder.TxtClass = (TextView) row.findViewById(com.example.skedge.R.id.TxtClass);
            freeClassHolder.TxtUntil = (TextView) row.findViewById(com.example.skedge.R.id.TxtTime);
            freeClassHolder.TxtMaxCapacity = (TextView) row.findViewById(com.example.skedge.R.id.TxtCurrent);
            freeClassHolder.free1 = (ImageView)row.findViewById(com.example.skedge.R.id.ImageFree1);
            freeClassHolder.free2 = (ImageView)row.findViewById(com.example.skedge.R.id.ImageFree2);
            freeClassHolder.free3 = (ImageView)row.findViewById(com.example.skedge.R.id.ImageFree3);
            freeClassHolder.free4 = (ImageView)row.findViewById(com.example.skedge.R.id.ImageFree4);
            freeClassHolder.check = (Button)row.findViewById(com.example.skedge.R.id.check);
            freeClassHolder.rep = (Button)row.findViewById(com.example.skedge.R.id.reportB);
            row.setTag(freeClassHolder);
        }

        else
        {
            freeClassHolder = (FreeClassHolder)row.getTag();
        }

        double res,currC,maxC;
        freeClass = (FreeClass) this.getItem(position);
        freeClassHolder.TxtBuilding.setText("בניין "+freeClass.getBuilding());
        freeClassHolder.TxtClass.setText("כיתה "+freeClass.getClassroom());
        freeClassHolder.TxtUntil.setText("פנויה עד "+freeClass.getUntil().substring(0,5));
        freeClassHolder.TxtMaxCapacity.setText(" תפוסה נוכחית "+freeClass.getCur()+"/"+freeClass.getMax());
        currS = freeClass.getCur();
        if (currS.equals(""))
        {
            currS="0";
        }
        currC   = Double.parseDouble(currS);
        maxC   = Double.parseDouble(freeClass.getMax());
        res = currC/maxC;
        cls=freeClass.getClassroom();
        bld=freeClass.getBuilding();
        //Toast.makeText(getContext()," for pos " + pos,Toast.LENGTH_LONG).show();
       // Toast.makeText(getContext()," cls " + cls,Toast.LENGTH_LONG).show();

        Integer intObj = new Integer(pos);
        map.put(intObj,cls);



        if ((res>=0) && (res<=0.25))
        {
            freeClassHolder.free1.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.greenB));
            freeClassHolder.free2.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.greenB));
            freeClassHolder.free3.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.greenB));
            freeClassHolder.free4.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.greenB));
        }
        if ((res>=0.26) && (res<=0.50))
        {
            freeClassHolder.free1.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.none));
            freeClassHolder.free2.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.yellowB));
            freeClassHolder.free3.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.yellowB));
            freeClassHolder.free4.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.yellowB));

        }
        if ((res>=0.51) && (res<=0.75))
        {
            freeClassHolder.free1.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.none));
            freeClassHolder.free2.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.none));
            freeClassHolder.free3.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.orangeB));
            freeClassHolder.free4.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.orangeB));

        }
        if (res>0.75)
        {
            freeClassHolder.free1.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.none));
            freeClassHolder.free2.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.none));
            freeClassHolder.free3.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.none));
            freeClassHolder.free4.setBackgroundColor(ContextCompat.getColor(getContext(), com.example.skedge.R.color.redB));
        }
        freeClassHolder.r=pos;
        freeClassHolder.check.setTag(pos); //For passing the list item index
        freeClassHolder.check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mClickListener != null)
                {
                    int r = freeClassHolder.r;
                    Integer intObj = new Integer(r);
                    String cls1  = (String) map.get(intObj);
                   mClickListener.onBtnClick((Integer) v.getTag(), freeClass.getBuilding(), cls1);
                }
            }
        });
        freeClassHolder.rep.setTag(pos); //For passing the list item index
        freeClassHolder.rep.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mClickListener != null)
                {
                    int r = freeClassHolder.r;
                    Integer intObj = new Integer(r);
                    String cls1  = (String) map.get(intObj);
                    mClickListener1.onBtnClick((Integer) v.getTag(), freeClass.getBuilding(), cls1);
                }
            }
        });
        return row;
    }

    static class FreeClassHolder
    {

        TextView TxtBuilding,TxtClass,TxtUntil,TxtMaxCapacity,TxtCurrCapacity;
        ImageView free1,free2,free3,free4;
        public Button check,rep;
        int r;

    }


}
