/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


/**
 *
 * @author Przemek
 */
public class BazaDanych {
    
    private JTextArea t;
    public BazaDanych(JTextArea t){
    this.t=t;
    }
    
    public void baza(){
        
     try {   
         // załadowanie sterownika
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         
         Connection polaczenie = DriverManager.getConnection(
         
         );
         
         Statement skladnia=polaczenie.createStatement();
         ResultSet rezultat = skladnia.executeQuery("select * from firma ");
                while (rezultat.next())
                    t.append(""+ rezultat.getString("REGON")
                    +" "+rezultat.getString("nazwa")+" "+
                            rezultat.getInt("liczba_pracownikow")+"\n");
                polaczenie.close();
     }
    catch (Exception e){
        JOptionPane.showMessageDialog(null,
                "Błąd "+e.getMessage(), 
                "Błąd połączenia",
                JOptionPane.ERROR_MESSAGE);
    }
    
}
    public void zapis(String REGON, String nazwa, int liczbaPracownikow){
    try {   
         // załadowanie sterownika
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         
         Connection polaczenie = DriverManager.getConnection(
         
         );
         
         Statement skladnia=polaczenie.createStatement();
         skladnia.addBatch("INSERT INTO firma VALUES (\'"+REGON+"\',\'"
                +nazwa+"\'," +liczbaPracownikow+")");
         skladnia.executeBatch();
         JOptionPane.showMessageDialog(null, "Zapisano Dane", "N2231",
         JOptionPane.INFORMATION_MESSAGE);  
         polaczenie.close();
     }
    catch (Exception e){
        JOptionPane.showMessageDialog(null,
                "Błąd "+e.getMessage(), 
                "Błąd połączenia",
                JOptionPane.ERROR_MESSAGE);
    }
    }
    
    public void bazaProcedura(){
        
     try {   
         // załadowanie sterownika
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         
         Connection polaczenie = DriverManager.getConnection(
         
         );
         
         //Statement skladnia=polaczenie.createStatement();
         PreparedStatement ps=polaczenie.prepareStatement("{call dbo.pobierz}");
         ResultSet rezultat = ps.executeQuery();
         while (rezultat.next())
                    t.append(""+ rezultat.getString("REGON")
                    +" "+rezultat.getString("nazwa")+" "+
                            rezultat.getInt("liczba_pracownikow")+"\n");
                polaczenie.close();
     }
               
     
    catch (Exception e){
        JOptionPane.showMessageDialog(null,
                "Błąd "+e.getMessage(), 
                "Błąd połączenia",
                JOptionPane.ERROR_MESSAGE);
    }
    
}
}
