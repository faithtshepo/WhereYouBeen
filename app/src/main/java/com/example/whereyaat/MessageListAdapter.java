package com.example.whereyaat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MessageListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Person> personsList;

    public MessageListAdapter(Context context, ArrayList<Person> personsList) {
        this.context = context;
        this.personsList = personsList;
    }

    @Override
    public int getCount() {
        return personsList.size();
    }

    @Override
    public Object getItem(int position) {
        return personsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.disp_messages,parent,false);
        }

        View v = View.inflate(context,R.layout.disp_messages,null);
        final Person p = (Person)this.getItem(position);

        TextView tvName = (TextView)convertView.findViewById(R.id.tvName);
        TextView tvMes = (TextView)convertView.findViewById(R.id.mess2);

        tvName.setText(p.getPersonName());
        tvMes.setText(p.getMessage());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,p.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }
}
