public class Professor extends Usuario {

    public Professor(String nome, String senha, int idade, String cpf) {
        super(nome, senha, idade, cpf);
    }

    @Override
    public void exibirPerfil() {
        System.out.println("[Professor] " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("CPF: " + getCpf());
    }

    // POLIMORFISMO: menu exclusivo do professor
    @Override
    public void exibirMenu(Sistema sistema) {

        int opcao;

        do {
            System.out.println("\n===== MENU DO PROFESSOR =====");
            System.out.println("1 - Criar curso");
            System.out.println("2 - Meus cursos e alunos");
            System.out.println("3 - Publicar atividade");
            System.out.println("4 - Lançar nota");
            System.out.println("0 - Sair (logout)");

            opcao = Entrada.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    criarCurso(sistema);
                    break;
                case 2:
                    verMeusCursos(sistema);
                    break;
                case 3:
                    publicarAtividade(sistema);
                    break;
                case 4:
                    lancarNota(sistema);
                    break;
                case 0:
                    System.out.println("Logout realizado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void criarCurso(Sistema sistema) {

        String nome = Entrada.lerTexto("Nome do curso: ");

        if (nome.isEmpty()) {
            System.out.println("O nome do curso não pode ser vazio.");
            return;
        }

        if (sistema.criarCurso(nome, getCpf()) != null) {
            System.out.println("Curso criado com sucesso!");
        } else {
            System.out.println("Limite de cursos atingido.");
        }
    }

    private void verMeusCursos(Sistema sistema) {

        Curso[] meus = sistema.cursosDoProfessor(getCpf());

        if (meus.length == 0) {
            System.out.println("Você ainda não criou nenhum curso.");
            return;
        }

        for (int i = 0; i < meus.length; i++) {
            System.out.println("\n--- " + meus[i].getNome() + " ---");

            if (meus[i].getTotalAlunos() == 0) {
                System.out.println("(nenhum aluno matriculado)");
            }
            for (int j = 0; j < meus[i].getTotalAlunos(); j++) {
                Usuario aluno = sistema.buscarPorCpf(meus[i].getCpfAluno(j));
                System.out.println("- " + aluno.getNome() + " (CPF " + aluno.getCpf() + ")");
            }
        }
    }

    private void publicarAtividade(Sistema sistema) {

        Curso curso = escolherCurso(sistema);
        if (curso == null) {
            return;
        }

        String titulo = Entrada.lerTexto("Título da atividade: ");
        String descricao = Entrada.lerTexto("Descrição: ");

        if (titulo.isEmpty()) {
            System.out.println("O título não pode ser vazio.");
            return;
        }

        if (sistema.publicarAtividade(curso, titulo, descricao)) {
            System.out.println("Atividade publicada em " + curso.getNome() + "!");
        } else {
            System.out.println("Limite de atividades atingido.");
        }
    }

    private void lancarNota(Sistema sistema) {

        Curso curso = escolherCurso(sistema);
        if (curso == null) {
            return;
        }

        if (curso.getTotalAlunos() == 0) {
            System.out.println("Esse curso ainda não tem alunos matriculados.");
            return;
        }

        System.out.println("\n--- Alunos de " + curso.getNome() + " ---");
        for (int i = 0; i < curso.getTotalAlunos(); i++) {
            Usuario aluno = sistema.buscarPorCpf(curso.getCpfAluno(i));
            System.out.println((i + 1) + " - " + aluno.getNome());
        }

        int escolha = Entrada.lerInt("Escolha o aluno (0 para voltar): ");

        if (escolha == 0) {
            return;
        }
        if (escolha < 1 || escolha > curso.getTotalAlunos()) {
            System.out.println("Aluno inválido.");
            return;
        }

        String titulo = Entrada.lerTexto("Nome da avaliação (ex: Prova 1): ");
        if (titulo.isEmpty()) {
            System.out.println("O nome da avaliação não pode ser vazio.");
            return;
        }

        double valor = Entrada.lerDouble("Nota (0 a 10): ");
        if (valor < 0 || valor > 10) {
            System.out.println("A nota deve estar entre 0 e 10.");
            return;
        }

        if (sistema.lancarNota(curso, curso.getCpfAluno(escolha - 1), titulo, valor)) {
            System.out.println("Nota lançada com sucesso!");
        } else {
            System.out.println("Limite de notas atingido.");
        }
    }

    // Lista os cursos do professor e devolve o escolhido (ou null)
    private Curso escolherCurso(Sistema sistema) {

        Curso[] meus = sistema.cursosDoProfessor(getCpf());

        if (meus.length == 0) {
            System.out.println("Você ainda não criou nenhum curso.");
            return null;
        }

        System.out.println("\n--- Seus cursos ---");
        for (int i = 0; i < meus.length; i++) {
            System.out.println((i + 1) + " - " + meus[i].getNome());
        }

        int escolha = Entrada.lerInt("Escolha o curso (0 para voltar): ");

        if (escolha == 0) {
            return null;
        }
        if (escolha < 1 || escolha > meus.length) {
            System.out.println("Curso inválido.");
            return null;
        }
        return meus[escolha - 1];
    }
}
