package org.schorn.ella.ws;

import org.glassfish.jersey.server.ResourceConfig;
import org.schorn.ella.ws.controller.MetaHtmlController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * SpringBootServletInitializer is abstract class that implements
 * WebApplicationInitializer
 *
 * @author b schorn
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class EllaWsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        try {
            new Starter(args).create().start();
            /* ---- alternatively -----
            Starter starter = new Starter(args).create();
            // Do something else first
            starter.start();
             */
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static public class Starter {

        private final String[] args;
        private SpringApplicationBuilder builder;
        private EllaWsApplication app;

        public Starter(String[] args) {
            this.args = args;
        }
        public Starter create() throws Exception {
            this.builder = new SpringApplicationBuilder(EllaWsApplication.class);
            this.app = new EllaWsApplication();
            return this;
        }

        public void start() throws Exception {
            this.app.configure(this.builder);
            this.builder.run(this.args);
        }
    }

    @Bean
    ResourceConfig resourceConfig() {
        return new ResourceConfig().register(MetaHtmlController.class);
    }
}
