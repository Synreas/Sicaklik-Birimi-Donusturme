/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sıcaklık.dönüştürme;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Berkay
 */

public class SıcaklıkDönüştürme {
    
    
    /**
     * @param args the command line arguments
     */
    static void yazdir(){
        System.out.println("1 -> Birimleri dönüştür");
        System.out.println("2 -> Yeni birim ekle");
        System.out.println("3 -> çıkış");
    }
    
    static double[] degerAl(String birim){
        /*File CDosya = new File("C:\\Program Files\\birimdonusturme");
        if (!CDosya.exists()){
            CDosya.mkdir();
        }*/
        File birim_dosya = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\birimdonusturme\\" + birim + ".txt");
        String donma = "", kaynama = "";
        try{
            FileReader reader = new FileReader(birim_dosya);
            int ch;
            
            while ((ch = reader.read()) != 32){
                donma += (char)ch;
            }
            
            while ((ch = reader.read()) != -1){
                kaynama += (char)ch;
            }
            
            double dizi[] = new double[2];
            dizi[0] = Double.parseDouble(donma);
            dizi[1] = Double.parseDouble(kaynama);
            return dizi;
            
        }
        
        catch(Exception e){
            System.out.println("Birim önceden tanımlanmamış. (" + birim + ")");
            //e.printStackTrace();
            double dizi[] = {0};
            return dizi;
        }
        //degerAl() fonksiyonu burda biter
    }
    //****************************************************
    
    static boolean yeniDeger(String birim, double donma, double kaynama){
        try{
            File DosyaC = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\birimdonusturme");
            
            if (!DosyaC.exists()){
                if (!DosyaC.mkdir()){
                    return false;
                }
            }
            
            File belge = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\birimdonusturme\\" + birim + ".txt");
            if (!belge.exists()){
                belge.createNewFile();
            }
            
            BufferedWriter Bwriter = new BufferedWriter(new FileWriter(belge));
            Bwriter.write(donma + " " + kaynama);
            Bwriter.close();
            return true;
        }
        
        catch (Exception e){
            //e.printStackTrace();
            return false;
        }
        //yeniDeger() sonu
    }
    //***********************************************************
    
    static double hesapla(double bazSıcaklık, double donma1, double kaynama1, double donma2, double kaynama2){
        // hedefsıcaklık = (bazSıcaklık - donma1) * (kaynama2 - donma2) / (kaynama1 - donma1) + donma2
        return (bazSıcaklık - donma1) * (kaynama2 - donma2) / (kaynama1 - donma1) + donma2;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Sıcaklık birimi Dönüştürme");
        System.out.println("==========================");
        OUTER:
        while (true) {
            yazdir();
            System.out.print("=> ");
            String secim123 = new Scanner(System.in).nextLine();
            switch (secim123) {
                case "1": //birim dönüştürme
                    double bazSıcaklık, donma1, kaynama1, donma2, kaynama2;
                    System.out.print("Kaynak birim: ");
                    String kaynakBirim = scan.nextLine();
                    double kaynak1[] = degerAl(kaynakBirim);
                    
                    System.out.print("Kaç derece " + kaynakBirim + " : ");
                    bazSıcaklık = new Scanner(System.in).nextDouble();
                    
                    System.out.print("Hedef birim: ");
                    double kaynak2[] = degerAl(new Scanner(System.in).nextLine());
                    
                    double hata[] = {0};
                    if (kaynak1 == hata || kaynak2 == hata){
                        continue;
                    }   
                    
                    donma1 = kaynak1[0];
                    kaynama1 = kaynak1[1];
                    donma2 = kaynak2[0];
                    kaynama2 = kaynak2[1];
                    System.out.println("" + hesapla(bazSıcaklık, donma1, kaynama1, donma2, kaynama2));
                    break;
                    
                case "2": //yeni birim
                    System.out.print("Birim adı: ");
                    String birimAdi = new Scanner(System.in).nextLine();
                    
                    System.out.print("Suyun donma derecesi: ");
                    double donma = new Scanner(System.in).nextDouble();
                    
                    System.out.print("Suyun kaynama dereceli: ");
                    double kaynama = new Scanner(System.in).nextDouble();
                    
                    if (yeniDeger(birimAdi, donma, kaynama)){
                        System.out.println("Birim başarıyla eklendi.");
                    }
                    
                    else{
                        System.out.println("Birim oluşturma başarısız.");
                    }   
                    break;
                    
                case "3": //çıkış
                    break OUTER;
                    
                default:
                    break;
            }
        }
    }
    
}
