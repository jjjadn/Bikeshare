package br.ufal.ic.p2.bikeshare.validacao;

import br.ufal.ic.p2.bikeshare.exception.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class ValidadorAluguel {

    public static LocalDateTime validarData(String dataStr) throws Exception {

        if (dataStr == null || dataStr.trim().isEmpty()) {
            throw new DataNaoPodeSerNulaException();
        }

        try {

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/uuuu H:mm")
                    .withResolverStyle(ResolverStyle.STRICT);
            return LocalDateTime.parse(dataStr.trim(), fmt);
        } catch (Exception e) {
            throw new DataInvalidaException();
        }
    }
}