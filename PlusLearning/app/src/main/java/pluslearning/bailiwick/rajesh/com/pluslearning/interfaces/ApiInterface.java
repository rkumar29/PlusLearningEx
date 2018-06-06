package pluslearning.bailiwick.rajesh.com.pluslearning.interfaces;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.Attendence;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.AttendenceResponse;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.FaqBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.GenericResponse;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.GetDownloads;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.GetNotices;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.GetTimeTable;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.GetUserDetailBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.LoginBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.TestResultResponse;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.TestTypeResponse;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("admin-ajax.php")
    Call<LoginBean> getLogin(@FieldMap(encoded = true) Map<String,String> fields);


   // @GET()
    @FormUrlEncoded
    @POST("admin-ajax.php")
    Call<GetUserDetailBean> getUserDetails(@FieldMap(encoded = true) Map<String,String> fields);

    @GET("admin-ajax.php?action=faq")
    Call<FaqBean> getFaqs();

    @GET("admin-ajax.php?action=downloads")
    Call<GetDownloads> getDownloads();

    @GET("admin-ajax.php?action=notice_board")
    Call<GetNotices> getNotices();

    @FormUrlEncoded
    @POST("admin-ajax.php")
    Call<GetNotices> getAttendanceReport(@FieldMap(encoded = true) Map<String,String> fields);

    @FormUrlEncoded
    @POST("admin-ajax.php")
    Call<GetTimeTable> gettimetable(@FieldMap(encoded = true) Map<String,String> fields);


    /*Attendence*/
    @FormUrlEncoded
    @POST("admin-ajax.php")
    Call<Attendence> getAttendence(@FieldMap(encoded = true) Map<String,String> fields);

    @FormUrlEncoded
    @POST("admin-ajax.php")
    Call<AttendenceResponse> getAttendenceMontly(@FieldMap(encoded = true) Map<String,String> fields);


    @FormUrlEncoded
    @POST("admin-ajax.php")
    Call<GenericResponse> submitQuery(@FieldMap(encoded = true) Map<String,String> fields);


    @Multipart
    @POST("admin-ajax.php")
    Call<GenericResponse> uploadImage( @Part MultipartBody.Part file,@Part("action") RequestBody action, @Part("user_id") RequestBody id);


   /* @FormUrlEncoded
    @POST("admin-ajax.php")
    Call<GetTestType> getTests(@FieldMap (encoded = true) Map<String,String> fields);*/
    /*Rsult*/
   @FormUrlEncoded
   @POST("admin-ajax.php")
   Call<TestResultResponse> getResult(@FieldMap(encoded = true) Map<String,String> fields);

    @FormUrlEncoded
    @POST("admin-ajax.php")
    Call<TestTypeResponse> getTestType(@FieldMap(encoded = true) Map<String,String> fields);




}
