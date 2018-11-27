package apk.win.first.secondgoogaut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class InfixUpgrade implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("attributes")
    @Expose
    private List<Object> attributes = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
    }
}
