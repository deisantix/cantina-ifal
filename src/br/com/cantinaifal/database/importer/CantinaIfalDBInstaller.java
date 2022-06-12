/*
 * LEITURA DE ARQUIVO BASEADA NO ARTIGO:
 * "Different ways of Reading a text file in Java"
 * https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
 */

package br.com.cantinaifal.database.importer;


import br.com.cantinaifal.Config;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;


public class CantinaIfalDBInstaller {
    
    public static void installDB(Connection con) throws FileNotFoundException {
        String sqlScript = Config.currentDir + "/lib/sql/cantina-ifal.sql";
        
        try {
            File file = new File(sqlScript);
            BufferedReader br = new BufferedReader(
                new FileReader(file)
            );
            SQLImporter.importSQL(con, br);
        } catch (Exception e) {
            throw new FileNotFoundException("Não foi possível importar o arquivo.sql");
        }
    }

}
