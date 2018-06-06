package pluslearning.bailiwick.rajesh.com.pluslearning.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.SubjectBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.interfaces.CustomRecyclerInterface;

public class SubjectRecyclerAdapter extends RecyclerView.Adapter<SubjectRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<SubjectBean> subjectBeans;
    private CustomRecyclerInterface customRecyclerInterface;
    int selectedPosition =1;
    public SubjectRecyclerAdapter(Context context, List<SubjectBean> subjectBeans,CustomRecyclerInterface customRecyclerInterface) {
        this.context = context;
        this.subjectBeans = subjectBeans;
        this.customRecyclerInterface = customRecyclerInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_header_syllabus,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final SubjectBean subjectBean = subjectBeans.get(holder.getAdapterPosition());
        holder.mSubject.setText(subjectBean.getSubject());
        holder.mSubject.setBackgroundColor(context.getResources().getColor(R.color.white));

        //setColor(position,holder);

        if(selectedPosition==position)
            holder.mSubject.setBackgroundColor(context.getResources().getColor(R.color.green_yellow));
        else
            holder.mSubject.setBackgroundColor(context.getResources().getColor(R.color.white));

        holder.mSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition=position;
                notifyDataSetChanged();
                customRecyclerInterface.recyclerClick(holder.getAdapterPosition(),subjectBean.getSubject());
            }
        });

    }


    @Override
    public int getItemCount() {
        return subjectBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mSubject;
        public ViewHolder(View itemView) {
            super(itemView);
            mSubject = itemView.findViewById(R.id.text_subject);

        }
    }
}
