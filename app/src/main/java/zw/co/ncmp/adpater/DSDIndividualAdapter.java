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
import zw.co.ncmp.business.DSDIndividual;
import zw.co.ncmp.util.AppUtil;
import zw.co.ncmp.util.ViewHolder;

/**
 * Created by tdhlakama on 10/16/2015.
 */

public class DSDIndividualAdapter extends ArrayAdapter<DSDIndividual> {

    private Context context;
    private ArrayList<DSDIndividual> list;

    public DSDIndividualAdapter(Context context, ArrayList<DSDIndividual> list) {
        super(context, R.layout.item, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_item, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) rowView
                    .findViewById(R.id.txt_name);

            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        DSDIndividual item = list.get(position);
        holder.name.setText((position + 1) + ". " + AppUtil.getStringDate(item.startDate) + " - " + (AppUtil.getStringDate(item.endDate) + " " + item.facility));
        return rowView;
    }

    public void changeDataSet(ArrayList<DSDIndividual> arrayList) {
        list.clear();
        list.addAll(arrayList);
        notifyDataSetChanged();
    }
}

