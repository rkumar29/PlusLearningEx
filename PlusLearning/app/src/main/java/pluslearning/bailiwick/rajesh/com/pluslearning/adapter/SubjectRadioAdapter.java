package pluslearning.bailiwick.rajesh.com.pluslearning.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.List;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.Result;
import pluslearning.bailiwick.rajesh.com.pluslearning.interfaces.CustomRecyclerInterface;

public class SubjectRadioAdapter extends RecyclerView.Adapter<SubjectRadioAdapter.ViewHolder> {

    private Context context;
    private List<Result> subjectBeans;
    private CustomRecyclerInterface customRecyclerInterface;
    private RadioButton lastCheckedRB = null;
    private int lastSelectedPosition = -1;

    public SubjectRadioAdapter(Context context, List<Result> subjectBeans, CustomRecyclerInterface customRecyclerInterface) {
        this.context = context;
        this.subjectBeans = subjectBeans;
        this.customRecyclerInterface = customRecyclerInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_radio_subject,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Result subjectBean = subjectBeans.get(holder.getAdapterPosition());
        holder.radio_button.setText(subjectBean.getSubName());
        holder.radio_button.setChecked(lastSelectedPosition == position);

        holder.radio_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* RadioButton checked_rb = (RadioButton) v;
                if(lastCheckedRB != null){
                    lastCheckedRB.setChecked(false);
                    Log.e("inside if","if");

                }
                lastCheckedRB = checked_rb;*/
                lastSelectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();
                customRecyclerInterface.recyclerClick(position,subjectBean.getMarksObtained());
            }
        });

    }


    @Override
    public int getItemCount() {
        return subjectBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RadioButton radio_button;

        public ViewHolder(View itemView) {
            super(itemView);
            radio_button =itemView.findViewById(R.id.radio_button);



        }
    }
}
