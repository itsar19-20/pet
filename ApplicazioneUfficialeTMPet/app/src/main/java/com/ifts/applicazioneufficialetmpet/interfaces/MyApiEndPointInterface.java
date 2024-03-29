package com.ifts.applicazioneufficialetmpet.interfaces;


import android.content.Intent;

import com.ifts.applicazioneufficialetmpet.Preferiti;
import com.ifts.applicazioneufficialetmpet.model.AnnuncioModel;
import com.ifts.applicazioneufficialetmpet.model.EventoModel;
import com.ifts.applicazioneufficialetmpet.model.PetSitter;
import com.ifts.applicazioneufficialetmpet.model.PreferitoModel;
import com.ifts.applicazioneufficialetmpet.model.UserModel;

import java.util.Date;
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
    Call<String> signUpUser(@Query("name") String nome, @Query("surname") String cognome,
                            @Query("username") String username, @Query("password") String password,
                            @Query("email") String email, @Query("type") String tipoUtente,
                            @Query("descrizione") String descrizione );


    //IMMAGINI

    @GET("/getImmagine")
    Call<String> getImage(@Query("username") String username);

    @POST("getImmagine")
    Call<String> setImage(@Query("username") String username,
                          @Query("immagine") String immagine);

    @Multipart
    @POST("/getImmagine")
    Call<RequestBody> setImage2(@Query("username") String username, @Query("immagine") String immagine,
                                @Part("description") RequestBody description);

    @Multipart
    @POST("/getImmagine")
    Call<ResponseBody> setImage3(@Query("username") String username, @Query("immagine") String immagine,
                                 @Part("description") RequestBody description);


   //CONTROLLO UTENTE BLOCCATO
    @GET("/ControllaUtenteBloccatoController")
    Call<String> getControlloBloccato(@Query("username") String username);



    //EVENTI

   @GET("/EventiController")
    Call<List<EventoModel>> getAllEvents();

    @POST("/EventiController")
    Call<String> setNewEvent(@Query("nomeEvento") String nomeEvento,
                             @Query("descrizione") String descrizione,
                             @Query("usernameOrgnizzatore") String usernameOrganizzatore,
                             @Query("urlImmagine") String urlImmagine);


    @PUT("/EventiController")
    Call<String> joinEvent(@Query("idEventoString") String idEventString, @Query("usernamePartecipante") String usernameEntrant);

    @DELETE("/EventiController")
    Call<String> removeEvent(@Query("idEventoString") int idEventString);

    @GET("/EventiUtenteController")
    Call<List<EventoModel>> getUserEvents(@Query("username") String username);

    @Multipart
    @POST("/ImmagineEventoController")
    Call<RequestBody> setImage(@Query("username") int username, @Query("immagine") String immagine, @Part("description") RequestBody description);

    //ANNUNCIO
    @POST("/AnnunciProprietarioController")
    Call<String> setNewAnnouncement(@Query("nomeAnnuncio") String nomeAnnuncio, @Query("usernameProprietario") String usernameProprietario, @Query("descrizioneProprietario") String descrizione, @Query("dataAnnuncio") String dataAnnuncio, @Query("urlImmagineAnnuncio") String urlImmagineAnnuncio);

    @PUT("/AnnunciPetSitterController")
    Call<String> joinAnnouncement(@Query("idAnnuncioString") String idAnnuncioString, @Query("usernamePartecipante") String usernamePartecipante);

    @DELETE("/AnnunciProprietarioController")
    Call<String> removeAnnouncement(@Query("idAnnuncioString") int idAnnuncioString);

    @GET("/AnnunciPetSitterController")
    Call<List<AnnuncioModel>> getUserAnnouncement();

    @GET("/AnnunciProprietarioController")
    Call<List<AnnuncioModel>> getProprietarioAnnoucement(@Query("usernameProprietario") String proprietarioAnnuncio);

    //  PREFERITI
    @DELETE("/PreferitiController")
    Call<String> removePreferitoPetSitter(@Query("idPreferito") int idPreferito);
    //PetSitter
    @POST("/PreferitiPetSitterController")
    Call<String> setNewPetSitterPreferiti(@Query("usernamePetSitter") String usernamePetSitter,
                                          @Query("idAnnuncioString") int idAnnuncio);

    @GET("/PreferitiPetSitterController")
    Call<List<PreferitoModel>> getPetSitterPreferiti(@Query("usernamePetSitter") String usernamePetSitter);



    //Propriietario
    @POST( "/PreferitiProprietarioController")
    Call<String> setNewPetSitterPreferitiProprietario(@Query("usernameProprietario") String usernameProprietario, @Query("usernamePetSitter") String usernamePetSitter,@Query("idAnnuncioStringa") int idAnnuncio);

    @GET("/PetSitterPerAnnuncioController")
    Call<List<PetSitter>> getPetSitter (@Query("id_annuncio") int id_annuncio);

    @GET("/PreferitiProprietarioController")
    Call<List<PreferitoModel>> getProprietarioPreferiti (@Query("usernameProprietario") String usernameProprietario);
}