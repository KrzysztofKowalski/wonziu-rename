import java.io._

import org.jsoup._
import org.jsoup.select._

import scala.collection.JavaConversions._

object Twitch {

  def main = {
    val input = new File("twitch.html")
    val enc = "utf-8"
    val document = Jsoup.parse(input, enc)

    val elements: Elements = document.select(".item")

    elements.listIterator().map {
      e =>
        println(e.toString)
    }

  }

}