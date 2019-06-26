/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica1;

/**
 *
 * @compilado luis
 */

import java.io.File;
import javax.swing.JOptionPane;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.data.DataTreeBuilder;
import org.jgap.data.IDataCreators;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.xml.XMLDocumentBuilder;
import org.jgap.xml.XMLManager;
import org.w3c.dom.Document;

/**
* En este ejemplo se muestra como resolver un problema clasico de algoritmos
* genéticos utilizando el framework JGAP. El problema consiste en lograr juntar
* el monto de dinero ingresado a la aplicacion por parametro con la menor
* cantidad de monedas posibles. Para resolver el problema nos basamos en la
* moneda de la Republica Argentina(Se adapto para trabajarlo en Euros aumentando)
* la moneda de 2 euros, cambiando la de 25 por 20 y agregando la de 2 centimos
* Moneda de 1 euro ( equivale a 100 centimos) Moneda de 50 Centimos Moneda de 20 Centimos
* Moneda de 10 Centimos Moneda de 5 Centimos Moneda de 2 Centimos Moneda de 1 Centimo
*
* @author Gabriel Veloso
* @author Ruben Arce
* @since 1.0
*/

public class CambioMinimo {
/**
	* The total number of times we'll let the population evolve.
	*/
	private static final int MAX_EVOLUCIONES_PERMITIDAS = 2200;
        private static int monto;
        
        private static String resultado="";
        private static long tiempoEvolucion;

    public static long getTiempoEvolucion() {
        return tiempoEvolucion;
    }

    public static void setTiempoEvolucion(long tiempoEvolucion) {
        CambioMinimo.tiempoEvolucion = tiempoEvolucion;
    }

    public static int getMonto() {
        return monto;
    }

    public static void setMonto(int monto) {
        CambioMinimo.monto = monto;
    }

    public static String getResultado() {
        return resultado;
    }

    public static void setResultado(String resultado) {
        CambioMinimo.resultado = resultado;
    }
	/**
	* Calcula utilizando algoritmos geneticos la solución al problema y la
	* imprime por pantalla
	*
	* @param Monto
	* Monto que se desea descomponer en la menor cantidad de monedas
	* posibles
	* @throws Exception
	*
	* @author Gabriel Veloso
	* @author Ruben Arce
	* @since 1.0
	*/

	public static void calcularCambioMinimo(int Monto)throws Exception {
		// Se crea una configuracion con valores predeterminados.
		// -------------------------------------------------------------
		Configuration conf = new DefaultConfiguration();
		// Se indica en la configuracion que el elemento mas apto siempre pase a
		// la proxima generacion
		// -------------------------------------------------------------
		conf.setPreservFittestIndividual(true);
		// Se Crea la funcion de aptitud y se setea en la configuracion
		// ---------------------------------------------------------
		FitnessFunction myFunc = new CambioMinimoFuncionAptitud(Monto);
		conf.setFitnessFunction(myFunc);
		// Ahora se debe indicar a la configuracion como seran los cromosomas: en
		// este caso tendran 8 genes (uno para cada tipo de moneda) con un valor
		// entero (cantidad de monedas de ese tipo).
		// Se debe crear un cromosoma de ejemplo y cargarlo en la configuracion
		// Cada gen tendra un valor maximo y minimo que debe setearse.
		// --------------------------------------------------------------
		Gene[] sampleGenes = new Gene[6];
		//sampleGenes[0] = new IntegerGene(conf, 0, Math.round(CambioMinimoFuncionAptitud.MAX_MONTO/200)); // Moneda 2 euros
                sampleGenes[0] = new IntegerGene(conf, 0, Math.round(CambioMinimoFuncionAptitud.MAX_MONTO/100)); // Moneda 1 euro
		sampleGenes[1] = new IntegerGene(conf, 0, 10); // Moneda 50 centimos
		sampleGenes[2] = new IntegerGene(conf, 0, 10); // Moneda 25 centimos
		sampleGenes[3] = new IntegerGene(conf, 0, 10); // Moneda 10 centimos
		sampleGenes[4] = new IntegerGene(conf, 0, 10); // Moneda 5 centimos
                //sampleGenes[6] = new IntegerGene(conf, 0, 10); // Moneda 2 centimos
		sampleGenes[5] = new IntegerGene(conf, 0, 10); // Moneda 1 centimo
		IChromosome sampleChromosome = new Chromosome(conf, sampleGenes);
		conf.setSampleChromosome(sampleChromosome);
		// Por ultimo se debe indicar el tamaño de la poblacion en la
		// configuracion
		// ------------------------------------------------------------
		conf.setPopulationSize(200);
		Genotype Poblacion;
		// El framework permite obtener la poblacion inicial de archivos xml
		// pero para este caso particular resulta mejor crear una poblacion
		// aleatoria, para ello se utiliza el metodo randomInitialGenotype que
		// devuelve la poblacion random creada
		Poblacion = Genotype.randomInitialGenotype(conf);
		// La Poblacion debe evolucionar para obtener resultados mas aptos
		// ---------------------------------------------------------------
		long TiempoComienzo = System.currentTimeMillis();
		for (int i = 0; i < MAX_EVOLUCIONES_PERMITIDAS; i++) {
			Poblacion.evolve();
		}
		long TiempoFin = System.currentTimeMillis();
		//System.out.println("Tiempo total de evolucion: " + (TiempoFin - TiempoComienzo) + " ms");
                tiempoEvolucion=(TiempoFin -TiempoComienzo);
		guardarPoblacion(Poblacion);
		// Una vez que la poblacion evoluciono es necesario obtener el cromosoma
		// mas apto para mostrarlo como solucion al problema planteado para ello
		// se utiliza el metodo getFittestChromosome
		IChromosome cromosomaMasApto = Poblacion.getFittestChromosome();
		//System.out.println("El cromosoma mas apto encontrado tiene un valor de aptitud de: " + cromosomaMasApto.getFitnessValue());
		resultado="El cromosoma mas apto encontrado tiene un valor de aptitud de: " + cromosomaMasApto.getFitnessValue()
                        +"\n"+"Y esta formado por la siguiente distribucion de monedas: "+"\n";
                //System.out.println("Y esta formado por la siguiente distribucion de monedas: ");
		//System.out.println("\t"	+ CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 0) + " Moneda 1 Dolar");
                resultado+="\t"+CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 0) + " Moneda 1 Dolar"+"\n";
                //System.out.println("\t"	+ CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 1) + " Moneda 50 Centavos");
                resultado+="\t"+CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 1) + " Moneda 50 Centavos"+"\n";
		//System.out.println("\t"	+ CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 2) + " Moneda 25 Centavos");
                resultado+="\t"+CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 2) + " Moneda 25 Centavos"+"\n";
		//System.out.println("\t"	+ CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 3) + " Moneda 10 Centavos");
		resultado+="\t"+CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 3) + " Moneda 10 Centavos"+"\n";
                //System.out.println("\t"	+ CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 4) + " Moneda 5 Centavos");
		resultado+="\t"+CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 4) + " Moneda 5 Centavos"+"\n";
                //System.out.println("\t" + CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 5) + " Moneda 1 Centavos");
                resultado+="\t"+CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 5) + " Moneda 1 Centavos"+"\n";
                //System.out.println("\t" + CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 6) + " Moneda 2 centimos");
		//System.out.println("\t"	+ CambioMinimoFuncionAptitud.getNumeroDeComendasDeGen(cromosomaMasApto, 7) + " Moneda 1 centimo");
		//System.out.println("Para un total de "+ CambioMinimoFuncionAptitud.montoCambioMoneda(cromosomaMasApto) + " centimos en " + CambioMinimoFuncionAptitud.getNumeroTotalMonedas(cromosomaMasApto) + " monedas.");
                resultado+=("Para un total de "+ CambioMinimoFuncionAptitud.montoCambioMoneda(cromosomaMasApto) + " centimos en " + CambioMinimoFuncionAptitud.getNumeroTotalMonedas(cromosomaMasApto) + " monedas.");
                //JOptionPane.showMessageDialog(null, resultado);
                obtenerResultado();
                obtenerTiempoEvolucion();
	}
        public static String obtenerResultado(){
            return resultado;
        }
        public static long obtenerTiempoEvolucion(){
            return tiempoEvolucion;
        }
	/**
	* Metodo principal: Recibe el monto en dinero por parametro para determinar
	* la cantidad minima de monedas necesarias para formarlo
	*
	* @param args
	* Monto de dinero
	* @throws Exception
	*
	* @author Gabriel Veloso
	* @author Ruben Arce
	* @since 1.0
	*/

	public static void main(String[] args) throws Exception {
			//int amount = 323;
                        int amount=Integer.parseInt(JOptionPane.showInputDialog("ingrese valor"));
                        monto=amount;
			try {
				//amount = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.out.println("El (Monto de dinero) debe ser un numero entero valido");
				System.exit(1);
			}
			if (amount < 1 || amount >= CambioMinimoFuncionAptitud.MAX_MONTO) {
				System.out.println("El monto de dinero debe estar entre 1 y "+ (CambioMinimoFuncionAptitud.MAX_MONTO - 1)+ ".");
			} else {
				calcularCambioMinimo(amount);
			}

	}



	// ---------------------------------------------------------------------
	// Este metodo permite guardar en un xml la ultima poblacion calculada
	// ---------------------------------------------------------------------

	public static void guardarPoblacion(Genotype Poblacion) throws Exception {
		DataTreeBuilder builder = DataTreeBuilder.getInstance();
		IDataCreators doc2 = builder.representGenotypeAsDocument(Poblacion);
		// create XML document from generated tree
		XMLDocumentBuilder docbuilder = new XMLDocumentBuilder();
		Document xmlDoc = (Document) docbuilder.buildDocument(doc2);
		XMLManager.writeFile(xmlDoc, new File("PoblacionCambioMinimo.xml"));
	}
}
