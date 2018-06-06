package pluslearning.bailiwick.rajesh.com.pluslearning.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pluslearning.bailiwick.rajesh.com.pluslearning.student_info.AcademicFragment;
import pluslearning.bailiwick.rajesh.com.pluslearning.student_info.CompetitiveFragment;

public class ViewPagerTestResultAdapter extends FragmentPagerAdapter {

    private Context context;

    public ViewPagerTestResultAdapter(Context context,FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new AcademicFragment();
        }else if (position == 1){
            return new CompetitiveFragment();
        }else{
            return new AcademicFragment();
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
            case 0: return "Academic";
            case 1: return "Competitive";
            default: return null;
        }
    }
}
