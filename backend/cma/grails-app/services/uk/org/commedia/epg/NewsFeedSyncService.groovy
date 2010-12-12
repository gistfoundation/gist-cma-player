package uk.org.commedia.epg

class NewsFeedSyncService {

    static transactional = true

    def updateFeeds() {
      println "updateNewsFeeds"

      uk.org.commedia.epg.Feed.list().each {
        println "\n\n\nChecking for new items in ${it}"
        def rss_response_text = it.baseUrl.toURL().text
        def rss = new XmlSlurper().parseText(rss_response_text)
        for ( itm in rss.channel.item ) {
          try {
            def guid = itm.guid?.text()
            def title = itm.title?.text()
            def description = itm.description?.text()
            def link = itm.link?.text()
            println "Processing station guid:${guid} link:${link} desc:${description} title:${title}"

            // Discover if we already have this news item in the database.
            if ( ( guid != null ) && ( guid.length() > 0 ) ) {
              println "looking up item by guid"
              def item = FeedItem.findByOwnerAndGuid(it, guid)
              if ( item == null ) {
                println "Item not found in database, create record"
                item = new FeedItem(owner:it,guid:guid,title:title,description:description,link:link)
                if ( item.save() ) {
                  println "saved"
                }
                else {
                  println item.errors.allErrors.each { err ->
                    println err.defaultMessage
                  }
                }
              }
              else {
                println "Item already found in database -> ${item}, breaking"
                break; // Break out of the for loop
              }
            }
            else {
              println "No guid, fall back to to md5"
            }
          }
          catch(Exception e) {
            println "erorr ${e}"
          }
        }
        println "Completed processing for ${it.baseUrl}"
      }
    }
}
