package org.example;


import org.example.Node;

import java.io.FileWriter;
import java.io.IOException;

public class doubleLinkedList {
    private Node head;
    private int count;

    // Construtor vazio
    // inicia o cabeça da lista como null
    public doubleLinkedList(){
        this.head = null;
        this.count = 0;
    }

    public boolean isEmpty() { // Verifica se DLL está vazia
        if (this.head == null) {
            return true;
        } else {
            return false;
        }
    }

    public int getCount() { return count; }

    // Percorre a DLL em ordem crescente
    // e mostra os seus valores
    public void showAscending(){
        // Percorre a lista e imprime os valores
        // em ordem Crescente
        if (head == null) System.out.print( "Lista vazia!\n");
        else{
            System.out.print( "Lista: [ ");
            Node pAnda = this.head;
            while (pAnda.getRight() != this.head){
                System.out.print( pAnda.getKey() + " ");
                pAnda= pAnda.getRight();
            }
            System.out.print( pAnda.getKey() +"]");
        }
    }
    public void transferDataToFile(String file) throws IOException {

        FileWriter arquivo = new FileWriter(file);
        try {
            if (head == null) System.out.print("Lista vazia!\n");
            else {
                System.out.print("Lista: [ ");
                Node pAnda = this.head;
                while (pAnda.getRight() != this.head) {
                    System.out.print(pAnda.getData() + " ");
                    arquivo.append(pAnda.getData() + "\n");
                    pAnda = pAnda.getRight();
                }
                System.out.print(pAnda.getData() + "]\n");
                arquivo.append(pAnda.getData());

            }
            arquivo.close();
        }catch (NullPointerException exception){
            throw exception;
        }
    }


    // Percorre a DLL em ordem decrescente e mostra os seus valores
    public void showDescending(){
        // Percorre a lista e imprime os valores
        // em ordem decrescente
        if (head == null) System.out.print( "Lista vazia!\n" );
        else{
            System.out.print( "Lista: [ " );
            Node pAnda = this.head;
            while (pAnda.getLeft() != this.head){
                pAnda= pAnda.getLeft();
                System.out.print( pAnda.getKey() + " " );
            }
            System.out.print( pAnda.getLeft().getKey() + "]");
        }
    }

    // Insere um novo elemento na DLL na ordem
    // crescente
    public boolean insertAscending(int Key, String dado){
        // Cria um novo no
        Node novoNo = new Node(Key, dado, null, null);

        // se lista vazia, insere o nó no ponteiro cabeça
        // e faz o nó apontar para ele mesmo
        if (head == null) {
            this.head = novoNo;
            novoNo.setRight(novoNo);
            novoNo.setLeft(novoNo);
        } else {
            // Caso contrário, procura posição de inserção
            // na ordem crescente de valores
            Node pAnda = this.head, pAnt = null;
            while (pAnda.getRight() != head && pAnda.getKey() < Key){
                pAnt = pAnda;
                pAnda = pAnda.getRight();
            }
            // Se não andou na lista e a Key que pAnda aponta
            // é maior que a Key a ser inserida, significa que a Key
            // a ser inserida é antes do cabeça
            // e cabeça precisa ser redefinido
            if (pAnt == null && pAnda.getKey() > Key){
                novoNo.setLeft(this.head.getLeft());
                this.head.getLeft().setRight(novoNo);
                this.head.setLeft(novoNo);
                novoNo.setRight(this.head);
                this.head = novoNo;
            } else {
                // Caso pAnda tenha caminhado na lista e
                // a Key que o pAnda aponta é maior do que a Key a ser inserido
                // insere o nó depois do ponteiro anterior pAnt
                if (pAnda.getKey() > Key && pAnt != null) {
                    pAnt.getRight().setLeft(novoNo);
                    novoNo.setRight(pAnda);
                    pAnt.setRight(novoNo);
                    novoNo.setLeft(pAnt);
                } else {// Senão, insere a Key depois do pAnda
                    pAnda.getRight().setLeft(novoNo);
                    novoNo.setRight(pAnda.getRight());
                    pAnda.setRight(novoNo);
                    novoNo.setLeft(pAnda);
                }
            }
        }
        this.count++;
        return true;
    }

    public void reList(){
        Node pAnda = this.head;
        int count = 0;
        while (pAnda.getRight() != this.head){
            pAnda.setKey(count);
            pAnda = pAnda.getRight();
            if(pAnda.getRight() == this.head){
                pAnda.setKey(count++);
            }
            count++;
        }
    }
    public void print(){
        Node pAnda = this.head;

        while (pAnda.getRight() != this.head){
            System.out.println(pAnda.getKey()+"."+pAnda.getData());
            pAnda = pAnda.getRight();
            if(pAnda.getRight() == this.head){
                System.out.println(pAnda.getKey()+"."+pAnda.getData());
            }
        }
    }

    // search e retorna o nó de Key passada como parâmetro
    public Node search(int Key){
        // Lista vazia
        if (isEmpty()) return null;

        // Procura o elemento
        Node pAnda = this.head;
        while (pAnda.getRight() != this.head){
            pAnda = pAnda.getRight();
        }

        if (pAnda.getRight() == this.head && pAnda.getKey() != Key){// elemento não encontrado
            return null;
        } else { // elemento encontrado
            return pAnda;
        }
    }
    public Node getHead() {return this.head;}

    // Remove um elemento da DLL
    // retornando verdadeiro ou falso
    public boolean remove(int Key) {
        // Lista vazia
        if (isEmpty()) return false;

        // Procura o elemento a ser removido
        Node pAnda = this.head, pAnt = null;
        while (pAnda.getRight() != this.head && pAnda.getKey() != Key){
            pAnt = pAnda;
            pAnda = pAnda.getRight();
        }

        if (pAnda.getRight() == this.head && pAnda.getKey() != Key){// elemento não encontrado
            return false;
        } else {
            // elemento encontrado no cabeça
            if (pAnt == null && pAnda.getKey() == Key){
                pAnda.getLeft().setRight(pAnda.getRight());
                this.head = this.head.getRight();
                this.head.setLeft(pAnda.getLeft());
                pAnda.setLeft(null);
                pAnda.setRight(null);
            } else { // elemento encontra-se no meio da lista
                pAnt.setRight(pAnda.getRight());
                pAnda.getRight().setLeft(pAnt);
                pAnda.setLeft(null);
                pAnda.setRight(null);
            }
            pAnda = null;
            this.count--;
            return true;
        }
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        int qtde = 0;
        sb.append("\n[Lista]\n");

        sb.append("L: [ ");
        Node pAnda = head;
        while (qtde != count) {
            sb.append("(" + pAnda.getKey()+", "+pAnda.getData() + ") ");
            qtde++;
            pAnda = pAnda.getRight();
        }
        sb.append("]\n");

        sb.append("Qtde.: " + count);
        if (count != 0) {
            sb.append("\nPrimeiro: (" + head.getKey() + ", " + head.getData()+")");
        }

        sb.append("");
        return sb.toString();
    }

}

