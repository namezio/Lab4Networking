package com.namezio.lab4_networking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
//http://asian.dotplays.com/wp-json/wp/v2/categories?page=1&%20per_page=10

public interface JsonPlaceHolderCategoryApi {
    @GET("/wp-json/wp/v2/categories?page=1&%20per_page=10")
    Call<List<Category>> getPost();
}
