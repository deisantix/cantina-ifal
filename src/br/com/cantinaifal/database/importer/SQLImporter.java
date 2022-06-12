/*
 * CÓDIGO BASEADO NA RESPOSTA DO STACKOVERFLOW
 * "How to execute .sql script file using JDBC [duplicate]"
 * https://stackoverflow.com/a/1498029
 */

package br.com.cantinaifal.database.importer;


import java.io.Reader;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLImporter {
    
    public static void importSQL(Connection con, Reader in) throws SQLException {
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("(;(\r)?\n)|(--\n)");

        Statement st = null;
        try {
            st = con.createStatement();

            while (scanner.hasNext()) {
                String line = scanner.next();
                if (line.startsWith("/*!") && line.endsWith("*/")) {
                    int i = line.indexOf(" ");
                    line = line.substring(i + 1, line.length() - " */".length());
                }

                if (line.trim().length() > 0) {
                    st.execute(line);
                }
            }
        } finally {
            if (st != null) {
                st.close();
            }
        }
        scanner.close();
    }
}
