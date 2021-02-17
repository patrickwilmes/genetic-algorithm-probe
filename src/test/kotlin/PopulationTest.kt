import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PopulationTest {
    @Test
    fun `given 2 individuals getFittest returns the one with the highest fitness score`() {
        repeat(100) {
            val first = Individual.create(5u)
            val second = Individual.create(5u)
            val firstScore = first.getFitness()
            val secondScore = second.getFitness()

            val population = Population(mutableListOf(first, second))

            val result = population.getFittest()
            if (firstScore > secondScore) {
                assertEquals(result, first)
            } else if (secondScore < firstScore){
                assertEquals(result, second)
            }
        }
    }

    @Test
    fun `given 2 individuals getSecondFittest returns the one with the second highest fitness score`() {
        repeat(100) {
            val first = Individual.create(5u)
            val second = Individual.create(5u)
            val firstScore = first.getFitness()
            val secondScore = second.getFitness()

            val population = Population(mutableListOf(first, second))

            val result = population.getSecondFittest()
            if (firstScore > secondScore) {
                assertEquals(result, second)
            } else if (secondScore < firstScore){
                assertEquals(result, first)
            }
        }
    }

}
