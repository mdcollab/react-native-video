package com.brentvatne.exoplayer;

import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSource;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.TransferListener;

import java.util.Map;

import okhttp3.Call;


public final class OkHttpWithHeadersDataSourceFactory implements Factory {
    private final OkHttpDataSourceFactory factory;
    private final Map<String, String> headers;

    public OkHttpWithHeadersDataSourceFactory(
          Call.Factory callFactory,
          String userAgent,
          TransferListener<? super DataSource> listener,
          Map<String, String> headers) {
        this.factory = new OkHttpDataSourceFactory(callFactory, userAgent, listener);
        this.headers = headers;
    }

    @Override
    public DataSource createDataSource() {
        OkHttpDataSource dataSource = factory.createDataSource();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                dataSource.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        return dataSource;
    }
}
