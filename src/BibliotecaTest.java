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
    
    // Os alunos devem continuar com os outros testes...
}
