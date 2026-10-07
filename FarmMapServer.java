import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Executors;

public class FarmMapServer {

    private static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws Exception {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        FarmMapService service = new FarmMapService();
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api", new FarmApiHandler(service));
        server.setExecutor(Executors.newFixedThreadPool(4));
        server.start();

        System.out.println("MIRRA Farm Map API running at http://localhost:" + port);
        System.out.println("Endpoint: GET http://localhost:" + port + "/api/farms/FARMER_A/map");
    }

    static class FarmApiHandler implements HttpHandler {

        private final FarmMapService service;

        FarmApiHandler(FarmMapService service) {
            this.service = service;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                addCorsHeaders(exchange);

                if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                    exchange.sendResponseHeaders(204, -1);
                    return;
                }

                if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                    sendJson(exchange, 405, "{\"error\":\"Method not allowed\"}");
                    return;
                }

                String path = exchange.getRequestURI().getPath();

                if ("/api/health".equals(path)) {
                    sendJson(exchange, 200, "{\"status\":\"ok\",\"service\":\"farm-map-api\"}");
                    return;
                }

                if ("/api/farms".equals(path)) {
                    sendJson(exchange, 200, farmListJson(service.listFarms()));
                    return;
                }

                if (path.startsWith("/api/farms/") && path.endsWith("/map")) {
                    String farmerId = extractFarmerId(path, "/map");
                    Farm farm = service.getFarmMap(farmerId);
                    if (farm == null) {
                        sendJson(exchange, 404, errorJson("No farm found for farmerId: " + farmerId));
                        return;
                    }
                    sendJson(exchange, 200, farmMapJson(farm));
                    return;
                }

                if (path.startsWith("/api/farms/") && path.endsWith("/canals")) {
                    String farmerId = extractFarmerId(path, "/canals");
                    Farm farm = service.getFarmMap(farmerId);
                    if (farm == null) {
                        sendJson(exchange, 404, errorJson("No canals found for farmerId: " + farmerId));
                        return;
                    }
                    sendJson(exchange, 200, canalsJson(farm));
                    return;
                }

                if (path.startsWith("/api/farms/")) {
                    String farmerId = extractFarmerId(path, "");
                    Farm farm = service.getFarmMap(farmerId);
                    if (farm == null) {
                        sendJson(exchange, 404, errorJson("No farm found for farmerId: " + farmerId));
                        return;
                    }
                    sendJson(exchange, 200, farmDetailsJson(farm));
                    return;
                }

                sendJson(exchange, 404, errorJson("Unknown endpoint: " + path));
            } finally {
                exchange.close();
            }
        }

        private String extractFarmerId(String path, String suffix) {
            String mid = path.substring("/api/farms/".length());
            if (!suffix.isEmpty() && mid.endsWith(suffix)) {
                mid = mid.substring(0, mid.length() - suffix.length());
            }
            if (mid.endsWith("/")) {
                mid = mid.substring(0, mid.length() - 1);
            }
            return mid;
        }

        private void addCorsHeaders(HttpExchange exchange) {
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, OPTIONS");
            exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type, Accept");
        }

        private void sendJson(HttpExchange exchange, int status, String body) throws IOException {
            byte[] payload = body.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
            exchange.sendResponseHeaders(status, payload.length);
            try (OutputStream out = exchange.getResponseBody()) {
                out.write(payload);
            }
        }

        private String errorJson(String message) {
            return "{\"error\":" + quote(message) + "}";
        }

        private String farmListJson(List<Farm> farms) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < farms.size(); i++) {
                Farm farm = farms.get(i);
                if (i > 0) {
                    sb.append(",");
                }
                sb.append("{\"farmerId\":").append(quote(farm.farmerId))
                        .append(",\"farmerName\":").append(quote(farm.farmerName))
                        .append(",\"farmId\":").append(quote(farm.farmId))
                        .append(",\"location\":").append(quote(farm.location))
                        .append("}");
            }
            sb.append("]");
            return sb.toString();
        }

        private String farmMapJson(Farm farm) {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            appendFarmCore(sb, farm);
            sb.append(",\"farmBoundary\":");
            appendPoints(sb, farm.boundary);
            sb.append(",\"markers\":");
            appendMarkers(sb, farm.markers);
            sb.append(",\"canals\":");
            appendCanals(sb, farm.canals);
            sb.append("}");
            return sb.toString();
        }

        private String farmDetailsJson(Farm farm) {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            appendFarmCore(sb, farm);
            sb.append("}");
            return sb.toString();
        }

        private String canalsJson(Farm farm) {
            StringBuilder sb = new StringBuilder();
            appendCanals(sb, farm.canals);
            return sb.toString();
        }

        private void appendFarmCore(StringBuilder sb, Farm farm) {
            sb.append("\"farmerId\":").append(quote(farm.farmerId))
                    .append(",\"farmerName\":").append(quote(farm.farmerName))
                    .append(",\"farmId\":").append(quote(farm.farmId))
                    .append(",\"location\":").append(quote(farm.location))
                    .append(",\"area\":").append(farm.area);
        }

        private void appendPoints(StringBuilder sb, List<GeoPoint> points) {
            sb.append("[");
            for (int i = 0; i < points.size(); i++) {
                GeoPoint p = points.get(i);
                if (i > 0) {
                    sb.append(",");
                }
                sb.append("{\"lat\":").append(coord(p.lat)).append(",\"lng\":").append(coord(p.lng)).append("}");
            }
            sb.append("]");
        }

        private void appendMarkers(StringBuilder sb, List<FarmMarker> markers) {
            sb.append("[");
            for (int i = 0; i < markers.size(); i++) {
                FarmMarker m = markers.get(i);
                if (i > 0) {
                    sb.append(",");
                }
                sb.append("{\"type\":").append(quote(m.type))
                        .append(",\"label\":").append(quote(m.label))
                        .append(",\"lat\":").append(coord(m.lat))
                        .append(",\"lng\":").append(coord(m.lng))
                        .append("}");
            }
            sb.append("]");
        }

        private void appendCanals(StringBuilder sb, List<Canal> canals) {
            sb.append("[");
            for (int i = 0; i < canals.size(); i++) {
                Canal c = canals.get(i);
                if (i > 0) {
                    sb.append(",");
                }
                sb.append("{\"id\":").append(quote(c.id))
                        .append(",\"name\":").append(quote(c.name))
                        .append(",\"type\":").append(quote(c.type))
                        .append(",\"waterStatus\":").append(quote(c.waterStatus))
                        .append(",\"length\":").append(c.length)
                        .append(",\"coordinates\":");
                appendPoints(sb, c.coordinates);
                sb.append("}");
            }
            sb.append("]");
        }

        private String coord(double value) {
            double rounded = Math.round(value * 1000000.0) / 1000000.0;
            return String.valueOf(rounded);
        }

        private String quote(String value) {
            if (value == null) {
                return "null";
            }
            StringBuilder sb = new StringBuilder("\"");
            for (int i = 0; i < value.length(); i++) {
                char ch = value.charAt(i);
                switch (ch) {
                    case '"':
                        sb.append("\\\"");
                        break;
                    case '\\':
                        sb.append("\\\\");
                        break;
                    case '\n':
                        sb.append("\\n");
                        break;
                    case '\r':
                        sb.append("\\r");
                        break;
                    case '\t':
                        sb.append("\\t");
                        break;
                    default:
                        if (ch < 0x20) {
                            sb.append(String.format("\\u%04x", (int) ch));
                        } else {
                            sb.append(ch);
                        }
                }
            }
            sb.append("\"");
            return sb.toString();
        }
    }
}
