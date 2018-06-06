package pluslearning.bailiwick.rajesh.com.pluslearning.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.ChapterBean;

public class ChapterRecyclerAdapter extends RecyclerView.Adapter<ChapterRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<ChapterBean> chapterBeanList;

    public ChapterRecyclerAdapter(Context context, List<ChapterBean> chapterBeanList) {
        this.context = context;
        this.chapterBeanList = chapterBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chapter_topic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChapterBean chapterBean = chapterBeanList.get(holder.getAdapterPosition());
        holder.mTopic.setText(chapterBean.getTopic());
        if (chapterBean.getStatus().equalsIgnoreCase("complete")){
            holder.mTopic.setTextColor(Color.GREEN);
        }else if (chapterBean.getStatus().equalsIgnoreCase("ongoing")){
            holder.mTopic.setTextColor(context.getResources().getColor(R.color.yellow));
        }else {
            holder.mTopic.setTextColor(context.getResources().getColor(R.color.dark_grey));
        }

    }

    @Override
    public int getItemCount() {
        return chapterBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTopic;
        public ViewHolder(View itemView) {
            super(itemView);
            mTopic = itemView.findViewById(R.id.tv_chapter_content);
        }
    }
}
