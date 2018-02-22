package zw.co.ncmp.adpater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import zw.co.ncmp.R;
import zw.co.ncmp.business.HTSEligibilityScreeningForm;
import zw.co.ncmp.util.ViewHolder;

import java.util.ArrayList;

/**
 * @uthor Tasu Muzinda
 */
public class HTSEligibilityScreeningFormAdapter extends ArrayAdapter<HTSEligibilityScreeningForm>{

    private Context context;
    private ArrayList<HTSEligibilityScreeningForm> list;

    public HTSEligibilityScreeningFormAdapter(Context context, ArrayList<HTSEligibilityScreeningForm> list) {
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
        HTSEligibilityScreeningForm item = list.get(position);
        holder.name.setText((position + 1) + ". " + item.firstName + " " + item.lastName + " " + item.facility);
        return rowView;
    }

    public void changeDataSet(ArrayList<HTSEligibilityScreeningForm> arrayList) {
        list.clear();
        list.addAll(arrayList);
        notifyDataSetChanged();
    }
}
