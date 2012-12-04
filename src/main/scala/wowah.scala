import dispatch._
import dispatch.json.JsHttp._
import dispatch.Http
import Http._
import java.io.FileWriter
import dispatch.json._

case class Auc(item: BigDecimal, owner: String, buyout: BigDecimal, quantity: BigDecimal){
 override def toString(): String = "Item ID: "+ item + " , " +  "Auction Maker: "+ owner + " , " + "Price: "+ buyout + " , " + "Quantity: "+ quantity + " , " +"\n"
}
class Resourcer extends fileWriter with auctionFilter {
  def getJson: List[JsValue] = {
    val page = url("http://us.battle.net/auction-data/315bfe7b80450746e2fdc4207a2fd8ed/auctions.json")
    val horde = Http(page ># ('horde ! obj))
    ('auctions ! list)(horde)
  }
  def getAuctions: List[Auc] = {
    val json = getJson
    json.map {
      auction=>
      val item = ('item ! num)(auction)
      val owner = ('owner ! str)(auction)
      val buyout = ('buyout ! num)(auction)
      val quantity = ('quantity ! num)(auction)
      Auc(item,owner,buyout,quantity)
    }
  }
}
object web extends fileWriter with auctionFilter{

  def main(args: Array[String]): Unit = {
    val resrc = new Resourcer
    val aucs: List[Auc] =  resrc.getAuctions
    val filteredAucs = resrc.filterById(74841, aucs)
    val aucString = filteredAucs.toString()
    val writing  = resrc.writeOut("auctions", aucString)

  }
}
trait auctionFilter{
  def filterById(itemId: BigDecimal, auctions: List[Auc])={
    auctions.filter(auction => auction.item == itemId)
  }
}
trait fileWriter{

  def writeOut(fname: String,text: String) {
    val out = new java.io.FileWriter(fname + ".txt", true)
    out.write(text)
    out.close
  }
}
