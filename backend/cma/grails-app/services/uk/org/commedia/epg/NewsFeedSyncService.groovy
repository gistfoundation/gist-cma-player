package uk.org.commedia.epg

import java.text.SimpleDateFormat

class NewsFeedSyncService {

    static transactional = true

    def updateFeeds() {
      println "updateNewsFeeds"
      def timestamp = System.currentTimeMillis()

      // Dates are of format Fri, 11 Mar 2011 12:14:17 GMT
      def date_parser = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss ZZZ");

      uk.org.commedia.epg.Feed.list().each { feed ->
        def elapsed = timestamp - ( feed.lastCheck != null ? feed.lastCheck : 0 )
        if ( elapsed > ( 600000 ) ) { // Every 10 mins (1000*60*10)
	        println "Checking for new items in ${feed.baseUrl}"
	        def rss_response_text = feed.baseUrl.toURL().text
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
                    def pubDate = itm.pubDate?.text()

                    def parsed_date = date_parser.parse(pubDate);
                    if ( parsed_date != null ) {
                      timestamp = parsed_date.getTime()
                      println "Converted date string ${pubDate} to time ${timestamp}"
                    }

	            //println "Processing station guid:${guid} link:${link} desc:${description} title:${title}"

              if ( ( guid == null ) || ( guid.length() == 0 ) ) {
                //println "Synthesise guid from md5 of title + description"
                guid = (title+" "+description).encodeAsMD5()
                //println "Generated guid = ${guid}"
              }

	            // Discover if we already have this news item in the database.
	            if ( ( guid != null ) && ( guid.length() > 0 ) ) {
	              println "looking up item by guid feed=${feed.id} guid=${guid}"
	              def item = FeedItem.findByOwnerAndGuid(feed, guid)
	              if ( item == null ) {
	                println "Item ${guid} not found in database, create record"
                        def disp_date = new Date().format( 'EEE, d MMM h:mm a' )
                        switch ( feed.feedType ) {
                          case 'twitter':
                            // We want to break out the feedname: from the first parts of the title and description
                            title = title.substring(title.indexOf(':')+2, title.length());
                            description = description.substring(description.indexOf(':')+2, description.length());
                            break;
                          default:
                            break;
                        }
	                item = new FeedItem(owner:feed, 
                                      guid:guid,
                                      title:title,
                                      description:description,
                                      link:link, 
                                      timestamp: timestamp,
                                      displayDate: disp_date)
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
	                // println "Item already found in database"
	              }
	            }
	            else {
	              println "Error - no guid"
	            }
	          }
	          catch(Exception e) {
	            println "erorr ${e}"
	          }
            finally {
            }
          }
          println "Completed processing for ${feed.baseUrl}"
        }
        else {
          println "Only ${elapsed} passed since last check of ${feed.baseUrl}"
        }
        feed.lastCheck = timestamp
        feed.save()
      }
    }
}
