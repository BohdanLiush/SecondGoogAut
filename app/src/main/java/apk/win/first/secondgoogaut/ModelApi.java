package apk.win.first.secondgoogaut;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ModelApi {

    //@GET("items?ids=1,2,3,4,5,6,7,8,9,10")
    @GET("items")
    Call<List<Model>> idsInfo(@Query("ids") String number);

    @GET("items")
    Call<Model> idInfoSingleObject(@Query("id") String number);

}
