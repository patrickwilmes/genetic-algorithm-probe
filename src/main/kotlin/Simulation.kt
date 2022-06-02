import kotlin.random.Random
import kotlin.random.nextUInt

class Simulation(private val population: Population) {
    private var generationCount: UInt = 0u
    private var fittest: Individual? = null
    private var secondFittest: Individual? = null

    fun simulate() {
        while (population.getHighestFitnessScore() < 5 || population.noDiversity()) {
            ++generationCount
            selection()
            crossover()
            if (Random.nextInt() % 7 < 5)
                mutation()
            addFittestOffspring()
            println("Generation: $generationCount Fittest: ${population.getFittest()}")
        }
        println("New solution found in generation: $generationCount")
        println("Fitness: ${population.getFittest().getFitness()}")
        println("Genes: ${population.getFittest().genes}")
    }

    private fun selection() {
        fittest = population.getFittest()
        secondFittest = population.getSecondFittest()
    }

    private fun crossover() {
        val geneLength = population.getGeneLength().toUInt()
        val crossoverPoint = Random.nextUInt(geneLength)

        repeat(crossoverPoint.toInt()) { index ->
            val temp = fittest?.genes!![index]
            fittest?.genes!![index] = secondFittest?.genes!![index]
            secondFittest?.genes!![index] = temp
        }
    }

    private fun mutation() {
        fittest?.genes!![Random.nextInt(population.getGeneLength().toInt())].reverse()
        fittest?.genes!![Random.nextInt(population.getGeneLength().toInt())].reverse()
    }

    private fun getFittestOffspring() = if (fittest?.getFitness()!! > secondFittest?.getFitness()!!)
        fittest
    else
        secondFittest

    private fun addFittestOffspring() {
        population.replaceLeastFittestWithFittestOffspring(getFittestOffspring()!!)
    }
}
