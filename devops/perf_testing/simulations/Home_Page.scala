import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Home_Page extends Simulation {
  val scn = scenario("Home Page")
    .during(1 minute){
      exec(http("Home Page")
        .get("http://localhost:8080/"))
        .pause(5,10)
    }
  setUp(scn.inject(rampUsers(10) during(1 minutes)))
}