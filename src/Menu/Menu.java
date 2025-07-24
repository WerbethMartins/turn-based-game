package Menu;

import Util.mensagemSleep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    mensagemSleep mensagem = new mensagemSleep();
    ArrayList<String> listaDificuldade = new ArrayList<>(Arrays.asList(
        "Fácil",
        "Normal",
        "Difícil",
        "Viciado"
    ));

    public void exibirMenuIniciar(){

        try{
            while(true){
                System.out.println("***** RPG *****");
                System.out.println("(1) - Iniciar o jogo");
                System.out.println("(2) - Criar personagem");
                System.out.println("(3) - Configurações");
                System.out.println("(0) - Sair");
                int op = scanner.nextInt();

                if(op == 0){
                    mensagem.mensagemSleep("Fechando o jogo.");
                    break;
                }

                switch (op){
                    case 1:
                        mensagem.mensagemSleep("Indo para o menu de dificuldades");
                        menuDificuldade(listaDificuldade);
                        break;
                    case 2:
                        System.out.println("Criação de personagens.");
                        break;
                    case 3:
                        System.out.println("Configuração");
                        break;
                    default:
                        break;
                }

            }
        }catch(InputMismatchException e){
            System.out.println("Entrada inválida");
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void menuDificuldade(ArrayList<String> dificuldade){
        try{
            while(true){
                System.out.println("***** Escolha a dificuldade *****");
                for (int i = 0; i < listaDificuldade.size(); i++) {
                    System.out.println("(" + (i+1) + ") - " + listaDificuldade.get(i));
                }
                System.out.println("(0) - Sair");
                int op = scanner.nextInt();

                if(op == 0) {
                    mensagem.mensagemSleep("Vontando ao menu iniciar...");
                    break;
                } else {
                    System.out.println("Tente novamente!");
                }
            }
        }catch(InputMismatchException e){
            System.out.println("Entrada inválida.");
        }catch(Exception e){
            System.out.println("erro: " + e.getMessage());
        }
    }
}
