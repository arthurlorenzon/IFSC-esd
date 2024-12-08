package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Exercicio4 {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, forneça o nome do arquivo como argumento.");
            return;
        }

        String nomeArquivo = args[0];
        ListaSimples<Registro> registros = new ListaSimples<>();

        // Ler o arquivo e armazenar os registros
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                int timestamp = Integer.parseInt(partes[0]);
                String tipo = partes[1];
                String usuario = partes[2];

                registros.adiciona(new Registro(timestamp, tipo, usuario));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // Ordenar os registros pelo nome do usuário
        ordenaRegistros(registros);

        // Calcular o tempo de acesso
        calculaTempoAcesso(registros);
    }

    private static void ordenaRegistros(ListaSimples<Registro> registros) {
        for (int i = 0; i < registros.comprimento() - 1; i++) {
            for (int j = i + 1; j < registros.comprimento(); j++) {
                Registro r1 = registros.obtem(i);
                Registro r2 = registros.obtem(j);
                if (r1.usuario.compareTo(r2.usuario) > 0) {
                    registros.substitui(i, r2);
                    registros.substitui(j, r1);
                }
            }
        }
    }

    private static void calculaTempoAcesso(ListaSimples<Registro> registros) {
        String usuarioAtual = null;
        int tempoTotal = 0;
        int ultimoTimestampEntrada = -1;

        for (int i = 0; i < registros.comprimento(); i++) {
            Registro registro = registros.obtem(i);

            if (!registro.usuario.equals(usuarioAtual)) {
                if (usuarioAtual != null && ultimoTimestampEntrada != -1) {
                    System.out.println(usuarioAtual + " esteve no sistema por " + tempoTotal + " segundos.");
                }
                usuarioAtual = registro.usuario;
                tempoTotal = 0;
                ultimoTimestampEntrada = -1;
            }

            if (registro.tipo.equals("ENTRADA")) {
                ultimoTimestampEntrada = registro.timestamp;
            } else if (registro.tipo.equals("SAIDA") && ultimoTimestampEntrada != -1) {
                tempoTotal += registro.timestamp - ultimoTimestampEntrada;
                ultimoTimestampEntrada = -1;
            }
        }

        // Exibir o último usuário processado
        if (usuarioAtual != null && ultimoTimestampEntrada != -1) {
            System.out.println(usuarioAtual + " esteve no sistema por " + tempoTotal + " segundos.");
        }
    }

    // Classe auxiliar para representar um registro
    static class Registro {
        int timestamp;
        String tipo;
        String usuario;

        Registro(int timestamp, String tipo, String usuario) {
            this.timestamp = timestamp;
            this.tipo = tipo;
            this.usuario = usuario;
        }
    }
}
