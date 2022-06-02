fun main() {
    val population = Population(15.downTo(1)
        .map { Individual.create(5u) }.toMutableList()
    )
    Simulation(population)
        .simulate()
}