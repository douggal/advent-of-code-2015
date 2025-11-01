# Advent of Code 2015
My solutions to an earlier Advent of Code programming contest puzzles from December 2015

Created:  Autumn 2023

Advent of Code Website:  [Advent of Code](https://adventofcode.com)

My solution to each day's puzzles in Scala 3 unless otherwise noted.

> ⚠️ Puzzle descriptions and input files are not included due to copyright restrictions.  
> Please visit the official Advent of Code website to view the original puzzles.

### Notes


#### Day 3
![Part 1 Visual](visualizations%2FAoC_2015_Day3_Part1_small.png)

#### Day 4

- [MD5 Scala implementation credit Alvin Alexander](https://alvinalexander.com/source-code/scala-method-create-md5-hash-of-string/)
- Surprised at how fast the brute force method ran.  For Part 1 I expected minutes not milliseconds.

#### Day 5

- Regex help [Regex 101](https://regex101.com/)
- and regex backrefernces here [Regular-Expressions.info](https://www.regular-expressions.info/brackets.html)


#### Day 6

- Took me longer to solve than I had expected.  Confused row, column vs x, y in the coords and made a lot of dumb 
mistakes setting up a representation of the grid.
- Refactored my code template for a better and cleaner organization
- Improved text file read with exception handling and Try/Success/Failure types. Thanks Al! Ref: Alvin Alexander [Learn Scala 3 The Fast Way](https://www.amazon.com/Learn-Scala-Fast-Way-Adventure-ebook/dp/B0BDWQ75YC/ref=sr_1_1?crid=2QL5ZC6H18JXH&keywords=learn+scala+3+the+fast+way&qid=1697330886&sprefix=learn+scala+3+the+fast+way%2Caps%2C80&sr=8-1)
- Use one dimensional sequence to represent the 1000 x 1000 array with arithmetic to handle row, column coordinates.
- Cartesian coords:  origin is bottom left with the x's going to the right, and the columns (y's) going upward.
- A coordinate is (x, y) with x being index of column and y the index of row in the gird of LEDs


#### Day 7

- DNF
- Works with test data presented in description
- Did not realize at first bit-width of Scala Int is 32, and was surprised bitwise ops are not supported on Short when I tried to use Short
- The NOT operation fooled me.  Didn't realize I had to mask off the high 16 bits.
- Followed example here: [Jan\-Pieter van den Heuvel \- Saving Christmas Using Scala](https://www.youtube.com/watch?v=tHU36gQ5iAI)
- Moved input files to /src/main/resources
- Update Day01 - Day06 classes to load file from resources folder
- Day 07 and after, using newer library features and simple scala.io.Source.fromResource to read input files
- [Scala does not have an unsigned int type](https://stackoverflow.com/questions/21212993/unsigned-variables-in-scala?noredirect=1&lq=1) !

## License
This project is licensed under the MIT License.

Acknowledgements
----------------
- Advent of Code (https://adventofcode.com)
- Scala language and community resources

