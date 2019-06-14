package lrssoftwares.com.br.agendapp;

class EventoCadastroClass {
    private int id;
    private String dataInicio;
    private String horaInicio;
    private String dataFim;
    private String horaFim;
    private String titulo;
    private String descricao;
    private String local;
    private String usuario;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String getDataInicio() {
        return dataInicio;
    }

    void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    String getHoraInicio() {
        return horaInicio;
    }

    void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    String getDataFim() {
        return dataFim;
    }

    void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    String getHoraFim() {
        return horaFim;
    }

    void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    String getTitulo() {
        return titulo;
    }

    void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    String getDescricao() {
        return descricao;
    }

    void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    String getUsuario() {
        return usuario;
    }

    void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}