class Livro {
    private String isbn;
    private String titulo;
    private String autor;
    private boolean disponivel;

    public Livro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    // Getters e Setters
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}
