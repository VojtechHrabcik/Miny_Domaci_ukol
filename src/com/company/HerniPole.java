package com.company;

import java.util.Random;

public class HerniPole {

    private boolean[][] minovePole;

    private int pocetPoliZbyvajicichDoVyhry;

    private int vyskaPole;
    private int sirkaPole;

    public HerniPole(int vyskaPole, int sirkaPole, int pocetMin){
        minovePole = new boolean[sirkaPole][vyskaPole];
        this.vyskaPole = vyskaPole;
        this.sirkaPole = sirkaPole;
                pocetPoliZbyvajicichDoVyhry = vyskaPole * sirkaPole - pocetMin;

        Random random = new Random();
        for (int i = 0; i < pocetMin;){
        int souradniceX = random.nextInt(sirkaPole);
        int souradniceY = random.nextInt(vyskaPole);
        if (!minovePole[souradniceX] [souradniceY]){
            minovePole[souradniceX][souradniceY] = true;
            i++;
}
}
}

    //je na policku mina?
    public boolean jeTamMina(int souradniceX, int souradniceY){
        return minovePole[souradniceX][souradniceY];
    }

    //vyhral uzivatel?
    public boolean vyhralUzivatel(){
        return pocetPoliZbyvajicichDoVyhry < 1;
    }

    //sniz pocet zbyvajicich poli
    public  void snizPocetZbyvajicichPoli(){
        pocetPoliZbyvajicichDoVyhry--;
    }

    //kolik sousedu ma minu?
    public int kolikSouseduMaMinu(int souradniceX, int souradniceY){
        int pocetMin = 0;

        if (souradniceX > 0 && souradniceY > 0 && minovePole [souradniceX-1][souradniceY-1]) pocetMin++;
        if (souradniceY > 0 && minovePole[souradniceX][souradniceY-1]) pocetMin++;
        if (souradniceX > sirkaPole - 1 && souradniceY > 0 && minovePole [souradniceX+1][souradniceY-1]) pocetMin++;
        if (souradniceX > 0 && minovePole [souradniceX-1][souradniceY]) pocetMin++;
        if (souradniceX < sirkaPole - 1 && minovePole [souradniceX+1][souradniceY]) pocetMin++;
        if (souradniceX > 0 && souradniceY < vyskaPole - 1 && minovePole[souradniceX-1][souradniceY+1]) pocetMin++;
        if (souradniceX < vyskaPole - 1 && minovePole[souradniceX][souradniceY+1]) pocetMin++;
        if (souradniceX < sirkaPole - 1 && souradniceY < vyskaPole - 1 && minovePole[souradniceX+1][souradniceY+1]) pocetMin++;

        return pocetMin;
    }
}
