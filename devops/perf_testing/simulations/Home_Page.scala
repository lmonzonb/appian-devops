import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Home_Page extends Simulation {
	val httpProtocol = http
		.baseUrl("http://appian-1.appiancorp.com:8080")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "http://appian-1.appiancorp.com:8080",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_3 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_5 = Map(
		"Accept" -> "application/font-woff2;q=1.0,application/font-woff;q=0.9,*/*;q=0.8",
		"Accept-Encoding" -> "identity")

	val headers_11 = Map(
		"Accept" -> "application/vnd.appian.tv.ui+json",
		"Accept-Language" -> "en_US",
		"Content-Type" -> "application/vnd.appian.tv+json",
		"X-APPIAN-CSRF-TOKEN" -> "6bd5791a-66e9-44f8-900d-ef01bedc08e8",
		"X-Appian-Features" -> "7ffceebc",
		"X-Appian-Features-Extended" -> "37f4ddc1fffceebc",
		"X-Appian-Ui-State" -> "stateful",
		"X-Client-Mode" -> "SITES",
		"X-Page-UrlStub" -> "news",
		"X-Site-UrlStub" -> "D6JMim",
		"x-appian-suppress-www-authenticate" -> "true")

	val headers_14 = Map("Accept" -> "image/webp,*/*")

	val headers_17 = Map(
		"Content-Type" -> "text/x-gwt-rpc; charset=utf-8",
		"Origin" -> "http://appian-1.appiancorp.com:8080",
		"X-APPIAN-CSRF-TOKEN" -> "6bd5791a-66e9-44f8-900d-ef01bedc08e8",
		"X-GWT-Module-Base" -> "http://appian-1.appiancorp.com:8080/suite/tempo/",
		"X-GWT-Permutation" -> "AFCF00B1F2EBB90AA6A36547B02243C9")

	val headers_18 = Map(
		"Accept-Language" -> "en_US",
		"X-APPIAN-CSRF-TOKEN" -> "6bd5791a-66e9-44f8-900d-ef01bedc08e8",
		"X-Appian-Features-Extended" -> "e4bc",
		"X-Appian-Ui-State" -> "stateful",
		"X-Atom-Content-Type" -> "application/vnd.appian.tv+json",
		"x-appian-suppress-www-authenticate" -> "true")

	val headers_20 = Map(
		"Accept" -> "application/json",
		"Accept-Language" -> "en_US",
		"X-APPIAN-CSRF-TOKEN" -> "6bd5791a-66e9-44f8-900d-ef01bedc08e8",
		"X-Appian-Features-Extended" -> "e4bc",
		"X-Appian-Ui-State" -> "stateful",
		"x-appian-suppress-www-authenticate" -> "true")

	val headers_22 = Map(
		"Accept" -> "application/atom+json,application/json",
		"Accept-Language" -> "en_US",
		"X-APPIAN-CSRF-TOKEN" -> "6bd5791a-66e9-44f8-900d-ef01bedc08e8",
		"X-Appian-Features-Extended" -> "e4bc",
		"X-Appian-Ui-State" -> "stateful",
		"X-Atom-Content-Type" -> "application/html",
		"x-appian-suppress-www-authenticate" -> "true")

	val headers_30 = Map(
		"Accept" -> "application/vnd.appian.tv.ui+json",
		"Accept-Language" -> "en_US",
		"Content-Type" -> "application/vnd.appian.tv+json",
		"X-APPIAN-CSRF-TOKEN" -> "6bd5791a-66e9-44f8-900d-ef01bedc08e8",
		"X-Appian-Features" -> "7ffceebc",
		"X-Appian-Features-Extended" -> "37f49dc1fffceebc",
		"X-Appian-Ui-State" -> "stateful",
		"X-Client-Mode" -> "SITES",
		"X-Site-UrlStub" -> "santandermexico",
		"x-appian-suppress-www-authenticate" -> "true")

	val headers_37 = Map(
		"Accept" -> "application/vnd.appian.tv.ui+json",
		"Accept-Language" -> "en_US",
		"Content-Type" -> "application/vnd.appian.tv+json",
		"Origin" -> "http://appian-1.appiancorp.com:8080",
		"X-APPIAN-CSRF-TOKEN" -> "6bd5791a-66e9-44f8-900d-ef01bedc08e8",
		"X-Appian-Cached-Datatypes" -> "192,2464,1504,2560,1568,672,832,992,2016,97,193,2465,1505,2561,1569,673,833,993,2017,194,1346,1442,2466,1506,2562,930,1986,994,2018,195,1347,1443,2467,1507,2563,931,1987,995,2019,1348,2468,1508,1988,101,197,1349,2469,1509,1989,2182,2470,1510,774,806,902,103,2183,2471,1511,775,807,903,2184,2472,1512,1608,1768,1896,904,2185,2473,1513,1609,1769,1897,905,1578,1610,1770,1898,906,938,1579,1611,1771,1899,907,939,1580,1612,2636,780,1900,908,940,13,1581,1613,2637,781,1901,909,941,1582,2638,686,910,942,1583,2639,687,911,943,1584,1648,656,720,912,944,113,1585,1649,657,721,913,945,658,690,722,850,1874,914,946,659,691,723,851,1875,915,947,52,2356,660,852,1876,2357,661,853,1877,2358,1430,662,758,854,2359,1431,663,759,855,152,2360,1432,856,2361,1433,857,1562,666,1563,667,2460,1564,668,2461,1565,669,94,126,2462,670,830,990,95,2463,671,831,991",
		"X-Appian-Features" -> "7ffceebc",
		"X-Appian-Features-Extended" -> "37f49dc1fffceebc",
		"X-Appian-Ui-State" -> "stateful",
		"X-Client-Mode" -> "SITES",
		"X-Site-UrlStub" -> "santandermexico",
		"x-appian-suppress-www-authenticate" -> "true")

	val headers_38 = Map(
		"Accept" -> "application/vnd.appian.tv.ui+json",
		"Accept-Language" -> "en_US",
		"Content-Type" -> "application/vnd.appian.tv+json",
		"X-APPIAN-CSRF-TOKEN" -> "6bd5791a-66e9-44f8-900d-ef01bedc08e8",
		"X-Appian-Cached-Datatypes" -> "192,2464,1504,2560,1568,672,832,992,2016,97,193,2465,1505,2561,1569,673,833,993,2017,194,1346,1442,2466,1506,2562,930,1986,994,2018,195,1347,1443,2467,1507,2563,931,1987,995,2019,1348,2468,1508,1988,101,197,1349,2469,1509,1989,2182,2470,1510,774,806,902,103,2183,2471,1511,775,807,903,2184,2472,1512,1608,1768,1896,904,2185,2473,1513,1609,1769,1897,905,1578,1610,1770,1898,906,938,1579,1611,1771,1899,907,939,1580,1612,2636,780,1900,908,940,13,1581,1613,2637,781,1901,909,941,1582,2638,686,910,942,1583,2639,687,911,943,1584,1648,656,720,912,944,113,1585,1649,657,721,913,945,658,690,722,850,1874,914,946,659,691,723,851,1875,915,947,52,2356,660,852,1876,2357,661,853,1877,2358,1430,662,758,854,2359,1431,663,759,855,152,2360,1432,856,2361,1433,857,1562,666,1563,667,2460,1564,668,2461,1565,669,94,126,2462,670,830,990,95,2463,671,831,991",
		"X-Appian-Features" -> "7ffceebc",
		"X-Appian-Features-Extended" -> "37f49dc1fffceebc",
		"X-Appian-Ui-State" -> "stateful",
		"X-Client-Mode" -> "SITES",
		"X-Page-UrlStub" -> "gobierno-e-instituciones",
		"X-Site-UrlStub" -> "santandermexico",
		"x-appian-suppress-www-authenticate" -> "true")



	val scn = scenario("AppianSimulation")
		.exec(http("request_0")
			.get("/suite/")
			.headers(headers_0))
		.pause(11)
		.exec(http("request_1")
			.post("/suite/auth?appian_environment=tempo")
			.headers(headers_1)
			.formParam("un", "admin.user")
			.formParam("pw", "AppianSpain1$")
			.formParam("_spring_security_remember_me", "on")
			.formParam("X-APPIAN-CSRF-TOKEN", "22e4a1f5-60c7-491a-a9d7-9e01ce813fe2")
			.resources(http("request_2")
			.get("/suite/tempo/tempo.nocache.js?appian_environment=tempo"),
            http("request_3")
			.get("/suite/tempo/ui/sail-client/tempo-14cc662e015d0c7968f1.cache.css?appian_environment=tempo")
			.headers(headers_3),
            http("request_4")
			.get("/suite/tempo/ui/sail-client/embeddedBootstrap.nocache.js?appian_environment=tempo"),
            http("request_5")
			.get("/suite/tempo/ui/sail-client/opensans-regular-appian-81d0487ba73afd292730e6f89e83c2ea.woff2")
			.headers(headers_5),
            http("request_6")
			.get("/suite/tempo/ui/sail-client/tempo-14cc662e015d0c7968f1.cache.js?appian_environment=tempo")))
		.pause(1)
		.exec(http("request_7")
			.get("/suite/tempo/ui/fontawesome/css/font-awesome.min.css")
			.headers(headers_3)
			.resources(http("request_8")
			.get("/suite/tempo/ui/jquery-bundle.js"),
            http("request_9")
			.get("/suite/tempo/AFCF00B1F2EBB90AA6A36547B02243C9.cache.js"),
            http("request_10")
			.get("/suite/tempo/ui/sail-client/embeddedAuthUtility-14cc662e015d0c7968f1.cache.js"),
            http("request_11")
			.get("/suite/rest/a/applications/latest/legacy/sites/D6JMim/page/news")
			.headers(headers_11),
            http("request_12")
			.get("/suite/rest/a/sites/latest/D6JMim/pages/news/interface")
			.headers(headers_11),
            http("request_13")
			.get("/suite/tempo/ui/sail-client/opensans-semibold-appian-0dd0a359a053b2b5bb856a9580da9780.woff2")
			.headers(headers_5),
            http("request_14")
			.get("/suite/personalization/img/nophoto.jpg")
			.headers(headers_14),
            http("request_15")
			.get("/suite/cors/ping?cv=2"),
            http("request_16")
			.get("/suite/tempo/ui/sail-client/embeddedApp-14cc662e015d0c7968f1.cache.js"),
            http("request_17")
			.post("/suite/tempo/tempoSvc")
			.headers(headers_17)
			.body(RawFileBody("test/appiansimulation/0017_request.dat")),
            http("request_18")
			.get("/suite/rest/a/custombranding/latest/branding")
			.headers(headers_18),
            http("request_19")
			.get("/suite/rest/a/content/latest/branding/logo/1587104578000")
			.headers(headers_14),
            http("request_20")
			.get("/suite/api/groups/broadcast-targets")
			.headers(headers_20),
            http("request_21")
			.get("/suite/tempo/fonts/OpenSans-Regular-webfont.woff2")
			.headers(headers_5),
            http("request_22")
			.get("/suite/api/feed/tempo?t=e,x,b&m=menu-news&st=o")
			.headers(headers_22),
            http("request_23")
			.get("/suite/tempo/ui/fontawesome/fonts/fontawesome-webfont.woff2?v=4.7.0")
			.headers(headers_5),
            http("request_24")
			.get("/suite/tempo/fonts/OpenSans-Bold-webfont.woff2")
			.headers(headers_5)))
		.pause(4)
		.exec(http("request_25")
			.get("/suite/sites/santandermexico")
			.headers(headers_0)
			.resources(http("request_26")
			.get("/suite/tempo/ui/sail-client/sites-14cc662e015d0c7968f1.cache.css?appian_environment=sites")
			.headers(headers_3),
            http("request_27")
			.get("/suite/tempo/ui/sail-client/embeddedBootstrap.nocache.js?appian_environment=sites"),
            http("request_28")
			.get("/suite/tempo/ui/sail-client/sites-14cc662e015d0c7968f1.cache.js?appian_environment=sites"),
            http("request_29")
			.get("/suite/rest/a/content/latest/igBrZqjqNxslzRcqg6vMqj1F4DMenfjj9v2lLGOawZ7L6cfjjtRzC8T/o")
			.headers(headers_14),
            http("request_30")
			.get("/suite/rest/a/applications/latest/legacy/sites/santandermexico")
			.headers(headers_30),
            http("request_31")
			.get("/suite/cors/ping?cv=2"),
            http("request_32")
			.get("/suite/rest/a/content/latest/igBrZqjqNxslzRcqg6vMaH1FxFduFz1DLcOIPaQWehHxnQCSBWPovf9/o")
			.headers(headers_14),
            http("request_33")
			.get("/suite/rest/a/sites/latest/santandermexico/pages/empresas/report")
			.headers(headers_30),
            http("request_34")
			.get("/suite/tempo/ui/sail-client/opensans-light-appian-83c3deca5df9e979b477c60c55772d98.woff2")
			.headers(headers_5),
            http("request_35")
			.get("/suite/tempo/ui/sail-client/opensans-bold-appian-15df1fb3e82321d94a0ca758c62e25d2.woff2")
			.headers(headers_5),
            http("request_36")
			.get("/suite/rest/a/content/latest/iYBrZqjqNxslzRcqg2lNLfr6fJgRkPGCfRIbkX6h2lgoYOT9_gbfA/viewportThumbnail/o;maxWidth=300;maxHeight=600")
			.headers(headers_14)))
		.pause(11)
		.exec(http("request_37")
			.post("/suite/rest/a/sites/latest/santandermexico/pages/empresas/report")
			.headers(headers_37)
			.body(RawFileBody("test/appiansimulation/0037_request.dat")))
		.pause(5)
		.exec(http("request_38")
			.get("/suite/rest/a/applications/latest/legacy/sites/santandermexico/page/gobierno-e-instituciones")
			.headers(headers_38)
			.resources(http("request_39")
			.get("/suite/rest/a/sites/latest/santandermexico/pages/gobierno-e-instituciones/report")
			.headers(headers_38),
            http("request_40")
			.get("/suite/rest/a/content/latest/iYBrZqjqNxslzRcqg2lNLfr6fJgRkPGCfRIbkX6h2lgoYOT9_gbfA/viewportThumbnail/o;maxWidth=600;maxHeight=900")
			.headers(headers_14)))

	setUp(scn.inject(rampUsers(10) during(1 minutes))).protocols(httpProtocol)
}