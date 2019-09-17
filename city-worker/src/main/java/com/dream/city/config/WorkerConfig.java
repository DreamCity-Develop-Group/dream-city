package com.dream.city.config;

import org.quartz.SchedulerFactory;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;

/**
 * @author WVv
 */
@Configuration
@EnableScheduling
public class WorkerConfig {
    /**
     * 继承org.springframework.scheduling.quartz.SpringBeanJobFactory
     * 实现任务实例化方式
     */
    public static class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware{
        private transient AutowireCapableBeanFactory beanFactory;

        @Override
        public void setApplicationContext(final ApplicationContext context){
            beanFactory = context.getAutowireCapableBeanFactory();
        }

        /**
         * 将job实例交给spring ioc托管
         * 我们在job实例实现类内可以直接使用spring注入的调用被spring ioc管理的实例
         *
         * @param bundle
         * @return
         * @throws Exception
         */
        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
            final Object job = super.createJobInstance(bundle);

            beanFactory.autowireBean(job);
            return job;
        }
    }

    @Bean
    public JobFactory jobFactory(){
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        return jobFactory;
    }

    @Bean(destroyMethod = "destroy",autowire = Autowire.NO)
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, DataSource dataSource)throws Exception{
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setStartupDelay(2);
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("/quartz.properties"));
        return schedulerFactoryBean;
    }
}
