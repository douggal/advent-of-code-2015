import java.io.File

class Day01(val runType: Int):

    def title: String = Day01.title
    def run: Unit = Day01.runPuzzle(runType)

    override def toString: String =
        s"$title"

end Day01

object Day01 {

    private val title = "AoC 2015 Day 1"
    private val testData: String = "day01TestData.txt"
    private val realData: String = "day01Input.txt"

    private def runPuzzle(runType:Int): Unit =

        // created 9/8/2023
        // https://adventofcode.com/2015/day/1

        println(s"--- Day 1: Not Quite Lisp ---")
        if (runType == 1)
            println("--- USING TEST DATA")
        else
            println("--- USING REAL INPUT DATA")

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
        println(s"$title Part 1 TBD")

        // parse dots


        // parse instructions


        // Part Two

        println(s"$title Part 2  [TBD]")

        println(s"End at ${java.time.ZonedDateTime.now()}")


}