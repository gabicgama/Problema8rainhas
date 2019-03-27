package problemas8rainhas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Gabriela
 */

public class main {

    static Random rnd = new Random();

    public static void main(String[] args) {

        Ag ag = new Ag();
        IndividuoFactory iF = new rainhasIndFactory(8);
        ag.executar(20, 130, iF, 4);

    }

}
