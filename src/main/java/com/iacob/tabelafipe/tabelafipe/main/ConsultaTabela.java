package com.iacob.tabelafipe.tabelafipe.main;



import com.iacob.tabelafipe.tabelafipe.service.ApiDataFetcher;

import java.util.Scanner;

public class ConsultaTabela {
    Scanner sc = new Scanner(System.in);
    private int tipoVeiculo = 0;
    private ApiDataFetcher apiDataFetcher = new ApiDataFetcher();
    private final String URL_INICIO = "https://parallelum.com.br/fipe/api/v1/"
    String tipo = "";

    public void menu() {
        System.out.println("""
                Escolha o tipo de veículo:
                1. Carro
                2. Moto
                3. Caminhão""");

        tipoVeiculo = sc.nextInt();

        switch (tipoVeiculo) {
            case 1:
                tipo = "carros/marcas/";
                break;
            case 2:
                tipo = "motos/marcas/";
                break;
            case 3:
                tipo = "caminhoes/marcas/";
                break;
            default:
                System.out.println("Opção inválida, tente novamente");
        }
        String teste = apiDataFetcher.getData(URL_INICIO + tipo);
        System.out.println(teste);


        }





    }
}
