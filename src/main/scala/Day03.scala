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
    val day = "03"
    private val testData: String = s"${day}-test.txt"
    private val realData: String = s"${day}-input.txt"

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
            val f = java.nio.file.Paths.get("src/main/resources").toAbsolutePath().toString
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

        // Commmon to both parts
        val origin = Tuple2[Int,Int](0, 0)

        def newLocation(c: Char, o: Tuple2[Int, Int]): Tuple2[Int, Int] = {
            c match
                case '<' => (o._1 + -1, o._2)
                case '^' => (o._1, o._2 + 1)
                case '>' => (o._1 + 1, o._2)
                case 'v' => (o._1, o._2 + -1)
        }

        // Part One
        println(s"Part 1: How many houses receive at least one present?")
        val p1T0 = Instant.now()

        // represent each house visited as a 2-tuple (x, y)
        // add each node to a Map with key (x, y) and its value is the running count of deliveries
        val houses = scala.collection.mutable.Map[(Int, Int),Int](origin -> 1)

        // List of (x,y) coord (the "address") of each house
        //  - save whole list for visualizations
        var addresses = List(origin)
        for (c <- input(0)) do
            addresses = newLocation(c, addresses.head) :: addresses

            if (houses.contains(addresses.head))
                houses(addresses.head) += 1
            else
                houses += (addresses.head -> 1)

        val answerP1 = houses.values.toVector.count(_ => true)  // count not sum, read carefully :)
        println(s"Answer $answerP1")

        val delta1 = Duration.between(p1T0, Instant.now())
        println(s"Part 1 elased time approx ${delta1.toMillis} milliseconds\n")


        // Part Two
        println(s"Part 2: with Santa and Robo-Santa delivering how many houses receive at least one present?")
        val p2T0 = Instant.now()

        // Addresses of houses visited
        var santaList = List(origin)  // Santa
        var roboList = List(origin)  // Robo-Santa

        // Houses visited: represent each house visited as a 2-tuple (x, y)
        // add each node to a Map with key (x, y) and its value is the running count of deliveries
        val santaHouses = scala.collection.mutable.Map[(Int, Int),Int](origin -> 1)  // Santa
        val roboHouses = scala.collection.mutable.Map[(Int, Int),Int](origin -> 1) // Robo-Santa

        // List of indexes of each stop along the way in the input string
        // Even integer index = Santa, Odd integer index = Robo-Santa
        val is = (0 until input(0).length).toList
        for (i <- is) do
            // next move
            val c = input(0)(i)

            if i % 2 == 0 then
                // Santa
                santaList = newLocation(c, santaList.head) :: santaList
                if (santaHouses.contains(santaList.head))
                    santaHouses(santaList.head) += 1
                else
                    santaHouses += (santaList.head -> 1)
            else
                // Robo-Santa
                roboList = newLocation(c, roboList.head) :: roboList
                if (roboHouses.contains(roboList.head))
                    roboHouses(roboList.head) += 1
                else
                    roboHouses += (roboList.head -> 1)


        val answerP2 = santaHouses.size + roboHouses.filter(x => !santaHouses.contains(x._1)).size
        // 2783 - too high!! must have double counted houses go back and fix

        println(s"Answer $answerP2")


        val delta2 = Duration.between(p2T0, Instant.now())
        println(f"Part 2 elapsed time approx ${delta2.toMillis} milliseconds")

        println(s"\nEnd at ${ZonedDateTime.now()}")

        // errata...for visualation with Excel chart
        println("\nPart 1 Santa:")
        //for (k, v) <- addresses do println(s"$k,$v, ${houses((k,v))}")
        println("\nPart 2: Santa")
        //for (k, v) <- santaList do println(s"$k,$v")
        println("\nPart 2: Robo-Santa")
        //for (k, v) <- roboList do println(s"$k,$v")


    }
}
