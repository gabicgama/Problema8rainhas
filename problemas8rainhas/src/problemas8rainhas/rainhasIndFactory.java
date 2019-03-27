package problemas8rainhas;

public class rainhasIndFactory implements IndividuoFactory {

    private int n;

    public rainhasIndFactory(int n) {
        this.n = n;
    }

    @Override
    public Individuo getIndividuo() {
        return new rainhasInd(n);
    }

}

//IndividuoFactory iF = new rainhasIndFactory(8);
//Ag.executar(20,100,iF)
