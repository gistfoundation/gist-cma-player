package uk.org.commedia.epg

class ScheduledTaskControllerService {

    static transactional = true
    def newsFeedSyncService
    def stationFeedSyncService

    def runScheduledTasks() {
      println "running scheduled tasks"
      stationFeedSyncService.doSync()
      newsFeedSyncService.updateFeeds()
      println "Completed"
    }
}
