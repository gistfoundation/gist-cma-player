package uk.org.commedia.epg

class FeedItem {

  String link
  String description
  String author
  // String[] category = []
  String title
  String pubDate
  String source
  String guid
  String md5
  Feed owner

  static belongsTo = [owner : uk.org.commedia.epg.Feed]

  static constraints = {
    guid(nullable:true)
  }
}
