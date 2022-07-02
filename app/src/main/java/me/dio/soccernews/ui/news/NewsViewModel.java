package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.dio.soccernews.data.remote.SoccerNewsApi;
import me.dio.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final SoccerNewsApi api;

    public NewsViewModel() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://progrs.github.io/soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        api = retrofit.create(SoccerNewsApi.class);
        extracted();


        //TODO REMOVER MOCK DE NOTICIAS
      //  List<News> news = new ArrayList<>();
      //  news.add(new News("Grêmio Tem Desfalque Importante", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."));
      //  news.add(new News("Grêmio Joga no  Sábado", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."));
      //  news.add(new News("Copa do Mundo Feminina Está Terminando", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."));

        //this.news.setValue(news);


    }

    private void extracted() {
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if(response.isSuccessful()){
                        news.setValue(response.body());
                }else {
                    //todo  penssar em um, estrategia de erro
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                //todo  penssar em um, estrategia de erro
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}