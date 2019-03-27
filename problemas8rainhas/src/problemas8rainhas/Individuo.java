
package problemas8rainhas;

import java.util.List;

/**
 *
 * @author Gabriela
 */
public interface Individuo {

    public float getAvaliacao();

    public List<Individuo> getMutantes();

    public List<Individuo> recombinacao(Individuo ind);

    public int getTam();

    public int[] getVet();

    public void imprimir(Individuo ind1, Individuo ind2);

    public void imprimir();

}
