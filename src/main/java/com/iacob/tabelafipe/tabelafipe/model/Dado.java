package com.iacob.tabelafipe.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dado(String codigo,
                   String nome) {
}
