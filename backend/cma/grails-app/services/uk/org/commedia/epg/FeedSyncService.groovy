package uk.org.commedia.epg

import grails.converters.*

class FeedSyncService {

    static transactional = false

    def doSync() {
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

          def stream_url = getStreamURL(link);

          println "Processing station guid:${guid} link:${link} desc:${description} title:${title}"

          def station = Station.findByGuid(guid) ?: new Station(name:title,description:description,guid:guid,playlistUrl:link,streamUrl:stream_url,source:"CMRSS")
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
}
