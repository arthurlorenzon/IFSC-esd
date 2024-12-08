package org.example;

import org.example.Pilha;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sequencia {
    public static void main(String[] args)  {
        try{
            FileInputStream file = new FileInputStream("sequencia.txt");
            Scanner scanner = new Scanner(file);
            Pilha pilha = new Pilha();

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] palavras = linha.split(" ");

                if (Character.isUpperCase(palavras[0].charAt(0))) {
                    System.out.println(linha);
                    continue;
                }
                if (pilha.estaVazia()) {
                    pilha.empilha(linha);
                } else {
                    System.out.println(linha);
                    System.out.println(pilha.desempilha());
                }
            }

        } catch (FileNotFoundException e){
            System.out.println("arquivo invalido");
        }
    }
}