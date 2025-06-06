class Emprestimo {
    private Usuario usuario;
    private Livro livro;
    private boolean devolvido;

    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.devolvido = false;
    }

    // Getters e Setters
    public Usuario getUsuario() { return usuario; }
    public Livro getLivro() { return livro; }
    public boolean isDevolvido() { return devolvido; }
    public void setDevolvido(boolean devolvido) { this.devolvido = devolvido; }
}
