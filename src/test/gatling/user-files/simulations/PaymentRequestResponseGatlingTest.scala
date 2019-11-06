import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the PaymentRequestResponse entity.
 */
class PaymentRequestResponseGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:8080"""

    val httpConf = http
        .baseUrl(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
        .silentResources // Silence all resources like css or css so they don't clutter the results

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "X-XSRF-TOKEN" -> "${xsrf_token}"
    )

    val keycloakHeaders = Map(
        "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
        "Upgrade-Insecure-Requests" -> "1"
    )

    val scn = scenario("Test the PaymentRequestResponse entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))
        .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.*);[\\s]").saveAs("xsrf_token"))
        ).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authentication")
        .headers(headers_http_authenticated)
        .formParam("username", "admin")
        .formParam("password", "admin")
        .formParam("remember-me", "true")
        .formParam("submit", "Login")
        .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.*);[\\s]").saveAs("xsrf_token"))).exitHereIfFailed
        .pause(2)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all paymentRequestResponses")
            .get("/api/payment-request-responses")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new paymentRequestResponse")
            .post("/api/payment-request-responses")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "requestMerchantID":"SAMPLE_TEXT"
                , "requestUniqueTransactionNo":"SAMPLE_TEXT"
                , "requestTxnAmount":"SAMPLE_TEXT"
                , "requestCurrencyType":"SAMPLE_TEXT"
                , "requestTypeField1":"SAMPLE_TEXT"
                , "requestSecurityID":"SAMPLE_TEXT"
                , "requestTypeField2":"SAMPLE_TEXT"
                , "requestTxtadditional1":"SAMPLE_TEXT"
                , "requestTxtadditional2":"SAMPLE_TEXT"
                , "requestTxtadditional3":"SAMPLE_TEXT"
                , "requestTxtadditional4":"SAMPLE_TEXT"
                , "requestTxtadditional5":"SAMPLE_TEXT"
                , "requestTxtadditional6":"SAMPLE_TEXT"
                , "requestTxtadditional7":"SAMPLE_TEXT"
                , "requestRu":"SAMPLE_TEXT"
                , "requestCheckSum":"SAMPLE_TEXT"
                , "requestMsg":"SAMPLE_TEXT"
                , "responseMerchantID":"SAMPLE_TEXT"
                , "responseUniqueTransactionNo":"SAMPLE_TEXT"
                , "responseTxnReferenceNo":"SAMPLE_TEXT"
                , "responseBankReferenceNo":"SAMPLE_TEXT"
                , "responseTxnAmount":"SAMPLE_TEXT"
                , "responseBankID":"SAMPLE_TEXT"
                , "responseBankMerchantID":"SAMPLE_TEXT"
                , "responseTxnType":"SAMPLE_TEXT"
                , "responseCurrencyName":"SAMPLE_TEXT"
                , "responseItemCode":"SAMPLE_TEXT"
                , "responseSecurityType":"SAMPLE_TEXT"
                , "responseSecurityID":"SAMPLE_TEXT"
                , "responseSecurityPassword":"SAMPLE_TEXT"
                , "responseTxnDate":"SAMPLE_TEXT"
                , "responseAuthStatus":"SAMPLE_TEXT"
                , "responseSettlementType":"SAMPLE_TEXT"
                , "responseAdditionalInfo1":"SAMPLE_TEXT"
                , "responseAdditionalInfo2":"SAMPLE_TEXT"
                , "responseAdditionalInfo3":"SAMPLE_TEXT"
                , "responseAdditionalInfo4":"SAMPLE_TEXT"
                , "responseAdditionalInfo5":"SAMPLE_TEXT"
                , "responseAdditionalInfo6":"SAMPLE_TEXT"
                , "responseAdditionalInfo7":"SAMPLE_TEXT"
                , "responseErrorStatus":"SAMPLE_TEXT"
                , "responseErrorDescription":"SAMPLE_TEXT"
                , "responseCheckSum":"SAMPLE_TEXT"
                , "responseMsg":"SAMPLE_TEXT"
                , "user":"SAMPLE_TEXT"
                , "requestTxnDate":"2020-01-01T00:00:00.000Z"
                , "requestTxnTime":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_paymentRequestResponse_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created paymentRequestResponse")
                .get("${new_paymentRequestResponse_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created paymentRequestResponse")
            .delete("${new_paymentRequestResponse_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
