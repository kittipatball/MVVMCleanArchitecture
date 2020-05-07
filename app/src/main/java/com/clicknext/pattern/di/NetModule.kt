package com.clicknext.pattern.di

import android.content.Context
import com.clicknext.pattern.R
import com.clicknext.pattern.connection.api.Api
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.security.KeyManagementException
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

val netModule = module {
    single { provideGson() }
    single { provideApi(gson = get() , context = androidContext()) }
}

private fun provideApi(gson: Gson , context: Context) = Retrofit.Builder()
    .baseUrl(context.getString(R.string.service_url))
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(getUnsafeOkHttpClient())
    .build()
    .create(Api::class.java)

private fun provideGson() = GsonBuilder().create()

@Throws(
    CertificateException::class,
    NoSuchAlgorithmException::class,
    KeyStoreException::class,
    KeyManagementException::class,
    IOException::class
)
private fun certPinning(): OkHttpClient
{

    val certificatePinner = CertificatePinner.Builder()
//        .add("mock.mock.go.th", "sha256/DRf6m3GPgSXFPsAAc/MRiWrYGRE3Mr8HmiDosj6XzZ4=")
//        .add("mock.mock.go.th", "sha256/VYZwGiJkq3NNo1YRI2RGiSTI1mqTWG8zDcRf1/KAN6I=")
//        .add("mock.mock.go.th", "sha256/du6FkDdMcVQ3u8prumAo6t3i3G27uMP2EOhR8R0at/U=")
        .build()

    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .certificatePinner(certificatePinner)
        .build()
}

private fun getUnsafeOkHttpClient(): OkHttpClient
{
    try
    {

        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager
        {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>
                                            , authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>
                                            , authType: String) {
            }

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())

        val sslSocketFactory = sslContext.socketFactory

        return OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .sslSocketFactory(sslSocketFactory , trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
            .build()

    }
    catch (e: Exception)
    {
        throw RuntimeException(e)
    }
}