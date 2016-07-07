package com.edwise.pocs.dropwizardseries.configurationclass.config;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class DropwizardConfig extends Configuration {

    @NotEmpty
    private String host;

    private String defaultName;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }
}
