package zw.co.ncmp.adpater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import zw.co.ncmp.R;
import zw.co.ncmp.business.CommunityBasedDSD;
import zw.co.ncmp.business.HTSTSTB6;
import zw.co.ncmp.util.AppUtil;
import zw.co.ncmp.util.ViewHolder;

import java.util.ArrayList;

/**
 * @uthor Tasu Muzinda
 */
public class HTSTSTB6Adapter extends ArrayAdapter<HTSTSTB6>{

    private Context context;
    private ArrayList<HTSTSTB6> list;

    public HTSTSTB6Adapter(Context context, ArrayList<HTSTSTB6> list) {
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
        HTSTSTB6 item = list.get(position);
        holder.name.setText((position + 1) + ". " + AppUtil.getStringDate(item.startDate) + " - " + (AppUtil.getStringDate(item.endDate) + " " + item.facility));
        return rowView;
    }

    public void changeDataSet(ArrayList<HTSTSTB6> arrayList) {
        list.clear();
        list.addAll(arrayList);
        notifyDataSetChanged();
    }
}
