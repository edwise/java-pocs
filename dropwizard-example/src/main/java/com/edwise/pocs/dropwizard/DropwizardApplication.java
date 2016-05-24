package com.edwise.pocs.dropwizard;

import com.edwise.pocs.dropwizard.config.DropwizardConfig;
import com.edwise.pocs.dropwizard.resource.MessageResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class DropwizardApplication extends Application<DropwizardConfig> {

    public static void main(String[] args) throws Exception {
        new DropwizardApplication().run(args);
    }

    @Override
    public void run(DropwizardConfig dropwizardConfig, Environment environment) {
        environment.jersey().register(new MessageResource());
    }
}
