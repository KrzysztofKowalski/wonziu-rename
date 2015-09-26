import java.io._

import org.jsoup._
import org.jsoup.select._

import scala.collection.JavaConversions._
import scala.util.Try

object Twitch {
  val enc = "utf-8"

  def main(args: Array[String]) {
    renameTwitch
    renameHitbox
  }

  def renameHitbox: Unit = {
    val input = new File("hitbox/add.html")
    val document = Jsoup.parse(input, enc)

    val elements: Elements = document.select("ul li")
    elements.map { e =>
      val desc = e.select(".t-meta > a").text().replaceAll("vonzay", "")
        .replaceAll("www.jadisco.pl - tu siedzimy", "")
      val id = e.select(".t-wrap").attr("href").split("/").reverse(0)
      val newName = s"${id} - ${desc}.mp4".replaceAll("-  -", "-")
      mv(s"hitbox/${id}.mp4", s"hitbox/${newName}")
    }
  }

  def renameTwitch: Unit = {
    val input = new File("twitch/twitch.html")
    val document = Jsoup.parse(input, enc)

    val elements: Elements = document.select(".video")
    elements.map { e =>
      val id = e.select(".cap").attr("href").split("/").reverse(0)
      var title = e.select(".title").text()
      var desc = e.select(".meta .desc").text()
      val date = e.select(".meta p.info").text().replaceAll("Wonziu ", "")
        .replaceAll(desc, "")
      val newName = s"${id} - ${date} - ${title} - ${desc}.mp4"
      var command = s"""mv "${id}.mp4" "$newName""""
      mv(s"twitch/${id}.mp4", s"twitch/${newName}")
    }
  }

  def mv(oldName: String, newName: String) = {
    println(s"$oldName -> $newName")
    Try(new File(oldName).renameTo(new File(newName))).getOrElse(false)
  }

}