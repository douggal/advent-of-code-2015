# advent-of-code-2015
My solutions to an earlier Advent of Code progrmming contest puzzles from December 2015

Created:  Autumn 2023

Advent of Code Website:  [Advent of Code](https://adventofcode.com)

My solution to each day's puzzles in Scala v3.3.1 unless otherwise noted.

1. Day 1: Not Quite Lisp
2. Day 2: I Was Told There Would Be No Math
3. Day 3: Perfectly Spherical Houses in a Vacuum
4. Day 4: The Ideal Stocking Stuffer
5. Day 5: Doesn't He Have Intern-Elves For This?
6. Day 6: 

### Notes

#### Day 1

```text
Part 1: To what floor do the instructions take Santa?
Santa arrives at floor 138
Elapsed time approx 5 milliseconds

Part 2: What is the position of the character that causes Santa to first enter the basement?
1771
Elapsed time approx 14 milliseconds
```
#### Day 2

```text
Part 1: How many total square feet of wrapping paper should they order?
1588178 square feet of paper is needed.
Part 1 Elapsed time approx 23 milliseconds

Part 2: How many total feet of ribbon should they order?
3783758 feet of ribbon is needed.
Part 2 Elapsed time approx 10 milliseconds
```

#### Day 3

```text
Part 1: How many houses receive at least one present?  Answer 2572
Part 1 elased time approx 20 milliseconds

Part 2: with Santa and Robo-Santa delivering how many houses receive at least one present?  Answer 2631
Part 2 elapsed time approx 9 milliseconds
```
![Part 1 Visual](visualizations%2FAoC_2015_Day3_Part1_small.png)

#### Day 4

- [MD5 Scala implementation credit Alvin Alexander](https://alvinalexander.com/source-code/scala-method-create-md5-hash-of-string/)
- Surprised at how fast the brute force method ran.  For Part 1 I expected minutes not milliseconds.

```text
Part 1: find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.
Answer smallest Int is 117946
Part 1 elased time approx 294 milliseconds
Part 2: smallest number combined with prefix produces a MD5 has with *6* leading 0's is found
Answer smallest Int is 3938038
Part 2 elapsed time approx 3880 milliseconds
```

#### Day 5

- Regex help [Regex 101](https://regex101.com/)
- and regex backrefernces here [Regular-Expressions.info](https://www.regular-expressions.info/brackets.html)

```text
Part 1: How many strings are nice?
Number of nice strings found is 258
Part 1 elased time approx 16 milliseconds

Part 2: How many strings are nice under these new rules?
Number of better nice strings found is 53
Part 2 elapsed time approx 7 milliseconds
```
