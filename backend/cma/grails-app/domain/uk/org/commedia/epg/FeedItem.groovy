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
  long timestamp

  static belongsTo = [owner : uk.org.commedia.epg.Feed]

  static constraints = {
    guid(nullable:true)
    author(nullable:true)
    pubDate(nullable:true)
    source(nullable:true)
    md5(nullable:true)
  }
}
