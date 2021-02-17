# Genetic Algorithm Probe

A genetic algorithm is a search heuristic that is inspired by Charles Darwin's theory of natural
evolution.
This repository contains a trivial implementation of a genetic algorithm for finding the fittest individual.

## How it works
Given is a population of n individuals. Each individual has a set of properties (genes) that could
be either 0 or 1. Initially the genes are set to random values.
Each individual has a fitness score defined as the number of all 1s in the chromosome (all genes together as string).
The following steps are used to determine the next generation:
- select the fittest and second fittest individual
- crossover both to create offspring
- maybe apply mutation to maintain diversity
- replace the least fit with the new offspring

### Selection
The selection of the fittest and second fittest is done via a fitness function that determines a fitness score for
a given individual. In this case it the number of 1s in the chromosome.

**Example:**

```
Individual(01010) has a fitness score of 2
```

### Crossover
Given the fittest and second fittest out of a population, one determines a random crossover point.
If n is the number of genes per individual, we want to have a crossover point p with `0 <= p <=n`.
Starting from beginning of the gene sequence until the crossover point, the genes of the fittest and
second fittest are switched to generate offspring.

**Example:**

_Given a crossover point of 2:_
```
Individual(01010)
Individual(11010)
----------------
Individual(11010) - the offspring 
```

### Mutation
After there is an offspring, there is a small probability that mutation occurs. Mutation means that some
genes are switched (taken by random).

**Example:**

```
Individual(10101) - Before Mutation
Individual(11101) - After Mutation
```

## Building and running
```bash
./gradlew clean build // on linux
./gradlew.bat clean build // on windows

java -jar build/libs/genetic-algorithm-probe-<VERSION>.jar
```