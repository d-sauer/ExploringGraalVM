package org.github.dsauer.graalExample.function;

import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.server.HttpServer;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

public class Application {

    public static void main(String[] args) {
        ExampleHandler exampleHandler = new ExampleHandler();
        RouterFunction<ServerResponse> exampleHelloRoute = routeExampleHello(exampleHandler);
        HttpHandler exampleHelloRouteHandler = RouterFunctions.toHttpHandler(exampleHelloRoute);

        startNetty(exampleHelloRouteHandler);
    }

    public static RouterFunction<ServerResponse> routeExampleHello(ExampleHandler exampleHandler) {
        RouterFunction<ServerResponse> route = RouterFunctions
                .route(GET("/example")
                        .and(accept(TEXT_PLAIN)), exampleHandler::hello);

        return route;
    }

    public static class ExampleHandler {

        public Mono<ServerResponse> hello(ServerRequest request) {
            return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                    .body(BodyInserters.fromObject("Hello there"));
        }
    }

    public static void startNetty(HttpHandler httpHandler) {
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        HttpServer server = HttpServer.create("localhost", 8080);
        server.startAndAwait(adapter);
    }

}
