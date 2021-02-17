import kotlin.random.Random
import kotlin.random.nextUInt

class Population(individuals: MutableList<Individual>) {
    private val individuals =
        if (individuals.groupBy { it.numberOfGenes() }.keys.count() == 1) individuals
        else throw RuntimeException("All individuals are supposed to have the exact same amount of genes")

    fun getGeneLength() = individuals.first().numberOfGenes()
    fun getHighestFitnessScore() = getFittest().getFitness()
    fun getFittest(): Individual = individuals.map { it to it.getFitness() }.maxByOrNull { it.second }!!.first
    fun getSecondFittest(): Individual = individuals.map { it to it.getFitness() }.sortedByDescending { it.second }[1].first
    fun replaceLeastFittestWithFittestOffspring(individual: Individual) {
        individuals.sortBy { it.getFitness() }
        individuals[0] = individual
    }

    fun noDiversity() = individuals.toSet().isEmpty()
}

class Individual private constructor(
    val genes: MutableList<Binary>
) {
    fun getFitness() = genes.count { it.isSet() }
    fun numberOfGenes() = genes.size.toUByte()
    fun chromosome() = genes.joinToString()

    override fun hashCode(): Int {
        return chromosome().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Individual) {
            return false
        }
        return other.chromosome() == chromosome()
    }

    override fun toString() = "${getFitness()}"

    companion object {
        fun create(numberOfGenes: UByte) = Individual(
            numberOfGenes.downTo(1U).map { getRandomBinaryValue() }.toMutableList()
        )
    }
}

class Simulation(val population: Population) {
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

fun main() {
    val population = Population(15.downTo(1).map { Individual.create(5u) }.toMutableList())
    Simulation(population)
        .simulate()
}