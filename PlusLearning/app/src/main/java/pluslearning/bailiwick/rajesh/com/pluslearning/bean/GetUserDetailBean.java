package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetUserDetailBean implements Serializable{

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("result")
    @Expose
    private List<ProfileResponse> result = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProfileResponse> getResult() {
        return result;
    }

    public void setResult(List<ProfileResponse> result) {
        this.result = result;
    }

}
