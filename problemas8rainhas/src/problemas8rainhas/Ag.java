package problemas8rainhas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Gabriela
 */

public class Ag {

    static Random rnd = new Random();

    public static void executar(int np, int nger, IndividuoFactory iF, int nElite) {

        List<Individuo> pop = new ArrayList<Individuo>(np);
        List<Individuo> filhos = new ArrayList<Individuo>();
        List<Individuo> mutantes = new ArrayList<Individuo>();
        List<Individuo> todos = new ArrayList<Individuo>();

        for (int m = 0; m < np; m++) {
            pop.add(iF.getIndividuo());
        }

        for (int i = 0; i < nger; i++) {
            todos.clear();
            todos.addAll(pop);

            System.out.println("-> " + (i + 1) + "ª Geração");
            mutantes = mutar(pop);
            todos.addAll(mutantes);
            filhos = recombinar(pop);
            todos.addAll(filhos);

            pop = selecao(todos, np, nElite);

            //imprimir melhor individuo de pop
            float menor;
            menor = pop.get(0).getTam();

            int pos = 0;

            for (int j = 0; j < pop.size(); j++) {
                if (pop.get(j).getAvaliacao() < menor) {
                    menor = pop.get(j).getAvaliacao();
                    pos = j;
                }
            }

            System.out.println("* Melhor indivíduo");
//            for (int k = 0; k < pop.size(); k++) {
//                if (pop.get(k).getAvaliacao() == menor) {
            System.out.print("* Solução: ");
            pop.get(pos).imprimir();
            System.out.print("* Avaliação: " + pop.get(pos).getAvaliacao());
            System.out.println("\n");
//                }
//            }
        }//end for geração
        //}
    }

    public static List<Individuo> recombinar(List<Individuo> pop) {

        List<Individuo> filhos = new ArrayList<Individuo>();
        List<Individuo> pais = new ArrayList<Individuo>();
        List<Individuo> rec = new ArrayList<Individuo>();

        int n, n2;

        while (pop.size() > 1) {

            do {
                n = rnd.nextInt(pop.size());
                n2 = rnd.nextInt(pop.size() - 1);
            } while (n == n2);

            rec = pop.get(n).recombinacao(pop.get(n2));
            filhos.addAll(rec);
            Individuo aux = pop.remove(n);
            pais.add(aux);
            aux = pop.remove(n2);
            pais.add(aux);

        }

        pop.addAll(pais);

        return filhos;
    }

    public static List<Individuo> mutar(List<Individuo> pop) {

        List<Individuo> mutantes = new ArrayList<Individuo>();
        List<Individuo> ret = new ArrayList<Individuo>();

        for (int i = 0; i < pop.size(); i++) {

            ret = pop.get(i).getMutantes();
            mutantes.addAll(ret);
        }

        return mutantes;
    }

    public static List<Individuo> selecao(List<Individuo> todos, int np, int nElite) {

        //na roleta e no elitismo, verificar se há clones
        List<Individuo> elite = selecionarElite(nElite, todos);
        List<Individuo> roleta = selecionarRoleta(np, nElite, todos);
        List<Individuo> melhoresInd = new ArrayList<Individuo>();

        melhoresInd.addAll(elite);
        melhoresInd.addAll(roleta);

        return melhoresInd;
    }

    //Seleciona os n primeiros individuos para elite;
    public static List<Individuo> selecionarElite(int nElite, List<Individuo> todos) {

        List<Individuo> elite = new ArrayList<Individuo>(nElite);

        float menor = Float.MAX_VALUE;
        float valor;
        int cont = 0;

        //selecionar os 4 primeiros com menor avaliação
        for (int i = 0; i < todos.size(); i++) {
            valor = todos.get(i).getAvaliacao();
            if (valor < menor) {
                menor = valor;
            }
        }

        do {
            for (int i = 0; i < todos.size(); i++) {
                if (todos.get(i).getAvaliacao() == menor) {

                    if (isClone(todos.get(i), elite) == false) {
                        elite.add(todos.get(i));
                        cont++;
                        todos.remove(i);
                    } else {
                    }
                    if (cont == nElite) {
                        break;
                    }
                }
            }
            menor = menor + 1;
        } while (cont < nElite);

        return elite;
    }

    public static List<Individuo> selecionarRoleta(int np, int nElite, List<Individuo> todos) {

        int tamRoleta = (np - nElite);
        float num, soma, total;
        int cont;
        List<Individuo> roleta = new ArrayList<Individuo>(tamRoleta);
        List<Individuo> sorteio = new ArrayList<Individuo>(todos.size());

        sorteio = todos;

        for (int i = 0; i < tamRoleta; i++) {

            cont = 0;
            soma = 0;
            total = 0;

            for (int j = 0; j < sorteio.size(); j++) {
                num = sorteio.get(j).getAvaliacao();
                if (num == 0.0) {
                    num = 1.f;

                }
                total += 1.f / num;
            }

            double min = 0.01;
            double max = total;
            double sort = min + (max - min) * rnd.nextDouble();

            while (soma < sort) {
                num = sorteio.get(cont).getAvaliacao();
                if (num == 0.0) {
                    num = 1.f;
                }
                soma += (1.f / num);
                cont++;

            }

            if (isClone(sorteio.get(cont - 1), roleta) == false) {
                roleta.add(sorteio.get(cont - 1));
                sorteio.remove(cont - 1);
            }
        }

        return roleta;
    }

    public static boolean isClone(Individuo ind, List<Individuo> list) {

        boolean ret = false;
        int x = ind.getTam();
        int[] vet = ind.getVet();
        int[] vet2;
        int cont;

        for (int i = 0; i < list.size(); i++) {
            cont = 0;
            vet2 = list.get(i).getVet();
            for (int j = 0; j < x; j++) {
                if (vet[j] == vet2[j]) {
                    cont++;
                }
            }
            if (cont == x) {
                ret = true;
            }

        }

        return ret;
    }

}
