package pluslearning.bailiwick.rajesh.com.pluslearning.profile;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.adapter.ViewPagerAdapter;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.GenericResponse;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.GetUserDetailBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.ProfileResponse;
import pluslearning.bailiwick.rajesh.com.pluslearning.rest.ApiClient;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ApiActions;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ProDilog;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.SavedData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    Toolbar mToolbar;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    ViewPagerAdapter viewPagerAdapter;
    TextView mUser,tv_course;
    private static final int REQUEST_PERMISSIONS = 20;
    CircleImageView iv_edit_image_profile;
    CircleImageView mProfile, mEditProfile;
    List<ProfileResponse> results;
    Uri mCropImageUri;

    private static final int GALLERY = 1;
    private static final int CAMERA_REQUEST = 1888;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
       // setHasOptionsMenu(true);
        results = new ArrayList<>();
        getUiObject(view);
        //settingTabs();
        getUserDetails();
        //setImage();
        clickEvents();


        return view;
    }

    private void setImage() {
        if (SavedData.getImage()!=null){
            Log.e("IMAGE",SavedData.getImage());
            Picasso.get().load(SavedData.getImage()).into(mProfile);
           // mProfile.setImageURI(Uri.parse(SavedData.getImage()));
        }
    }

    private void clickEvents() {
        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reqPermission();
            }
        });
    }

    public void getUiObject(View view){
        mToolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar_main);
        mToolbar.setTitle("Profile");

        mTabLayout = view.findViewById(R.id.tabs);
        mViewPager = view.findViewById(R.id.pager);
        mUser = view.findViewById(R.id.tv_name);
        tv_course=view.findViewById(R.id.tv_course);
        iv_edit_image_profile = view.findViewById(R.id.iv_edit_image_profile);
        mProfile =view.findViewById(R.id.image_profile);

    }



   /* @Override
    public void onPrepareOptionsMenu(Menu menu) {

        MenuItem menuItem = menu.findItem(R.id.notify);
        menuItem.setIcon(R.drawable.square_edit_outline);

    }
*/
   public void getUserDetails(){
       ProDilog.getInstance().show(getActivity(),"Loading...");

       Map<String,String> fields = new HashMap<>();
       fields.put("action", ApiActions.ACTION_GET_USER_DETAILS);
       //fields.put("user_id", String.valueOf(SharedPref.getUserId(Objects.requireNonNull(getActivity()))));
       fields.put("user_id", "4");
       ApiClient.get().getUserDetails(fields).enqueue(new Callback<GetUserDetailBean>() {
           @Override
           public void onResponse(@NonNull Call<GetUserDetailBean> call, @NonNull Response<GetUserDetailBean> response) {
               ProDilog.getInstance().dismiss();
               results = response.body().getResult();
               mUser.setText(results.get(0).getName());
               tv_course.setText(results.get(0).getCourseName());
               Picasso.get().load(results.get(0).getImage()).into(mProfile);
                Log.e("Size",results.size() +"");
               settingTabs();

           }
           @Override
           public void onFailure(@NonNull Call<GetUserDetailBean> call, @NonNull Throwable t) {
               ProDilog.getInstance().dismiss();
               Log.e("user detail->",t.toString());
           }
       });
   }


    public void settingTabs(){
        viewPagerAdapter = new ViewPagerAdapter(getActivity(),getChildFragmentManager(),results);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(viewPagerAdapter);

    }
    private void reqPermission() {
        Dexter.withActivity(getActivity())
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            changeProfile();
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

    public void changeProfile() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Image From"), GALLERY);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                Log.e("contentURI ",contentURI.toString());
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getActivity()).getContentResolver(), contentURI);
                    mProfile.setImageBitmap(bitmap);
                    mCropImageUri = data.getData();
                    uploadImage();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else if (requestCode == CAMERA_REQUEST) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            mProfile.setImageBitmap(thumbnail);

        }
    }
    public String getPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();
        cursor = getActivity().getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    private void uploadImage() {
        String path = getPath(mCropImageUri);
        Log.e("PATH", path);
        final File file = new File(path);
        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        RequestBody action = RequestBody.create(MediaType.parse("image/*"), ApiActions.ACTION_UPLOAD_IMAGE);
       // RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"),String.valueOf(SharedPref.getUserId(Objects.requireNonNull(getActivity()))));
        RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"),
                "4");
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
        ProDilog.getInstance().show(getActivity(), "Loading...");
        ApiClient.get().uploadImage(body, action, user_id).enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                  ProDilog.getInstance().dismiss();
                  if (response.body().getStatus()==1){
                      SavedData.saveImage(results.get(0).getImage());
                  }
                   Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                ProDilog.getInstance().dismiss();
                Log.e("user detail->", t.toString());
            }
        });

    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      /*  if (requestCode == CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE) {
            if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // required permissions granted, start crop image activity
            } else {
                Toast.makeText(getActivity(), "Cancelling, required permissions are not granted", Toast.LENGTH_LONG).show();
            }*/
        }

}
