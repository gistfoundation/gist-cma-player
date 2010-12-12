// Place your Spring DSL code here
beans = {

    doFeedTimerTask(org.springframework.scheduling.timer.MethodInvokingTimerTaskFactoryBean){
        targetObject = ref("newsFeedSyncService")
        targetMethod = 'updateFeeds'
    }
    
    doFeedScheduledTimerTask(org.springframework.scheduling.timer.ScheduledTimerTask){
        delay = 60000
        period = 60000
        timerTask = ref('doFeedTimerTask')
    }
    
    timerFactory(org.springframework.scheduling.timer.TimerFactoryBean){
        scheduledTimerTasks = [ref('doFeedScheduledTimerTask')]
    }   
}
