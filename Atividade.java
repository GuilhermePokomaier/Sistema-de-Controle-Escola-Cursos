public class Atividade {

    private Curso curso;
    private String titulo;
    private String descricao;

    public Atividade(Curso curso, String titulo, String descricao) {
        this.curso = curso;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Curso getCurso() {
        return curso;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
