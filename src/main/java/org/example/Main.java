package org.example;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        doubleLinkedList list = null;

        Scanner input = new Scanner(System.in);
        String command = "i";
        while(!command.equals("o")) {
            System.out.println("Digite seu Comando:");
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
                String[] commandContent = command.split(" ");

                    if(list == null){
                        System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                    }else {
                        System.out.println("transferindo conteudo da lista encadeada para o arquivo: " + commandContent[1]);

                        list.transferDataToFile(commandContent[1]);
                    }
            }
            if (command.startsWith(":x")) {
                String[] commandContent = command.split(" ");
                if(list == null){
                    System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                }else {
                    list.remove(Integer.parseInt(commandContent[1]));

                    System.out.println("Lista atual : \n");
                    list.reList();
                    list.print();
                }
            }
            if (command.startsWith(":xG")) {
                String[] commandContent = command.split(" ");
                if(list == null) {
                    System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                }else {
                    int length = list.getCount();
                    for (int i = Integer.parseInt(commandContent[1]); i <= length+1; i++) {
                        list.remove(i);
                    }

                    System.out.println("Lista atual : \n");
                    list.print();
                }
            }
            if (command.startsWith(":XG")) {
                String[] commandContent = command.split(" ");
                if(list == null){
                    System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                }else {

                    for (int i = 1; i <= Integer.parseInt(commandContent[1]); i++) {
                        list.remove(i);
                    }

                    System.out.println("Lista atual : \n");
                    list.reList();
                    list.print();
                }
            }
            if (command.startsWith(":s")) {
                if (list == null) {
                    System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                } else {
                    Node current = list.getHead();
                    int i =1;
                    do {
                        for (int j = 1; j <= 10; j++) {

                            System.out.println(i + ". " + current.getData());
                            current = current.getRight();
                            if(current == list.getHead()){break;}
                            i++;
                        }
                        System.out.println("\n");
                    }
                    while (current != list.getHead());
                }
            }


            // Colocar os dois ":/" para serem uma operação só, mas diferentes ao mesmo tempo
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


            if (command.startsWith(":a")) {
                try {
                    int posLin = Integer.parseInt(command.split(" ")[1]);
                    if (list == null) {
                        System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                    } else {
                        System.out.println("Digite as novas linhas. Após digitar as novas linhas, utilize ':a' em uma linha vazia.");
                        String newLine = input.nextLine();
                        while (!newLine.equals(":a")) {
                            list.insertAt(posLin, newLine);
                            newLine = input.nextLine();
                            posLin++;
                        }
                        System.out.println("Novas linhas adicionadas com sucesso.");
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    System.out.println("Epa algo deu errado, após o ':a' use um número da linha.\n");
                }
            }

            if (command.startsWith(":i")){
                try {
                    String[] commandContent = command.split(" ", 3);
                    int posLin = Integer.parseInt(commandContent[1]);
                    String newLine = commandContent[2];
                    if (list == null) {
                        System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                    } else {
                        System.out.println("Inserindo a nova linha na posição: " + posLin);
                        if (posLin > 1) {
                            posLin = posLin - 2;
                        }
                        list.insertAt(posLin, newLine);
                        System.out.println("Novas linhas adicionadas com sucesso.");
                    }
                }catch (ArrayIndexOutOfBoundsException exception) {
                    System.out.println("Epa algo deu errado, após o ':i' use um número da linha.\n");
                }
            }
        }
        System.out.println("\nObrigado por usar nosso programa :)\n");
    }
}

