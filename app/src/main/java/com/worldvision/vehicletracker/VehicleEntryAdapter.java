package com.worldvision.vehicletracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mjohanso on 5/25/2016.
 */
public class VehicleEntryAdapter extends ArrayAdapter<VehicleEntry> {
    private final Context context;
    private final ArrayList<VehicleEntry> values;

    public VehicleEntryAdapter(Context context, ArrayList<VehicleEntry> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.vehicle_entry_row_layout, parent, false);
        int total = values.get(position).endMileage - values.get(position).startMileage;

        ((TextView) rowView.findViewById(R.id.firstLine)).setText(Integer.toString(total));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date date = format.parse(values.get(position).dateOfEntry);

            ((TextView) rowView.findViewById(R.id.secondLine)).setText(date.toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rowView;
    }

}
