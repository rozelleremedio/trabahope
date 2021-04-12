package com.example.trabahopeapp.ViewAll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.trabahopeapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class JobsAdapter extends BaseAdapter implements Filterable {

    Context context;
    ArrayList <Jobs> list;
    ArrayList <Jobs> marraylist;
    LayoutInflater inflater;


    public JobsAdapter(Context context, ArrayList<Jobs> list) {
        this.context = context;
        this.list = list;
        this.marraylist= list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHandler handler = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.available_jobs_layout, null);
            handler = new ItemHandler();
            handler.jobTitle = convertView.findViewById(R.id.jobTitle);
            handler.companyName = convertView.findViewById(R.id.companyName);
            handler.jobType = convertView.findViewById(R.id.jobType);
            handler.salary = convertView.findViewById(R.id.salary);
            handler.barangay = convertView.findViewById(R.id.barangay);
            handler.jobPostCreated = convertView.findViewById(R.id.jobPostCreated);

            convertView.setTag(handler);
        } else handler = (ItemHandler) convertView.getTag();
        handler.jobTitle.setText(list.get(position).getJobTitle());
        handler.companyName.setText(list.get(position).getCompanyName());
        handler.jobType.setText(list.get(position).getjobType());
        handler.salary.setText("Est. P" + list.get(position).getSalary() + ".00");
        handler.jobPostCreated.setText(list.get(position).getJobPostCreated());

        String barangayNo = list.get(position).getBarangay();

        switch (barangayNo) {

            case "1":
                handler.barangay.setText("Tisa");
                break;
            case "2":
                handler.barangay.setText("Guadalupe");
                break;
            case "3":
                handler.barangay.setText("Hipodromo");
                break;
            case "4":
                handler.barangay.setText("Punta Princesa");
                break;
            case "5":
                handler.barangay.setText("Zapatera");
                break;

        }


        return convertView;
    }




    static class ItemHandler {
        TextView jobTitle, companyName, jobType, salary, barangay, jobPostCreated;
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    list = marraylist;
                } else {

                    ArrayList<Jobs> filteredList = new ArrayList<>();

                    for (Jobs txt : list) {

                        if (txt.getJobTitle().toString().toLowerCase().contains(charString)|| txt.getCompanyName().toString().toLowerCase().contains(charString)) {

                            filteredList.add(txt);
                        }
                    }

                    list = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (ArrayList<Jobs>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}


