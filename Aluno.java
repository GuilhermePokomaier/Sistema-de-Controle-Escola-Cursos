public class Aluno extends Usuario {

    public Aluno(String nome, String senha, int idade, String cpf) {
        super(nome, senha, idade, cpf);
    }

    @Override
    public void exibirPerfil() {
        System.out.println("[Aluno] " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("CPF: " + getCpf());
    }

    // POLIMORFISMO: menu exclusivo do aluno
    @Override
    public void exibirMenu(Sistema sistema) {

        int opcao;

        do {
            System.out.println("\n===== MENU DO ALUNO =====");
            System.out.println("1 - Matricular-se em um curso");
            System.out.println("2 - Meus cursos");
            System.out.println("3 - Ver atividades");
            System.out.println("4 - Ver minhas notas");
            System.out.println("0 - Sair (logout)");

            opcao = Entrada.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    matricular(sistema);
                    break;
                case 2:
                    verMeusCursos(sistema);
                    break;
                case 3:
                    verAtividades(sistema);
                    break;
                case 4:
                    verNotas(sistema);
                    break;
                case 0:
                    System.out.println("Logout realizado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void matricular(Sistema sistema) {

        Curso[] todos = sistema.getCursos();

        if (todos.length == 0) {
            System.out.println("Ainda não existem cursos cadastrados.");
            return;
        }

        System.out.println("\n--- Cursos disponíveis ---");
        for (int i = 0; i < todos.length; i++) {
            Usuario professor = sistema.buscarPorCpf(todos[i].getCpfProfessor());
            String status = todos[i].temAluno(getCpf()) ? " [já matriculado]" : "";
            System.out.println((i + 1) + " - " + todos[i].getNome()
                    + " (Prof. " + professor.getNome() + ")" + status);
        }

        int escolha = Entrada.lerInt("Escolha o curso (0 para voltar): ");

        if (escolha == 0) {
            return;
        }
        if (escolha < 1 || escolha > todos.length) {
            System.out.println("Curso inválido.");
            return;
        }

        if (todos[escolha - 1].matricular(getCpf())) {
            System.out.println("Matrícula realizada em " + todos[escolha - 1].getNome() + "!");
        } else {
            System.out.println("Não foi possível matricular (já matriculado ou turma cheia).");
        }
    }

    private void verMeusCursos(Sistema sistema) {

        Curso[] meus = sistema.cursosDoAluno(getCpf());

        if (meus.length == 0) {
            System.out.println("Você ainda não está matriculado em nenhum curso.");
            return;
        }

        System.out.println("\n--- Meus cursos ---");
        for (int i = 0; i < meus.length; i++) {
            Usuario professor = sistema.buscarPorCpf(meus[i].getCpfProfessor());
            System.out.println("- " + meus[i].getNome() + " (Prof. " + professor.getNome() + ")");
        }
    }

    private void verAtividades(Sistema sistema) {

        Curso[] meus = sistema.cursosDoAluno(getCpf());

        if (meus.length == 0) {
            System.out.println("Você ainda não está matriculado em nenhum curso.");
            return;
        }

        for (int i = 0; i < meus.length; i++) {
            System.out.println("\n--- " + meus[i].getNome() + " ---");

            Atividade[] atividades = sistema.atividadesDoCurso(meus[i]);

            if (atividades.length == 0) {
                System.out.println("(nenhuma atividade publicada)");
            }
            for (int j = 0; j < atividades.length; j++) {
                System.out.println("* " + atividades[j].getTitulo() + ": " + atividades[j].getDescricao());
            }
        }
    }

    private void verNotas(Sistema sistema) {

        Nota[] notas = sistema.notasDoAluno(getCpf());

        if (notas.length == 0) {
            System.out.println("Nenhuma nota lançada até o momento.");
            return;
        }

        System.out.println("\n--- Minhas notas ---");
        double soma = 0;
        for (int i = 0; i < notas.length; i++) {
            System.out.println(notas[i].getCurso().getNome() + " | "
                    + notas[i].getTitulo() + " | " + notas[i].getValor());
            soma += notas[i].getValor();
        }
        System.out.println("Média geral: " + String.format("%.2f", soma / notas.length));
    }
}
