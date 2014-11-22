/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indexer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author Petr
 */
public class Indexer {

    private String ipES;
    private String portES;
    private String configURL = "config.txt";
    
    public void Indexer(){
        loadReportConfig();
    }
     /**
     * metoda nacte konfig
     *
     * @throws FileNotFoundException nemam konfig
     * @throws IOException chyba v pristupu k souboru
     */
    
    /* 
     * TODO: je potřeba definovat vstup z configu a definovat jaké příkazy v jaké syntaxi budeme používat
     */
    private void loadReportConfig() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(this.configURL));
            String line = br.readLine();
            String[] pole;

            while (line != null) {
                if (line.toLowerCase().contains("[license]")) {
                    while (!line.toLowerCase().contains("[server]")) {
                        line = br.readLine();
                        pole = line.split(":");
                        if (pole[0].equals("licensekey")) {
                            //licensekey:veovber
                            License.checkLicense(pole[1]);
                        }
                    }
                }
                if (line.toLowerCase().contains("[server]")) {
                    while (!line.toLowerCase().contains("[task]")) {
                        line = br.readLine();
                        if (!line.startsWith("//")){ //možnost komentovat v config.txt - začátek řádku je //
                            pole = line.split(":");
                            if (pole[0].equals("ip")) {
                                //ip:192.168.0.0
                                this.ipES = pole[1];
                            }
                            if (pole[0].equals("port")) {
                                this.portES = pole[1];
                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(new Date() + " IO Exception in reading config.");
        }
    }
    
}
