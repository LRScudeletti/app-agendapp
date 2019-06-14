package lrssoftwares.com.br.agendapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

class CrudClass {
    private SQLiteDatabase sqLiteDatabase;
    private final SQLiteOpenHelper baseDadosSQLiteOpenHelper;

    CrudClass(Context contexto) {
        baseDadosSQLiteOpenHelper = new SQLiteOpenHelper(contexto);
    }

    //region [ USUÁRIO ]
    // Inserir
    void inserirUsuario(UsuarioClass usuarioClass) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", usuarioClass.getNome());
        valores.put("usuario", usuarioClass.getUsuario());
        valores.put("senha", usuarioClass.getSenha());

        sqLiteDatabase.insert("usuario", null, valores);
        sqLiteDatabase.close();
    }

    // Pesquisar
    List<UsuarioClass> PesquisarUsuario(String usuario, String senha) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        List<UsuarioClass> lista = new ArrayList<>();
        String[] colunas = new String[]{"_id", "nome", "usuario", "senha"};

        Cursor cursor = sqLiteDatabase.query("usuario", colunas, "usuario = ? and senha =? ", new String[]{"" + usuario, "" + senha}, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                UsuarioClass exercicioClass = new UsuarioClass();
                exercicioClass.setId(cursor.getInt(0));
                exercicioClass.setNome(cursor.getString(1));
                exercicioClass.setUsuario(cursor.getString(2));
                exercicioClass.setSenha(cursor.getString(3));
                lista.add(exercicioClass);

            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return lista;
    }

    //endregion

    //region [ EVENTO ]
    // Inserir
    void inserirEvento(EventoCadastroClass eventoCadastroClass) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("dataInicio", eventoCadastroClass.getDataInicio());
        valores.put("horaInicio", eventoCadastroClass.getHoraInicio());
        valores.put("dataFim", eventoCadastroClass.getDataFim());
        valores.put("horaFim", eventoCadastroClass.getHoraFim());
        valores.put("titulo", eventoCadastroClass.getTitulo());
        valores.put("descricao", eventoCadastroClass.getDescricao());
        valores.put("usuario", eventoCadastroClass.getUsuario());
        valores.put("nome", eventoCadastroClass.getNome());
        valores.put("local", eventoCadastroClass.getLocal());

        sqLiteDatabase.insert("evento", null, valores);
        sqLiteDatabase.close();
    }

    // Atualizar
    void atualizarEvento(EventoCadastroClass eventoCadastroClass) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("datainicio", eventoCadastroClass.getDataInicio());
        valores.put("horainicio", eventoCadastroClass.getHoraInicio());
        valores.put("datafim", eventoCadastroClass.getDataFim());
        valores.put("horafim", eventoCadastroClass.getHoraFim());
        valores.put("titulo", eventoCadastroClass.getTitulo());
        valores.put("descricao", eventoCadastroClass.getDescricao());
        valores.put("nome", eventoCadastroClass.getNome());
        valores.put("usuario", eventoCadastroClass.getUsuario());
        valores.put("local", eventoCadastroClass.getLocal());

        sqLiteDatabase.update("evento", valores, "_id = ?", new String[]{"" + eventoCadastroClass.getId()});
        sqLiteDatabase.close();
    }

    // Pesquisar
    List<EventoCadastroClass> PesquisarEventosUsuario(String usuario) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        List<EventoCadastroClass> lista = new ArrayList<>();
        String[] colunas = new String[]{"_id", "datainicio", "horainicio", "datafim", "horafim", "titulo", "descricao", "nome", "usuario", "local"};

        Cursor cursor = sqLiteDatabase.query("evento", colunas, "usuario = ?", new String[]{"" + usuario}, null, null, "datainicio, horainicio;");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                EventoCadastroClass eventoCadastroClass = new EventoCadastroClass();
                eventoCadastroClass.setId(cursor.getInt(0));
                eventoCadastroClass.setDataInicio(cursor.getString(1));
                eventoCadastroClass.setHoraInicio(cursor.getString(2));
                eventoCadastroClass.setDataFim(cursor.getString(3));
                eventoCadastroClass.setHoraFim(cursor.getString(4));
                eventoCadastroClass.setTitulo(cursor.getString(5));
                eventoCadastroClass.setDescricao(cursor.getString(6));
                eventoCadastroClass.setLocal(cursor.getString(9));
                lista.add(eventoCadastroClass);


            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return lista;
    }

    List<EventoCadastroClass> PesquisarEventos() {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        List<EventoCadastroClass> lista = new ArrayList<>();
        String[] colunas = new String[]{"_id", "datainicio", "horainicio", "datafim", "horafim", "titulo", "descricao", "nome", "usuario", "local"};

        Cursor cursor = sqLiteDatabase.query("evento", colunas, null, null, null, null, "datainicio, horainicio;");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                EventoCadastroClass eventoCadastroClass = new EventoCadastroClass();
                eventoCadastroClass.setId(cursor.getInt(0));
                eventoCadastroClass.setDataInicio(cursor.getString(1));
                eventoCadastroClass.setHoraInicio(cursor.getString(2));
                eventoCadastroClass.setDataFim(cursor.getString(3));
                eventoCadastroClass.setHoraFim(cursor.getString(4));
                eventoCadastroClass.setTitulo(cursor.getString(5));
                eventoCadastroClass.setDescricao(cursor.getString(6));
                eventoCadastroClass.setNome(cursor.getString(7));
                eventoCadastroClass.setLocal(cursor.getString(9));
                lista.add(eventoCadastroClass);


            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return lista;
    }

    // Excluir
    void excluirEvento(int idEvento) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        sqLiteDatabase.delete("evento", "_id = ?", new String[]{"" + idEvento});
        sqLiteDatabase.close();
    }
    //endregion

    //region [ PARÂMETRO ]
    List<ParametroClass> pesquisarParametro() {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        List<ParametroClass> lista = new ArrayList<>();
        String[] colunas = new String[]{"_id", "nome", "valor"};

        Cursor cursor = sqLiteDatabase.query("parametros", colunas, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                ParametroClass parametroClass = new ParametroClass();
                parametroClass.setId(cursor.getInt(0));
                parametroClass.setNome(cursor.getString(1));
                parametroClass.setValor(cursor.getInt(2));
                lista.add(parametroClass);

            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return lista;
    }

    void atualizarParametro(ParametroClass parametroClass) {
        sqLiteDatabase = baseDadosSQLiteOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", parametroClass.getNome());
        valores.put("valor", parametroClass.getValor());

        sqLiteDatabase.update("parametros", valores, "_id = ?", new String[]{"" + parametroClass.getId()});

        sqLiteDatabase.close();
    }
    //endregion
}
