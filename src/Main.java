
import Menu.Menu;
import Personagens.Arqueiro;
import Personagens.Personagem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Batalha batalha = new Batalha();

        batalha.batalhaTurnos();

    }
}