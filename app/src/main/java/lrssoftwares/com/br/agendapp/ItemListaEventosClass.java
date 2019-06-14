package lrssoftwares.com.br.agendapp;

public class ItemListaEventosClass {
    private int id;
    private String dataInicio;
    private String horaInicio;
    private String dataFim;
    private String horaFim;
    private String titulo;
    private String descricao;
    private String nome;
    private String local;

    ItemListaEventosClass(int id, String dataInicio, String horaInicio, String dataFim, String horaFim, String titulo, String descricao, String nome, String local) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
        this.dataFim = dataFim;
        this.horaFim = horaFim;
        this.titulo = titulo;
        this.descricao = descricao;
        this.nome = nome;
        this.local = local;
    }

    ItemListaEventosClass(EventoCadastroClass eventoCadastroClass) {
        this.id = eventoCadastroClass.getId();
        this.dataInicio = eventoCadastroClass.getDataInicio();
        this.horaInicio = eventoCadastroClass.getHoraInicio();
        this.dataFim = eventoCadastroClass.getDataFim();
        this.horaFim = eventoCadastroClass.getHoraFim();
        this.titulo = eventoCadastroClass.getTitulo();
        this.descricao = eventoCadastroClass.getDescricao();
        this.nome = eventoCadastroClass.getNome();
        this.local = eventoCadastroClass.getLocal();
    }

    public int getId() {
        return id;
    }

    String getDataInicio() {
        return dataInicio;
    }

    String getHoraInicio() {
        return horaInicio;
    }

    String getDataFim() {
        return dataFim;
    }

    String getHoraFim() {
        return horaFim;
    }

    String getTitulo() {
        return titulo;
    }

    String getDescricao() {
        return descricao;
    }

    String getNome() {
        return nome;
    }

    String getLocal() {
        return local;
    }
}
