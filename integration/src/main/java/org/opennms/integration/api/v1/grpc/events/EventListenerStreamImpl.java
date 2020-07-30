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

import org.opennms.integration.api.v1.events.EventListener;
import org.opennms.integration.api.v1.events.EventSubscriptionService;
import org.opennms.integration.api.v1.grpc.ModelConverter;
import org.opennms.integration.api.v1.model.InMemoryEvent;
import org.opennms.integration.api.v1.proto.EventListenerGrpc;
import org.opennms.integration.api.v1.proto.Events;
import org.opennms.integration.api.v1.proto.Model;

import com.google.common.base.Strings;

import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

public class EventListenerStreamImpl extends EventListenerGrpc.EventListenerImplBase {

    private final EventSubscriptionService eventSubscriptionService;

    public EventListenerStreamImpl(EventSubscriptionService eventSubscriptionService) {
        this.eventSubscriptionService = eventSubscriptionService;
    }

    public void onEvent(Events.EventListenerId request,
                        StreamObserver<Model.InMemoryEvent> responseObserver) {

        if (!Strings.isNullOrEmpty(request.getName())) {
            EventListener listener = new EventListenerImpl(request.getName(), responseObserver);
            eventSubscriptionService.addEventListener(listener);
        }
    }

    private class EventListenerImpl implements EventListener {

        private final String name;
        private final StreamObserver<Model.InMemoryEvent> observer;

        private EventListenerImpl(String name, StreamObserver<Model.InMemoryEvent> observer) {
            this.name = name;
            this.observer = observer;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getNumThreads() {
            return 1;
        }

        @Override
        public void onEvent(InMemoryEvent e) {
            ServerCallStreamObserver streamObserver = (ServerCallStreamObserver) observer;
            if (streamObserver.isCancelled()) {
                eventSubscriptionService.removeEventListener(this);
            }
            Model.InMemoryEvent event = ModelConverter.convertInMemoryEvent(e);
            observer.onNext(event);
        }
    }

}
