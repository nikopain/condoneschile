package usm.cc.network;

import retrofit2.Call;
import retrofit2.http.GET;
import usm.cc.Model.ProductsResponse;

public interface ApiInterface {

    @GET("get/productos")
    Call<ProductsResponse> getProducts();
}