/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab_f;

public class L {
    Nod Head;
    int Tam;
    
    public L(Nod H, int T) { //Se implementa la LinkedList
        this.Head = H;
        this.Tam = T;
    }

    public Nod get(int i) {
        if (i == 0) {
            return this.Head;
        } else {
            if (i < 0 || i > Tam) {
                System.out.println("Error");
                return null;
            } else {
                Nod P = this.Head;
                for (int j = 0; j < i; j++) {
                    P = P.sig;
                }
                return P;
            }
        }
    }

    public L SL(int i, int E, int tam) { //Se crea una sublista
        L Ls = new L(new Nod(this.get(i).po), tam);
        Nod P = Ls.Head;
        for (int j = 1; j < tam; j++) {
            P.sig = new Nod(this.get(j).po);
            P = P.sig;
        }
        return Ls;
    }
}