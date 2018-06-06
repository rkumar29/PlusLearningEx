package pluslearning.bailiwick.rajesh.com.pluslearning.faq;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.adapter.ExpandableListAdapter;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.AppController;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.Constant;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ProDilog;


/**
 * A simple {@link Fragment} subclass.
 */
public class FaqFragment extends Fragment implements View.OnClickListener {

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<String> mHeading;
    HashMap<String, List<String>> mSubHeading;
    private int lastExpandedPosition = -1;
    Toolbar mToolbar;
    Button mGenerateTicket;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq, container, false);
        getUiObject(view);
        //faqs = new ArrayList<>();
        //setExpandable();
        getFaq();

        return view;
    }

    public void getUiObject(View view) {
        expandableListView = view.findViewById(R.id.ev_doubt_query);
        mToolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar_main);
        mToolbar.setTitle("FAQ");
        mGenerateTicket = view.findViewById(R.id.btn_generate_ticket);

        mGenerateTicket.setOnClickListener(this);

    }

    private void setExpandable() {
        /*mHeading = new ArrayList<>();
        mSubHeading = new HashMap<>();


        mHeading.add("Ques : How can i change my Profile ?");
        mHeading.add("Ques : How can i change my Profile ?");
        mHeading.add("Ques : How can i change my Profile ?");
        mHeading.add("Ques : How can i change my Profile ?");
        mHeading.add("Ques : How can i change my Profile ?");

        List<String> firstQues = new ArrayList<>();
        firstQues.add("Dummy Text Provided only for development purpose. This text will be change while dynamic");

        List<String> secQues = new ArrayList<>();
        secQues.add("Dummy Text Provided only for development purpose. This text will be change while dynamic");

        List<String> thirdQues = new ArrayList<>();
        thirdQues.add("Dummy Text Provided only for development purpose. This text will be change while dynamic");

        List<String> fourthQues = new ArrayList<>();
        fourthQues.add("Dummy Text Provided only for development purpose. This text will be change while dynamic");

        List<String> fifthQues = new ArrayList<>();
        fifthQues.add("Dummy Text Provided only for development purpose. This text will be change while dynamic");

        mSubHeading.put(mHeading.get(0), firstQues);
        mSubHeading.put(mHeading.get(0), secQues);
        mSubHeading.put(mHeading.get(0), thirdQues);
        mSubHeading.put(mHeading.get(0), fourthQues);
        mSubHeading.put(mHeading.get(0), fifthQues);*/

        expandableListAdapter = new ExpandableListAdapter(getActivity(), mHeading, mSubHeading);
        expandableListView.setAdapter(expandableListAdapter);


        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });


    }

    public void getFaq() {
        final String faq_url = Constant.BASE_URL + "?action=" + Constant.ACTION_FAQ;

        mHeading = new ArrayList<>();
        mSubHeading = new HashMap<>();

        ProDilog.getInstance().show(getActivity(), "Loading...");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, faq_url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ProDilog.getInstance().dismiss();
                            mGenerateTicket.setVisibility(View.VISIBLE);
                            JSONObject jsdata = new JSONObject(response);
                            if (jsdata.getInt("status") == 1){
                                JSONArray faq = jsdata.getJSONArray("faq");
                                List<String> answers;
                                for (int i =0;i<faq.length();i++) {
                                   /* faqs.add(new Faq(faq.getJSONObject(i).getString("question"),
                                            faq.getJSONObject(i).getString("answer")));*/
                                   answers  = new ArrayList<>();
                                   mHeading.add(faq.getJSONObject(i).getString("question"));
                                   answers.add(faq.getJSONObject(i).getString("answer"));
                                   mSubHeading.put(mHeading.get(i), answers);
                                }
                                setExpandable();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ProDilog.getInstance().dismiss();
                Log.e("Error :", error.toString());
            }
        })

        {
            /** Passing some request headers* */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
               // headers.put("Content-Type", "application/json");
                headers.put("key", "NjAxNzI1OmUyZmJlZW");
                return headers;
            }

            /*@Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("action", Constant.ACTION_GET_TEST_RESULT);
                params.put("student_id", "4");
                params.put("test_type_id", "2");
                Log.e("Param", "" + params);
                return params;
            }*/
        };
        AppController.retryPolicey(stringRequest);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_generate_ticket:
                FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                GenerateTicketFragment ticketFragment = new GenerateTicketFragment();
                fragmentTransaction.add(R.id.frame_plearn, ticketFragment, null).addToBackStack(null).commit();
                break;
        }
    }
}
