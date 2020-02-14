package com.sf.Hierarchie.model;
// Class test pour ajouter un pseudo au score
public class Alphabet {
    String lettre1[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","n","o","p","k","r","s","t","u","v","w","x","y","z" };
    String lettre2[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","n","o","p","k","r","s","t","u","v","w","x","y","z" };
    String lettre3[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","n","o","p","k","r","s","t","u","v","w","x","y","z" };

    public void pseudo(int a, int b, int c) {

        String pseudo = lettre1[a] + lettre2[b] + lettre3[c];
        System.out.println(pseudo);
    }
}
