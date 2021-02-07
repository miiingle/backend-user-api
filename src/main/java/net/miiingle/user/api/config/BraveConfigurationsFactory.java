package net.miiingle.user.api.config;

import brave.propagation.Propagation;
import brave.propagation.aws.AWSPropagation;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class BraveConfigurationsFactory {

    @Singleton
    public Propagation.Factory awsPropagationFactory() {
        return AWSPropagation.FACTORY;
    }
}
