package zen8.hieule.api_gateway;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
@Slf4j
public class APIRequestFilter extends AbstractGatewayFilterFactory<APIRequestFilter.Config>{

    public APIRequestFilter() {
        super(Config.class);
    }

    private final List<String> permitUrls = List.of("/user/register");

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String url = request.getPath().toString();
            log.info("-------------[ {} ]", url);

            if (permitUrls.contains(url)) {
                return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                }));
            }

            ServerHttpResponse response = exchange.getResponse();
            HttpHeaders requestHeaders = request.getHeaders();

            if (requestHeaders.containsKey("Authorization")) {
                final String token = requestHeaders.getOrEmpty("Authorization").get(0).substring(7);
                if (!token.equals("xxx-token")) {
                    return error(exchange.getResponse(), url, "Token invalid");
                }

                log.info("Token invalid");
                return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                }));
            } else {
                return error(response, url, "Request invalid, please try again!");
            }
        };
    }

    private Mono<Void> error(ServerHttpResponse response, String url, String message) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("timestamp", new Date());
        errors.put("path", url);
        errors.put("status", 401);
        errors.put("error", "Unauthorized");
        errors.put("message", message);

        byte[] bytes = new Gson().toJson(errors).getBytes(StandardCharsets.UTF_8);

        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);

        return response.writeWith(Mono.just(buffer));
    }

    public static class Config {

    }
}
