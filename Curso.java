public class Curso {

    private String nome;
    private String cpfProfessor;

    // ARRAY com o CPF dos alunos matriculados
    private String[] cpfsAlunos = new String[50];
    private int totalAlunos = 0;

    public Curso(String nome, String cpfProfessor) {
        this.nome = nome;
        this.cpfProfessor = cpfProfessor;
    }

    public String getNome() {
        return nome;
    }

    public String getCpfProfessor() {
        return cpfProfessor;
    }

    public int getTotalAlunos() {
        return totalAlunos;
    }

    public String getCpfAluno(int posicao) {
        return cpfsAlunos[posicao];
    }

    public boolean temAluno(String cpf) {
        for (int i = 0; i < totalAlunos; i++) {
            if (cpfsAlunos[i].equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    // Retorna false se o aluno já está matriculado ou se a turma está cheia
    public boolean matricular(String cpf) {
        if (temAluno(cpf) || totalAlunos >= cpfsAlunos.length) {
            return false;
        }
        cpfsAlunos[totalAlunos] = cpf;
        totalAlunos++;
        return true;
    }
}
