public class Nota {

    private Curso curso;
    private String cpfAluno;
    private String titulo;   // ex: "Prova 1", "Trabalho final"
    private double valor;

    public Nota(Curso curso, String cpfAluno, String titulo, double valor) {
        this.curso = curso;
        this.cpfAluno = cpfAluno;
        this.titulo = titulo;
        this.valor = valor;
    }

    public Curso getCurso() {
        return curso;
    }

    public String getCpfAluno() {
        return cpfAluno;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getValor() {
        return valor;
    }
}
