package br.ufal.ic.p2.bikeshare.persistencia;

import br.ufal.ic.p2.bikeshare.models.Sistema;
import java.io.*;
import java.util.LinkedHashMap;

public class PersistenciaXML {
    private static final String ARQUIVO = "bikeshare.dat";

    public static void salvar(Sistema sistema) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(sistema);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar os dados", e);
        }
    }


    public static Sistema carregar() {

        File f = new File(ARQUIVO);

        if (!f.exists() || f.length() == 0) {
            return new Sistema(new LinkedHashMap<>(), 1, new LinkedHashMap<>(), 1, new LinkedHashMap<>());
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            return (Sistema) in.readObject();
        } catch (Exception e) {
            return new Sistema(new LinkedHashMap<>(), 1, new LinkedHashMap<>(), 1, new LinkedHashMap<>());
        }
    }

    public static void apagar() {

        File f = new File(ARQUIVO);
        if (f.exists()) f.delete();
    }
}