package zw.co.ncmp.adpater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import zw.co.ncmp.R;
import zw.co.ncmp.business.Mentee;
import zw.co.ncmp.util.AppUtil;
import zw.co.ncmp.util.ViewHolder;
/**
 * Created by tdhlakama on 10/16/2015.
 */

public class MenteeAdapter extends ArrayAdapter<Mentee>  {

    private Context context;
    private ArrayList<Mentee> list;

    public MenteeAdapter(Context context, ArrayList<Mentee> list) {
        super(context, R.layout.item, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.item, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) rowView
                    .findViewById(R.id.txt_name);
            viewHolder.more = (TextView) rowView
                    .findViewById(R.id.txt_more);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        Mentee item = list.get(position);
        holder.name.setText((position + 1) + " . " + item);
        holder.more.setText("Date of Birth: " + AppUtil.getStringDate(item.dateOfBirth));
        return rowView;
    }

    public void changeDataSet(ArrayList<Mentee> arrayList) {
        list.clear();
        list.addAll(arrayList);
        notifyDataSetChanged();
    }

}

