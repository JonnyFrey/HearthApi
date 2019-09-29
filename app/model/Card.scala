package model

case class Card (
                  id: String,
                  name: String,
                  typeCol: String,
                  multiClassGroup: Option[String],
                  cardClass: Option[String],
                  cost: Option[Int],
                  overload: Option[Int],
                  attack: Option[Int],
                  health: Option[Int],
                  armor: Option[Int],
                  durability: Option[Int],
                  spellDamage: Option[Int],
                  questReward: Option[String],
                  rarity: Option[String],
                  set: String,
                  text: Option[String],
                  flavor: Option[String],
                  hideStats: Option[Boolean]
                )