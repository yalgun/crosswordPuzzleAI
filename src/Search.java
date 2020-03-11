/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Yavuz Algun
 */
public class Search {
    public static int counter = 0;
    public static int N = 6;
    public static ArrayList<ArrayList<String>> sozcukListesi;
    public static String[][] kurVeCoz(String[][] Table) throws FileNotFoundException, IOException{
        sozcukListesi = new ArrayList<>();
        for(int i =0 ;i<=15;i++){
            ArrayList<String> newListe =new ArrayList<>();
            sozcukListesi.add(newListe);
        }
        String fileName = "sozluk.txt";
        ClassLoader classLoader = new Search().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        String sozcuk = reader.readLine();
        while(sozcuk !=null){
            if(sozcuk.length()>15){
                sozcukListesi.get(0).add(sozcuk);
            } else {
                sozcukListesi.get(sozcuk.length()).add(sozcuk);
            }
            sozcuk = reader.readLine();
        }        
        
        for(int i = 0;i<N;i++){
            for(int j = 0;j<N;j++){
                System.out.print(Table[i][j]+ " ");
            }
            System.out.println("");
        }
        System.out.println("******************");
        Table = satirCoz(Table, 0, 0);
        for(int i = 0;i<N;i++){
            for(int j = 0;j<N;j++){
                System.out.print(Table[i][j]+ " ");
            }
            System.out.println("");
        }
        return Table;
    }
    public static void main(String[] args) throws FileNotFoundException, IOException{
        String[][] Table = new String[N][N];
        for(int i=0;i<N;i++){
            for(int j = 0;j<N;j++){
                Table[i][j]="-";
            }
        }
        Table[3][0] ="/";
        Table[1][1] ="/";
        Table[2][2] ="/";
        Table[4][5] ="/";

        sozcukListesi = new ArrayList<>();
        for(int i =0 ;i<=15;i++){
            ArrayList<String> newListe =new ArrayList<>();
            sozcukListesi.add(newListe);
        }
        String fileName = "sozluk.txt";
        ClassLoader classLoader = new Search().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        String sozcuk = reader.readLine();
        while(sozcuk !=null){
            if(sozcuk.length()>15){
                sozcukListesi.get(0).add(sozcuk);
            } else {
                sozcukListesi.get(sozcuk.length()).add(sozcuk);
            }
            sozcuk = reader.readLine();
        }        
        
        for(int i = 0;i<N;i++){
            for(int j = 0;j<N;j++){
                System.out.print(Table[i][j]+ " ");
            }
            System.out.println("");
        }
        System.out.println("******************");
        Table = satirCoz(Table, 0, 0);
        for(int i = 0;i<N;i++){
            for(int j = 0;j<N;j++){
                System.out.print(Table[i][j]+ " ");
            }
            System.out.println("");
        }
        
        
        
    }
    public static String cumleDon(String[][] Table,int a,int indexX,int indexY){
        String cumle = null;
        int i = indexY;
        for(;i<a;i++){
            cumle +=Table[indexX][a];
        }
        return cumle;
    }
    
    public static String yatayCumleDon(String[][] Table, int indexX,int indexY){
        String cumle = "";
        int ustSinir=indexY;
        int altSinir=indexY;
        for(;altSinir>=0;altSinir--){
            if(Table[indexX][altSinir].equals("/")){
                //altSinir--;
                break;
            }
        }
        altSinir++;
        for(;ustSinir<N;ustSinir++){
            if(Table[indexX][ustSinir].equals("/")){
                break;
            }
        }
        for(;altSinir<ustSinir;altSinir++){
            cumle += Table[indexX][altSinir];
        }
        return cumle;
    }
    
    public static int yataySonuncuGetir(String[][] Table, int x,int y){
        int indis =-1; 
        for(;y<N;y++){
            if(Table[x][y].equals("/")){
                indis = y;
                break;
            }
        }
        return indis;
    }
    
    public static String dikeyCumleDon(String[][] Table, int indexX,int indexY){
        String cumle = "";
        int ustSinir=indexX;
        int altSinir=indexX;
        for(;altSinir>=0;altSinir--){
            if(Table[altSinir][indexY].equals("/")){
                //altSinir--;
                break;
            }
        }
        altSinir++;
        for(;ustSinir<N;ustSinir++){
            if(Table[ustSinir][indexY].equals("/")){
                break;
            }
        }
        for(;altSinir<ustSinir;altSinir++){
            cumle =cumle+ Table[altSinir][indexY];
        }
        return cumle;
    }
    
    public static boolean olduMuDuz(String[][] Table,int x,int y){
        boolean masterBoolean = true;
        while(true){
            if(Table[y][x].equals("/")){
                x +=1;
                if(x >= N){
                    y+=1;
                    x=0;
                }
                if(y >= N ){
                    break;
                }
                continue;
            }
            boolean isExist=false;
            String cumle = dikeyCumleDon(Table, y, x);
            for(int i = 0; i< sozcukListesi.get(cumle.length()).size();i++){
                if(esitMi(sozcukListesi.get(cumle.length()).get(i), cumle)){
                    isExist = true;
                    break;
                }
            }
            if(isExist){
                int yeniIndis = 1+x;
                if(yeniIndis >= N && y==(N-1)){
                    masterBoolean = true;
                    break;
                } else if(yeniIndis >= N){
                    x=0;
                    y=y+1;
                } else {
                    x = yeniIndis;
                }
            } else {
                masterBoolean = false;
                break;
            }
        }
        return masterBoolean;
    }
    
    public static boolean olduMu(String[][] Table,int x,int y){
        int firstNonBlack = 0;
        for(int i=x;i<N;i++){
            if(!Table[i][y].equals("/")){
                firstNonBlack = i;
                break;
            }   
        }
        boolean isExist=false;
        String cumle = dikeyCumleDon(Table, firstNonBlack, y);
        for(int i = 0; i< sozcukListesi.get(cumle.length()).size();i++){
            if(esitMi(sozcukListesi.get(cumle.length()).get(i), cumle)){
                isExist = true;
                break;
            }
        }
        if(isExist){
            int yeniIndis = cumle.length()+ firstNonBlack;
            if(yeniIndis >= N && y==(N-1)){
                return true;
            } else if(yeniIndis >= N){
                return olduMu(Table, 0, y+1);
            } else {
                return olduMu(Table, yeniIndis, y);
            }
            
        }else{
            return false;
        }
        
    }
    
    public static boolean esitMi(String stringA,String stringB){
        boolean esitMi = true;
        if(stringA.length() != stringB.length()){
            return false;
        }
        if(stringA.length()==2){
            return true;
        }
        for(int i = 0;i<stringA.length();i++){
            if(stringA.charAt(i) != stringB.charAt(i) && stringB.charAt(i)!='-'){
                esitMi = false;
            }
        }
        return esitMi;
    }
    
    public static boolean dikKontrol(String[][] Table, int x,int y){
    int firstNonBlack = 0;
        for(int i=x;i<N;i++){
            if(!Table[i][y].equals("/")){
                firstNonBlack = i;
                break;
            }
            
        }
        boolean isExist=false;
        String cumle = dikeyCumleDon(Table, firstNonBlack, y);
        for(int i = 0; i< sozcukListesi.get(cumle.length()).size();i++){
            if(esitMi(sozcukListesi.get(cumle.length()).get(i), cumle)){
                isExist = true;
                break;
            }
        }
        if(isExist){
            int yeniIndis = cumle.length()+ firstNonBlack;
            if(yeniIndis >= N && x==(N-1)){
                return true;
            } else if(yeniIndis >= N){
                return dikKontrol(Table, x, y+1);
            } else {
                return olduMu(Table, yeniIndis, y);
            }
            
        }else{
            return false;
        }
    }
    
    public static boolean tamOlduMuDongu(String[][] Table, int x, int y){
        boolean masterBoolean = true;
        while(true){
            if(Table[y][x].equals("/")){
                x +=1;
                if(x >= N){
                    y+=1;
                    x=0;
                }
                if(y >= N ){
                    break;
                }
                continue;
            }
            boolean isExist=false;
            String cumle = dikeyCumleDon(Table, y, x);
            for(int i = 0; i< sozcukListesi.get(cumle.length()).size();i++){
                if(sozcukListesi.get(cumle.length()).get(i).equals(cumle)||cumle.length()==2){
                    isExist = true;
                    break;
                }
            }
            if(isExist){
                int yeniIndis = 1+x;
                if(yeniIndis >= N && y==(N-1)){
                    masterBoolean = true;
                    break;
                } else if(yeniIndis >= N){
                    x=0;
                    y=y+1;
                } else {
                    x = yeniIndis;
                }
            } else {
                masterBoolean = false;
                break;
            }
        }
        return masterBoolean;
    }
    
    public static boolean tamOlduMu(String[][] Table,int x,int y){
        int firstNonBlack = 0;
        for(int i=x;i<N;i++){
            if(!Table[i][y].equals("/")){
                firstNonBlack = i;
                break;
            }
            
        }
        boolean isExist=false;
        String cumle = dikeyCumleDon(Table, firstNonBlack, y);
        for(int i = 0; i< sozcukListesi.get(cumle.length()).size();i++){
            if(sozcukListesi.get(cumle.length()).get(i).equals(cumle)||cumle.length()==2){
                isExist = true;
                break;
            }
        }
        if(isExist){
            int yeniIndis = cumle.length()+ firstNonBlack;
            if(yeniIndis >= N && y==(N-1)){
                return true;
            } else if(yeniIndis >= N){
                return olduMu(Table, 0, y+1);
            } else {
                return olduMu(Table, yeniIndis, y);
            }
            
        }else{
            return false;
        }
    }
    
    public static String[][] copyArray(String[][] array){
        String[][] newArray = new String[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }
    
    public static String[][] satirCoz(String[][] Table,int x,int y){
        if(counter % 1000 == 0 )
            System.out.println(counter++);
        int firstNonBlack = 0;
        for(int i=y;i<N;i++){
            if(!Table[x][i].equals("/")){
                firstNonBlack = i;
                break;
            }
        }
        String[][] tempTable = copyArray(Table);
        String yatayString = yatayCumleDon(tempTable, x, firstNonBlack);
        for(int i = 0; i<sozcukListesi.get(yatayString.length()).size();i++){
            if(esitMi(sozcukListesi.get(yatayString.length()).get(i),yatayString)){
                for(int j = 0;j< yatayString.length();j++){
                    tempTable[x][firstNonBlack+j]=""+sozcukListesi.get(yatayString.length()).get(i).charAt(j);
                }
                if(yataySonuncuGetir(tempTable, x, y)==-1 && x==(N-1)){
                    if(olduMuDuz(tempTable,0,0)){
                        return tempTable;
                    }
                }else if(yataySonuncuGetir(tempTable, x, y) == -1){
                    if(olduMuDuz(tempTable, 0, 0)){
                        String[][] newTable = satirCoz(tempTable, x+1, 0);
                        if(tamOlduMuDongu(newTable,0,0)){
                            return newTable;
                        }
                    }
                }else {
                    if(olduMuDuz(tempTable, 0, 0)){
                        String[][] newTable = satirCoz(tempTable, x, yataySonuncuGetir(tempTable, x, y)+1);
                        if(tamOlduMuDongu(newTable,0,0)){
                            return newTable;
                        }
                    }
                }
            }
        }
        return Table; //tempTable donuyordu
    }
            
    
}
