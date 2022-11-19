/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab_f;

public class Nod { //Se crea nodos para la LinkedList
    Nod sig;
    Sp po;

    public Nod(Sp punto) {
        this.po = punto;
        this.sig = null;
    }
} 