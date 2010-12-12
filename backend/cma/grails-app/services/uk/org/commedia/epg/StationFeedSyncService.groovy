package uk.org.commedia.epg

import grails.converters.*
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

class StationFeedSyncService {

    static transactional = false

    def doSync() {
      // For use in various lookups
      def google = new RESTClient("https://ajax.googleapis.com/ajax/services/")

      long timestamp = System.currentTimeMillis()
      println "Loading live feeds from commedia ice cast rss feed"
      def live_feed_url = "http://icecast.commedia.org.uk/rss.xml"
      def rss_response_text = live_feed_url.toURL().text
      // println "Rss response: ${rss_response_text}"
      def rss = new XmlSlurper().parseText(rss_response_text)
      // println "Rss: ${rss}"
      rss.channel.item.each {
        try {
          def guid = it.guid?.text()
          def title = it.title?.text()
          def description = it.description?.text()
          def link = it.link?.text()

          println "Processing station guid:${guid} link:${link} desc:${description} title:${title}"

          def station = Station.findByGuid(guid) 
          if ( station == null ) {
            def stream_url = getStreamURL(link);
            station = new Station(name:title,description:description,guid:guid,playlistUrl:link,streamUrl:stream_url,source:"CMRSS")
            station.homePage = getStationHomePage(google,title)
          }
          station.live = true;
          station.lastSeen = timestamp
          if ( station.save() ) {
            println "${station.name} (${station.guid}) - live"
          }
          else {
            println station.errors.allErrors.each {
               println it.defaultMessage
            }
          }
        }
        catch(Exception e) {
          println "erorr ${e}"
        }
      }
    }

    def getStreamURL(playlist_url) {
      def stream_text = playlist_url.toURL().text.trim();
      println "Stream URL: ${stream_text}"
      stream_text
    }

    def getStationHomePage(google, station_name) {
      println "Trying to look up a url for ${station_name}"
      def google_search_url = "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=\"${java.net.URLEncoder.encode(station_name)}\""
      println "qry: ${google_search_url}"
      def google_resp_text = google_search_url.toURL().text
      println "Got response, parse json"
      def google_response_json = JSON.parse(google_resp_text)
      def target_url = google_response_json?.responseData?.results[0]?.unescapedUrl
      println "Result of lookup ${target_url}"
      target_url
    }
}
