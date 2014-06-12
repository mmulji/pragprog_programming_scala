import scala.actors.Actor._

def sumOfFactorsInRange(lower: Int, upper: Int, number: Int) = {
  (0 /: (lower to upper)) { (sum, i) => if (number % i == 0) sum + i else sum }
}

def isPerfectConcurrent(candidate: Int) = {
  val RANGE = 1000000
  val numberOfPartitions = (candidate.toDouble / RANGE).ceil.toInt
  val caller = self
  
  for (i <- 0 until numberOfPartitions) { 
    val lower = i * RANGE + 1;
    val upper = candidate min (i + 1) * RANGE
    
    actor { //<label id="code.fasterperfectnumberfinder.actor" />
      caller ! sumOfFactorsInRange(lower, upper, candidate) //<label id="code.fasterperfectnumberfinder.send"/>
    }
  }
     
  val sum = (0 /: (0 until numberOfPartitions)) { (partialSum, i) =>
    receive { //<label id="code.fasterperfectnumberfinder.receive" />
      case sumInRange : Int => partialSum + sumInRange
    }
  }
             
  2 * candidate == sum
}

println("6 is perfect? " + isPerfectConcurrent(6))
println("33550336 is perfect? " + isPerfectConcurrent(33550336))
println("33550337 is perfect? " + isPerfectConcurrent(33550337))
