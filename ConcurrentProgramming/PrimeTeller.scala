//START:ISPRIME
import scala.actors._
import Actor._

def isPrime(number: Int) = { 
  println("Going to find if " + number + " is prime")

  var result = true

  if (number == 2 || number == 3) result = true
              
  for (i <- 2 to Math.sqrt(number.toDouble).floor.toInt; if result) {               
    if (number % i == 0) result = false
  }
                              
  println("done finding if " + number + " is prime")                              
  result
}
//END:ISPRIME

//START:ACTOR
val primeTeller = actor {
  var continue = true
  
  while (continue) {
    receive {
      case (caller : Actor, number: Int) => caller ! (number, isPrime(number)) //<label id="code.primeteller.process" />
      case "quit" => continue = false
    }
  }
}
//END:ACTOR

//START:USE
primeTeller ! (self, 2)
primeTeller ! (self, 131)
primeTeller ! (self, 132)

for (i <- 1 to 3) {
  receive {
    case (number, result) => println(number + " is prime? " + result)
  }
}

primeTeller ! "quit"
//END:USE
