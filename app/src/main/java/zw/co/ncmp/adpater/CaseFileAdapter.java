package zw.co.ncmp.adpater;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import zw.co.ncmp.R;
import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.util.AppUtil;
import zw.co.ncmp.util.ViewHolder;

/**
 * Created by tdhlakama on 10/16/2015.
 */

public class CaseFileAdapter extends ArrayAdapter<CaseFile> {

    private Context context;
    private ArrayList<CaseFile> list;

    public CaseFileAdapter(Context context, ArrayList<CaseFile> list) {
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
        CaseFile item = list.get(position);
        holder.name.setText((position + 1) + " . " + item.facility + " : " + AppUtil.getStringDate(item.dateCreated));
        holder.more.setText("View Detail");

        holder.more.setTypeface(null, Typeface.BOLD_ITALIC);
        return rowView;
    }

    public void changeDataSet(ArrayList<CaseFile> arrayList) {
        list.clear();
        list.addAll(arrayList);
        notifyDataSetChanged();
    }

}

