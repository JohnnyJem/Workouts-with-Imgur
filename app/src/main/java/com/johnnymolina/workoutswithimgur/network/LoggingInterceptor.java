package com.johnnymolina.workoutswithimgur.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 *
 * Credit @EpicPandaForce
 * http://stackoverflow.com/questions/32963394/how-to-use-interceptor-to-add-headers-in-retrofit-2-0
 */
public class LoggingInterceptor
        implements Interceptor {
    @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Timber.i(String.format("Sending request %s on %s%n%s",
                request.url(),
                chain.connection(),
                request.headers()));
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Timber.i(String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}