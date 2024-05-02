package com.iacob.tabelafipe.tabelafipe.main;

import com.iacob.tabelafipe.tabelafipe.model.Dado;
import com.iacob.tabelafipe.tabelafipe.model.Modelos;
import com.iacob.tabelafipe.tabelafipe.model.Veiculo;
import com.iacob.tabelafipe.tabelafipe.service.ApiDataFetcher;
import com.iacob.tabelafipe.tabelafipe.service.DataMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ConsultaTabela {
    Scanner sc = new Scanner(System.in);
    private String marcaEscolhida;
    private final ApiDataFetcher apiDataFetcher = new ApiDataFetcher();
    private final DataMapper conversor = new DataMapper();

    private final String URL_INICIO = "https://parallelum.com.br/fipe/api/v1/";
    String tipo = "";

    public void menu() {
        System.out.println("""
                Escolha o tipo de veículo:
                1. Carro
                2. Moto
                3. Caminhão""");

        int tipoVeiculo = sc.nextInt();


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
                System.out.println("\nOpção inválida, tente novamente");
        }

        String jsonMarcas = apiDataFetcher.getData(URL_INICIO + tipo);

        List<Dado> listaMarca = conversor.convertList(jsonMarcas, Dado.class);
//        System.out.println(listaMarca);
        listaMarca.stream().sorted(Comparator.comparing(Dado::codigo
        )).forEach(t -> System.out.println("Marca: " + t.nome() +
                " | Código: " + t.codigo()));

        System.out.println("\nEscolha uma marca da lista acima e digite o código correspondente:");
        sc.nextLine();
        marcaEscolhida = sc.nextLine();

        String jsonModelos = apiDataFetcher.getData(URL_INICIO + tipo + marcaEscolhida + "/modelos/");
//        System.out.println(jsonModelos);
        Modelos listaModelo = conversor.convertData(jsonModelos, Modelos.class);
        listaModelo.modelos().stream().forEach(t -> System.out.println("Modelo: " + t.nome() +
                " | Código: " + t.codigo()));

        System.out.println("\nDigite um pedaço do nome do carro para filtrar a lista:");

        String busca = sc.nextLine();

        System.out.println("\nModelos Filtrados: ");
        listaModelo.modelos().stream().filter(modelo -> modelo.nome().toLowerCase().contains(busca.toLowerCase()))
                .forEach(t -> System.out.println("Modelo: " + t.nome() +
                " | Código: " + t.codigo()));

        System.out.println("\nEscolha um modelo específico e digite o código correspondente: ");
        String codigoEspecifico = sc.nextLine();
        String jsonEspecifico =
                apiDataFetcher.getData(URL_INICIO + tipo + marcaEscolhida + "/modelos/" + codigoEspecifico + "/anos");
        //System.out.println(jsonEspecifico);
        List<Dado> listaAnos = conversor.convertList(jsonEspecifico, Dado.class);
//        System.out.println(listaAnos);
        List<Veiculo> listaVeiculos = new ArrayList<>();

        listaAnos.parallelStream().forEach(t -> {
            String jsonVeiculo =
                    apiDataFetcher.getData(URL_INICIO + tipo + marcaEscolhida + "/modelos/" + codigoEspecifico +
                            "/anos/" + t.codigo());
            Veiculo veiculo = conversor.convertData(jsonVeiculo, Veiculo.class);
            listaVeiculos.add(veiculo);
        });
        listaVeiculos.stream().sorted(Comparator.comparing(Veiculo::anoModelo).reversed()).forEach(veiculo ->System.out.println(veiculo.marca() + " " + veiculo.modelo() + " Ano: " + veiculo.anoModelo() + " Valor: " + veiculo.valor() + " Combustível: " + veiculo.combustivel()));

    }
}