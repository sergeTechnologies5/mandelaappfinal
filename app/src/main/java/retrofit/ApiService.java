package retrofit;

import java.util.List;

import models.Comment;
import models.Login;
import models.LoginResponse;
import models.Payment;
import models.Response;
import models.Services;
import models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("createaccount.php")
    Call<Response> createAccount(@Body User user);
    @POST("comments.php")
    Call<Response> comment(@Body Comment comment);
    @GET("viewservices.php")
    Call<List<Services>> getServices();

    //payment done
    @POST("mpesa/requestcheckout.php")
    Call<Response> payForservices(@Body Payment payment);

    //payment done
    @POST("withdraw.php")
    Call<Response> withDrawservices(@Body Payment payment);

    @POST("login.php")
    Call<LoginResponse>  login(@Body Login login);

}
