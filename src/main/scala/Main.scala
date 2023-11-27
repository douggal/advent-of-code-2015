import java.time.ZonedDateTime

@main
def Main(): Unit = {

    // for each day's puzzle, create an instance of class and supply an
    // input file type, called runType:
    // runType = 1 for test data
    //         = 2 for real data

    val title = "Advent of Code 2015"
    val runType = 2 // Default to type =1, the test data

    // Which day's puzzle to run?
    val day = 7

    // Implement each day's puzzle
    val d7 = Day07(s"$title Day 7", runType)
    val d6 = Day06(s"$title Day 6", runType)
    val d5 = Day05(s"$title Day 5", runType)
    val d4 = Day04(s"$title Day 4", runType)
    val d3 = Day03(s"$title Day 3", runType)
    val d2 = Day02(s"$title Day 2", runType)
    val d1 = Day01(s"$title Day 1", runType)

    // Run the selected day's puzzle
    println(s"Begin: ${ZonedDateTime.now()}")

    day match {
        case 1 => d1.run()
        case 2 => d2.run()
        case 3 => d3.run()
        case 4 => d4.run()
        case 5 => d5.run()
        case 6 => d6.run()
        case 7 => d7.run()
        case _ => println("Error day does not match with a class")
    }

    println(s"\nEnd: ${ZonedDateTime.now()}")
}