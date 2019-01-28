package com.example.admin.keystroke_dynamics.Adapter;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.admin.keystroke_dynamics.DTO.ApplicationDatabase;
import com.example.admin.keystroke_dynamics.DTO.Measure.Measure;
import com.example.admin.keystroke_dynamics.DTO.Measure.MeasureWrapper;
import com.example.admin.keystroke_dynamics.DTO.User.User;
import com.example.admin.keystroke_dynamics.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    private List<Measure> measures;
    private List<User> users;

    public ExpandableListAdapter(Context context,
                                 List<String> expandableListTitle,
                                 HashMap<String, List<String>> expandableListDetail,
                                 List<Measure> measures,
                                 List<User> users) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        this.measures = measures;
        this.users = users;
        db = ApplicationDatabase.getDatabase(context);
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_measure, null);
        }
        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expanded_list_measure_textview);
        TextView expandedListTextViewMs = convertView.findViewById(R.id.expanded_list_measure_textview_ms);
        expandedListTextView.setText(expandedListText);
        MeasureWrapper measure = new MeasureWrapper(measures.get(listPosition));
        expandedListTextViewMs.setText(Integer.toString(measure.times.get(expandedListPosition)) + " ms");
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_measure, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.list_measure_textview);
        TextView listTitleTextViewUserName = (TextView) convertView.findViewById(R.id.list_measure_textview_user_name);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        MeasureWrapper measure = new MeasureWrapper(measures.get(listPosition));
        listTitleTextView.setText("ID: " + Integer.toString(measure.id));
        User user = users.get(measure.userId-1);
        listTitleTextViewUserName.setText(user.getUsername());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    ApplicationDatabase db;
}
