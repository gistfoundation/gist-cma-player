package uk.org.commedia.epg

import grails.converters.JSON
import grails.converters.XML

class InfoController {

  // List news items for the given sources since the given timestamp. Or else everything
  def news = { 
    println "List Stations - ${request.format}"
    def response = [:];
    def timestamp = System.currentTimeMillis()

    def selected_feed = params["feed"] ?: "CMA"
    int max_items = params["maxitems"] ?: 10
    int since = params["since"] ?: 0

    def selected_feeds = [ selected_feed ]

    response["timestamp"] = timestamp
    response["since"] = since
    response["feeds"] = selected_feeds

    selected_feeds.each {
      def feed = Feed.findByPlayerFeedCode(it)
      def feed_info = [:]
      response[feed.playerFeedCode] = feed_info
      feed_info.feedName = feed.playerFeedName
      feed_info.desc = feed.description
      feed_info.items = []
      int count = 0
      FeedItem.findAllByOwner(feed, [sort:'timestamp',order:'desc']).each {
        if ( count++ < max_items ) {
          feed_info.items.add([it.link, it.description, it.author, it.title, it.pubDate, it.source])
        }
      }
    }

    withFormat {
      html response
      xml { render response as XML }
      json { render response as JSON }
    }

  }
}
