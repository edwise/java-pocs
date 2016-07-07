package com.edwise.pocs.dropwizardseries.configurationclass;

import com.edwise.pocs.dropwizardseries.configurationclass.config.DropwizardConfig;
import com.edwise.pocs.dropwizardseries.configurationclass.resource.MessageResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class DropwizardApplication extends Application<DropwizardConfig> {

    public static void main(String[] args) throws Exception {
        new DropwizardApplication().run(args);
    }

    @Override
    public void run(DropwizardConfig dropwizardConfig, Environment environment) {
        environment.jersey()
                   .register(new MessageResource(dropwizardConfig.getHost(), dropwizardConfig.getDefaultName()));
    }
}
