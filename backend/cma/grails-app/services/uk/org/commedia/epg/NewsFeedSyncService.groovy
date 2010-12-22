package uk.org.commedia.epg

class NewsFeedSyncService {

    static transactional = true

    def updateFeeds() {
      println "updateNewsFeeds"
      def timestamp = System.currentTimeMillis()

      uk.org.commedia.epg.Feed.list().each {
        def elapsed = timestamp - ( it.lastCheck != null ? it.lastCheck : 0 )
        if ( elapsed > 60000 ) {
	        println "\nChecking for new items in ${it.baseUrl}"
	        def rss_response_text = it.baseUrl.toURL().text
	        def rss = new XmlSlurper().parseText(rss_response_text)

          def items_list = []
	        for ( itm in rss.channel.item ) {
            items_list.add(itm)
          }

          for ( itm in items_list.reverse() ) {
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
	                item = new FeedItem(owner:it,guid:guid,title:title,description:description,link:link, timestamp: System.currentTimeMillis())
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
	                println "Item already found in database"
	              }
	            }
	            else {
	              println "No guid, fall back to to md5"
	            }
	          }
	          catch(Exception e) {
	            println "erorr ${e}"
	          }
            finally {
            }
          }
          println "Completed processing for ${it.baseUrl}"
        }
        else {
          println "Only ${elapsed} passed since last check of ${it.baseUrl}"
        }
        it.lastCheck = timestamp
        it.save()
      }
    }
}
