package uk.org.commedia.epg

class Station {

  String name
  String playlistUrl
  String streamUrl
  String guid
  String description
  Boolean live=false
  // Where we got the info from - So we can detect stations not broadcasting
  String source
  // When did we last see this station
  long lastSeen

  static constraints = {
    name(nullable:false)
    playlistUrl(nullable:true)
    streamUrl(nullable:false)
    guid(blank:false,nullable:false)
    description(size:0..4000,blank:true,nullable:true)
    live(nullable:false)
    source(nullable:true)
  }
}
