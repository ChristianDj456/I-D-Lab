package com.mycompany.lab_f;

/* Course name and code -> Algorithms And Complexity
   Students name and ID -> Cristian dj. Chavez Sarmiento - ID 200153656 & Valeria Andrea Jimenez Silvera - ID 200135722 IST 4310-01 NRC 3264
   Name of the activity -> I+D
   Date -> 17/11/2022
   Description of the activity |
                               V
    The Closest Pair Algorithm is used to find the closest distance.
     cuts between two randomly generated coordinates in an LinkedList
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Lab_F {
    private static int Cou; //Se mantiene un seguimiento de cada repeticion.

    public Lab_F() {
        Cou = 0;
    }

    public int getCompar() {
        return Cou;
    }
    
    public static double[] BruteForce(L Coords, double d_min) { // Se halla el par mas cercano con Brute Force Algorithm.
        double dmin = d_min;
        double[] V = new double[3]; //Se crea una matriz para almacenar el d_min y el punto con el par mas cercano.
        V[0] = dmin;
        for (int i = 0; i < Coords.Tam; i++) {
            for (int j = i + 1; j < Coords.Tam; j++) {
                double d = distance(Coords, i, j); //Se compara la distancia con cada par.
                if (d < dmin) {
                    Cou++;
                    dmin = d;
                    V[0] = d;
                    V[1] = Coords.get(i).po.Posi;
                    V[2] = Coords.get(j).po.Posi;
                } else {
                    Cou++;
                }
            }
        }
        return V;
    }
    
 public static double distance(L coords, int i, int j) { // Se calcula la distancia entre i y j
        // unpacks coordinates of the ith and jth elements.
        int x1 = coords.get(i).po.X;
        int x2 = coords.get(j).po.X;
        int y1 = coords.get(i).po.Y;
        int y2 = coords.get(j).po.Y;
        double d = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1); // computes their distance
        return Math.sqrt(d);
    }
 
  public static double[] ClosestPair(int n, L X, double Md) {
        // Se encuentra el par mas cercano usando recursividad
        if (n > 3) { //Si hay mas de 3 particulas, se divide el plano entre 2.
            double[] Gg1 = new double[3];
            double[] Gg2 = new double[3];
            int Ledge = 0;
            Cou++;
            if (n % 2 == 1) {
                Ledge = 1;
            }
            L G2 = X.SL(n / 2, n, n / 2);
            L G1 = X.SL(0, n / 2, n / 2);
            Gg1 = ClosestPair(n / 2, G1, Md);
            Gg2 = ClosestPair(n / 2, G2, Md);
            double[] G = new double[3];
            if (Gg1[0] < Gg2[0]) { // Se almacenan los pares mas cercanos
                G = Gg1;
                Cou++;
            } else {
                Cou++;
                G = Gg2;
            }
            L Candi = FCandys(X, G[0]); //Lista que almacena las posibles coordenadas.
            // Se aplica BruteForce si hay al menos dos candidatos
            if (Candi.Tam > 1) {
                Cou++;
                Gg1 = BruteForce(Candi, Md);
                if (Gg1[0] < G[0]) {
                    Cou++;
                    return Gg1; //Se compara la distancia minima actual con los candidatos
                } else {
                    Cou++;
                    return G;
                }
            } else {
                Cou++;
                return G;
            }
        } else {
            Cou++;
            double[] vec = new double[3];
            return BruteForce(X, Md); // Se aplica BruteForce si hay 3 o menos
        }
    }
    

    public static void PCoords(List<int[]> Coord) { // Se imprimen coordenadas.
        for (int i = 0; i < Coord.size(); i++) {
            System.out.println("X: " + Coord.get(i)[0] + " Y: " + Coord.get(i)[1] + " Posicion: " + Coord.get(i)[2]);
        }
    }

    public static L FCandys(L Coors, double min) {
        ArrayList<Sp> Can = new ArrayList<Sp>();
        int i = 0;
        while (i < Coors.Tam / 2) { //Se comparan las posiciones entre X y Y.
            // of both subsets.
            if (Math.abs(Coors.get(i).po.X - Coors.get(Coors.Tam / 2).po.X) < min && Math.abs(Coors.get(i).po.Y - Coors.get(Coors.Tam / 2).po.Y) < min) {
                Cou++;
                Can.add(Coors.get(i).po);//Se crea un candidato cuando la distancia es menor que d_min.
                i++;
            } else {
                Cou++;
                i = Coors.Tam / 2;
            }
        }
        while (i < Coors.Tam) { //Se comparan para no obtener repetidos.
            if (Math.abs(Coors.get(i).po.X - Coors.get(Coors.Tam / 2 - 1).po.X) < min
                    && Math.abs(Coors.get(i).po.Y - Coors.get(Coors.Tam / 2 - 1).po.Y) < min) {
                Cou++;
                Can.add(Coors.get(i).po);
                i++;
            } else {
                Cou++;
                i = Coors.Tam;
            }
        }
        // Se pasa de ArrayList a LinkedList
        L Lc;
        if (Can.isEmpty()) {
            Lc = new L(null, Can.size());
        } else {
            Lc = new L(new Nod(Can.get(0)), Can.size());
            Nod P = Lc.Head;
            for (int j = 1; j < Can.size(); j++) {
                P.sig = new Nod(Can.get(j));
                P = P.sig;
            }
        }
        return Lc;
    }

   

   

    public static L Starting(int T) { //Se crean coordenadas aleatorias en LinkedList.
        Random Rem = new Random();
        Nod P = null;
        Nod PTR = null;
        for (int i = 0; i < T; i++) {
            Sp Te = new Sp(Rem.nextInt(10000), Rem.nextInt(10000), i);
            Nod Tem = new Nod(Te);
            if (i == 0) {
                PTR = Tem;
                P = PTR;
            } else {
                P.sig = Tem;
                P = P.sig;
            }
        }
        SortL(PTR);
        L l = new L(PTR, T);
        P = l.Head;
        while (P.sig != null) {
            P = P.sig;
        }
        // P.next = list.head;
        return l;
    }

    private static void SortL(Nod PTR) { //Se ordena la LinkedList con respecto a X.
        Nod P = PTR;
        int tam = 0;
        while (P != null) {
            tam++;
            P = P.sig;
        }
        P = PTR;
        for (int i = 0; i < tam; i++) {
            int min = P.po.X;
            Nod P2 = P.sig;
            while (P2 != null) {
                if (P2.po.X < min) {
                    Sp temp = P2.po;
                    P2.po = P.po;
                    P.po = temp;
                    min = P.po.X;
                }
                P2 = P2.sig;
            }
            P = P.sig;
        }
    }

    public static void main(String[] args) {
        Analitic Times = new Analitic();
        Times.Analysis(20000);
    }
}