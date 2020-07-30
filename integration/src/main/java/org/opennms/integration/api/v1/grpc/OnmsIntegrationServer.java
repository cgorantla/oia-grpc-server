/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2020 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2020 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.integration.api.v1.grpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.opennms.integration.api.v1.events.EventForwarder;
import org.opennms.integration.api.v1.grpc.alarms.AlarmLifeCycleListenerStream;
import org.opennms.integration.api.v1.grpc.alarms.AlarmLifeCycleListenerStreamImpl;
import org.opennms.integration.api.v1.grpc.events.EventForwarderStreamImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;

public class OnmsIntegrationServer implements GrpcIntegrationServer {

    private static final Logger LOG = LoggerFactory.getLogger(OnmsIntegrationServer.class);

    private Server server;
    private static final int PORT = 8991;
    private AlarmLifeCycleListenerStream alarmLifeCycleListenerStream;
    private final EventForwarder eventForwarder;

    public OnmsIntegrationServer(EventForwarder eventForwarder) {
        this.eventForwarder = eventForwarder;
    }


    public void start() throws IOException {
        alarmLifeCycleListenerStream = new AlarmLifeCycleListenerStreamImpl();
        NettyServerBuilder serverBuilder = NettyServerBuilder.forAddress(new InetSocketAddress(PORT))
                .addService(alarmLifeCycleListenerStream)
                .addService(new EventForwarderStreamImpl(eventForwarder));
        server = serverBuilder.build();
        server.start();
        LOG.info("OpenNMS Integration Server started");

    }

    public void shutdown() {
        if (server != null) {
            server.shutdown();
        }
    }

    @Override
    public AlarmLifeCycleListenerStream getAlarmLifeCycleListenerStream() {
        return alarmLifeCycleListenerStream;
    }
}
