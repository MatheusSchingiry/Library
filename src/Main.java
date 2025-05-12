import Biblioteca.ArquivoUtils;
import Biblioteca.Obra;
import Biblioteca.Tema;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Tema> temas = ArquivoUtils.carregarTemas("temas.txt");
        List<Obra> obras = ArquivoUtils.carregarObras("obras.txt");
        int opcao;

        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║        BIBLIOTECA MULTITEMÁTICO        ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1 - Listar Base                        ║");
            System.out.println("║ 2 - Gerenciar Base                     ║");
            System.out.println("║ 3 - Sair                               ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("Selecione uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        menuListar(scanner, temas, obras);
                        break;
                    case 2:
                        menuGerenciar(scanner, temas, obras);
                        break;
                    case 3:
                        ArquivoUtils.salvarTemas(temas, "temas.txt");
                        ArquivoUtils.salvarObras(obras, "obras.txt");
                        System.out.println("Dados salvos. Encerrando programa...");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número.");
                scanner.nextLine(); // Limpa entrada inválida (ex: letra)
            }
        }
    }

    private static void menuListar(Scanner scanner, List<Tema> temas, List<Obra> obras) {
        int contadorGlobal = 1;
        Map<Integer, Obra> mapaOpcoes = new HashMap<>();

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("        Temas e Obras        ");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        for (Tema tema : temas) {
            System.out.println(tema.getNome() + ":");

            int contadorLocal = 1;
            for (Obra obra : obras) {
                if (tema.getNome().equals(obra.getNome())) {
                    System.out.printf("   %d - %s%n", contadorGlobal, obra.getTitulo());
                    mapaOpcoes.put(contadorGlobal, obra);
                    contadorGlobal++;
                    contadorLocal++;
                }
            }

            System.out.println();
        }

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.print("Digite o número da obra para ver detalhes ou 0 para sair: ");

        int opcao;
        try {
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Retornando ao menu.");
            scanner.nextLine(); // limpar buffer inválido
            return;
        }

        if (opcao == 0) return;

        Obra obraSelecionada = mapaOpcoes.get(opcao);
        if (obraSelecionada != null) {
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("Título: " + obraSelecionada.getTitulo());
            System.out.println("   Conteúdo: " + obraSelecionada.getConteudo());
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        } else {
            System.out.println("Número inválido. Tente novamente.");
        }
    }

    private static void menuGerenciar(Scanner scanner, List<Tema> temas, List<Obra> obras){
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║         MENU GERENCIAR         ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║ 1 - Temas                      ║");
        System.out.println("║ 2 - Obras                      ║");
        System.out.println("║ 3 - Voltar                     ║");
        System.out.println("╚════════════════════════════════╝");
        System.out.print("Selecione uma opcao: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                menuTemas(scanner, temas, obras);
                break;
            case 2:
                menuObras(scanner, temas, obras);
                break;
            case 3:
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void menuTemas(Scanner scanner, List<Tema> temas, List<Obra> obras) {
        System.out.println("\n╔════════════════════════════╗");
        System.out.println("║         MENU TEMAS         ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║ 1 - Adicionar novo tema    ║");
        System.out.println("║ 2 - Excluir tema           ║");
        System.out.println("║ 3 - Voltar                 ║");
        System.out.println("╚════════════════════════════╝");
        System.out.print("Selecione a opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Digite o tema: ");
                String nomeTema = scanner.nextLine();
                temas.add(new Tema(nomeTema));
                System.out.println("\nCriado com Sucesso!\n");
                break;

            case 2:
                if (temas.isEmpty()) {
                    System.out.println("Nenhum tema cadastrado.\n");
                    break;
                }
                listarTemas(temas);
                System.out.print("Digite o número do tema que deseja apagar: ");
                int indexTema = scanner.nextInt();
                scanner.nextLine();
                if (indexTema > 0 && indexTema <= temas.size()) {
                    // Obter o nome do tema a ser removido
                    String nomeTemaRemover = temas.get(indexTema - 1).getNome();

                    // Contar quantas obras estão associadas ao tema
                    long obrasAssociadas = obras.stream()
                            .filter(obra -> obra.getNome().equals(nomeTemaRemover))
                            .count();

                    // Avisar o usuário sobre as obras associadas
                    if (obrasAssociadas > 0) {
                        System.out.println("Atenção: O tema '" + nomeTemaRemover + "' possui " + obrasAssociadas + " obra(s) associada(s) que serão apagadas junto.");
                    }

                    // Remover todas as obras associadas a este tema
                    obras.removeIf(obra -> obra.getNome().equals(nomeTemaRemover));

                    // Remover o tema
                    temas.remove(indexTema - 1);
                    System.out.println("\nTema e suas obras associadas foram removidos com sucesso!\n");
                } else {
                    System.out.println("Índice inválido.\n");
                }
                break;

            case 3:
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void menuObras(Scanner scanner, List<Tema> temas, List<Obra> obras) {
        System.out.println("\n╔════════════════════════════╗");
        System.out.println("║         MENU OBRAS         ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║ 1 - Adicionar nova obra    ║");
        System.out.println("║ 2 - Excluir obra           ║");
        System.out.println("║ 3 - Voltar                 ║");
        System.out.println("╚════════════════════════════╝");
        System.out.print("Selecione a opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                if (temas.isEmpty()) {
                    System.out.println("\nNenhum tema cadastrado!\n");
                    return;
                }
                listarTemas(temas);
                System.out.print("Digite o número do tema: ");
                int temaIndex = scanner.nextInt();
                scanner.nextLine();

                if (temaIndex < 1 || temaIndex > temas.size()) {
                    System.out.println("Tema inválido.\n");
                    return;
                }

                System.out.print("Digite o título da obra: ");
                String nomeObra = scanner.nextLine();
                System.out.print("Digite o conteúdo da obra: ");
                String conteudoObra = scanner.nextLine();

                obras.add(new Obra(temas.get(temaIndex - 1).getNome(), nomeObra, conteudoObra));
                System.out.println("\nCadastrado com Sucesso.\n");
                break;

            case 2:
                if (temas.isEmpty() || obras.isEmpty()) {
                    System.out.println("Nenhum tema ou obra cadastrada.\n");
                    return;
                }
                listarTemas(temas);
                System.out.print("Digite o número do tema: ");
                int index = scanner.nextInt();
                scanner.nextLine();

                if (index < 1 || index > temas.size()) {
                    System.out.println("Tema inválido.\n");
                    return;
                }

                String temaSelecionado = temas.get(index - 1).getNome();
                List<Obra> obrasDoTema = new ArrayList<>();

                for (Obra obra : obras) {
                    if (obra.getNome().equals(temaSelecionado)) {
                        obrasDoTema.add(obra);
                    }
                }

                if (obrasDoTema.isEmpty()) {
                    System.out.println("Nenhuma obra encontrada para esse tema.\n");
                    return;
                }

                for (int i = 0; i < obrasDoTema.size(); i++) {
                    System.out.println((i + 1) + " - " + obrasDoTema.get(i).getTitulo());
                }

                System.out.print("Digite o número da obra que deseja remover: ");
                int obraIndex = scanner.nextInt();
                scanner.nextLine();

                if (obraIndex > 0 && obraIndex <= obrasDoTema.size()) {
                    obras.remove(obrasDoTema.get(obraIndex - 1));
                    System.out.println("Removido com Sucesso!\n");
                } else {
                    System.out.println("Índice inválido.\n");
                }
                break;

            case 3:
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void listarTemas(List<Tema> temas) {
        for (int i = 0; i < temas.size(); i++) {
            System.out.println((i + 1) + " - " + temas.get(i).getNome());
        }
        System.out.println();
    }
}