@main
def main(): Unit = {

    // for each day's puzzle, create an instance of class and supply an
    // input file type, called runType:
    // runType = 1 for test data
    //         = 2 for real data

    // Which day's puzzle to run?
    val day = 6
    val runType = 1

    // Implement each day's puzzle
    val d6 = Day06("AoC 2015 Day 6", runType)
    val d5 = Day05("AoC 2015 Day 5", runType)
    val d4 = Day04("AoC 2015 Day 4", runType)
    val d3 = Day03("AoC 2015 Day 3", runType)
    val d2 = Day02("AoC 2015 Day 2", runType)
    val d1 = Day01("AoC 2015 Day 1", runType)

    // Run the selected day's puzzle
    day match {
        case 1 => d1.run
        case 2 => d2.run
        case 3 => d3.run
        case 4 => d4.run
        case 5 => d5.run
        case 6 => d6.run
        case _ => println("Error day does not match with a runner")
    }

}