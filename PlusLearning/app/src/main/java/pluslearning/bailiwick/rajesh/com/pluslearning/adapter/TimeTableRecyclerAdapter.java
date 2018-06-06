package pluslearning.bailiwick.rajesh.com.pluslearning.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.TimeTableBean;

public class TimeTableRecyclerAdapter extends RecyclerView.Adapter<TimeTableRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<TimeTableBean> timeTableBeans;

    public TimeTableRecyclerAdapter(Context context, List<TimeTableBean> timeTableBeans) {
        this.context = context;
        this.timeTableBeans = timeTableBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_time_table,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimeTableBean timeTableBean = timeTableBeans.get(holder.getAdapterPosition());
        holder.mTime.setText(timeTableBean.getTime());
        holder.mSubject.setText(timeTableBean.getSubject());

        Log.e("Size",timeTableBeans.size() +"");
    }

    @Override
    public int getItemCount() {
        return timeTableBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTime,mSubject;
        public ViewHolder(View itemView) {
            super(itemView);
            mTime = itemView.findViewById(R.id.tv_time);
            mSubject = itemView.findViewById(R.id.tv_subject);
        }
    }
}
