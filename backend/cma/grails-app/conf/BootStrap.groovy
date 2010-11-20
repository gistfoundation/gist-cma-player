import uk.org.commedia.epg.*

class BootStrap {

  // These two beans will be auto injected from the spring application context based on the class names. See the grails-app/services dir for the vocab sync service
  def springSecurityService
  def feedSyncService

  def init = { servletContext ->
    def user_role = Role.findByAuthority('user') ?: new Role(authority: 'user').save()
    def admin_role = Role.findByAuthority('admin') ?: new Role(authority: 'admin').save()

    def admin_user = User.findByUsername('admin') ?: new User(username: 'admin', password: springSecurityService.encodePassword('admin'), enabled:true).save()
    def cma_user = User.findByUsername('cma') ?: new User(username: 'cma', password: springSecurityService.encodePassword('cma'), enabled:true).save()

    def working_station = Station.findByGuid('TheEyeFM') ?: new Station(name:'TheEyeFM',url:'http://icecast.commedia.org.uk:8000/theeyefm.mp3',guid:'TheEyeFM').save()

    println "Station: ${working_station}"

    println "Calling feed sync service"
    feedSyncService.doSync()
  }
  def destroy = {
  }
}
