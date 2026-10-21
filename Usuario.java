public abstract class Usuario {

    private String nome;
    private String senha;
    private int idade;
    private String cpf;

    public Usuario(String nome, String senha, int idade, String cpf) {
        this.nome = nome;
        this.senha = senha;
        this.idade = idade;
        this.cpf = cpf;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    // Método polimórfico: cada tipo de usuário mostra o perfil do seu jeito
    public void exibirPerfil() {
        System.out.println("[Usuário] " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("CPF: " + cpf);
    }

    // Método polimórfico: cada tipo de usuário tem o seu próprio menu
    public abstract void exibirMenu(Sistema sistema);
}
