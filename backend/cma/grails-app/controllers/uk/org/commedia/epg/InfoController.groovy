package uk.org.commedia.epg

import grails.converters.JSON
import grails.converters.XML

class InfoController {

  // List news items for the given sources since the given timestamp. Or else everything
  def news = { 
    println "List Stations - ${request.format}"
    def response = [:];

    def selected_feeds = Feed.list()
    selected_feeds.each {
      def feed_info = [:]
      response[it.playerFeedCode] = feed_info
      feed_info.feedName = it.playerFeedName
      feed_info.desc = it.description
      feed_info.items = []
      FeedItem.findAllByOwner(it).each {
        feed_info.items.add([it.link, it.description, it.author, it.title, it.pubDate, it.source])
      }
    }

    // if(request?.format && request.format != "html"){
    // }
    // else {
    //   params.max = Math.min(params.max ? params.int('max') : 10, 100)
    //   response = [stationInstanceList: Station.list(params), stationInstanceTotal: Station.count()]
    // }

    withFormat {
      html response
      xml { render response as XML }
      json { render response as JSON }
    }

  }
}
