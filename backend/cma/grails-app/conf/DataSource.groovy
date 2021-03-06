dataSource {
    pooled = true
    driverClassName = "org.hsqldb.jdbcDriver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
          driverClassName = "com.mysql.jdbc.Driver"
          dbCreate =  "update" // "create-drop"           // "create"
          username = "gist"
          password = "gist"
          url = "jdbc:mysql://localhost/cmadev?autoReconnect=true&amp;characterEncoding=utf8"
          properties {
            validationQuery="select 1"
            testWhileIdle=true
            timeBetweenEvictionRunsMillis=60000
          }
          //   dbCreate = "create-drop" // one of 'create', 'create-drop','update'
          //   url = "jdbc:hsqldb:mem:devDB"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:hsqldb:mem:testDb"
        }
    }
    production {
        dataSource {
          driverClassName = "com.mysql.jdbc.Driver"
          dbCreate =  "update" // "create-drop"           // "create"
          username = "gist"
          password = "gist"
          url = "jdbc:mysql://localhost/cmalive?autoReconnect=true&amp;characterEncoding=utf8"
          properties { 
            validationQuery="select 1"
            testWhileIdle=true
            timeBetweenEvictionRunsMillis=60000
          } 
        }
    }
}
