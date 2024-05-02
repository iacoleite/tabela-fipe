package com.iacob.tabelafipe.tabelafipe.service;

import java.util.List;

public interface IDataMapper {
    <T> T convertData(String json, Class<T> classe);

    <T> List<T> convertList(String json, Class<T> classe);
}
