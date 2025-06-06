import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo");
        }
        livros.add(livro);
    }

    public void removerLivro(String isbn) {
        Livro livro = buscarLivroPorIsbn(isbn);
        if (livro != null) {
            livros.remove(livro);
        }
    }

    public Livro buscarLivroPorIsbn(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    public List<Livro> buscarLivrosPorTitulo(String titulo) {
        List<Livro> resultados = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultados.add(livro);
            }
        }
        return resultados;
    }

    public void registrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        usuarios.add(usuario);
    }

    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public void emprestarLivro(int usuarioId, String isbn) {
        Usuario usuario = buscarUsuarioPorId(usuarioId);
        Livro livro = buscarLivroPorIsbn(isbn);

        if (usuario == null || livro == null) {
            throw new IllegalArgumentException("Usuário ou livro não encontrado");
        }

        if (!livro.isDisponivel()) {
            throw new IllegalStateException("Livro não está disponível para empréstimo");
        }

        Emprestimo emprestimo = new Emprestimo(usuario, livro);
        emprestimos.add(emprestimo);
        livro.setDisponivel(false);
    }

    public void devolverLivro(String isbn) {
        Livro livro = buscarLivroPorIsbn(isbn);
        if (livro == null) {
            throw new IllegalArgumentException("Livro não encontrado");
        }

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().getIsbn().equals(isbn) && !emprestimo.isDevolvido()) {
                emprestimo.setDevolvido(true);
                livro.setDisponivel(true);
                return;
            }
        }
        throw new IllegalStateException("Livro não estava emprestado");
    }

    public List<Livro> listarLivrosDisponiveis() {
        List<Livro> disponiveis = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                disponiveis.add(livro);
            }
        }
        return disponiveis;
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        List<Emprestimo> ativos = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (!emprestimo.isDevolvido()) {
                ativos.add(emprestimo);
            }
        }
        return ativos;
    }
}
