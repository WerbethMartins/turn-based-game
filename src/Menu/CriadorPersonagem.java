package Menu;
import Util.mensagemSleep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CriadorPersonagem {
    Scanner scanner = new Scanner(System.in);
    mensagemSleep mensagem = new mensagemSleep();

    public void criarPersonagem(){

        ArrayList<String> personagens = new ArrayList<>(Arrays.asList(
               "Arqueiro",
               "Guerreiro",
               "Mago"
        ));

        System.out.print("Digite o nome do seu personagem: ");
        String nome = scanner.next();

        try {
            while(true){
                for (int i = 0; i < personagens.size(); i++) {
                    System.out.println("(" + (i+1) + ") - " + personagens.get(i));
                }
                System.out.println("(0) - Sair");
                System.out.println("Opção: ");
                int op = scanner.nextInt();

                if(op == 0) {
                    mensagem.mensagemSleep("Voltando para o menu iniciar...");
                    break;
                }
            }
        }catch(InputMismatchException e){
            System.out.println("Entráda inválida.");
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
