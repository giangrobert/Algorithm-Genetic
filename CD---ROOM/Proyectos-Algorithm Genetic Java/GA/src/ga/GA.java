
package ga;

public class GA {

    public static void main(String[] args) {
        FitnessCalc.setSolution("1111000000000000000000000000000000000000000000000000000000001111");
        Population myPop = new Population(100, true);
        int generationCount = 0;
        while (myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness()) {
            generationCount++;
            System.out.println("Generacion: " + generationCount + " Mas fuerte: " + myPop.getFittest().getFitness());
            myPop = Algorithm.evolvePopulation(myPop);
        }
        System.out.println("Solución encontrada!");
        System.out.println("Generacion: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());

    }
}
