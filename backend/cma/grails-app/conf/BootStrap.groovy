import uk.org.commedia.epg.*

class BootStrap {

  // These two beans will be auto injected from the spring application context based on the class names. See the grails-app/services dir for the vocab sync service
  def springSecurityService
  def stationFeedSyncService

  def init = { servletContext ->
    def user_role = Role.findByAuthority('user') ?: new Role(authority: 'user').save()
    def admin_role = Role.findByAuthority('admin') ?: new Role(authority: 'admin').save()

    def admin_user = User.findByUsername('admin') ?: new User(username: 'admin', password: springSecurityService.encodePassword('admin'), enabled:true).save()
    def cma_user = User.findByUsername('cma') ?: new User(username: 'cma', password: springSecurityService.encodePassword('cma'), enabled:true).save()

    // def working_station = Station.findByGuid('TheEyeFM') ?: new Station(name:'TheEyeFM',url:'http://icecast.commedia.org.uk:8000/theeyefm.mp3',guid:'TheEyeFM').save()
    // println "Station: ${working_station}"

    // Create a default CMA news feed
    def cma_news_feed = Feed.findByPlayerFeedCode('CMA') ?: new Feed(baseUrl:'http://www.commedia.org.uk/feed/',playerFeedCode:'CMA', playerFeedName:'CMA').save()

    def cma_twitter_feed = Feed.findByPlayerFeedCode('CMAT') ?: new Feed(baseUrl:'http://twitter.com/statuses/user_timeline/community_media.rss',
                                                                         playerFeedCode:'CMAT', playerFeedName:'CMA Twitter', feedType:'twitter').save()

    def cma_list_feed = Feed.findByPlayerFeedCode('CMAL') ?: new Feed(baseUrl:'http://mailman.commedia.org.uk/pipermail/cma-l/rss.xml',
                                                                     playerFeedCode:'CMAL', playerFeedName:'CMA List').save()

    println "Calling feed sync service"
    stationFeedSyncService.doSync()
  }
  def destroy = {
  }
}
