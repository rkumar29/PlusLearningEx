package pluslearning.bailiwick.rajesh.com.pluslearning.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.PlBean;

public class PlRecyclerAdapter extends RecyclerView.Adapter<PlRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<PlBean> plBeanList;

    public PlRecyclerAdapter(Context context, List<PlBean> plBeanList) {
        this.context = context;
        this.plBeanList = plBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pl_cat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final PlBean plBean = plBeanList.get(holder.getAdapterPosition());
        holder.mCatName.setText(plBean.getCat_name());
        holder.mCatName.setCompoundDrawablesWithIntrinsicBounds(null,context.getResources().getDrawable(plBean.getLogo()),null,null);
        holder.mCatItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return plBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mCatName;
        CardView mCatItem;
        ViewHolder(View itemView) {
            super(itemView);
            mCatName = itemView.findViewById(R.id.tv_cat_name);
            mCatItem = itemView.findViewById(R.id.cv_cat);
        }
    }

}
