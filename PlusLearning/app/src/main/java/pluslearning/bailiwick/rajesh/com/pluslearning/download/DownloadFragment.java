package pluslearning.bailiwick.rajesh.com.pluslearning.download;


import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.adapter.DownloadRecyclerAdapter;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.Download;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.GetDownloads;
import pluslearning.bailiwick.rajesh.com.pluslearning.interfaces.DownloadInterface;
import pluslearning.bailiwick.rajesh.com.pluslearning.rest.ApiClient;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.AppController;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.Constant;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ProDilog;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment implements DownloadInterface {

    Toolbar mToolbar;
    RecyclerView mDownloadRecycler;
    DownloadRecyclerAdapter downloadRecyclerAdapter;
    List<Download> downloadBeanList;
    DownloadManager.Request downloadRequest;
    DownloadManager downloadManager;
    File fileName;
    String mTitle = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download, container, false);
        downloadBeanList = new ArrayList<Download>();
        getUiObject(view);
        setRecycler();
        getDownload();


        return view;
    }

    private void getFiles() {
        ProDilog.getInstance().show(getActivity(),"Loading...");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.BASE_URL,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ProDilog.getInstance().dismiss();
                            Log.e("Response", response);
                            JSONObject jsdata = new JSONObject(response);

                            downloadRecyclerAdapter.notifyDataSetChanged();
                        } catch (Exception ex) {
                            ex.printStackTrace(); }
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
            public Map<String,String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("key", "NjAxNzI1OmUyZmJlZW");
                return headers;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("action", Constant.ACTION_Downloads);
                params.put("student_id", "4" );
                Log.e("Param", "" + params);
                return params; }
        };
        AppController.retryPolicey(stringRequest);
        AppController.getInstance().addToRequestQueue(stringRequest);


    }

    private void getUiObject(View view) {
        mToolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar_main);
        mToolbar.setTitle("Download");
        mDownloadRecycler = view.findViewById(R.id.rv_download);
    }

    public void setRecycler(){
        downloadRecyclerAdapter = new DownloadRecyclerAdapter(getActivity(),downloadBeanList,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        mDownloadRecycler.setLayoutManager(layoutManager);
        mDownloadRecycler.setItemAnimator(new DefaultItemAnimator());
        mDownloadRecycler.setAdapter(downloadRecyclerAdapter);

    }

    @Override
    public void onDownloadClick(int position, String name , String link) {
        reqPermission(name,link);
        Toast.makeText(getActivity(), "Download Available Soon", Toast.LENGTH_SHORT).show();
    }


    private void reqPermission(final String name,final String link) {
        Dexter.withActivity(getActivity())
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            downloadPdf(name,link);
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getActivity(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", "pluslearning.bailiwick.rajesh.com.pluslearning", null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    private void downloadPdf(String name,String link){
        Toast.makeText(getActivity(), "Download Started...", Toast.LENGTH_SHORT).show();
        fileName = new File(Environment.DIRECTORY_DOWNLOADS+"/PlusLearning/");
        String filePath = fileName.getAbsolutePath();
        downloadRequest = new DownloadManager.Request(Uri.parse(link));
        downloadRequest.setDestinationInExternalPublicDir(filePath,name+".pdf");
        downloadRequest.allowScanningByMediaScanner();
        downloadRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        downloadManager = (DownloadManager) Objects.requireNonNull(getActivity()).getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadManager != null){
            downloadManager.enqueue(downloadRequest);
        }
    }

    public void getDownload(){
        ProDilog.getInstance().show(getActivity(),"Loading...");
        ApiClient.get().getDownloads().enqueue(new Callback<GetDownloads>() {
            @Override
            public void onResponse(@NonNull Call<GetDownloads> call, @NonNull retrofit2.Response<GetDownloads> response) {
                ProDilog.getInstance().dismiss();
                if (response.body().getStatus() == 1){
                    downloadBeanList.addAll(response.body().getDownloads());
                    downloadRecyclerAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetDownloads> call, @NonNull Throwable t) {
                ProDilog.getInstance().dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
