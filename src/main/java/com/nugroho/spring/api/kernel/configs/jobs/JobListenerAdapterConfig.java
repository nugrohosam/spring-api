package com.nugroho.spring.api.kernel.configs.jobs;

import java.util.HashMap;

import com.nugroho.spring.api.global.Config;
import com.nugroho.spring.api.presist.jobs.Job;
import com.nugroho.spring.api.presist.jobs.book.BookReturnJob;
import com.rabbitmq.client.Channel;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JobListenerAdapterConfig extends MessageListenerAdapter {

    /**
     * Spring {@link ChannelAwareMessageListener} entry point.
     * <p>
     * Delegates the message to the target listener method, with appropriate
     * conversion of the message argument. If the target method returns a non-null
     * object, wrap in a Rabbit message and send it back.
     * 
     * @param message the incoming Rabbit message
     * @param channel the Rabbit channel to operate on
     * @throws Exception if thrown by Rabbit API methods
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception { // NOSONAR
        var convertedMessage = (String) extractMessage(message);
        var messageProperty = message.getMessageProperties();

        log.info(" [x] Receive message : '" + convertedMessage + "'");
        var type = messageProperty.getHeader(Config.RABBIT_QUEUE_HEADER_TYPE) == null ? null
                : (String) messageProperty.getHeader(Config.RABBIT_QUEUE_HEADER_TYPE);

        var purposes = messageProperty.getHeader(Config.RABBIT_QUEUE_HEADER_PURPOSES) == null ? null
                : (String) messageProperty.getHeader(Config.RABBIT_QUEUE_HEADER_PURPOSES);

        if (type == null || purposes == null) {
            return;
        }

        Job job = null;

        switch (type) {
            case Config.RABBIT_QUEUE_HEADER_TYPE_SCHEDULE:
                job = getJob(purposes);
                if (job != null) {
                    job.setData(convertedMessage);
                    job.doJob();
                }

                break;
            case Config.RABBIT_QUEUE_HEADER_TYPE_MESSAGE:
                break;
        }
    }

    public Job getJob(String purpose) {
        var purposesList = new HashMap<String, Job>();

        // Add your job here...
        purposesList.put(BookReturnJob.PURPOSES, new BookReturnJob());
        // ...

        return purposesList.get(purpose);
    }
}
