import scala.io.{BufferedSource, Source}
import scala.util.{Try, Success, Failure}
import java.time.{Duration, Instant, ZonedDateTime}
import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex

/** Advent of Code 2015 Day 6
 *
 * Defines a class, its companion object and runner method for
 * the AoC Day 6 puzzles.
 */
class Day06:

    var title: String = "Advent of Code 2015"
    var runType: Int = 1 // Default to test data

    def run():Unit = Day06.runPuzzle(runType)

    override def toString: String = s"Class ${Day06.puzzleTitle}"

end Day06

object Day06 {

    // created 9/24/2023
    // https://adventofcode.com/2015/day/6
    val day = "06"
    val puzzleTitle = "Day 6"

    // input data files
    private val testData: String = s"${day}-test.txt"
    private val realData: String = s"${day}-input.txt"

    // a one-arg constructor
    def apply(runType: Int): Day06 = {
        var p = new Day06()
        p.runType = runType
        p
    }

    // a two-arg constructor
    def apply(title: String, runType: Int): Day06 = {
        var p = new Day06()
        p.title = title
        p.runType = runType
        p
    }

    // Read text file method (modified from original) from Alvin Alexander.  Thanks Al!
    // _Learn Scala 3 The Fast Way!: Book 1, The Adventure Begins_  Amazon Kindle edition, September 2022
    def readTextFile(filename: String): Try[List[String]] = {
        var source: BufferedSource = null
        try
            source = Source.fromFile(filename, "UTF-8")
            val lines = source.getLines().toList
            Success(lines)
        catch
            case t: Throwable => Failure(t)
        finally
            if source != null then
                source.close
    }

    private def runPuzzle(runType: Int): Unit = {

        println(s"--- Advent of Code 2015 ---")
        println(s"--- $puzzleTitle ---\n")

        // Read the puzzle input data file
        print("Attempting to read input data file using ")
        if (runType == 1) then
            println("TEST DATA ... ")
        else
            println("REAL INPUT DATA ...")
        val filename = if (runType == 1) testData else realData
        val path = java.nio.file.Paths.get("src/main/resources").toAbsolutePath().toString
        val text = readTextFile(s"$path/$filename")
        text match
            case Success(contents) => println(s"input file successfully read in.")
            case Failure(exception) => {
                System.err.println(s"exiting. ERROR! ${exception.getMessage}")
                System.exit(1)
            }
        val input = text.get

        println("\nData Quality Control:")
        println(s"  Input file name: $filename")
        println(s"  Each line is a: ${input.head.getClass}")
        println(s"  Number lines: ${input.length}")
        println(s"  Number items per line: ${input.head.count(_ => true)}")
        println(s"  First line: ${input.head.take(72)}")
        if (input.size > 1)
            println(s"  Last line: ${input.last}")
        println("End QC on input file\n")

        // Commmon to both parts

        // Regx to parse each input line
        val pat = raw"(turn on|toggle|turn off) (\d+,\d+) through (\d+,\d+)".r

        // Parse out (x,y) coords from string and return as Tuple2
        def parseCoord(corner: String): (Int, Int) = {
            val coords = corner.split(',').map(_.toInt)
            (coords(0), coords(1))
        }

        // The LED grid.
        // Model the square grid of 1000 x 1000 1 dimensional sequence of LEDs in a mutable ArrayBuffer.
        // Makes sense - how could a FP technique would do any better?
        // Part 1:  0 = off, 1 = on.
        // Part 2:  value indicates brightness.
        // Origin is bottom left, with x's going to right, confusingly representing columns in the grid, and
        // the y's increasing in upward direction, reprsenting rows in the grid.

        // given a coord (x, y) then its cell in grid is found by
        //        y * offset + x + 1
        // given a index of cell, then its cooresponding coord is
        //        ( , )

        val offset = 1000
        val grid = ArrayBuffer[Int]()

        // Index 0 in the 1d grid is not used.  Populated with count of led's per side, or maybe it could be used to represent an attribute of the power source connection.
        grid.append(offset)

        for
            y <- 0 until offset
            x <- 0 until offset
        do
            grid.append(0)
            // println(s"Added item for coord: (${row}, ${col}), index = ${grid.length-1}")

        // ----------
        //  Part One
        // ----------

        println(s"Part 1")
        val p1T0 = Instant.now()

        def turnOn(corner1: String, corner2: String): Unit = {
            val c1 = parseCoord(corner1)
            val c2 = parseCoord(corner2)
            // assume: lower left to upper right orientation
            for
                x <- c1._1 to c2._1
                y <- c1._2 to c2._2 // use to not until - inclusive
            do
                grid(y * offset + x + 1) = 1
        }

        def toggle(corner1: String, corner2: String) = {
            val c1 = parseCoord(corner1)
            val c2 = parseCoord(corner2)
            // assume: lower left to upper right orientation
            for
                x <- c1._1 to c2._1
                y <- c1._2 to c2._2 // use to not until - inclusive
            do
                grid(y*offset + x + 1) ^= 1 // exclusive OR - flip the bit
        }

        def turnOff(corner1: String, corner2: String) = {
            val c1 = parseCoord(corner1)
            val c2 = parseCoord(corner2)
            // assume: lower left to upper right orientation
            for
                x <- c1._1 to c2._1
                y <- c1._2 to c2._2 // use to not until - inclusive
            do
                grid(y*offset + x + 1) = 0
        }

        var linesProcessed = 0
        for line <- input do {
            val pat(cmd, corner1, corner2) = line
            cmd match
                case "turn on" => turnOn(corner1, corner2)
                case "toggle" => toggle(corner1, corner2)
                case "turn off" => turnOff(corner1, corner2)
                case _ => {
                    println("ERROR!  Unidentfied instruction")
                    System.exit(1)
                }
                linesProcessed += 1
        }
        println(s"Processed ${linesProcessed} lines of instructions.")
        println(s"Count of lights turned on is: ${grid.tail.sum}")
        // 378891 too high - do not incl head

        // print grid
//        for
//            y <- 0 until offset
//            x <- 0 until offset
//        do
//            print(s"${grid(y*offset+x+1)}, ")
//            if x == offset-1 then
//                println

        val delta1 = Duration.between(p1T0, Instant.now())
        println(s"Part 1 run time approx ${delta1.toMillis} milliseconds\n")

        // ----------
        //  Part Two
        // ----------

        println(s"Part 2: What is the total brightness of all lights combined after following Santa's instructions?")
        val p2T0 = Instant.now()

        def turnOn2(corner1: String, corner2: String): Unit = {
            val c1 = parseCoord(corner1)
            val c2 = parseCoord(corner2)
            // assume: lower left to upper right orientation
            for
                x <- c1._1 to c2._1
                y <- c1._2 to c2._2 // use to not until - inclusive
            do
                grid(y * offset + x + 1) += 1
        }

        def toggle2(corner1: String, corner2: String) = {
            val c1 = parseCoord(corner1)
            val c2 = parseCoord(corner2)
            // assume: lower left to upper right orientation
            for
                x <- c1._1 to c2._1
                y <- c1._2 to c2._2 // use to not until - inclusive
            do
                grid(y * offset + x + 1) += 2
        }

        def turnOff2(corner1: String, corner2: String) = {
            val c1 = parseCoord(corner1)
            val c2 = parseCoord(corner2)
            // assume: lower left to upper right orientation
            for
                x <- c1._1 to c2._1
                y <- c1._2 to c2._2 // use to not until - inclusive
            do
                if grid(y * offset + x + 1) >0 then
                    grid(y * offset + x + 1) -= 1
        }

        // reset grid to all 0's
        for
            y <- 0 until offset
            x <- 0 until offset
        do
            grid(y*offset + x + 1) = 0

        var linesProcessed2 = 0
        for line <- input do {
            val pat(cmd, corner1, corner2) = line
            cmd match
                case "turn on" => turnOn2(corner1, corner2)
                case "toggle" => toggle2(corner1, corner2)
                case "turn off" => turnOff2(corner1, corner2)
                case _ => {
                    println("ERROR! Part 2 Unidentfied instruction")
                    System.exit(1)
                }
            linesProcessed2 += 1
        }
        println(s"Processed ${linesProcessed2} lines of instructions.")
        println(s"Sum of values of lights is: ${grid.tail.sum}")

        val delta2 = Duration.between(p2T0, Instant.now())
        println(f"Part 2 run time approx ${delta2.toMillis} milliseconds")

        // errata...for visualation with Excel chart

    }
}
