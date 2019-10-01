package company.wkdk.id.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static IUsersApi service;
    private static ApiManager apiManager;

    private ApiManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://forlap.sipsmampk.online/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IUsersApi.class);
    }

    public static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    public void createUser(User user, Callback<User> callback) {
        Call<User> userCall = service.createUser(user);
        userCall.enqueue(callback);
    }
}