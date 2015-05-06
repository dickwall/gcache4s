package com.geekclave.gcache4s

import org.scalatest.{Matchers, FunSpec}
import scala.concurrent.duration._

/**
 * Created by dick on 5/5/15.
 */
class GCache4SSpec extends FunSpec with Matchers {
  describe ("GCache4S") {
    it ("should build a simple, usable cache") {
      var testMap = Map.empty[Int, Int]

      val cache = GCache().maximumSize(10) { (x: Int) =>
        testMap += x -> x * x
        println(s"Squaring $x")
        x * x
      }
      cache.get(5) should be (25)
      cache.get(10) should be (100)

      testMap.size should be (2)
      testMap.get(10) should be (Some(100))
      testMap.get(5) should be (Some(25))
      testMap.get(1) should be (None)

    }

    it ("should have a working toString") {
      val cacheBuilder = GCache().maximumSize(10).initialCapacity(5)
        .refreshAfterWrite(100.milliseconds).expireAfterWrite(500.milliseconds)
        .removalListener { (removal: RemovalNotification[Int, Int]) =>
          println(s"Removing ${removal.key} for reason ${removal.cause}")
        }

      val asString = cacheBuilder.toString
      asString should be("GCache(CacheBuilder{initialCapacity=5, maximumSize=10, expireAfterWrite=500000000ns, removalListener})")
      val cache = cacheBuilder { (x: Int) => println(s"Calcing $x"); x * x }

      println(cache)

      cache.get(5) should be (25)
      cache.get(5) should be (25)

      Thread.sleep(1000)
      cache.invalidate(5)

      cache.get(5) should be (25)
    }


  }
}
