import java.io.File

/** Advent of Code 2015 Day XX: TEMPLATE for each day
 *
 * Defines a class, its companion object and runner method for
 * the AoC Day XX puzzles.
 */
class DayXX:

    var title: String = "Default"
    var runType: Int = 1 // Default to test data

    def run = DayXX.runPuzzle(title, runType)

    override def toString: String = s"Class ${DayXX.puzzleTitle}"

end DayXX

object DayXX {

    // created 9/8/2023
    // https://adventofcode.com/2015/day/XX

    val puzzleTitle = "Day 1: Not Quite Lisp"
    val day = "dayXX"
    private val testData: String = s"${day}TestData.txt"
    private val realData: String = s"${day}Input.txt"

    // a one-arg constructor
    def apply(title: String): DayXX = {
        var p = new DayXX()
        p.title = title
        p
    }

    // a two-arg constructor
    def apply(title: String, runType: Int): DayXX = {
        var p = new DayXX()
        p.title = title
        p.runType = runType
        p
    }

    private def runPuzzle(title: String, runType: Int): Unit =

        println(s"--- $title ---")
        println(s"--- $puzzleTitle ---")
        if (runType == 1)
            println("--- USING TEST DATA ---")
        else
            println("--- USING REAL INPUT DATA ---")

        // Puzzle Input Data File
        val filename = if (runType == 1) testData else realData

        val readInputData = () => {
            val f = java.nio.file.Paths.get("./input").toAbsolutePath().toString
            val source = io.Source.fromFile(s"$f/$filename", "UTF-8")
            for {
                line <- source.getLines().toVector
            } yield
                line
        }

        val input = readInputData()
        /*for (line <- input) {
            println(line)
        }*/

        println("\nData Quality Control:")
        println(s"Start Timestamp ${java.time.ZonedDateTime.now()}")
        println(s"Input file name: $filename")
        println(s"Each line is a: ${input.getClass}")
        println(s"Number lines: ${input.length}")
        println(s"Number items per line: ${input.head.count(_ => true)}")
        println(s"Input first line: ${input.head}")
        if (input.size > 1) println(s"Input last line: ${input.tail.last}")
        println("End QC on input file\n")

        // Part One
        println(s"${} Part 1 TBD")

        // parse dots


        // parse instructions


        // Part Two

        println(s"$title Part 2  [TBD]")

        println(s"End at ${java.time.ZonedDateTime.now()}")
}