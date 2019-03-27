package problemas8rainhas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Gabriela
 */

public class rainhasInd implements Individuo {

    private int[] vet;

    public rainhasInd(int nRainhas) {

        this.vet = new int[nRainhas];
        Random rnd = new Random();
        for (int i = 0; i < nRainhas; i++) {
            int x = rnd.nextInt(nRainhas);
            this.vet[i] = x;
        }

    }

    @Override
    public float getAvaliacao() {

        int colisao = 0;

        for (int i = 0; i < this.vet.length - 1; i++) {
            for (int j = i + 1; j < this.vet.length; j++) {

                if (this.vet[i] == this.vet[j]) {
                    colisao++;
                } else if (this.vet[i] + (j - i) == vet[j]) {
                    colisao++;
                } else if (this.vet[i] - (j - i) == vet[j]) {
                    colisao++;
                }
            }
        }

        return colisao;
    }

    //copiar o pai, fazer uma probalidade para cada posição do vetor
    //se cair até 10% sortear um numero entre o escopo
    //se continuar igual o pai, sortear uma posicao e fazer alteração
    //ate ficar diferente
    //criar um novo objeto rainhaInd e preencher seus valores com os valores de this neste novo objeto realizar mutação
    @Override
    public List<Individuo> getMutantes() {

        int n, cont, prob, n2;
        int[] mutante;
        mutante = this.vet.clone();
        Random rnd = new Random();
        List<Individuo> ret = new ArrayList<Individuo>();
        rainhasInd ind = new rainhasInd(this.vet.length);

        cont = 0;
        for (int i = 0; i < this.vet.length; i++) {
            prob = rnd.nextInt(100) + 1;

            if (prob <= 10) {
                n = rnd.nextInt(this.vet.length);
                mutante[i] = n;
            }
        }

        for (int j = 0; j < this.vet.length; j++) {
            if (this.vet[j] == mutante[j]) {
                cont++;
            }
        }

        if (cont == this.vet.length) {

            do {
                cont = 0;
                n = rnd.nextInt(this.vet.length);
                n2 = rnd.nextInt(this.vet.length);
                mutante[n] = n2;

                for (int i = 0; i < this.vet.length; i++) {
                    if (this.vet[i] == mutante[i]) {
                        cont++;
                    }
                }

            } while (cont == this.vet.length);
        }

        
        ind.vet = mutante;
        ret.add(ind);

        return ret;
    }

    //recombinar
    //sortear uma posição para corte entre 0 e tamanho do vetor
    //criar dois vetores com this.vet e o ind.vet (na main -> i1.recombinar(i2) )
    //preencher com vetores com um for ate e prencher com as metades do vetor
    //add os dois vetores combinados no arraylist;
    @Override
    public List<Individuo> recombinacao(Individuo ind) {

        List<Individuo> ret = new ArrayList<Individuo>(2);
        
        if (ind instanceof rainhasInd) {

            Random rnd = new Random();
            int tam = this.vet.length;
            int corte = rnd.nextInt(tam - 2) + 1;
            int[] filho1, filho2;
            filho1 = new int[tam];
            filho2 = new int[tam];

            for (int i = 0; i <= corte; i++) {
                filho1[i] = this.vet[i];
                filho2[i] = ((rainhasInd) ind).vet[i];
            }

            for (int j = corte + 1; j < this.vet.length; j++) {
                filho1[j] = ((rainhasInd) ind).vet[j];
                filho2[j] = this.vet[j];
            }

            rainhasInd v = new rainhasInd(tam);
            rainhasInd v2 = new rainhasInd(tam);
            v.vet = filho1;
            v2.vet = filho2;
            ret.add(v);
            ret.add(v2);

            //imprimir(v, v2);
        }
        return ret;

    }

    public int getTam() {
        return this.vet.length;
    }
    
    public int[] getVet(){
        return this.vet;
    }

    //metodos pra imprimir indivíduo
    
    public void imprimir(Individuo ind1, Individuo ind2) {

        if (ind1 instanceof rainhasInd) {
            for (int i = 0; i < ((rainhasInd) ind1).vet.length; i++) {
                System.out.print(((rainhasInd) ind1).vet[i] + " ");
            }
        }
        System.out.println("");
        if (ind2 instanceof rainhasInd) {
            for (int i = 0; i < ((rainhasInd) ind2).vet.length; i++) {
                System.out.print(((rainhasInd) ind2).vet[i] + " ");
            }
        }
    }

    public void imprimir() {
        //public void imprimir(Individuo ind1) {

        for (int i = 0; i < this.vet.length; i++) {
            System.out.print(this.vet[i] + " ");
        }
        System.out.println("");

    }

}
