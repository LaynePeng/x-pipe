package com.ctrip.xpipe.redis.console.alert;


import com.ctrip.xpipe.api.email.EmailType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen.zhu
 * <p>
 * Oct 18, 2017
 */
public class AlertMessageEntity {

    private String m_title;

    private EmailType m_type;

    private String m_content;

    private List<String> m_receivers;

    private Map<String, Object> params;

    public AlertMessageEntity(String title, EmailType type, String content, List<String> receivers) {
        m_title = title;
        m_type = type;
        m_content = content;
        m_receivers = receivers;
        params = new HashMap<>();
    }

    public <T> AlertMessageEntity addParam(String key, T val) {
        this.params.put(key, val);
        return this;
    }

    public <T> T getParam(String key) {
        Object val = this.params.get(key);
        try {
            T t = (T) val;
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    public String getContent() {
        return m_content;
    }

    public List<String> getReceivers() {
        return m_receivers;
    }

    public String getReceiverString() {
        StringBuilder builder = new StringBuilder(100);

        for (String receiver : m_receivers) {
            builder.append(receiver).append(",");
        }

        String tmpResult = builder.toString();
        if (tmpResult.endsWith(",")) {
            return tmpResult.substring(0, tmpResult.length() - 1);
        } else {
            return tmpResult;
        }
    }

    public String getTitle() {
        return m_title;
    }

    public EmailType getType() {
        return m_type;
    }

    public void setContent(String content) {
        m_content = content;
    }

    @Override
    public String toString() {
        return "title: " + m_title + " content: " + m_content + " type: " + m_type + " receiver: " + getReceiverString();
    }
}
