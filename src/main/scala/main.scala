@main
def main(): Unit = {

  // for each day's puzzle, create an instance of class and supply an
  // input file type, called runType:  
  // runType = 1 for test data
  //         = 2 for real data
  
  val p = Day01(1)
  // val p = Day02(1)
  // etc...
  
  // run the day's puzzle
  p.run
  
}