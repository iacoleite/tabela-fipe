package com.iacob.tabelafipe.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(String Marca, String Modelo, String AnoModelo, String Combustivel, String Valor) {
}
