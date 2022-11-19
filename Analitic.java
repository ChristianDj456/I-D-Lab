/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab_f;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Santi Mercado
 */
public class Analitic {
    private static void create(String name)
    // creates a file with a given name
    {
        try {
            // defines the filename
            String fname = (name);
            // creates a new File object
            File f = new File(fname);

            String msg = "creating file `" + fname + "' ... ";
            // creates the new file
            f.createNewFile();

        } catch (IOException err) {
            // complains if there is an Input/Output Error
            err.printStackTrace();
        }

        return;
    }

    private static void write(String name, int tm, ArrayList<Integer> is, ArrayList<Integer> comparisons,ArrayList<Integer> runtimes){
        try {
            // defines the filename
            String filename = (name);
            PrintWriter out = new PrintWriter(filename);
            String fmt = ("%10s %10s %10s\n"); 
            for (int i = 0; i < tm; ++i) {
                out.printf(fmt, is.get(i), comparisons.get(i), runtimes.get(i));
            }

            out.close();
        } catch (FileNotFoundException err) {
            // complains if file does not exist
            err.printStackTrace();
        }

        return;
    }

    public void Analysis(int Reach) { //Reach es el numero de enteros a llegar.
        ArrayList<Integer> RunT = new ArrayList<Integer>();
        ArrayList<Integer> is = new ArrayList<Integer>();
        ArrayList<Integer> Compar = new ArrayList<Integer>(); //Se inicializa las listas.
        for (int i = 50; i < Reach; i = i*3/2) {
            Lab_F IDK = new Lab_F(); // Se crea una nueva clase
            System.out.println(i);
            long total = 0;
            int C;
            L Lista = IDK.Starting(i); // Creamos una lista de coordenadas y la ordenamos con respecto a X
            for (int j = 0; j < 256; j++) {
                long startTime = System.nanoTime();
                IDK.ClosestPair(i, Lista, 999999999);
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime; // Se calcula el tiempo de ejecucion para cada repeticion
                total = total + totalTime;
            }
            C = IDK.getCompar();
            C = C / 256; //Se halla el promedio de iteraciones.
            total = total / 256; //Se halla el tiempo de ejecucion promedio
            RunT.add((int) total);
            is.add(i); //Se almacenan en un arreglo el numero total de coordenadas
            Compar.add(C);
        }
        create("Times.txt");
        write("Times.txt", is.size(), is, Compar, RunT);
    }
}