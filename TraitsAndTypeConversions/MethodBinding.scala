//START:WRITER
abstract class Writer  {
  def writeMessage(message: String)
}
//END:WRITER
     
//START:TRAITS
trait UpperCaseWriter extends Writer {
  abstract override def writeMessage(message: String) = 
    super.writeMessage(message.toUpperCase)
}

trait ProfanityFilteredWriter extends Writer {
  abstract override def writeMessage(message: String) = 
    super.writeMessage(message.replace("stupid", "s-----"))
}                 
//END:TRAITS
    
//START:CLASS
class StringWriterDelegate extends Writer {
  val writer = new java.io.StringWriter
  
  def writeMessage(message: String) = writer.write(message)
  override def toString() : String = writer.toString
}
//END:CLASS

//START:USE
val myWriterProfanityFirst = 
  new StringWriterDelegate with UpperCaseWriter with ProfanityFilteredWriter

val myWriterProfanityLast = 
  new StringWriterDelegate with ProfanityFilteredWriter with UpperCaseWriter

myWriterProfanityFirst writeMessage "There is no sin except stupidity"
myWriterProfanityLast writeMessage "There is no sin except stupidity"

println(myWriterProfanityFirst)
println(myWriterProfanityLast)
//END:USE
