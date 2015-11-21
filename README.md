# WordSearch

WordSearch was a simple project assigned for my Data Structures and Algorithms Class (CS 360)
at the University of Alabama.

The WordSearch implements a Trie to solve a word search given a dictionary. It has been
tested and can run on a 1000x1000 puzzle.

You can run the program with square word search puzzle. Sample puzzles and dictionaries are
provided in this repository.

The puzzle must have the following format:
1. Line 1 must have the dimension of the puzzle
2. Characters must be on subsequent lines delimited by a space.

An example puzzle file is below:
```
5
a b c d e
f g h i j
k l m n o
p q r s t
u v w x y
```

The dictionary must have the following format:

1. Each line has an individual entry in the dictionary.

An example file is below:

```
wheel
vehicle
character
```

To run the program, run `ant jar`, and then use the syntax

```bash
$ java -jar WordSearch.jar <path to puzzle> <path to dictionary>
```

# Contibuting
Please feel free to contact me if you're interested in contributing to this repository.
