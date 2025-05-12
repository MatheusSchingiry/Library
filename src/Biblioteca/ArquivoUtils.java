package Biblioteca;

import java.io.*;
import java.util.*;

public class ArquivoUtils {

    public static void salvarTemas(List<Tema> temas, String caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (Tema tema : temas) {
                writer.write(tema.getNome());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar temas: " + e.getMessage());
        }
    }

    public static List<Tema> carregarTemas(String caminho) {
        List<Tema> temas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                temas.add(new Tema(linha));
            }
        } catch (IOException e) {
            System.out.println("Arquivo de temas não encontrado. Será criado ao salvar.");
        }
        return temas;
    }

    public static void salvarObras(List<Obra> obras, String caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (Obra obra : obras) {
                writer.write(obra.getNome() + ";" + obra.getTitulo() + ";" + obra.getConteudo().replace("\n", "\\n"));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar obras: " + e.getMessage());
        }
    }

    public static List<Obra> carregarObras(String caminho) {
        List<Obra> obras = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";", 3);
                if (partes.length == 3) {
                    String nome = partes[0];
                    String titulo = partes[1];
                    String conteudo = partes[2].replace("\\n", "\n");
                    obras.add(new Obra(nome, titulo, conteudo));
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo de obras não encontrado. Será criado ao salvar.");
        }
        return obras;
    }
}