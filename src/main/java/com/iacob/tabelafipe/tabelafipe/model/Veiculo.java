package com.iacob.tabelafipe.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(@JsonAlias("Marca") String marca,
                      @JsonAlias("Modelo") String modelo,
                      @JsonAlias("AnoModelo") String anoModelo,
                      @JsonAlias("Combustivel") String combustivel,
                      @JsonAlias("Valor") String valor) {
}
