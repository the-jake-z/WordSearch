# WordSearch

WordSearch was a simple project assigned for my Data Structures and Algorithms Class (CS 360)
at the University of Alabama.

The WordSearch implements a Trie to solve a word search given a dictionary. From
wikipedia:

--------------------------------------------------------------------------------
> ...a trie, also called digital tree and sometimes radix tree or prefix tree
> (as they can be searched by prefixes), is an ordered tree data structure that
> is used to store a dynamic set or associative array where the keys are usually
> strings.

--------------------------------------------------------------------------------

The program first reads in the puzzle, then the dictionary of words. As it reads
in the dictionary of words, it builds a prefix tree. Each node represents a
letter in a word. For example, if you had the words cat, cats, call, and carry
you'd have a tree rooted at C that looks like this:

```
             c
             |  
             a
           / | \
          l  r  t
         /   |   \
        l    r    s
             |
             y
```

Each alphabet letter has its own tree, so you will have at most 26 trees that
you are dealing with.

Each node also stores whether or not it is the end of the word in a boolean
value. This allows for the dictionary to contain `cat` and `cats` and still be
able to find the words.

At each location in the puzzle, you do a depth first search in 1 direction,
which gives you all the valid words. As you perform your DFS in the graph, the
you trace down the prefix tree for that letter. As soon as the program reaches
a node in the prefix tree that is the end of a word, it prints that word to the
console.

---

You can run the program with square word search puzzle. Sample puzzles and
dictionaries are provided in this repository.

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

1. Each line has an individual word which is an entry in the dictionary.

An example file is below:

```
wheel
vehicle
character
```

To run the program, run `ant jar`, and then use the syntax

```bash
$ java -jar WordSearch.jar "path to puzzle" "path to dictionary"
```

The program outputs the text the console screen, which means you can use Standard IO Redirection to place the output in a file.
For example, if you wanted the the output to go into the file `results.txt`, you could write

```bash
$ java -jar WordSearch.jar "path to puzzle" "path to dictionary" >> results.txt
```

# Requirements

- Any version of the Java JDK (version 8.0+)
- ANT

# Contributing
Please feel free to contact me if you're interested in contributing to this repository.
