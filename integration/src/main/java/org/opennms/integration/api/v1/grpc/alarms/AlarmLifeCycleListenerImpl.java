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

package org.opennms.integration.api.v1.grpc.alarms;

import static org.opennms.integration.api.v1.grpc.ModelConverter.buildAlarmProto;

import java.util.List;
import java.util.stream.Collectors;

import org.opennms.integration.api.v1.alarms.AlarmLifecycleListener;
import org.opennms.integration.api.v1.grpc.GrpcIntegrationServer;
import org.opennms.integration.api.v1.grpc.ModelConverter;
import org.opennms.integration.api.v1.model.Alarm;
import org.opennms.integration.api.v1.proto.Alarms;
import org.opennms.integration.api.v1.proto.Model;

import io.grpc.stub.StreamObserver;

public class AlarmLifeCycleListenerImpl implements AlarmLifecycleListener {

    private final GrpcIntegrationServer grpcIntegrationServer;

    public AlarmLifeCycleListenerImpl(GrpcIntegrationServer grpcIntegrationServer) {
        this.grpcIntegrationServer = grpcIntegrationServer;
    }


    @Override
    public void handleAlarmSnapshot(List<Alarm> alarms) {
        List<StreamObserver<Alarms.AlarmsList>> observers = grpcIntegrationServer.getAlarmLifeCycleListenerStream().getSnapShotObserver();
        if (observers.isEmpty()) {
            return;
        }
        List<Model.Alarm> protoAlarms = alarms.stream().map(ModelConverter::buildAlarmProto).collect(Collectors.toList());
        Alarms.AlarmsList.Builder builder = Alarms.AlarmsList.newBuilder();
        builder.addAllAlarms(protoAlarms);
        observers.forEach(observer -> observer.onNext(builder.build()));
    }

    @Override
    public void handleNewOrUpdatedAlarm(Alarm alarm) {
        List<StreamObserver<Model.Alarm>> observers = grpcIntegrationServer.getAlarmLifeCycleListenerStream().getNewOrUpdatedObserver();
        if (observers.isEmpty()) {
            return;
        }
        observers.forEach(observer -> observer.onNext(buildAlarmProto(alarm)));
    }

    @Override
    public void handleDeletedAlarm(int alarmId, String reductionKey) {
        List<StreamObserver<Alarms.DeleteAlarm>> observers = grpcIntegrationServer.getAlarmLifeCycleListenerStream().getDeleteAlarmObserver();
        if (observers.isEmpty()) {
            return;
        }
        Alarms.DeleteAlarm deleteAlarm = Alarms.DeleteAlarm.newBuilder().setId(alarmId)
                .setReductionKey(reductionKey).build();
        observers.forEach(observer -> observer.onNext(deleteAlarm));
    }



}
