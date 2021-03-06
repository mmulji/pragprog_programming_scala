//START:CASECLASSES
sealed abstract case class Trade()
case class Sell(stockSymbol: String, quantity: Int) extends Trade
case class Buy(stockSymbol: String, quantity: Int) extends Trade
case class Hedge(stockSymbol: String, quantity: Int) extends Trade
//END:CASECLASSES
                   
//START:PROCESSOR
class TradeProcessor {
  def processTransaction(request : Trade) {
    request match {
      case Sell(stock, 1000) => println("Selling 1000-stocks of " + stock)
      case Sell(stock, quantity) => printf("Selling %d stocks of %s\n", quantity, stock)
      case Buy(stock, quantity) if (quantity > 2000) => 
        printf("Buying %d (large) stocks of %s\n", quantity, stock)
      case Buy(stock, quantity) => printf("Buying %d stocks of %s\n", quantity, stock)
    }
  }
}
//END:PROCESSOR
