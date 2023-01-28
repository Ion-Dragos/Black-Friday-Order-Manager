# ALGORITMI PARALELI SI DISTRIBUITI - TEMA 2
_________________________________________________________
### Manager de comenzi de Black Friday in Java
### In Dragos-Cosmin - 336CC

## main
* Clasa principala a proiectului, care contine metoda main. Aceasta preia din linia de comanda calea catre
fisierele ce trebuie citite si numarul de thread-uri ce vor fi folosite pentru executia comenzilor.
* Am pornit cozile de tip atomic si executorii
* Am decalarat writerii de comenzi si produse
* Am declarat readerul pentru comenzi
* Am pornit thread-urile de citire a comenzilor

## MyRunnableOrders
* Clasa ce implementeaza Runnable si care este folosita pentru a citi comenzile din fisierul de comenzi
* Am citit linie cu linie si am separat prin split dupa "," elementele de care aveam nevoie, adica id-ul si numarul de comenzi 
* Verificam sa nu ne ocupam de comenzile care au 0 produse si lansez thread-uri de nivel 2 pentru a se ocupa de produsele din fiecare comanda
* Am grija sa inchid writer-ul si sa dau shutdown la executor.

## MyRunnableOrderProducts
* Clasa ce implementeaza Runnable si care este folosita pentru a citi produsele dintr-o comanda
* Am citit linie cu linie si am separat prin split dupa "," elementele de care aveam nevoie, adica id-ul si numele comenzii
* Am verificat sa fie acelasi id si am scris in fisier "shipped"
* Am scazut numarul de produse cu acel id.
* La fel, am avut grija sa inchid writer-ul si sa dau shutdown la executor.