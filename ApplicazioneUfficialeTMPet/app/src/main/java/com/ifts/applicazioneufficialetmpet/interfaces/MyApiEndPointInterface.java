package com.ifts.applicazioneufficialetmpet.interfaces;


import com.ifts.applicazioneufficialetmpet.model.EventoModel;
import com.ifts.applicazioneufficialetmpet.model.UserModel;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface MyApiEndPointInterface {

    //LOGIN

    @POST("/login")
    Call<UserModel> getUser(@Query("username") String username, @Query("password") String password);

    //REGISTRAZIONE

    @POST("/signUp")
    Call<String> signUpUser(@Query("name") String nome, @Query("surname") String cognome, @Query("username") String username, @Query("password") String password, @Query("email") String email, @Query("type") String tipoUtente, @Query("descrizione") String descrizione );


    //IMMAGINI

    @GET("/getImmagine")
    Call<String> getImage(@Query("username") String username);

    @POST("getImmagine")
    Call<String> setImage(@Query("username") String username, @Query("immagine") String immagine);

    @Multipart
    @POST("/getImmagine")
    Call<RequestBody> setImage2(@Query("username") String username, @Query("immagine") String immagine, @Part("description") RequestBody description);

    @Multipart
    @POST("/getImmagine")
    Call<ResponseBody> setImage3(@Query("username") String username, @Query("immagine") String immagine, @Part("description") RequestBody description);


    @GET("/ControllaUtenteBloccatoController")
    Call<String> getControlloBloccato(@Query("username") String username);



    //EVENTI

   @GET("/EventiController")
    Call<List<EventoModel>> getAllEvents();

    @POST("/EventiController")
    Call<String> setNewEvent(@Query("nomeEvento") String nomeEvento, @Query("descrizione") String descrizione, @Query("usernameOrgnizzatore") String usernameOrganizzatore,@Query("urlImmagine") String urlImmagine);


    @PUT("/EventiController")
    Call<String> joinEvent(@Query("idEventoString") String idEventString, @Query("usernamePartecipante") String usernameEntrant);

    @DELETE("/")
    Call<String> removeEvent(@Query("idEventoString") int idEventString);

    @GET("/EventiUtenteController")
    Call<List<EventoModel>> getUserEvents(@Query("username") String username);

    @Multipart
    @POST("/ImmagineEventoController")
    Call<RequestBody> setImage(@Query("username") int username, @Query("immagine") String immagine, @Part("description") RequestBody description);

}
