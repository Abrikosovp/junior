package ru.shifu.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;
/**
 * Main class of the parser application.
 * @author Pavel Abrikosov(abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 18.12.2018
 */
public class StartParser {

    /**
     * Logger for info output.
     */
    private final static Logger LOGGER = LogManager.getLogger(DBVacancy.class);

    public void task() throws SchedulerException {
        Configurator settings = new Configurator();
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        JobDetail job = newJob(Jobs.class).withIdentity("job1", "group1").build();
        CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1").
                withSchedule(cronSchedule(settings.getValue("cron.time")))
                .build();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

    public static void main(String[] args) {
        StartParser parser = new StartParser();
        try {
            parser.task();
        } catch (SchedulerException e) {
            LOGGER.error("ERROR", e);
        }
    }
}
