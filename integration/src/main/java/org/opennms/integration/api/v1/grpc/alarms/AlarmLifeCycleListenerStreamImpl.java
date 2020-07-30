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

import java.util.ArrayList;
import java.util.List;

import org.opennms.integration.api.v1.proto.AlarmLifecycleListenerGrpc;
import org.opennms.integration.api.v1.proto.Alarms;
import org.opennms.integration.api.v1.proto.Model;

import io.grpc.stub.StreamObserver;

public class AlarmLifeCycleListenerStreamImpl extends AlarmLifecycleListenerGrpc.AlarmLifecycleListenerImplBase implements AlarmLifeCycleListenerStream {


    private List<StreamObserver<Alarms.AlarmsList>> snapShotObserver = new ArrayList<>();

    private List<StreamObserver<Model.Alarm>> newOrUpdatedObserver = new ArrayList<>();

    private List<StreamObserver<Alarms.DeleteAlarm>> deleteAlarmObserver = new ArrayList<>();


    @Override
    public void handleAlarmSnapshot(Model.Empty request,
                                    io.grpc.stub.StreamObserver<Alarms.AlarmsList> responseObserver) {

        snapShotObserver.add(responseObserver);
    }


    @Override
    public void handleNewOrUpdatedAlarm(Model.Empty request,
                                        io.grpc.stub.StreamObserver<Model.Alarm> responseObserver) {
        newOrUpdatedObserver.add(responseObserver);
    }


    @Override
    public void handleDeletedAlarm(Model.Empty request,
                                   io.grpc.stub.StreamObserver<Alarms.DeleteAlarm> responseObserver) {
        deleteAlarmObserver.add(responseObserver);
    }

    @Override
    public List<StreamObserver<Alarms.AlarmsList>> getSnapShotObserver() {
        return snapShotObserver;
    }

    @Override
    public List<StreamObserver<Model.Alarm>> getNewOrUpdatedObserver() {
        return newOrUpdatedObserver;
    }

    @Override
    public List<StreamObserver<Alarms.DeleteAlarm>> getDeleteAlarmObserver() {
        return deleteAlarmObserver;
    }
}
