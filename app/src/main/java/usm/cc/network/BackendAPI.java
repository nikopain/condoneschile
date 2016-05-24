package usm.cc.network;

import retrofit2.Call;
import retrofit2.http.GET;
import usm.cc.Model.CondomDTO;

/**
 * Created by niko on 17/05/2016.
 */
public interface BackendAPI {

    @GET("get/productos")
    Call<CondomDTO> getProductos();


}
