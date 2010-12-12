package uk.org.commedia.epg

class Feed {

  // See here  http://www.w3schools.com/rss/rss_reference.asp for fields
  String playerFeedCode
  String playerFeedName
  String baseUrl
  String description
  String title
  String copyright
  String skipHours
  String skipDays

  Set items = []

  static hasMany = [  items : uk.org.commedia.epg.FeedItem ]

  static constraints = {
    playerFeedCode(unique:true)
    description(nullable:true)
    title(nullable:true)
    copyright(nullable:true)
    skipHours(nullable:true)
    skipDays(nullable:true)
  }
}
