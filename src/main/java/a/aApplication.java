package a;

import a.db.EntityActorDatabase;
import a.resources.EntityActorResource;
import a.resources.EntityThreadResource;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class aApplication extends Application<aConfiguration> {

    public static void main(final String[] args) throws Exception {
        new aApplication().run(args);
    }

    @Override
    public String getName() {
        return "a";
    }

    @Override
    public void initialize(final Bootstrap<aConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final aConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(EntityActorResource.class);
        environment.jersey().register(EntityThreadResource.class);
    }

}
