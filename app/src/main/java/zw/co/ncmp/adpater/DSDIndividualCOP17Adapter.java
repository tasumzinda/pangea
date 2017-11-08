package zw.co.ncmp.adpater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import zw.co.ncmp.R;
import zw.co.ncmp.business.DSDIndividual;
import zw.co.ncmp.business.DSDIndividualCOP17;
import zw.co.ncmp.util.AppUtil;
import zw.co.ncmp.util.ViewHolder;

import java.util.ArrayList;

/**
 * Created by User on 3/22/2017.
 */
public class DSDIndividualCOP17Adapter extends ArrayAdapter<DSDIndividualCOP17>{

    private Context context;
    private ArrayList<DSDIndividualCOP17> list;

    public DSDIndividualCOP17Adapter(Context context, ArrayList<DSDIndividualCOP17> list) {
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
        DSDIndividualCOP17 item = list.get(position);
        holder.name.setText((position + 1) + ". " + AppUtil.getStringDate(item.startDate) + " - " + (AppUtil.getStringDate(item.endDate) + " " + item.facility));
        return rowView;
    }

    public void changeDataSet(ArrayList<DSDIndividualCOP17> arrayList) {
        list.clear();
        list.addAll(arrayList);
        notifyDataSetChanged();
    }
}
