package com.laishishui.ecommerce.notifile;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 *
 * <h2>实现对事件的通知 可以做定制化</h2>
 * Create by tachai on 2021/12/27 8:44 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class QinyiNotifier extends AbstractEventNotifier {

    protected QinyiNotifier(InstanceRepository repository) {
        super(repository);
    }

    /**
     * <h2> 实现对事件的通知</h2>
     * @param event
     * @param instance
     * @return
     */
    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {


        return Mono.fromRunnable(()->{
            if(event instanceof InstanceStatusChangedEvent){
                log.info("Instance Status Change: [{}],[{}],[{}]",instance.getRegistration().getName(),
                        event.getInstance(),
                        ((InstanceStatusChangedEvent)event).getStatusInfo().getStatus());
            }else {
                log.info("Instance Info: [{}],[{}],[{}]",
                        instance.getRegistration().getName(),
                        event.getInstance(),
                        event.getType());
            }
        });
    }
}
