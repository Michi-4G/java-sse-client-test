package com.example;

import com.tinkerforge.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.media.sse.EventInput;
import org.glassfish.jersey.media.sse.InboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.net.URI;


public class SSEClient {

    private static final String HOST = "localhost";
    private static final int PORT = 4223;
    private static final String UID = "hj3"; // Change to your UID

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, AlreadyConnectedException, TimeoutException, NotConnectedException, InterruptedException {



        IPConnection ipcon = new IPConnection(); // Create IP connection
        BrickletIO4 io = new BrickletIO4(UID, ipcon); // Create device object

        ipcon.connect(HOST, PORT); // Connect to brickd
        // Don't use device before ipcon is connected

        // Set pin 2 and 3 to output high
        //io.setConfiguration((short)((1 << 2) | (1 << 3)), 'o', true);
        //io.setConfiguration((short)(1 << 1), 'o', true);
        io.setConfiguration((short) 15, 'o', true);

        Thread.sleep(1000);

        // Set pin 1 to output low
        io.setConfiguration((short)0, 'o', false);

        Thread.sleep(100);

        io.setConfiguration((short)0, 'o', true);


        //by enter -> pin 0 to low for 1 second, high afterwards



        System.out.println("Press key to exit"); System.in.read();
        ipcon.disconnect();

//        Client client = ClientBuilder.newBuilder()
//                .register(SseFeature.class).build();
//        WebTarget target = client.target("http://touchless-travel.herokuapp.com/webapp/api/connect/asdf");
//
//        EventInput eventInput = target.request().get(EventInput.class);
//        while (!eventInput.isClosed()) {
//            final InboundEvent inboundEvent = eventInput.read();
//            if (inboundEvent == null) {
//                // connection has been closed
//                break;
//            }
//            System.out.println(inboundEvent.getName() + "; "
//                    + inboundEvent.readData(String.class));
//        }
    }
}