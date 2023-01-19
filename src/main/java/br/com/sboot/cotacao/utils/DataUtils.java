package br.com.sboot.cotacao.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtils {

    public static LocalDate transformarData(String data) {
        String dtStr = data.substring(0, 10);
        return LocalDate.parse(dtStr);
    }

}
