package pluslearning.bailiwick.rajesh.com.pluslearning.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.ChapterBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.SyllabusBean;

public class SyllabusRecyclerAdapter extends RecyclerView.Adapter<SyllabusRecyclerAdapter.ViewHolder> {

    private Context context;
    List<SyllabusBean> syllabusBeans;

    public SyllabusRecyclerAdapter(Context context, List<SyllabusBean> syllabusBeans) {
        this.context = context;
        this.syllabusBeans = syllabusBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_syllabus,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SyllabusBean syllabusBean = syllabusBeans.get(holder.getAdapterPosition());
        holder.mChapter.setText(syllabusBean.getChapter());
        if (syllabusBean.getStatus().equalsIgnoreCase("complete")){
            holder.mChapter.setBackgroundColor(context.getResources().getColor(R.color.green));
        }else if (syllabusBean.getStatus().equalsIgnoreCase("ongoing")){
            holder.mChapter.setBackgroundColor(context.getResources().getColor(R.color.yellow));
        }else {
            holder.mChapter.setBackgroundColor(context.getResources().getColor(R.color.dark_grey));
        }
    }

    @Override
    public int getItemCount() {
        return syllabusBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mChapter;
        RecyclerView mTopicRecycler;
        List<ChapterBean> chapterBeanList;
        ChapterRecyclerAdapter chapterRecyclerAdapter;

        public ViewHolder(View itemView) {
            super(itemView);
            mChapter = itemView.findViewById(R.id.tv_chapter);
            mTopicRecycler = itemView.findViewById(R.id.rv_syllabus_content);

            chapterBeanList = new ArrayList<>();
            chapterBeanList.add(new ChapterBean("1.Introduction","complete"));
            chapterBeanList.add(new ChapterBean("2.Overview","complete"));
            chapterBeanList.add(new ChapterBean("3.Practice","ongoing"));
            chapterBeanList.add(new ChapterBean("4.Questions","not done"));

            chapterRecyclerAdapter = new ChapterRecyclerAdapter(context,chapterBeanList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            mTopicRecycler.setLayoutManager(layoutManager);
            mTopicRecycler.setItemAnimator(new DefaultItemAnimator());
            mTopicRecycler.setAdapter(chapterRecyclerAdapter);
        }
    }
}
