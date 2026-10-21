public class Index {

    public static void main(String[] args) {

        Sistema sistema = new Sistema();

        int opcao;

        do {

            System.out.println("\n===== SISTEMA DE CONTROLE ESCOLA/CURSOS =====");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar");
            System.out.println("3 - Encerrar programa");

            opcao = Entrada.lerInt("Escolha uma opção: ");

            switch (opcao) {

                // ================= LOGIN =================
                case 1:

                    String nomeLogin = Entrada.lerTexto("Digite seu nome: ");
                    String senhaLogin = Entrada.lerTexto("Digite sua senha: ");

                    Usuario usuario = sistema.login(nomeLogin, senhaLogin);

                    if (usuario == null) {
                        System.out.println("Nome ou senha incorretos.");
                    } else {
                        System.out.println("\nLogin realizado com sucesso!\n");

                        // POLIMORFISMO: o mesmo comando funciona para Aluno e Professor
                        usuario.exibirPerfil();
                        usuario.exibirMenu(sistema);
                    }

                    break;

                // ================= CADASTRO =================
                case 2:

                    if (sistema.usuariosCheio()) {
                        System.out.println("Limite de usuários atingido.");
                        break;
                    }

                    String nome = Entrada.lerTexto("Digite seu nome: ");
                    String senha = Entrada.lerTexto("Digite sua senha: ");
                    int idade = Entrada.lerInt("Digite sua idade: ");
                    String cpf = Entrada.lerTexto("Digite seu CPF: ");

                    if (nome.isEmpty() || senha.isEmpty() || cpf.isEmpty() || idade <= 0) {
                        System.out.println("Dados inválidos. Cadastro cancelado.");
                        break;
                    }

                    if (sistema.cpfJaCadastrado(cpf)) {
                        System.out.println("Já existe um usuário com esse CPF.");
                        break;
                    }

                    System.out.println("\nTipo de usuário:");
                    System.out.println("1 - Aluno");
                    System.out.println("2 - Professor");

                    int tipo = Entrada.lerInt("Escolha: ");

                    if (tipo == 2) {
                        sistema.cadastrarUsuario(new Professor(nome, senha, idade, cpf));
                    } else {
                        sistema.cadastrarUsuario(new Aluno(nome, senha, idade, cpf));
                    }

                    System.out.println("Cadastro realizado com sucesso!");

                    break;

                // ================= FINALIZANDO =================
                case 3:

                    System.out.println("Programa encerrado.");
                    break;

                default:

                    System.out.println("Opção inválida.");
            }

        } while (opcao != 3);
    }
}
