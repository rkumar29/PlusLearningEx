package pluslearning.bailiwick.rajesh.com.pluslearning.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import pluslearning.bailiwick.rajesh.com.pluslearning.bean.ProfileResponse;
import pluslearning.bailiwick.rajesh.com.pluslearning.profile.InformationFragment;
import pluslearning.bailiwick.rajesh.com.pluslearning.profile.ParentInformationFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    List<ProfileResponse> results;

    public ViewPagerAdapter(Context context, FragmentManager fm, List<ProfileResponse> results) {
        super(fm);
        this.context = context;
        this.results =results;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            //String name  = "demo";
           // Log.e("Rsult list ", String.valueOf(results.size()) );

            return  InformationFragment.newInstance (results);


        }else if (position == 1){
            return  ParentInformationFragment.newInstance(results);
        }else{
            return  InformationFragment.newInstance(results);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Information";
            case 1: return "Parents Information";
            default: return null;
        }
    }
}
