package controllers

import io.swagger.annotations.Api
import javax.inject._
import model.Card
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Api
@Singleton
class CardController @Inject()(db: HearthDb, cc: MessagesControllerComponents)(implicit context: ExecutionContext) extends MessagesAbstractController(cc) {

  implicit val cardWrite = Json.format[Card]

  def cards(name: String) = Action.async { implicit request: Request[AnyContent] =>
    db.findByName(name).map { card =>
      val json = Json.toJson(card)
      Ok(json).as(JSON)
    }
  }
}
