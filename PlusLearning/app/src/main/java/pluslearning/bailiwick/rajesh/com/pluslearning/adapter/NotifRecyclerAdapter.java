package pluslearning.bailiwick.rajesh.com.pluslearning.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.NotificationBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.interfaces.NotificatioInterface;

public class NotifRecyclerAdapter extends RecyclerView.Adapter<NotifRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<NotificationBean> notificationBeanList;
    private NotificatioInterface customRecyclerClick;

    public NotifRecyclerAdapter(Context context, List<NotificationBean> notificationBeanList, NotificatioInterface customRecyclerClick) {
        this.context = context;
        this.notificationBeanList = notificationBeanList;
        this.customRecyclerClick = customRecyclerClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final NotificationBean notificationBean = notificationBeanList.get(holder.getAdapterPosition());
        holder.mImage.setImageResource(notificationBean.getImage());
        holder.mNotification.setText(notificationBean.getNotification());
        holder.mDaysAgo.setText(notificationBean.getDays_ago());
        holder.mNotificLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customRecyclerClick.onRecycleClick(holder.getAdapterPosition(),
                        notificationBean.getNotification(),notificationBean.getDays_ago());
            }
        });


    }

    @Override
    public int getItemCount() {
        return notificationBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        LinearLayout mNotificLayout;
        TextView mNotification,mDaysAgo;
        public ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image_notification);
            mNotification = itemView.findViewById(R.id.tv_notification);
            mDaysAgo = itemView.findViewById(R.id.tv_date);
            mNotificLayout = itemView.findViewById(R.id.ll_notification);
        }
    }
}
