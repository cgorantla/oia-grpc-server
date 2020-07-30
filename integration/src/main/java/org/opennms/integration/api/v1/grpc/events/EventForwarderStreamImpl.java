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

package org.opennms.integration.api.v1.grpc.events;

import org.opennms.integration.api.v1.events.EventForwarder;
import org.opennms.integration.api.v1.grpc.ModelConverter;
import org.opennms.integration.api.v1.model.InMemoryEvent;
import org.opennms.integration.api.v1.proto.EventForwarderGrpc;
import org.opennms.integration.api.v1.proto.Events;

public class EventForwarderStreamImpl extends EventForwarderGrpc.EventForwarderImplBase {

    private final EventForwarder eventForwarder;

    public EventForwarderStreamImpl(EventForwarder eventForwarder) {
        this.eventForwarder = eventForwarder;
    }

    @Override
    public void sendAsync(org.opennms.integration.api.v1.proto.Model.InMemoryEvent inMemoryEvent,
                          io.grpc.stub.StreamObserver<org.opennms.integration.api.v1.proto.Model.Empty> responseObserver) {
        InMemoryEvent eventToSend = ModelConverter.convertInMemoryEvent(inMemoryEvent);
        if (eventToSend != null) {
            eventForwarder.sendAsync(eventToSend);
        }
    }

    @Override
    public void sendSync(org.opennms.integration.api.v1.proto.Model.InMemoryEvent inMemoryEvent,
                         io.grpc.stub.StreamObserver<org.opennms.integration.api.v1.proto.Events.Ack> responseObserver) {
        InMemoryEvent eventToSend = ModelConverter.convertInMemoryEvent(inMemoryEvent);
        if (eventToSend != null) {
            eventForwarder.sendSync(eventToSend);
            responseObserver.onNext(Events.Ack.newBuilder().build());
        }
    }
}
