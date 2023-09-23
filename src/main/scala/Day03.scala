import java.io.File
import java.time.{Duration, Instant, ZonedDateTime}
import scala.collection.mutable.ArrayBuffer

/** Advent of Code 2015 Day 3
 *
 * Defines a class, its companion object and runner method for
 * the AoC Day 3 puzzles.
 */
class Day03:

    var title: String = "Default"
    var runType: Int = 1 // Default to test data

    def run = Day03.runPuzzle(title, runType)

    override def toString: String = s"Class ${Day03.puzzleTitle}"

end Day03

object Day03 {

    // created 9/23/2023
    // https://adventofcode.com/2015/day/3

    val puzzleTitle = "Day 3: Perfectly Spherical Houses in a Vacuum"
    val day = "Day03"
    private val testData: String = s"${day}TestData.txt"
    private val realData: String = s"${day}Input.txt"

    // a one-arg constructor
    def apply(title: String): Day03 = {
        var p = new Day03()
        p.title = title
        p
    }

    // a two-arg constructor
    def apply(title: String, runType: Int): Day03 = {
        var p = new Day03()
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
        println(s"Part 1: How many houses receive at least one present?")
        val p1T0 = Instant.now()

        // represent each node as a 2-tuple (x, y)
        // add each node to a Map with key (x, y) and value runnint count of deliveries

        val origin = Tuple2[Int,Int](0, 0)
        val deliveries = scala.collection.mutable.Map[(Int, Int),Int](origin -> 1)

        var c = origin
        for (m <- input(0)) do
            m match
                case '<' => c = (c._1 + -1, c._2)
                case '^' => c = (c._1, c._2 + 1)
                case '>' => c = (c._1+1, c._2)
                case 'v' => c = (c._1, c._2 + -1)
            if (deliveries.contains(c))
                deliveries(c) += 1
            else
                deliveries += (c -> 1)

        val moreThanOne = deliveries.values.toVector
        val answerP1 = moreThanOne.count(_ > 1)
        println(s"Answer $answerP1")

        // 1762 too low

        val delta1 = Duration.between(p1T0, Instant.now())
        println(s"Part 1 elased time approx ${delta1.toMillis} milliseconds\n")


        // Part Two
        println(s"Part 2: ")
        val p2T0 = Instant.now()

        val delta2 = Duration.between(p2T0, Instant.now())
        println(f"Part 2 elapsed time approx ${delta2.toMillis} milliseconds")

        println(s"\nEnd at ${ZonedDateTime.now()}")
    }

}