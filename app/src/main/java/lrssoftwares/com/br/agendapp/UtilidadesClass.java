package lrssoftwares.com.br.agendapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class UtilidadesClass {

    boolean verificarConexao(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null;
    }
}



