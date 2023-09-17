import java.io.File
import java.time.{Instant, Duration, ZonedDateTime}
import scala.collection.mutable.ArrayBuffer

/** Advent of Code 2015 Day 1
 *
 * Defines a class, its companion object and runner method for
 * the AoC Day 1 puzzles.
 */
class Day01:

    var title: String = "Default"
    var runType: Int = 1 // Default to test data

    def run = Day01.runPuzzle(title, runType)

    override def toString: String = s"Class ${Day01.puzzleTitle}"

end Day01

object Day01 {

    // created 9/8/2023
    // https://adventofcode.com/2015/day/1

    val puzzleTitle = "Day 1: Not Quite Lisp"
    val day = "day01"
    private val testData: String = s"${day}TestData.txt"
    private val realData: String = s"${day}Input.txt"

    // a one-arg constructor
    def apply(title: String): Day01 = {
        var p = new Day01()
        p.title = title
        p
    }

    // a two-arg constructor
    def apply(title: String, runType: Int): Day01 = {
        var p = new Day01()
        p.title = title
        p.runType = runType
        p
    }

    private def runPuzzle(title: String, runType: Int): Unit = {

        println(s"--- $title ---")
        println(s"--- $puzzleTitle ---")
        if (runType == 1)
            println("--- USING TEST DATA ---\n")
        else
            println("--- USING REAL INPUT DATA ---\n")

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

        println(s"Start Timestamp ${ZonedDateTime.now()}")

        println("\nData Quality Control:")
        println(s"Input file name: $filename")
        println(s"Each line is a: ${input.getClass}")
        println(s"Number lines: ${input.length}")
        println(s"Number items per line: ${input.head.count(_ => true)}")
        println(s"Input first line: ${input.head.take(25)}")
        if (input.size > 1) println(s"Input last line: ${input.tail.last}")
        println("End QC on input file\n")

        // Part One
        println(s"Part 1: To what floor do the instructions take Santa?")
        val p1T0 = Instant.now()

        val inputSeq = input(0).toVector
        val floor = inputSeq.map(x => if (x == '(') then 1 else -1).sum
        println(s"Santa arrives at floor $floor")

        val delta1 = Duration.between(p1T0, Instant.now())
        println(s"Elapsed time approx ${delta1.toMillis} milliseconds\n")


        // Part Two
        println(s"Part 2: What is the position of the character that causes Santa to first enter the basement?")
        val p2T0 = Instant.now()
        val floorInstructions = inputSeq.map(x => if (x == '(') then 1 else -1)

        var floors = ArrayBuffer[Int](0)
        for (i <- 0 to floorInstructions.length-1) {
            floors.append(floors(i) + floorInstructions(i))
        }
        val answerP2 = floors.takeWhile(_ != -1).length
        println(answerP2)

        val delta2 = Duration.between(p2T0, Instant.now())
        println(f"Elapsed time approx ${delta1.toMillis} milliseconds")

        println(s"\nEnd at ${ZonedDateTime.now()}")
    }

}