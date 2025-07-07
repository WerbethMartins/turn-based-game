package Menu;

public class mensagemSleep {

    public void mensagemSleep(String mensagem){
        try{
            System.out.println(mensagem);
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);
            System.out.println("Finalizando o carregamento...");
            Thread.sleep(500);
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
