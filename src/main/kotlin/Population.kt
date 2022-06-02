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
