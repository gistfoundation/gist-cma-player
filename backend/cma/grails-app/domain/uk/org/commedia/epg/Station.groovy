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
  // Override program name from timetable
  String progName
  // When should the overridden program name expire, null=not set, -1=never
  long progNameExpiry = -1
  // Override current track
  String trackName
  // When should the track name expire (Start time + track length?)
  long trackNameExpiry = -1
  // Station Logo URL
  String stationLogo
  // Station home page
  String homePage

  static constraints = {
    name(nullable:false)
    playlistUrl(nullable:true)
    streamUrl(nullable:false)
    guid(blank:false,nullable:false)
    description(size:0..4000,blank:true,nullable:true)
    live(nullable:false)
    source(nullable:true)
    progName(nullable:true)
    progNameExpiry(nullable:true)
    trackName(nullable:true)
    trackNameExpiry(nullable:true)
    stationLogo(nullable:true)
    homePage(nullable:true)
    lastSeen(nullable:true)
  }
}
