@main
def main(): Unit = {

    // for each day's puzzle, create an instance of class and supply an
    // input file type, called runType:
    // runType = 1 for test data
    //         = 2 for real data

    // Which day's puzzle to run?
    val day = 2
    val runType = 2

    // Implement each day's puzzle
    val d2 = Day02("AoC 2015 Day 2", runType)
    val d1 = Day01("AoC 2015 Day 1", runType)

    // Run the selected day's puzzle
    day match {
        case 1 => d1.run
        case 2 => d2.run
        case _ => println("Error day does not match with a runner")
    }

}