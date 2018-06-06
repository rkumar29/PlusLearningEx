package pluslearning.bailiwick.rajesh.com.pluslearning.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;

/**
 * Created by Rajesh on 2/15/2018.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> dataHeader;
    private HashMap<String,List<String>> dataChild;

    public ExpandableListAdapter(Context context, List<String> dataHeader, HashMap<String, List<String>> dataChild) {
        this.context = context;
        this.dataHeader = dataHeader;
        this.dataChild = dataChild;
    }

    @Override
    public int getGroupCount() {
        return dataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.dataChild.get(this.dataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.dataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.dataChild.get(this.dataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_header_ques,parent,false);
        }
        TextView header = (TextView)convertView.findViewById(R.id.tv_questions);
        header.setText(headerTitle);

        if (isExpanded){
            header.setBackground(context.getResources().getDrawable(R.drawable.border_selected_header));
            header.setTextColor(context.getResources().getColor(R.color.white));
          //  convertView.setBackgroundColor(context.getResources().getColor(R.color.sea_green,null));
        }else {
            header.setBackground(context.getResources().getDrawable(R.drawable.border_grey));
            header.setTextColor(context.getResources().getColor(R.color.black));
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childText = (String) getChild(groupPosition,childPosition);
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_subheader,parent,false);
        }
        TextView subHead = (TextView)convertView.findViewById(R.id.tv_answer);
        subHead.setText(childText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
