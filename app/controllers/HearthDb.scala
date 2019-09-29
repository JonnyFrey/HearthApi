package controllers

import com.google.inject.Inject
import javax.inject.Singleton
import model.Card
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HearthDb @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit context: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class CardTable(tag: Tag) extends Table[Card](tag, "cards") {
    def id = column[String]("id")
    def name = column[String]("name")
    def typeCol = column[String]("type")
    def multiClassGroup = column[Option[String]]("multiClassGroup")
    def cardClass = column[Option[String]]("cardClass")
    def cost = column[Option[Int]]("cost")
    def overload = column[Option[Int]]("overload")
    def attack = column[Option[Int]]("attack")
    def health = column[Option[Int]]("health")
    def armor = column[Option[Int]]("armor")
    def durability = column[Option[Int]]("durability")
    def spellDamage = column[Option[Int]]("spellDamage")
    def questReward = column[Option[String]]("questReward")
    def rarity = column[Option[String]]("rarity")
    def set = column[String]("set")
    def text = column[Option[String]]("text")
    def flavor = column[Option[String]]("flavor")
    def hideStats = column[Option[Boolean]]("hideStats")
    def * = (id, name, typeCol, multiClassGroup, cardClass, cost, overload, attack, health, armor, durability, spellDamage, questReward, rarity, set, text, flavor, hideStats) <> (Card.tupled, Card.unapply)
  }

  private val cards = TableQuery[CardTable]

  def findByName(name: String): Future[Seq[Card]] = {
    val action = cards.filter(_.name === name).result
    db.run(action)
  }

}
