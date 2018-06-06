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
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.Download;
import pluslearning.bailiwick.rajesh.com.pluslearning.interfaces.DownloadInterface;

public class DownloadRecyclerAdapter extends RecyclerView.Adapter<DownloadRecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<Download> downloadBeans;
    private DownloadInterface plearnInterface;

    public DownloadRecyclerAdapter(Context context, List<Download> downloadBeans, DownloadInterface plearnInterface) {
        this.context = context;
        this.downloadBeans = downloadBeans;
        this.plearnInterface = plearnInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_download,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Download downloadBean = downloadBeans.get(holder.getAdapterPosition());
        holder.mDownload.setText(downloadBean.getTitle());
        holder.mCardDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plearnInterface.onDownloadClick(holder.getAdapterPosition(),downloadBean.getTitle(),downloadBean.getLink());
            }
        });
    }

    @Override
    public int getItemCount() {
        return downloadBeans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mDownload;
        CardView mCardDownload;
        public MyViewHolder(View itemView) {
            super(itemView);
            mDownload = itemView.findViewById(R.id.tv_download);
            mCardDownload = itemView.findViewById(R.id.cv_card_download);
        }
    }
}
