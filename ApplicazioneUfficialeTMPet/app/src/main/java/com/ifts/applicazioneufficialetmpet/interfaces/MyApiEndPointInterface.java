package com.ifts.applicazioneufficialetmpet.interfaces;


import com.ifts.applicazioneufficialetmpet.model.UserModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface MyApiEndPointInterface {

    //LOGIN

    @POST("/login")
    Call<UserModel> getUser(@Query("username") String username, @Query("password") String password);

    //REGISTRAZIONE

    @POST("/signUp")
    Call<String> signUpUser(@Query("name") String nome, @Query("surname") String cognome, @Query("username") String username, @Query("password") String password, @Query("email") String email, @Query("type") String tipoUtente);


    //IMMAGINI

    @GET("/getImmagine")
    Call<String> getImage(@Query("username") String username);

    @POST("getImmagine")
    Call<String> setImage(@Query("username") String username, @Query("immagine") String immagine);

    @Multipart
    @POST("/getImmagine")
    Call<RequestBody> setImage2(@Query("username") String username, @Query("immagine") String immagine, @Part("description") RequestBody description);

    //CONTROLLO UTENTE BLOCCATO

    @GET("/ControllaUtenteBloccatoController")
    Call<String> getControlloBloccato(@Query("username") String username);

    //EVENTI
/*
    @GET("/EventiController")
    Call<List<Evento>> getAllEvents();

    @POST("/EventiController")
    Call<String> setNewEvent(@Query("nomeEvento") String eventName, @Query("descrizione") String description, @Query("usernameOrgnizzatore") String usernameOrganizer);

    @PUT("/EventiController")
    Call<String> joinEvent(@Query("idEventoString") String idEventString, @Query("usernamePartecipante") String usernameEntrant);

    @DELETE("/EventiController")
    Call<String> removeEvent(@Query("idEventoString") String idEventString);

    @GET("/EventiUtenteController")
    Call<List<Evento>> getUserEvents(@Query("username") String username);

*/
}
