package lrssoftwares.com.br.agendapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

    private static final String nomeBase = "BaseDados_AgendaApp";
    private static final int versaoBase = 1;

    SQLiteOpenHelper(Context contexto) {
        super(contexto, nomeBase, null, versaoBase);
    }

    @Override
    public void onCreate(SQLiteDatabase sdBaseDados) {

        // Tabela usuário
        sdBaseDados.execSQL("create table usuario (_id integer primary key autoincrement, nome text not null, usuario text not null, senha text not null);");
        sdBaseDados.execSQL("insert into usuario values (1, 'Luiz Rogério Scudeletti', 'Ro', '123');");
        sdBaseDados.execSQL("insert into usuario values (2, 'Nome de Teste', 'Teste', 'teste');");

        // Tabela usuário
        sdBaseDados.execSQL("create table evento (_id integer primary key autoincrement, datainicio text not null, horainicio text not null, datafim text, horafim text, titulo text not null, descricao text not null, usuario text not null, nome text not null, local text not null);");
        sdBaseDados.execSQL("insert into evento values (1, '17/12/2018', '14:00', '17/12/2018', '17:00', 'Aula', 'Aula de programação para as crianças do projeto vida.', 'Ro', 'Luiz Rogério Scudeletti', 'Barra Bonita - SP');");
        sdBaseDados.execSQL("insert into evento values (2, '20/12/2018', '13:30', '20/12/2018', '17:30', 'Palestra', 'Palestra sobre qualidade de software nas empresas.', 'Ro', 'Luiz Rogério Scudeletti', 'Barra Bonita - SP');");
        sdBaseDados.execSQL("insert into evento values (3, '21/12/2018', '13:30', '21/12/2018', '17:30', 'Show no clube de campo', 'Show com a banda de rock Hulligans - Evento gratuito.', 'Ro', 'Luiz Rogério Scudeletti', 'Unesp - Bauru - SP');");
        sdBaseDados.execSQL("insert into evento values (4, '24/12/2018', '14:00', '24/12/2018', '17:00', 'Aula', 'Aula de programação para as crianças do projeto vida.', 'Ro', 'Luiz Rogério Scudeletti', 'Unesp - Bauru -SP');");
        sdBaseDados.execSQL("insert into evento values (5, '26/01/2019', '14:00', '26/01/2019', '17:00', 'Evento Unesp', 'Futebol beneficiente na quadra da Unesp - Entrada: 1kg de alimento.', 'Ro', 'Luiz Rogério Scudeletti', 'Igaraçu do Tietê - SP');");

        sdBaseDados.execSQL("insert into evento values (6, '18/12/2018', '13:30', '18/12/2018', '17:30', 'Palestra', 'Palestra sobre qualidade de software nas empresas.', 'Teste', 'Nome de Teste', 'E. E. José Conti - São Paulo - SP');");
        sdBaseDados.execSQL("insert into evento values (7, '19/12/2018', '13:30', '19/12/2018', '17:30', 'Palestra', 'Palestra sobre desenvolvimenro de sistemas.', 'Teste', 'Nome de Teste', 'São Paulo - SP');");
        sdBaseDados.execSQL("insert into evento values (8, '23/12/2018', '13:30', '23/12/2018', '17:30', 'Palestra', 'Palestra sobre programação com Java e Spring Boot.', 'Teste', 'Nome de Teste', 'São Paulo - SP');");

        // Criando tabela parâmetros
        sdBaseDados.execSQL("create table parametros (_id integer primary key autoincrement, nome text, valor int)");

        // Inserindo parâmetro Tela Cheia
        sdBaseDados.execSQL("insert into parametros values (1, 'Tema', 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sdBaseDados, int versaoAnterior, int versaoAtual) {

    }
}
