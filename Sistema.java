// Guarda todos os dados do portal em ARRAYS e oferece os métodos de consulta/cadastro.
public class Sistema {

    private Usuario[] usuarios = new Usuario[100];
    private int totalUsuarios = 0;

    private Curso[] cursos = new Curso[50];
    private int totalCursos = 0;

    private Atividade[] atividades = new Atividade[200];
    private int totalAtividades = 0;

    private Nota[] notas = new Nota[500];
    private int totalNotas = 0;

    // ================= USUÁRIOS =================

    // Retorna false se o CPF já existe ou se o limite de usuários foi atingido
    public boolean cadastrarUsuario(Usuario usuario) {
        if (totalUsuarios >= usuarios.length || buscarPorCpf(usuario.getCpf()) != null) {
            return false;
        }
        usuarios[totalUsuarios] = usuario;
        totalUsuarios++;
        return true;
    }

    public boolean cpfJaCadastrado(String cpf) {
        return buscarPorCpf(cpf) != null;
    }

    public boolean usuariosCheio() {
        return totalUsuarios >= usuarios.length;
    }

    // Retorna o usuário (Aluno ou Professor) ou null se o login falhar
    public Usuario login(String nome, String senha) {
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i].getNome().equals(nome) && usuarios[i].getSenha().equals(senha)) {
                return usuarios[i];
            }
        }
        return null;
    }

    public Usuario buscarPorCpf(String cpf) {
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i].getCpf().equals(cpf)) {
                return usuarios[i];
            }
        }
        return null;
    }

    // ================= CURSOS =================

    public Curso criarCurso(String nome, String cpfProfessor) {
        if (totalCursos >= cursos.length) {
            return null;
        }
        Curso curso = new Curso(nome, cpfProfessor);
        cursos[totalCursos] = curso;
        totalCursos++;
        return curso;
    }

    public Curso[] getCursos() {
        Curso[] resultado = new Curso[totalCursos];
        for (int i = 0; i < totalCursos; i++) {
            resultado[i] = cursos[i];
        }
        return resultado;
    }

    public Curso[] cursosDoProfessor(String cpfProfessor) {
        int contador = 0;
        for (int i = 0; i < totalCursos; i++) {
            if (cursos[i].getCpfProfessor().equals(cpfProfessor)) {
                contador++;
            }
        }
        Curso[] resultado = new Curso[contador];
        int posicao = 0;
        for (int i = 0; i < totalCursos; i++) {
            if (cursos[i].getCpfProfessor().equals(cpfProfessor)) {
                resultado[posicao] = cursos[i];
                posicao++;
            }
        }
        return resultado;
    }

    public Curso[] cursosDoAluno(String cpfAluno) {
        int contador = 0;
        for (int i = 0; i < totalCursos; i++) {
            if (cursos[i].temAluno(cpfAluno)) {
                contador++;
            }
        }
        Curso[] resultado = new Curso[contador];
        int posicao = 0;
        for (int i = 0; i < totalCursos; i++) {
            if (cursos[i].temAluno(cpfAluno)) {
                resultado[posicao] = cursos[i];
                posicao++;
            }
        }
        return resultado;
    }

    // ================= ATIVIDADES =================

    public boolean publicarAtividade(Curso curso, String titulo, String descricao) {
        if (totalAtividades >= atividades.length) {
            return false;
        }
        atividades[totalAtividades] = new Atividade(curso, titulo, descricao);
        totalAtividades++;
        return true;
    }

    public Atividade[] atividadesDoCurso(Curso curso) {
        int contador = 0;
        for (int i = 0; i < totalAtividades; i++) {
            if (atividades[i].getCurso() == curso) {
                contador++;
            }
        }
        Atividade[] resultado = new Atividade[contador];
        int posicao = 0;
        for (int i = 0; i < totalAtividades; i++) {
            if (atividades[i].getCurso() == curso) {
                resultado[posicao] = atividades[i];
                posicao++;
            }
        }
        return resultado;
    }

    // ================= NOTAS =================

    public boolean lancarNota(Curso curso, String cpfAluno, String titulo, double valor) {
        if (totalNotas >= notas.length) {
            return false;
        }
        notas[totalNotas] = new Nota(curso, cpfAluno, titulo, valor);
        totalNotas++;
        return true;
    }

    public Nota[] notasDoAluno(String cpfAluno) {
        int contador = 0;
        for (int i = 0; i < totalNotas; i++) {
            if (notas[i].getCpfAluno().equals(cpfAluno)) {
                contador++;
            }
        }
        Nota[] resultado = new Nota[contador];
        int posicao = 0;
        for (int i = 0; i < totalNotas; i++) {
            if (notas[i].getCpfAluno().equals(cpfAluno)) {
                resultado[posicao] = notas[i];
                posicao++;
            }
        }
        return resultado;
    }
}
