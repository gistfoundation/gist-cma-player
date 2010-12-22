// Place your Spring DSL code here
beans = {

    doScheduledJobsTimerTask(org.springframework.scheduling.timer.MethodInvokingTimerTaskFactoryBean){
        targetObject = ref("scheduledTaskControllerService")
        targetMethod = 'runScheduledTasks'
    }
    
    doScheduledTimerTask(org.springframework.scheduling.timer.ScheduledTimerTask){
        delay = 60000
        period = 60000
        timerTask = ref('doScheduledJobsTimerTask')
    }
    
    timerFactory(org.springframework.scheduling.timer.TimerFactoryBean){
        scheduledTimerTasks = [ref('doScheduledTimerTask')]
    }   
}
