import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BibliotecaTest {
    
    @Test
    public void testAdicionarLivro() {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro = new Livro("123", "Java 101", "Autor X");
        
        biblioteca.adicionarLivro(livro);
        
        assertEquals(1, biblioteca.listarLivrosDisponiveis().size());
        assertEquals(livro, biblioteca.buscarLivroPorIsbn("123"));
    }
    
    @Test
    public void testAdicionarLivroNulo() {
        Biblioteca biblioteca = new Biblioteca();
        
        assertThrows(IllegalArgumentException.class, () -> {
            biblioteca.adicionarLivro(null);
        });
    }

    //  Teste remoção de livro
    @Test
    public void testRemoverLivro() {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro = new Livro("123", "Java 101", "Autor X");
        biblioteca.adicionarLivro(livro);

        biblioteca.removerLivro("123");

        assertNull(biblioteca.buscarLivroPorIsbn("123"));
        assertEquals(0, biblioteca.listarLivrosDisponiveis().size());
    }

    //  Teste busca por ISBN
    @Test
    public void testBuscarLivroPorIsbn() {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro = new Livro("123", "Java 101", "Autor X");
        biblioteca.adicionarLivro(livro);

        Livro encontrado = biblioteca.buscarLivroPorIsbn("123");

        assertNotNull(encontrado);
        assertEquals("Java 101", encontrado.getTitulo());
    }

    //  Teste busca por título
    @Test
    public void testBuscarLivrosPorTitulo() {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro1 = new Livro("123", "Java 101", "Autor X");
        Livro livro2 = new Livro("456", "Java Avançado", "Autor Y");
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);

        List<Livro> encontrados = biblioteca.buscarLivrosPorTitulo("Java");

        assertEquals(2, encontrados.size());
    }

    //  Teste registro de usuário
    @Test
    public void testRegistrarUsuario() {
        Biblioteca biblioteca = new Biblioteca();
        Usuario usuario = new Usuario(1, "João");

        biblioteca.registrarUsuario(usuario);

        Usuario encontrado = biblioteca.buscarUsuarioPorId(1);
        assertNotNull(encontrado);
        assertEquals("João", encontrado.getNome());
    }

    //  Teste empréstimo de livro
    @Test
    public void testEmprestarLivro() {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro = new Livro("123", "Java 101", "Autor X");
        Usuario usuario = new Usuario(1, "João");

        biblioteca.adicionarLivro(livro);
        biblioteca.registrarUsuario(usuario);

        biblioteca.emprestarLivro(1, "123");

        assertFalse(livro.isDisponivel());
        assertEquals(0, biblioteca.listarLivrosDisponiveis().size());
        assertEquals(1, biblioteca.listarEmprestimosAtivos().size());
    }

    //  Teste devolução de livro
    @Test
    public void testDevolverLivro() {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro = new Livro("123", "Java 101", "Autor X");
        Usuario usuario = new Usuario(1, "João");

        biblioteca.adicionarLivro(livro);
        biblioteca.registrarUsuario(usuario);
        biblioteca.emprestarLivro(1, "123");

        biblioteca.devolverLivro("123");

        assertTrue(livro.isDisponivel());
        assertEquals(1, biblioteca.listarLivrosDisponiveis().size());
        assertEquals(0, biblioteca.listarEmprestimosAtivos().size());
    }

    //  Teste listagem de livros disponíveis
    @Test
    public void testListarLivrosDisponiveis() {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro1 = new Livro("123", "Java 101", "Autor X");
        Livro livro2 = new Livro("456", "Estruturas de Dados", "Autor Y");
        Usuario usuario = new Usuario(1, "João");

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.registrarUsuario(usuario);

        biblioteca.emprestarLivro(1, "123");

        List<Livro> disponiveis = biblioteca.listarLivrosDisponiveis();
        assertEquals(1, disponiveis.size());
        assertEquals("456", disponiveis.get(0).getIsbn());
    }

    //  Teste listagem de empréstimos ativos
    @Test
    public void testListarEmprestimosAtivos() {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro1 = new Livro("123", "Java 101", "Autor X");
        Livro livro2 = new Livro("456", "Estruturas de Dados", "Autor Y");
        Usuario usuario1 = new Usuario(1, "João");
        Usuario usuario2 = new Usuario(2, "Maria");

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.registrarUsuario(usuario1);
        biblioteca.registrarUsuario(usuario2);

        biblioteca.emprestarLivro(1, "123");
        biblioteca.emprestarLivro(2, "456");

        List<Emprestimo> ativos = biblioteca.listarEmprestimosAtivos();
        assertEquals(2, ativos.size());
    }

    //  Teste erro: empréstimo de livro não disponível
    @Test
    public void testEmprestarLivroNaoDisponivel() {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro = new Livro("123", "Java 101", "Autor X");
        Usuario usuario1 = new Usuario(1, "João");
        Usuario usuario2 = new Usuario(2, "Maria");

        biblioteca.adicionarLivro(livro);
        biblioteca.registrarUsuario(usuario1);
        biblioteca.registrarUsuario(usuario2);

        biblioteca.emprestarLivro(1, "123");

        assertThrows(IllegalStateException.class, () -> {
            biblioteca.emprestarLivro(2, "123");
        });
    }

    //  Teste erro: devolução de livro não emprestado
    @Test
    public void testDevolverLivroNaoEmprestado() {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro = new Livro("123", "Java 101", "Autor X");
        biblioteca.adicionarLivro(livro);

        assertThrows(IllegalStateException.class, () -> {
            biblioteca.devolverLivro("123");
        });
    }

    //  Teste erro: empréstimo de livro inexistente
    @Test
    public void testEmprestarLivroInexistente() {
        Biblioteca biblioteca = new Biblioteca();
        Usuario usuario = new Usuario(1, "João");
        biblioteca.registrarUsuario(usuario);

        assertThrows(IllegalArgumentException.class, () -> {
            biblioteca.emprestarLivro(1, "999");
        });
    }

    //  Teste erro: empréstimo com usuário inexistente
    @Test
    public void testEmprestarComUsuarioInexistente() {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro = new Livro("123", "Java 101", "Autor X");
        biblioteca.adicionarLivro(livro);

        assertThrows(IllegalArgumentException.class, () -> {
            biblioteca.emprestarLivro(99, "123");
        });
    }
}
}
