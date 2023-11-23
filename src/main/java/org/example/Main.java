package org.example;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        doubleLinkedList list = null;

        Scanner input = new Scanner(System.in);
        String command = "i";
        boolean functional = true;
        System.out.println("(use :help para ver todos os comandos disponíveis).");
        while(functional) {
            System.out.println("digite seu comando:");
            command = input.nextLine();

            if (command.startsWith(":e")) {
                try {
                    String[] commandContent = command.split(" ");

                    list = fileOperations.transferFileDataToDoubleLinkedList(commandContent[1]);
                    System.out.println("Criando Lista\n");
                    list.print();
                    System.out.println("\nLista criada :)\n");
                }catch (FileNotFoundException e){
                    System.out.println("Epa! Arquivo nao encontrado. tente novamente\n");
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Epa digite um arquivo por favor\n");
                }
            }

            if (command.startsWith(":w")) {
                try {
                    String[] commandContent = command.split(" ");

                    if (list == null) {
                        System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                    } else {
                        System.out.println("transferindo conteudo da lista encadeada para o arquivo: " + commandContent[1]);

                        list.transferDataToFile(commandContent[1]);
                    }
                }catch (ArrayIndexOutOfBoundsException exception){
                    System.out.println("Epa! informe o arquivo em que deseja escrever\n");
                }
            }
            if (command.startsWith(":x")) {
                try {
                    String[] commandContent = command.split(" ");
                    if (list == null) {
                        System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                    } else {
                        list.remove(Integer.parseInt(commandContent[1]));

                        System.out.println("Lista atual : \n");
                        list.reList();
                        list.print();
                    }
                }catch (Exception exception){
                    System.out.println("Epa algo deu errado tente novamente\n");
                }
            }
            if (command.startsWith(":xG")) {
                try {
                    String[] commandContent = command.split(" ");
                    if (list == null) {
                        System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                    } else {
                        int length = list.getCount();
                        for (int i = Integer.parseInt(commandContent[1]); i <= length + 1; i++) {
                            list.remove(i);
                        }

                        System.out.println("Lista atual : \n");
                        list.print();
                    }
                }catch (Exception exception){
                    System.out.println("Epa algo deu errado tente novamente\n");
                }
            }
            if (command.startsWith(":XG")) {
                try {
                    String[] commandContent = command.split(" ");
                    if (list == null) {
                        System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                    } else {

                        for (int i = 1; i <= Integer.parseInt(commandContent[1]); i++) {
                            list.remove(i);
                        }

                        System.out.println("Lista atual : \n");
                        list.reList();
                        list.print();
                    }
                }catch (Exception exception){
                    System.out.println("Epa algo deu errado tente novamente\n");
                }
            }
            if (command.startsWith(":s")) {
                try {
                    String[] commandContent = command.split(" ");
                    if (list == null) {
                        System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                    } else if (commandContent.length == 3) {
                        Node current = list.getHead();
                        int length = list.getCount();
                        int i = Integer.parseInt(commandContent[1]);
                        for (int k = 1; k <= length; k++) {
                            if (k == Integer.parseInt(commandContent[1])) {
                                for (int m = Integer.parseInt(commandContent[1]); m <= Integer.parseInt(commandContent[2]); m++) {
                                    System.out.println(i + ". " +current.getData());
                                    current = current.getRight();
                                    i++;
                                    if(i%11 == 0){
                                        System.out.println("\n");
                                    }
                                }
                            }
                            current = current.getRight();
                        }
                    }
                    else {

                        Node current = list.getHead();
                        int i = 1;
                        do {
                            for (int j = 1; j <= 10; j++) {

                                System.out.println(i + ". " + current.getData());
                                current = current.getRight();
                                if (current == list.getHead()) {
                                    break;
                                }
                                i++;
                            }
                            System.out.println("\n");
                        }
                        while (current != list.getHead());
                    }
                }catch (Exception exception){
                    System.out.println(exception+"Epa algo deu errado tente novamente\n");
                }
            }
            if (command.startsWith(":/")) {

                try {
                    String[] commandContent = command.split(" ");
                    if (list == null) {
                        System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                    } else {
                        String element = commandContent[1];
                        if(commandContent.length == 2){
                        System.out.println("Procurando pelo elemento: " + element);
                        Node current = list.getHead();
                        int line = 1;
                        int found = 0;
                        do {
                            if (Objects.equals(current.getData(), element)) {
                                System.out.println(line + ". " + current.getData());
                                found = 1;
                            }
                            current = current.getRight();
                            line++;
                        } while (current != list.getHead());
                        if (current == list.getHead() && found == 0) {
                            System.out.println(element + " nao encontrado");
                        }
                    }if (commandContent.length == 3){
                            String elem = commandContent[1];
                            String elemTroca = commandContent[2];
                            System.out.println("Procurando pelo elemento: " + elem + " para substituir por: " + elemTroca);
                            Node current = list.getHead();
                            int line = 1;
                            do {
                                if (Objects.equals(current.getData(), elem)){
                                    current.setData(current.getData().replace(elem, elemTroca));
                                    System.out.println("Elemento substituído na linha " + line + ". " + current.getData());
                                }
                                current = current.getRight();
                                line++;
                            } while (current != list.getHead());
                        }

                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    System.out.println("Epa algo deu errado tente novamente por favor\n");
                }
            }
            if(command.startsWith(":q!")){
                try {
                    System.out.println("Tem certeza que quer finalizar o programa? Y:SIM | N:NAO\n");
                    Scanner Input = new Scanner(System.in);
                    String answear  = Input.nextLine();
                    if (Objects.equals(answear, "Y")){
                        functional =false;
                    }
                }catch (Exception exception){
                    System.out.println("Epa algo deu errado tente novamente por favor\n");
                }
            }
            if (command.startsWith(":help")){
                System.out.println("Digite ':e', para abrir um arquivo de nome “nomeArq.txt”, lê o seu conteúdo e armazena" +
                        "cada linha em um Node da lista encadeada circular(é necessário utilizar esse comando para executar os outros comandos). \n");
                System.out.println("Digite ':w', para salvar o conteúdo da lista encadeada circular em um arquivo de nome “nomeArq.txt”.\n");
                System.out.println("Digite ':q!', para sair do programa sem salvar as modificações realizadas.\n");
                System.out.println("Digite ':v', para marcar um texto da lista (valor1 ao valor2), se ambos forem válidos.\n");
                System.out.println("Digite ':y', para copiar o texto marcado em ':v' para uma lista de Cópia.\n");
                System.out.println("Digite ':c', para Ccortar o texto marcado.\n");
                System.out.println("Digite ':p', para colar o texto marcado a partir da linha inicial(valor), se o valor for válido. \n");
                System.out.println("Digite ':s', para exibir na tela o conteúdo do programa fonte completo de 10 em 10 linhas, caso o usuário " +
                        "deseje, pode escrever o mesmo comando seguido \n de (valor1 e valor2) para obter apenas o conteúdo do valor1 até o valor2," +
                        " em ambos os casos as linhas serão exibidas e respeitadas.\n");
                System.out.println("Digite ':x', para apagar a linha de posição (valor) da lista.\n");
                System.out.println("Digite ':xG', para apagar as linhas da posição (valor) até o final da lista.\n");
                System.out.println("Digite ':XG', para apagar as linhas da posição (valor) até o início da lista.\n");
                System.out.println("Digite ':/', para percorrer a lista, localizar a(s) linha(s) (com a correspondente numeração da linha)" +
                        " na(s) qual(is) o (valor), indicado após o comando, encontra-se, caso o usuário queira trocar o(s) valor(es) por outro," +
                        " é só indicar (valor1 e valor2) após o comando.\n");
                System.out.println("Digite ':a', para permitir a edição de uma ou mais novas linhas e inserir na lista depois da\n" +
                        "posição (valor). O término da entrada é dada por um “:a” em uma linha vazia. Quando a lista está vazia, insere a partir do início da lista.\n");
                System.out.println("Digite ':i', para Permitir a inserção da linha [conteudo da nova linha] e inserir na lista\n" +
                        "antes da posição (valor) com a seguinte sintaxe (valor[conteudo da nova linha]). Quando a lista está vazia, insere no início da lista.\n");
                System.out.println("Todos os códigos que possuem a indicação (valores) devem ser escritos seguindo o exemplo, :s 3 5.\n");

            }
        }
        System.out.println("\nObrigado por usar nosso programa :)\n");
    }
}