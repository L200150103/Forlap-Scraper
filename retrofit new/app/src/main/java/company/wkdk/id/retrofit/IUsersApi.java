package company.wkdk.id.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.POST;

public interface IUsersApi {


    @POST("forlap")

    Call<User> createUser(@Body User user);

}
