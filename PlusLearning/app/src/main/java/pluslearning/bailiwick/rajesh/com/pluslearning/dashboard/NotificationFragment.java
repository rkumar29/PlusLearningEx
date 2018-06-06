package pluslearning.bailiwick.rajesh.com.pluslearning.dashboard;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.adapter.NotifRecyclerAdapter;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.NotificationBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.interfaces.NotificatioInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment implements NotificatioInterface {

    Toolbar mToolbar;
    RecyclerView mNotificationRecycler;
    List<NotificationBean> notificationBeanList;
    NotifRecyclerAdapter notifRecyclerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        getUiObject(v);
        setRecycler();

        return v;
    }

    public void getUiObject(View view){
        mToolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar_main);
        mToolbar.setTitle("Notification");

        mNotificationRecycler = view.findViewById(R.id.rv_notification);
    }

    public void setRecycler(){
        notificationBeanList = new ArrayList<>();
        notificationBeanList.add(new NotificationBean(R.drawable.bg,
                Objects.requireNonNull(getActivity()).getResources().getString(R.string.dummy_text),"4days ago"));
        notificationBeanList.add(new NotificationBean(R.drawable.bg,
                Objects.requireNonNull(getActivity()).getResources().getString(R.string.dummy_text),"4days ago"));
        notificationBeanList.add(new NotificationBean(R.drawable.bg,
                Objects.requireNonNull(getActivity()).getResources().getString(R.string.dummy_text),"4days ago"));
        notificationBeanList.add(new NotificationBean(R.drawable.bg,
                Objects.requireNonNull(getActivity()).getResources().getString(R.string.dummy_text),"4days ago"));
        notificationBeanList.add(new NotificationBean(R.drawable.bg,
                Objects.requireNonNull(getActivity()).getResources().getString(R.string.dummy_text),"4days ago"));


        notifRecyclerAdapter = new NotifRecyclerAdapter(getActivity(),notificationBeanList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mNotificationRecycler.setLayoutManager(layoutManager);
        mNotificationRecycler.setItemAnimator(new DefaultItemAnimator());
        mNotificationRecycler.setAdapter(notifRecyclerAdapter);


    }





    @Override
    public void onRecycleClick(int positon, String notification, String days_ago) {
        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NotificViewFragment notificViewFragment = new NotificViewFragment();
        fragmentTransaction.add(R.id.frame_plearn,notificViewFragment,null).addToBackStack(null).commit();
    }
}
