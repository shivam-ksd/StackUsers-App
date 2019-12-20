package com.example.myapplication;

import android.support.annotation.NonNull;
import android.arch.paging.PageKeyedDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    public static final int PAGE_SIZE = 20;
    private static final int FIRST_PAGE = 1;
    private static final String SITE_NAME = "stackoverflow";
    private LoadParams<Integer> params;
    private LoadCallback<Integer, Item> callback;


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getUsers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {

                        if (response.body() != null) {

                            callback.onResult(response.body().items, null, FIRST_PAGE + 1);

                        }

                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        this.params = params;
        this.callback = callback;
        RetrofitClient.getInstance()
                .getApi()
                .getUsers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                        if (response.body() != null) {
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().items, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getUsers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                        if (response.body() != null) {
                            Integer key = response.body().has_more ? params.key + 1 : null;
                            callback.onResult(response.body().items, key);
                        }

                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {

                    }
                });


    }
}
