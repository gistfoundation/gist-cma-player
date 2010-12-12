// Place your Spring DSL code here
beans = {

    doNewsFeedTimerTask(org.springframework.scheduling.timer.MethodInvokingTimerTaskFactoryBean){
        targetObject = ref("newsFeedSyncService")
        targetMethod = 'updateFeeds'
    }
    
    doNewsFeedScheduledTimerTask(org.springframework.scheduling.timer.ScheduledTimerTask){
        delay = 60000
        period = 60000
        timerTask = ref('doNewsFeedTimerTask')
    }
    
    timerFactory(org.springframework.scheduling.timer.TimerFactoryBean){
        scheduledTimerTasks = [ref('doNewsFeedScheduledTimerTask')]
    }   
}
