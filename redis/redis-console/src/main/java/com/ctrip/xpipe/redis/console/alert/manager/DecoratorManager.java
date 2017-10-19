package com.ctrip.xpipe.redis.console.alert.manager;

import com.ctrip.xpipe.redis.console.alert.AlertEntity;
import com.ctrip.xpipe.redis.console.alert.decorator.AlertMessageDecorator;
import com.ctrip.xpipe.redis.console.alert.decorator.Decorator;
import com.ctrip.xpipe.redis.console.alert.decorator.RecoverMessageDecorator;
import com.ctrip.xpipe.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author chen.zhu
 * <p>
 * Oct 18, 2017
 */
@Component
public class DecoratorManager {

    @Autowired
    Map<String, Decorator> decorators;

    /* There's another option to generate content for recover message
    So the @param isAlertMessage is the pivot to switch between two phases
    */
    public Pair<String, String> generateTitleAndContent(AlertEntity alert, boolean isAlertMessage) {
        Decorator decorator = getDecorator(isAlertMessage);
        String content = decorator.generateContent(alert);
        String title = decorator.generateTitle(alert);
        return new Pair<>(title, content);
    }

    private Decorator getDecorator(boolean isAlertMessage) {
        if(isAlertMessage) {
            return decorators.get(AlertMessageDecorator.ID);
        } else {
            return decorators.get(RecoverMessageDecorator.ID);
        }
    }
}
