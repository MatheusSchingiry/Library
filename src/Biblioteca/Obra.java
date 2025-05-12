package Biblioteca;

public class Obra extends Tema{

    private String titulo;
    private String conteudo;

    public Obra(String nome) {
        super(nome);
    }

    public Obra(String nome, String titulo, String conteudo) {
        super(nome);
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
